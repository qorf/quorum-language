package plugins.quorum.Libraries.Game.Graphics.Models.Data;

import android.renderscript.Matrix4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import org.lwjgl.system.MemoryStack;
import quorum.Libraries.Compute.*;
import quorum.Libraries.Containers.*;
import quorum.Libraries.Game.Graphics.Models.Data.*;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.System.File_;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.lang.Math;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.lwjgl.assimp.Assimp.*; //for the flags, but you could do this a different way too

public class ModelLoader {
    public java.lang.Object me_ = null;
    public static final int MAX_WEIGHTS = 4;
    private static final int DEFAULT_MAX_JOINTS_MATRICES_LISTS = 100;

    public ModelData_ Load(File_ filePath, File_ texturesPath, boolean animation) {
        String path = filePath.GetAbsolutePath();
        String textures = texturesPath.GetAbsolutePath();
        return Load(path, textures,animation);
    }

    public ModelData_ Load(String path, String textures, boolean animation) {
        ModelLoader_ quorumLoader = (ModelLoader_)me_;

        //first read the file to see if it's an animating model
        int flags = 0;
        AIScene aiSceneCheck = aiImportFile(path, flags);
        if (aiSceneCheck == null) {
            throw new RuntimeException("Error loading model: " + path + ", with error:" + Assimp.aiGetErrorString());
        }
        boolean hasAnimations = aiSceneCheck.mNumAnimations() > 0;
        boolean vulkanLoading = quorumLoader.IsUsingLeftHandedCoordinates();
        // Lights are read from this import, where the node tree is intact;
        // aiProcess_PreTransformVertices drops them below.
        Array_ fileLights = new Array();
        // glTF stores its color numbers (factors, vertex colors, light
        // colors) as amounts of light; Quorum keeps every color as a
        // picture stores it (sRGB) and the shaders turn it into light, so
        // glTF's are turned into picture colors here. Other formats' colors are already picture colors.
        boolean linearColors = IsGLTF(path);
        ProcessLights(aiSceneCheck, vulkanLoading, fileLights, linearColors);
        aiReleaseImport(aiSceneCheck);

        // Most model files are right-handed and the world is left-handed, so z is
        // flipped and each triangle's winding reversed, which keeps front faces
        // clockwise. aiProcess_ConvertToLeftHanded is not used because it also
        // flips texture v, which MeshVulkan already does (Vulkan) and
        // ProcessTextureCoordinates does for OpenGL.
        int leftHanded = vulkanLoading ? (aiProcess_MakeLeftHanded | aiProcess_FlipWindingOrder) : 0;
        if(hasAnimations) {
            flags = aiProcess_GenSmoothNormals | aiProcess_JoinIdenticalVertices |
                    aiProcess_Triangulate | aiProcess_FixInfacingNormals | aiProcess_CalcTangentSpace | aiProcess_LimitBoneWeights |
                    leftHanded;
        } else {
            flags = aiProcess_GenSmoothNormals | aiProcess_JoinIdenticalVertices |
                    aiProcess_Triangulate | aiProcess_FixInfacingNormals | aiProcess_CalcTangentSpace | aiProcess_LimitBoneWeights |
                    aiProcess_PreTransformVertices | leftHanded;
        }
        AIScene aiScene = aiImportFile(path, flags);
        if (aiScene == null) {
            throw new RuntimeException("Error loading model: " + path + ", with error:" + Assimp.aiGetErrorString());
        }

        boolean isAnimatedModel = aiScene.mNumAnimations() > 0;
        ModelData_ data = new ModelData();
        data.SetLights(fileLights);
        Array_ materials = data.GetMaterials();
        // Textures stored inside the file, as a .glb does, are written out
        // once so they load like any texture.
        String[] embeddedPaths = ExtractEmbeddedTextures(aiScene, path);
        int numMaterials = aiScene.mNumMaterials();
        for (int i = 0; i < numMaterials; i++) {
            AIMaterial aiMaterial = AIMaterial.create(aiScene.mMaterials().get(i));
            Material_ material = ProcessMaterial(aiMaterial, textures, embeddedPaths, linearColors);
            materials.Add(material);
        }

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer aiMeshes = aiScene.mMeshes();
        Array_ meshes = data.GetMeshes();
        for (int i = 0; i < numMeshes; i++) {
            AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
            MeshData_ mesh = ProcessMesh(aiMesh, vulkanLoading, linearColors);
            meshes.Add(mesh);
        }

        int numAnimations = aiScene.mNumAnimations();
        if(isAnimatedModel && numAnimations > 0) {
            PointerBuffer animationsBuffer = aiScene.mAnimations();
            Animations_ animations = new Animations();
            Array_ animMeshDataList = animations.GetAnimationMeshData();
            //First get the bone list
            List<Bone_> boneList = new ArrayList<>();
            for (int i = 0; i < numMeshes; i++) {
                AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
                AnimationMeshData_ animMeshData = ProcessBones(aiMesh, boneList);
                animMeshDataList.Add(animMeshData);
            }




            //Now "Bake" in frames of animation so we can use it in the engine

            AnimationNode_ rootNode = BuildNodesTree(aiScene.mRootNode(), null);
            Matrix4_ globalInverseTransformation = ToMatrix(aiScene.mRootNode().mTransformation()).Inverse();
            ProcessAnimations(aiScene, boneList, rootNode, globalInverseTransformation, animations);
            data.SetAnimations(animations);
        }

        aiReleaseImport(aiScene);
        return data;
    }

    private static AnimationChannel_ StoreRawAnimationData(AINodeAnim aiChannel, float ticksPerSecond) {
        AnimationChannel_ channel = new AnimationChannel();

        //Create the Quorum values
        Array_ positions = new Array(); //Vector3
        Array_ rotations = new Array(); //Quaternion
        Array_ scalings = new Array(); //Vector3
        Number32BitArray_ timestamps = new Number32BitArray();

        //Set the arrays
        channel.SetPositions(positions);
        channel.SetRotations(rotations);
        channel.SetScalings(scalings);
        channel.SetTimestamps(timestamps);

        //Create the positions
        int positionKeys = aiChannel.mNumPositionKeys();
        AIVectorKey.Buffer positionBuffer = aiChannel.mPositionKeys();
        if (positionBuffer != null) {
            for (int i = 0; i < positionKeys; i++) {
                AIVectorKey key = positionBuffer.get(i);
                AIVector3D val = key.mValue();
                Vector3_ vector = new Vector3();
                vector.Set(val.x(), val.y(), val.z());
                positions.Add(vector);
            }
        }

        //Create the quaternions
        int quaternionKeys = aiChannel.mNumRotationKeys();
        AIQuatKey.Buffer quaternionBuffer = aiChannel.mRotationKeys();

        if (quaternionBuffer != null) {
            for (int i = 0; i < quaternionKeys; i++) {
                AIQuatKey key = quaternionBuffer.get(i);
                AIQuaternion val = key.mValue();
                Quaternion_ quat = new Quaternion();
                quat.Set(val.x(), val.y(), val.z(), val.w());
                rotations.Add(quat);
            }
        }

        //Create the scaling values
        int scalingKeys = aiChannel.mNumScalingKeys();
        AIVectorKey.Buffer scalingBuffer = aiChannel.mScalingKeys();

        if (scalingBuffer != null) {
            for (int i = 0; i < scalingKeys; i++) {
                AIVectorKey key = scalingBuffer.get(i);
                AIVector3D val = key.mValue();
                Vector3_ vector = new Vector3();
                vector.Set(val.x(), val.y(), val.z());
                scalings.Add(vector);
            }
        }

        //Create the timestamps
        int timestampKeys = aiChannel.mNumPositionKeys();
        AIVectorKey.Buffer timestampBuffer = aiChannel.mPositionKeys();
        timestamps.SetSize(timestampKeys);

        if (timestampBuffer != null) {
            for (int i = 0; i < timestampKeys; i++) {
                AIVectorKey key = timestampBuffer.get(i);
                float value = (float) (key.mTime() / ticksPerSecond);
                timestamps.Set(i, value);
            }
        }

        return channel;
    }

    private static void ProcessAnimations(AIScene aiScene, List<Bone_> boneList,
                                          AnimationNode_ rootNode, Matrix4_ globalInverseTransformation,
                                          Animations_ animations) {
        Array_ animationsTopLevelArray = animations.GetAnimations();

        int maxJointsMatricesLists = DEFAULT_MAX_JOINTS_MATRICES_LISTS;

        // Process all animations. We get the raw channels and bake in frames.
        int numAnimations = aiScene.mNumAnimations();
        PointerBuffer aiAnimations = aiScene.mAnimations();
        for (int i = 0; i < numAnimations; i++) {
            AIAnimation aiAnimation = AIAnimation.create(aiAnimations.get(i));
            int maxFrames = CalculateAnimationMaxFrames(aiAnimation);
            float frameMillis = (float)(aiAnimation.mDuration() / maxFrames);

            Animation_ animation = new Animation();
            Array_ frames = animation.GetFrames();
            Array_ channels = animation.GetChannels();
            animation.SetName(aiAnimation.mName().dataString());
            animation.SetMilliseconds(frameMillis);
            animationsTopLevelArray.Add(animation);

            //Now get the raw channels for processing Quorum side if we want it.
            int numChannels = aiAnimation.mNumChannels();
            PointerBuffer channelsBuffer = aiAnimation.mChannels();
            float ticksPerSecond = (float) aiAnimation.mTicksPerSecond();
            // Default to 25 ticks per second if the exporter omitted it
            if (ticksPerSecond == 0) {
                ticksPerSecond = 25.0f;
            }

            if (channelsBuffer != null) {
                for (int j = 0; j < numChannels; j++) {
                    AINodeAnim aiChannel = AINodeAnim.create(channelsBuffer.get(j));
                    String boneName = aiChannel.mNodeName().dataString();
                    AnimationChannel_ quorumChannel = StoreRawAnimationData(aiChannel, ticksPerSecond);
                    quorumChannel.SetBone(boneName);
                    channels.Add(quorumChannel);
                }
            }

            Iterator<Bone_> boneIterator = boneList.iterator();
            int maxBoneID = -1;
            while (boneIterator.hasNext())
            {
                int boneID = boneIterator.next().GetBoneID();
                if (boneID > maxBoneID)
                    maxBoneID = boneID;
            }

            if (maxBoneID >= 0)
                maxJointsMatricesLists = maxBoneID + 1;

            //Now bake in the frames so we can send it to GPU if we wish.
            for (int j = 0; j < maxFrames; j++) {
                AnimationFrame_ frame = new AnimationFrame();
                Array_ joints = frame.GetJoints();
                joints.SetSize(maxJointsMatricesLists);
                for(int k = 0; k < joints.GetSize(); k++) {
                    Matrix4_ identity = new Matrix4();
                    identity.IdentityMatrix();
                    joints.Set(k, identity);
                }
                BuildFrameMatrices(aiAnimation, boneList, frame, j, rootNode,
                        rootNode.GetNodeTransform(), globalInverseTransformation);
                frames.Add(frame);
            }
        }

        // Also create a single animation frame to represent the transformations used for the bind pose.
        AnimationFrame_ bindPoseFrame = new AnimationFrame();
        Array_ joints = bindPoseFrame.GetJoints();
        joints.SetSize(maxJointsMatricesLists);
        for (int i = 0; i < joints.GetSize(); i++)
        {
            // Matrices default to the identity matrix.
            Matrix4_ identity = new Matrix4();
            joints.Set(i, identity);
        }

        BuildFrameMatrices(null, boneList, bindPoseFrame, 0, rootNode, rootNode.GetNodeTransform(), globalInverseTransformation);
        animations.SetBindPoseFrame(bindPoseFrame);
    }



    private static void BuildFrameMatrices(AIAnimation aiAnimation, List<Bone_> boneList, AnimationFrame_ animatedFrame,
                                           int frame, AnimationNode_ node, Matrix4_ parentTransformation, Matrix4_ globalInverseTransform) {
        String nodeName = node.GetName();
        Matrix4_ nodeTransform = node.GetNodeTransform();
        if (aiAnimation != null)
        {
            AINodeAnim aiNodeAnim = FindAIAnimNode(aiAnimation, nodeName);
            if (aiNodeAnim != null)
            {
                nodeTransform = BuildNodeTransformationMatrix(aiNodeAnim, frame);
            }
        }

        Matrix4_ nodeGlobalTransform = new Matrix4();
        nodeGlobalTransform.Set(parentTransformation);
        nodeGlobalTransform.Multiply(nodeTransform);

        List<Bone_> affectedBones = new ArrayList<>();
        Iterator<Bone_> iterator = boneList.iterator();
        while(iterator.hasNext()) {
            Bone_ next = iterator.next();
            if(next.GetName() != null && next.GetName().equals(nodeName)) {
                affectedBones.add(next);
            }
        }
        for (Bone_ bone : affectedBones) {
            Matrix4_ boneTransform = new Matrix4();
            boneTransform.Set(globalInverseTransform);
            boneTransform.Multiply(nodeGlobalTransform);
            boneTransform.Multiply(bone.GetOffset());
            int boneID = bone.GetBoneID();
            Array_ joints = animatedFrame.GetJoints();
            joints.Set(boneID, boneTransform);
        }

        Array_ children = node.GetChildren();
        for(int i = 0; i < children.GetSize(); i++) {
            AnimationNode_ childNode = (AnimationNode_) children.Get(i);
            BuildFrameMatrices(aiAnimation, boneList, animatedFrame, frame, childNode, nodeGlobalTransform,
                    globalInverseTransform);
        }
    }

    private static Matrix4_ BuildNodeTransformationMatrix(AINodeAnim aiNodeAnim, int frame) {
        AIVectorKey.Buffer positionKeys = aiNodeAnim.mPositionKeys();
        AIVectorKey.Buffer scalingKeys = aiNodeAnim.mScalingKeys();
        AIQuatKey.Buffer rotationKeys = aiNodeAnim.mRotationKeys();

        AIVectorKey aiVecKey;
        AIVector3D vec;

        Matrix4_ nodeTransform = new Matrix4();
        int numPositions = aiNodeAnim.mNumPositionKeys();
        if (numPositions > 0) {
            aiVecKey = positionKeys.get(Math.min(numPositions - 1, frame));
            vec = aiVecKey.mValue();
            nodeTransform.Translate(vec.x(), vec.y(), vec.z());
        }
        int numRotations = aiNodeAnim.mNumRotationKeys();
        if (numRotations > 0) {
            AIQuatKey quatKey = rotationKeys.get(Math.min(numRotations - 1, frame));
            AIQuaternion aiQuat = quatKey.mValue();
            Quaternion_ quat = new Quaternion();
            quat.Set(aiQuat.x(), aiQuat.y(), aiQuat.z(), aiQuat.w());
            nodeTransform.Rotate(quat);
        }
        int numScalingKeys = aiNodeAnim.mNumScalingKeys();
        if (numScalingKeys > 0) {
            aiVecKey = scalingKeys.get(Math.min(numScalingKeys - 1, frame));
            vec = aiVecKey.mValue();
            nodeTransform.Scale(vec.x(), vec.y(), vec.z());
        }

        return nodeTransform;
    }

    private static AnimationNode_ BuildNodesTree(AINode aiNode, AnimationNode_ parentNode) {
        String nodeName = aiNode.mName().dataString();
        AnimationNode_ node = new AnimationNode();
        node.SetName(nodeName);
        node.SetParent(parentNode);
        node.SetNodeTransform(ToMatrix(aiNode.mTransformation()));

        int numChildren = aiNode.mNumChildren();
        PointerBuffer aiChildren = aiNode.mChildren();
        for (int i = 0; i < numChildren; i++) {
            AINode aiChildNode = AINode.create(aiChildren.get(i));
            AnimationNode_ childNode = BuildNodesTree(aiChildNode, node);
            Array_ array = node.GetChildren();
            if(array == null) {
                array = new Array();
                node.SetChildren(array);
            }
            array.Add(childNode);
        }
        return node;
    }

    private static int CalculateAnimationMaxFrames(AIAnimation aiAnimation) {
        int maxFrames = 0;
        int numNodeAnims = aiAnimation.mNumChannels();
        PointerBuffer aiChannels = aiAnimation.mChannels();
        for (int i = 0; i < numNodeAnims; i++) {
            AINodeAnim aiNodeAnim = AINodeAnim.create(aiChannels.get(i));
            int numFrames = Math.max(Math.max(aiNodeAnim.mNumPositionKeys(), aiNodeAnim.mNumScalingKeys()),
                    aiNodeAnim.mNumRotationKeys());
            maxFrames = Math.max(maxFrames, numFrames);
        }

        return maxFrames;
    }

    private static AINodeAnim FindAIAnimNode(AIAnimation aiAnimation, String nodeName) {
        AINodeAnim result = null;
        int numAnimNodes = aiAnimation.mNumChannels();
        PointerBuffer aiChannels = aiAnimation.mChannels();
        for (int i = 0; i < numAnimNodes; i++) {
            AINodeAnim aiNodeAnim = AINodeAnim.create(aiChannels.get(i));
            if (nodeName.equals(aiNodeAnim.mNodeName().dataString())) {
                result = aiNodeAnim;
                break;
            }
        }
        return result;
    }

    private static AnimationMeshData_ ProcessBones(AIMesh aiMesh, List<Bone_> boneList) {
        Integer32BitArray_ boneIds = new Integer32BitArray();
        Number32BitArray_ weights = new Number32BitArray();

        Map<Integer, List<VertexWeight_>> weightSet = new HashMap<>();
        int numBones = aiMesh.mNumBones();
        PointerBuffer aiBones = aiMesh.mBones();
        for (int i = 0; i < numBones; i++) {
            AIBone aiBone = AIBone.create(aiBones.get(i));
            int id = boneList.size();
            Bone_ bone = new Bone();
            bone.SetBoneID(id);
            bone.SetName(aiBone.mName().dataString());
            bone.SetOffset(ToMatrix(aiBone.mOffsetMatrix()));
            boneList.add(bone);
            int numWeights = aiBone.mNumWeights();
            AIVertexWeight.Buffer aiWeights = aiBone.mWeights();
            for (int j = 0; j < numWeights; j++) {
                AIVertexWeight aiWeight = aiWeights.get(j);
                VertexWeight_ vw = new VertexWeight();
                vw.SetBoneID(bone.GetBoneID());
                vw.SetVertexID(aiWeight.mVertexId());
                vw.SetWeight(aiWeight.mWeight());

                List<VertexWeight_> vertexWeightList = weightSet.get(vw.GetVertexID());
                if (vertexWeightList == null) {
                    vertexWeightList = new ArrayList<>();
                    weightSet.put(vw.GetVertexID(), vertexWeightList);
                }
                vertexWeightList.add(vw);
            }
        }

        int numVertices = aiMesh.mNumVertices();
        boneIds.SetSize(numVertices * MAX_WEIGHTS);
        weights.SetSize(numVertices * MAX_WEIGHTS);
        for (int i = 0; i < numVertices; i++) {
            List<VertexWeight_> vertexWeightList = weightSet.get(i);
            int size = vertexWeightList != null ? vertexWeightList.size() : 0;
            for (int j = 0; j < MAX_WEIGHTS; j++) {
                int index = (i * MAX_WEIGHTS) + j;
                if (j < size) {
                    VertexWeight_ vw = vertexWeightList.get(j);
                    weights.Set(index, (float)vw.GetWeight());
                    boneIds.Set(index, vw.GetBoneID());
                } else {
                    weights.Set(index, 0.0f);
                    boneIds.Set(index, 0);
                }
            }
        }

        AnimationMeshData_ mesh = new AnimationMeshData();
        mesh.SetBoneIDs(boneIds);
        mesh.SetWeights(weights);
        return mesh;
    }

    /*
    Reads the lights in a model file into ModelLightData objects. The scene must come from an
    import that keeps the node tree, as
    each light's position and direction are in its node's space: they are
    multiplied by the node's transforms up to the root, so they land in the
    same space as meshes flattened by aiProcess_PreTransformVertices. Under
    Vulkan, z is negated, as aiProcess_MakeLeftHanded does to the meshes.
    Area lights are read as point lights; ambient and unknown lights are
    skipped.
    */
    /*
     * Returns true if the file is glTF (.gltf or .glb), whose color numbers
     * are amounts of light rather than picture colors.
     */
    private static boolean IsGLTF(String path) {
        String lower = path.toLowerCase();
        return lower.endsWith(".gltf") || lower.endsWith(".glb");
    }

    /*
     * Turns an amount of light (0 to 1) into the color a picture would store
     * for it (sRGB), the inverse of the shaders' decoding (Linear lighting
     * plan LL4). Values outside 0 to 1 are kept to that range.
     */
    private static double PictureFromLinear(double value) {
        if (value <= 0) {
            return 0;
        }
        if (value >= 1) {
            return 1;
        }
        if (value <= 0.0031308) {
            return value * 12.92;
        }
        return 1.055 * Math.pow(value, 1.0 / 2.4) - 0.055;
    }

    private static void ProcessLights(AIScene aiScene, boolean leftHanded, Array_ lights, boolean linearColors) {
        int numLights = aiScene.mNumLights();
        if (numLights <= 0) {
            return;
        }
        PointerBuffer aiLights = aiScene.mLights();
        if (aiLights == null) {
            return;
        }
        AINode root = aiScene.mRootNode();

        for (int i = 0; i < numLights; i++) {
            AILight aiLight = AILight.create(aiLights.get(i));
            int type = aiLight.mType();
            int quorumType;
            if (type == aiLightSource_DIRECTIONAL) {
                quorumType = 1; // ModelLightData:DIRECTIONAL
            } else if (type == aiLightSource_SPOT) {
                quorumType = 2; // ModelLightData:SPOT
            } else if (type == aiLightSource_POINT || type == aiLightSource_AREA) {
                quorumType = 0; // ModelLightData:POINT
            } else {
                continue;
            }

            String name = aiLight.mName().dataString();

            // The light's node: its global transform, and for glTF, its
            // range, which assimp keeps in the node's metadata.
            AINode[] found = new AINode[1];
            double[] global = null;
            if (root != null) {
                global = FindNodeTransform(root, name, IdentityArray(), found);
            }
            if (global == null) {
                global = IdentityArray();
            }

            AIVector3D localPosition = aiLight.mPosition();
            AIVector3D localDirection = aiLight.mDirection();
            double px = localPosition.x();
            double py = localPosition.y();
            double pz = localPosition.z();
            double dx = localDirection.x();
            double dy = localDirection.y();
            double dz = localDirection.z();
            if (dx * dx + dy * dy + dz * dz < 1e-12) {
                // glTF lights point down their node's -z.
                dx = 0;
                dy = 0;
                dz = -1;
            }

            double x = global[0] * px + global[1] * py + global[2] * pz + global[3];
            double y = global[4] * px + global[5] * py + global[6] * pz + global[7];
            double z = global[8] * px + global[9] * py + global[10] * pz + global[11];
            double directionX = global[0] * dx + global[1] * dy + global[2] * dz;
            double directionY = global[4] * dx + global[5] * dy + global[6] * dz;
            double directionZ = global[8] * dx + global[9] * dy + global[10] * dz;
            if (leftHanded) {
                z = -z;
                directionZ = -directionZ;
            }

            // Assimp gives the color times the strength (glTF's importer
            // multiplies them), so the brightest part becomes the intensity
            // and the color is kept with its brightest part at 1.
            AIColor3D aiColor = aiLight.mColorDiffuse();
            double red = aiColor.r();
            double green = aiColor.g();
            double blue = aiColor.b();
            double brightest = Math.max(red, Math.max(green, blue));
            double intensity = 0;
            if (brightest > 0) {
                intensity = brightest;
                red = red / brightest;
                green = green / brightest;
                blue = blue / brightest;
            } else {
                red = 1;
                green = 1;
                blue = 1;
            }

            double range = 0;
            if (found[0] != null) {
                range = ReadLightRange(found[0]);
            }

            if (linearColors) {
                red = PictureFromLinear(red);
                green = PictureFromLinear(green);
                blue = PictureFromLinear(blue);
            }

            ModelLightData_ light = new ModelLightData();
            light.SetType(quorumType);
            light.SetName(name);
            light.SetColor(red, green, blue);
            light.SetIntensity(intensity);
            light.SetRange(range);
            if (quorumType == 2) {
                // Half-angles in radians, measured from the direction.
                light.SetConeAngles(Math.toDegrees(aiLight.mAngleInnerCone()),
                        Math.toDegrees(aiLight.mAngleOuterCone()));
            }
            light.SetPosition(x, y, z);
            light.SetDirection(directionX, directionY, directionZ);
            lights.Add(light);
        }
    }

    /*
    Looks for the node with the given name below (and including) the given
    node, and returns its global transform as 16 numbers, row by row, or null
    if there is no such node. The node found is put in
    found[0].
    */
    private static double[] FindNodeTransform(AINode node, String name, double[] parentTransform, AINode[] found) {
        double[] global = MultiplyArrays(parentTransform, ToArray(node.mTransformation()));
        if (name.equals(node.mName().dataString())) {
            found[0] = node;
            return global;
        }
        int count = node.mNumChildren();
        if (count > 0) {
            PointerBuffer children = node.mChildren();
            if (children != null) {
                for (int i = 0; i < count; i++) {
                    double[] result = FindNodeTransform(AINode.create(children.get(i)), name, global, found);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    /*
    Reads a glTF light's range, which assimp puts in its node's metadata as
    "PBR_LightRange", or returns 0 if there is none.
    */
    private static double ReadLightRange(AINode node) {
        AIMetaData metadata = node.mMetadata();
        if (metadata == null) {
            return 0;
        }
        int count = metadata.mNumProperties();
        if (count <= 0) {
            return 0;
        }
        AIString.Buffer keys = metadata.mKeys();
        AIMetaDataEntry.Buffer values = metadata.mValues();
        if (keys == null || values == null) {
            return 0;
        }
        for (int i = 0; i < count; i++) {
            if (!"PBR_LightRange".equals(keys.get(i).dataString())) {
                continue;
            }
            AIMetaDataEntry entry = values.get(i);
            if (entry.mType() == AI_FLOAT) {
                ByteBuffer bytes = entry.mData(4);
                if (bytes != null) {
                    return bytes.order(java.nio.ByteOrder.nativeOrder()).getFloat(0);
                }
            } else if (entry.mType() == AI_DOUBLE) {
                ByteBuffer bytes = entry.mData(8);
                if (bytes != null) {
                    return bytes.order(java.nio.ByteOrder.nativeOrder()).getDouble(0);
                }
            }
        }
        return 0;
    }

    // An identity transform as 16 numbers, row by row.
    private static double[] IdentityArray() {
        return new double[] {
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        };
    }

    // An assimp matrix as 16 numbers, row by row.
    private static double[] ToArray(AIMatrix4x4 m) {
        return new double[] {
            m.a1(), m.a2(), m.a3(), m.a4(),
            m.b1(), m.b2(), m.b3(), m.b4(),
            m.c1(), m.c2(), m.c3(), m.c4(),
            m.d1(), m.d2(), m.d3(), m.d4()
        };
    }

    // The product a times b of two 4 by 4 matrices, row by row.
    private static double[] MultiplyArrays(double[] a, double[] b) {
        double[] result = new double[16];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += a[row * 4 + k] * b[k * 4 + column];
                }
                result[row * 4 + column] = sum;
            }
        }
        return result;
    }

    private static Matrix4_ ToMatrix (AIMatrix4x4 aiMatrix) {
        Matrix4_ result = new Matrix4();
        //I think both are row-major, or at least so far as I can tell from the documentation in both.
        result.Set(
            aiMatrix.a1(), aiMatrix.a2(), aiMatrix.a3(), aiMatrix.a4(),
            aiMatrix.b1(), aiMatrix.b2(), aiMatrix.b3(), aiMatrix.b4(),
            aiMatrix.c1(), aiMatrix.c2(), aiMatrix.c3(), aiMatrix.c4(),
            aiMatrix.d1(), aiMatrix.d2(), aiMatrix.d3(), aiMatrix.d4()
        );

        return result;
    }

    private static MeshData_ ProcessMesh(AIMesh aiMesh, boolean vulkanLoading, boolean linearColors) {
        Number32BitArray_ vertices = ProcessVertices(aiMesh);
        Integer32BitArray_ indices = ProcessIndices(aiMesh);
        Number32BitArray_ coordinates = ProcessTextureCoordinates(aiMesh, vulkanLoading);
        Number32BitArray_ normals = ProcessNormals(aiMesh);
        Number32BitArray_ tangents = ProcessTangents(aiMesh, normals.GetSize());
        Number32BitArray_ bitangents = ProcessBiTangents(aiMesh, normals.GetSize());

        //if there are no textures, we need empty slots, or at least
        //the textbook says we do.
        if (coordinates.GetSize() == 0) {
            int size = (vertices.GetSize() / 3) * 2;
            coordinates.SetSize(size);
        }

        int materialIndex = aiMesh.mMaterialIndex();
        MeshData_ mesh = new MeshData();
        mesh.SetMaterialIndex(materialIndex);
        mesh.SetVertices(vertices);
        mesh.SetIndices(indices);
        mesh.SetTextureCoordinates(coordinates);
        mesh.SetNormals(normals);
        mesh.SetTangents(tangents);
        mesh.SetBitangents(bitangents);
        // Vertex colors, when the file has them.
        AIColor4D.Buffer aiColors = aiMesh.mColors(0);
        if (aiColors != null && aiColors.remaining() > 0) {
            Number32BitArray_ colors = new Number32BitArray();
            colors.SetSize(aiColors.remaining() * 4);
            int index = 0;
            while (aiColors.remaining() > 0) {
                AIColor4D color = aiColors.get();
                if (linearColors) {
                    colors.Set(index, PictureFromLinear(color.r()));
                    colors.Set(index + 1, PictureFromLinear(color.g()));
                    colors.Set(index + 2, PictureFromLinear(color.b()));
                } else {
                    colors.Set(index, color.r());
                    colors.Set(index + 1, color.g());
                    colors.Set(index + 2, color.b());
                }
                colors.Set(index + 3, color.a());
                index = index + 4;
            }
            mesh.SetColors(colors);
        }
        return mesh;
    }

    /*
     * Writes each texture stored inside a model file (such as a .glb) to a
     * folder of its own in the temporary folder, once, and returns their
     * paths in order, so a material naming "*0" can load the first. A file
     * with no stored textures gives an empty array. The folder's name comes
     * from the model's path and when it last changed, so a changed model
     * writes its textures again.
     */
    private static String[] ExtractEmbeddedTextures(AIScene aiScene, String modelPath) {
        int count = aiScene.mNumTextures();
        String[] paths = new String[count];
        if (count == 0) {
            return paths;
        }
        File modelFile = new File(modelPath);
        String folderName = Integer.toHexString((modelFile.getAbsolutePath() + ":" + modelFile.lastModified()).hashCode());
        File folder = new File(new File(System.getProperty("java.io.tmpdir"), "QuorumModelTextures"), folderName);
        folder.mkdirs();
        PointerBuffer aiTextures = aiScene.mTextures();
        for (int i = 0; i < count; i++) {
            AITexture texture = AITexture.create(aiTextures.get(i));
            try {
                if (texture.mHeight() == 0) {
                    // Compressed: the bytes of a .png or .jpg file.
                    String extension = texture.achFormatHintString();
                    if (extension == null || extension.length() == 0) {
                        extension = "png";
                    }
                    File out = new File(folder, "texture_" + i + "." + extension);
                    if (!out.exists()) {
                        ByteBuffer bytes = texture.pcDataCompressed();
                        byte[] data = new byte[texture.mWidth()];
                        bytes.get(data);
                        Files.write(out.toPath(), data);
                    }
                    paths[i] = out.getAbsolutePath();
                } else {
                    // Uncompressed: width by height texels, written as a .png.
                    File out = new File(folder, "texture_" + i + ".png");
                    if (!out.exists()) {
                        int width = texture.mWidth();
                        int height = texture.mHeight();
                        AITexel.Buffer texels = texture.pcData();
                        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                        for (int y = 0; y < height; y++) {
                            for (int x = 0; x < width; x++) {
                                AITexel texel = texels.get(y * width + x);
                                int argb = ((texel.a() & 0xFF) << 24) | ((texel.r() & 0xFF) << 16) | ((texel.g() & 0xFF) << 8) | (texel.b() & 0xFF);
                                image.setRGB(x, y, argb);
                            }
                        }
                        javax.imageio.ImageIO.write(image, "png", out);
                    }
                    paths[i] = out.getAbsolutePath();
                }
            } catch (java.io.IOException exception) {
                paths[i] = "";
            }
        }
        return paths;
    }

    /*
     * Turns a texture path from a model file into one Quorum can load: a
     * stored texture ("*0") becomes its written-out file, and a file path
     * becomes relative to the working directory, as before. An empty path
     * stays empty.
     */
    private static String ResolveTexturePath(String rawPath, String texturesDir, String[] embeddedPaths) {
        if (rawPath == null || rawPath.length() == 0) {
            return "";
        }
        if (rawPath.startsWith("*")) {
            try {
                int index = Integer.parseInt(rawPath.substring(1));
                if (index >= 0 && index < embeddedPaths.length && embeddedPaths[index] != null) {
                    return embeddedPaths[index];
                }
            } catch (NumberFormatException exception) {
                // Not a stored texture's number; treated as a file name.
            }
            return "";
        }
        java.nio.file.Path workingDirectoryPath = java.nio.file.Paths.get(System.getProperty("user.dir"));
        java.nio.file.Path thePathObject = new java.io.File(
                texturesDir + File.separator + rawPath).toPath().toAbsolutePath();
        return workingDirectoryPath.relativize(thePathObject).toString();
    }

    /*
     * Returns the path of a material's first texture of a type, resolved as
     * ResolveTexturePath does, or "" if it has none.
     */
    private static String GetTexturePath(AIMaterial aiMaterial, int type, String texturesDir, String[] embeddedPaths, MemoryStack stack) {
        AIString aiPath = AIString.calloc(stack);
        int result = aiGetMaterialTexture(aiMaterial, type, 0, aiPath, (IntBuffer) null,
                null, null, null, null, null);
        if (result != aiReturn_SUCCESS) {
            return "";
        }
        return ResolveTexturePath(aiPath.dataString(), texturesDir, embeddedPaths);
    }

    /*
     * Returns the mesh's texture coordinates, two a vertex. Assimp gives v
     * with 0 at the bottom of the picture (it flips glTF's v). For Vulkan
     * they are passed on as Assimp gives them: MeshVulkan flips v as it
     * builds the vertex buffer, giving v with 0 at the top, as the images
     * are stored. Flipping here too made every model file's textures upside
     * down on Vulkan (found on the LightsPunctualLamp). For
     * OpenGL, v is flipped here, as before.
     */
    private static Number32BitArray_ ProcessTextureCoordinates(AIMesh aiMesh, boolean vulkanLoading) {
        AIVector3D.Buffer aiTextCoords = aiMesh.mTextureCoords(0);
        int numTextCoords = aiTextCoords != null ? aiTextCoords.remaining() : 0;
        Number32BitArray_ array = new Number32BitArray();
        array.SetSize(numTextCoords * 2); //two each, x and y

        for (int i = 0; i < numTextCoords; i++) {
            int j = i * 2;
            AIVector3D textCoord = aiTextCoords.get();
            array.Set(j, textCoord.x());
            if (vulkanLoading) {
                array.Set(j + 1, textCoord.y());
            } else {
                array.Set(j + 1, 1 - textCoord.y());
            }
        }
        return array;
    }
    private static Integer32BitArray_ ProcessIndices(AIMesh aiMesh) {
        int numFaces = aiMesh.mNumFaces();
        AIFace.Buffer aiFaces = aiMesh.mFaces();
        //all faces should be the same size because we use triangles
        //However, just check
        if(numFaces > 0) {
            AIFace aiFace = aiFaces.get(0);
            int size = aiFace.mNumIndices();
            if(size != 3) {
                throw new RuntimeException("Loading model that is not triangles. If this triggers, there's a bug in the loader.");
            }
        }
        Integer32BitArray_ array = new Integer32BitArray();
        array.SetSize(numFaces * 3);
        int j = 0;
        for (int i = 0; i < numFaces; i++) {
            AIFace aiFace = aiFaces.get(i);
            IntBuffer buffer = aiFace.mIndices();
            while (buffer.remaining() > 0) {
                array.Set(j, buffer.get());
                j = j + 1;
            }
        }
        return array;
    }

    private static Number32BitArray_ ProcessVertices(AIMesh aiMesh) {
        int size = aiMesh.mNumVertices();
        AIVector3D.Buffer aiVertices = aiMesh.mVertices();
        Number32BitArray_ array = new Number32BitArray();
        array.SetSize(size * 3); //X, Y, and Z coordinates
        int i = 0;
        while (aiVertices.remaining() > 0) {
            AIVector3D aiVertex = aiVertices.get();
            array.Set(i, aiVertex.x());
            array.Set(i + 1, aiVertex.y());
            array.Set(i + 2, aiVertex.z());
            i = i + 3;
        }

        return array;
    }

    private static Number32BitArray_ ProcessTangents(AIMesh aiMesh, int numNormals) {
        Number32BitArray_ array = new Number32BitArray();
        AIVector3D.Buffer aiTangents = aiMesh.mTangents();
        if(aiTangents != null) {
            int size = aiTangents.remaining() * 3;
            array.SetSize(size);
        } else {
            array.SetSize(numNormals);
            return array;
        }
        int i = 0;
        while (aiTangents.remaining() > 0) { //can't be null at this point.
            AIVector3D aiTangent = aiTangents.get();
            array.Set(i, aiTangent.x());
            array.Set(i + 1, aiTangent.y());
            array.Set(i + 2, aiTangent.z());
            i = i + 3;
        }

        return array;
    }

    private static Number32BitArray_ ProcessBiTangents(AIMesh aiMesh, int numNormals) {
        Number32BitArray_ array = new Number32BitArray();
        AIVector3D.Buffer aiBitangents = aiMesh.mBitangents();
        if(aiBitangents != null) {
            int size = aiBitangents.remaining() * 3;
            array.SetSize(size);
        } else {
            array.SetSize(numNormals);
            return array;
        }
        // The bitangent is turned around. With the left-handed flip,
        // Assimp's bitangent points along the file's own v, down the
        // picture (measured again after the v fix in
        // ProcessTextureCoordinates), but normal maps
        // (glTF requires it, and most other exporters do the same) store +Y,
        // the green channel, as up the picture. Used as it was, every normal
        // map was lit upside down: glTF's NormalTangentTest looked like its
        // "incorrect-flipped-y" picture. This was measured on
        // the lamp, DamagedHelmet, BoomBox and NormalTangentTest, with file
        // tangents and with tangents Assimp works out.
        int i = 0;
        while (aiBitangents.remaining() > 0) { //can't be null at this point.
            AIVector3D aiBitangent = aiBitangents.get();
            array.Set(i, -aiBitangent.x());
            array.Set(i + 1, -aiBitangent.y());
            array.Set(i + 2, -aiBitangent.z());
            i = i + 3;
        }

        return array;
    }

    private static Number32BitArray_ ProcessNormals(AIMesh aiMesh) {
        Number32BitArray_ array = new Number32BitArray();
        AIVector3D.Buffer aiNormals = aiMesh.mNormals();
        if(aiNormals == null) {
            return array;
        }
        int size = aiNormals.remaining() * 3;
        array.SetSize(size);
        int i = 0;
        while (aiNormals.remaining() > 0) {
            AIVector3D aiNormal = aiNormals.get();
            array.Set(i, aiNormal.x());
            array.Set(i + 1, aiNormal.y());
            array.Set(i + 2, aiNormal.z());
            i = i + 3;
        }
        return array;
    }

    /*
     * One part of a material's color: turned into a picture color if the
     * file stores amounts of light (glTF), as it is otherwise.
     */
    private static double ColorPart(double value, boolean linearColors) {
        if (linearColors) {
            return PictureFromLinear(value);
        }
        return value;
    }

    private static Material_ ProcessMaterial(AIMaterial aiMaterial, String texturesDir, String[] embeddedPaths, boolean linearColors) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            AIColor4D colour = AIColor4D.create();

            Vector4_ diffuse = new Vector4();
            int result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, aiTextureType_NONE, 0,
                    colour);
            if (result == aiReturn_SUCCESS) {
                diffuse.Set(ColorPart(colour.r(), linearColors), ColorPart(colour.g(), linearColors), ColorPart(colour.b(), linearColors), colour.a());
            }
            String texturePath = GetTexturePath(aiMaterial, aiTextureType_DIFFUSE, texturesDir, embeddedPaths, stack);
            if (texturePath.length() == 0) {
                texturePath = GetTexturePath(aiMaterial, aiTextureType_BASE_COLOR, texturesDir, embeddedPaths, stack);
            }
            if (texturePath.length() > 0) {
                // The texture is the color. A glTF base color factor tints it;
                // other files' diffuse colors, often a
                // gray left by the exporter, don't, as before.
                diffuse.Set(1.0, 1.0, 1.0, 1.0);
                result = aiGetMaterialColor(aiMaterial, AI_MATKEY_BASE_COLOR, aiTextureType_NONE, 0, colour);
                if (result == aiReturn_SUCCESS) {
                    diffuse.Set(ColorPart(colour.r(), linearColors), ColorPart(colour.g(), linearColors), ColorPart(colour.b(), linearColors), colour.a());
                }
            }

            String normalMapPath = GetTexturePath(aiMaterial, aiTextureType_NORMALS, texturesDir, embeddedPaths, stack);

            AIString aiMetallicRoughnessPath = AIString.calloc(stack);
            Assimp.aiGetMaterialTexture(aiMaterial, AI_MATKEY_GLTF_PBRMETALLICROUGHNESS_METALLICROUGHNESS_TEXTURE, 0, aiMetallicRoughnessPath, (IntBuffer) null,
                    null, null, null, null, null);
            String metallicRoughnessPath = ResolveTexturePath(aiMetallicRoughnessPath.dataString(), texturesDir, embeddedPaths);

            // The glow and ambient occlusion maps, under either of their names.
            String emissiveMapPath = GetTexturePath(aiMaterial, aiTextureType_EMISSIVE, texturesDir, embeddedPaths, stack);
            if (emissiveMapPath.length() == 0) {
                emissiveMapPath = GetTexturePath(aiMaterial, aiTextureType_EMISSION_COLOR, texturesDir, embeddedPaths, stack);
            }
            String occlusionMapPath = GetTexturePath(aiMaterial, aiTextureType_AMBIENT_OCCLUSION, texturesDir, embeddedPaths, stack);
            if (occlusionMapPath.length() == 0) {
                occlusionMapPath = GetTexturePath(aiMaterial, aiTextureType_LIGHTMAP, texturesDir, embeddedPaths, stack);
            }

            float[] metallicArr = new float[]{0.0f};
            int[] pMax = new int[]{1};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_METALLIC_FACTOR, aiTextureType_NONE, 0, metallicArr, pMax);
            if (result != aiReturn_SUCCESS) {
                metallicArr[0] = 0.0f;
            }

            float[] roughnessArr = new float[]{0.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_ROUGHNESS_FACTOR, aiTextureType_NONE, 0, roughnessArr, pMax);
            if (result != aiReturn_SUCCESS) {
                roughnessArr[0] = 0.0f;
            }

            Vector4_ emissive = new Vector4();
            result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_EMISSIVE,
                    aiTextureType_NONE, 0, colour);
            if(result == aiReturn_SUCCESS) {
                emissive.Set(ColorPart(colour.r(), linearColors), ColorPart(colour.g(), linearColors), ColorPart(colour.b(), linearColors), colour.a());
            }

            float[] opacityArr = new float[]{1.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_OPACITY, aiTextureType_NONE, 0, opacityArr, pMax);
            if (result != aiReturn_SUCCESS) {
                opacityArr[0] = 1.0f;
            }

            float[] refractiveIndexArr = new float[]{1.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_REFRACTI, aiTextureType_NONE, 0, refractiveIndexArr, pMax);
            if (result != aiReturn_SUCCESS) {
                refractiveIndexArr[0] = 1.0f;
            }

            float[] bumpScalingArr = new float[]{1.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_BUMPSCALING, aiTextureType_NONE, 0, bumpScalingArr, pMax);
            if (result != aiReturn_SUCCESS) {
                bumpScalingArr[0] = 1.0f;
            }

            float[] emissiveStrengthArr = new float[]{1.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_EMISSIVE_INTENSITY, aiTextureType_NONE, 0, emissiveStrengthArr, pMax);
            if (result != aiReturn_SUCCESS) {
                emissiveStrengthArr[0] = 1.0f;
            }

            // How much light goes through the surface (glTF's KHR_materials_transmission), and its texture.
            float[] transmissionArr = new float[]{0.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_TRANSMISSION_FACTOR, aiTextureType_NONE, 0, transmissionArr, pMax);
            if (result != aiReturn_SUCCESS) {
                transmissionArr[0] = 0.0f;
            }
            String transmissionMapPath = GetTexturePath(aiMaterial, aiTextureType_TRANSMISSION, texturesDir, embeddedPaths, stack);

            int[] twoSidedArr = new int[]{0};
            result = aiGetMaterialIntegerArray(aiMaterial, AI_MATKEY_TWOSIDED, aiTextureType_NONE, 0, twoSidedArr, pMax);
            boolean twoSided = result == aiReturn_SUCCESS && twoSidedArr[0] != 0;

            // Only glTF files say; others keep "", the cut out below half
            // the texture's alpha that every model had before.
            String alphaMode = "";
            AIString aiAlphaMode = AIString.calloc(stack);
            result = aiGetMaterialString(aiMaterial, AI_MATKEY_GLTF_ALPHAMODE, aiTextureType_NONE, 0, aiAlphaMode);
            if (result == aiReturn_SUCCESS && aiAlphaMode.dataString().length() > 0) {
                alphaMode = aiAlphaMode.dataString();
            }

            float[] alphaCutoffArr = new float[]{0.5f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_GLTF_ALPHACUTOFF, aiTextureType_NONE, 0, alphaCutoffArr, pMax);
            if (result != aiReturn_SUCCESS) {
                alphaCutoffArr[0] = 0.5f;
            }

            int[] shadingArr = new int[]{0};
            result = aiGetMaterialIntegerArray(aiMaterial, AI_MATKEY_SHADING_MODEL, aiTextureType_NONE, 0, shadingArr, pMax);
            boolean unlit = result == aiReturn_SUCCESS && shadingArr[0] == aiShadingMode_Unlit;

            // The color texture's transform (glTF KHR_texture_transform).
            AIUVTransform transform = AIUVTransform.calloc(stack);
            result = aiGetMaterialUVTransform(aiMaterial, _AI_MATKEY_UVTRANSFORM_BASE, aiTextureType_DIFFUSE, 0, transform);
            boolean hasTransform = result == aiReturn_SUCCESS;

            Material_ material = new Material();
            material.SetTexturePath(texturePath);
            material.SetDiffuse(diffuse);
            material.SetEmissive(emissive);
            material.SetNormalMapPath(normalMapPath);
            material.SetMetalRoughMap(metallicRoughnessPath);
            material.SetMetallic(metallicArr[0]);
            material.SetRoughness(roughnessArr[0]);
            material.SetOpaque(opacityArr[0]);
            material.SetRefractiveIndex(refractiveIndexArr[0]);
            material.SetBumpShading(bumpScalingArr[0]);
            material.SetEmissiveMapPath(emissiveMapPath);
            material.SetEmissiveStrength(emissiveStrengthArr[0]);
            material.SetTransmission(transmissionArr[0]);
            material.SetTransmissionMapPath(transmissionMapPath);
            material.SetAmbientOcclusionMapPath(occlusionMapPath);
            material.SetTwoSided(twoSided);
            material.SetAlphaMode(alphaMode);
            material.SetAlphaCutoff(alphaCutoffArr[0]);
            material.SetUnlit(unlit);
            if (hasTransform) {
                material.SetTextureTransform(transform.mTranslation().x(), transform.mTranslation().y(),
                        transform.mScaling().x(), transform.mScaling().y(), transform.mRotation());
            }


            return material;
        }
    }
}
