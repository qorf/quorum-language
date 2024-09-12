package plugins.quorum.Libraries.Game.Graphics.Models;

import android.renderscript.Matrix4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import org.lwjgl.system.MemoryStack;
import quorum.Libraries.Compute.*;
import quorum.Libraries.Containers.*;
import quorum.Libraries.Game.Graphics.Models.*;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.System.File_;

import java.io.File;
import java.lang.Math;
import java.nio.IntBuffer;
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
        int flags = aiProcess_GenSmoothNormals | aiProcess_JoinIdenticalVertices |
                aiProcess_Triangulate | aiProcess_FixInfacingNormals | aiProcess_CalcTangentSpace | aiProcess_LimitBoneWeights |
                (animation ? 0 : aiProcess_PreTransformVertices);
        AIScene aiScene = aiImportFile(path, flags);
        if (aiScene == null) {
            //throw new RuntimeException("Error loading model [modelPath: " + modelPath + ", texturesDir:" + texturesDir + "]");
        }

        ModelData_ data = new ModelData();
        Array_ materials = data.GetMaterials();
        int numMaterials = aiScene.mNumMaterials();
        for (int i = 0; i < numMaterials; i++) {
            AIMaterial aiMaterial = AIMaterial.create(aiScene.mMaterials().get(i));
            Material_ material = ProcessMaterial(aiMaterial, textures);
            materials.Add(material);
        }

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer aiMeshes = aiScene.mMeshes();
        Array_ meshes = data.GetMeshes();
        for (int i = 0; i < numMeshes; i++) {
            AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
            MeshData_ mesh = ProcessMesh(aiMesh);
            meshes.Add(mesh);
        }

        int numAnimations = aiScene.mNumAnimations();
        if(animation && numAnimations > 0) {
            Animations_ animations = new Animations();
            Array_ animMeshDataList = animations.GetAnimationMeshData();
            List<Bone_> boneList = new ArrayList<>();
            for (int i = 0; i < numMeshes; i++) {
                AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
                AnimationMeshData_ animMeshData = ProcessBones(aiMesh, boneList);
                animMeshDataList.Add(animMeshData);
            }

            AnimationNode_ rootNode = BuildNodesTree(aiScene.mRootNode(), null);
            Matrix4_ globalInverseTransformation = ToMatrix(aiScene.mRootNode().mTransformation()).Inverse();
            ProcessAnimations(aiScene, boneList, rootNode, globalInverseTransformation, animations);
            data.SetAnimations(animations);
        }

        aiReleaseImport(aiScene);
        return data;
    }

    private static void ProcessAnimations(AIScene aiScene, List<Bone_> boneList,
                                          AnimationNode_ rootNode, Matrix4_ globalInverseTransformation,
                                          Animations_ animations) {
        Array_ animationsTopLevelArray = animations.GetAnimations();

        int maxJointsMatricesLists = DEFAULT_MAX_JOINTS_MATRICES_LISTS;//temporary
        // Process all animations
        int numAnimations = aiScene.mNumAnimations();
        PointerBuffer aiAnimations = aiScene.mAnimations();
        for (int i = 0; i < numAnimations; i++) {
            AIAnimation aiAnimation = AIAnimation.create(aiAnimations.get(i));
            int maxFrames = CalculateAnimationMaxFrames(aiAnimation);
            float frameMillis = (float) (aiAnimation.mDuration() / aiAnimation.mTicksPerSecond());

            Animation_ animation = new Animation();
            Array_ frames = animation.GetFrames();
            animation.SetName(aiAnimation.mName().dataString());
            animation.SetMilliseconds(frameMillis);
            animationsTopLevelArray.Add(animation);

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
    }



    private static void BuildFrameMatrices(AIAnimation aiAnimation, List<Bone_> boneList, AnimationFrame_ animatedFrame,
                                           int frame, AnimationNode_ node, Matrix4_ parentTransformation, Matrix4_ globalInverseTransform) {
        String nodeName = node.GetName();
        AINodeAnim aiNodeAnim = FindAIAnimNode(aiAnimation, nodeName);
        Matrix4_ nodeTransform = node.GetNodeTransform();
        if (aiNodeAnim != null) {
            nodeTransform = BuildNodeTransformationMatrix(aiNodeAnim, frame);
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
            joints.Set(bone.GetBoneID(), boneTransform);
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

    private static MeshData_ ProcessMesh(AIMesh aiMesh) {
        Number32BitArray_ vertices = ProcessVertices(aiMesh);
        Integer32BitArray_ indices = ProcessIndices(aiMesh);
        Number32BitArray_ coordinates = ProcessTextureCoordinates(aiMesh);
        Number32BitArray_ normals = ProcessNormals(aiMesh);
        Number32BitArray_ tangents = ProcessTangents(aiMesh, normals.GetSize());
        Number32BitArray_ bitangents = ProcessBiTangents(aiMesh, normals.GetSize());

        //if there are no textures, we need empty slots, or at least
        //the textbook says we do.
        if (coordinates.GetSize() == 0) {
            int size = (coordinates.GetSize() / 3) * 2;
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
        return mesh;
    }

    private static Number32BitArray_ ProcessTextureCoordinates(AIMesh aiMesh) {
        AIVector3D.Buffer aiTextCoords = aiMesh.mTextureCoords(0);
        int numTextCoords = aiTextCoords != null ? aiTextCoords.remaining() : 0;
        Number32BitArray_ array = new Number32BitArray();
        array.SetSize(numTextCoords * 2); //two each, x and y

        for (int i = 0; i < numTextCoords; i++) {
            int j = i * 2;
            AIVector3D textCoord = aiTextCoords.get();
            array.Set(j, textCoord.x());
            array.Set(j + 1, 1 - textCoord.y());
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
        int i = 0;
        while (aiBitangents.remaining() > 0) { //can't be null at this point.
            AIVector3D aiBitangent = aiBitangents.get();
            array.Set(i, aiBitangent.x());
            array.Set(i + 1, aiBitangent.y());
            array.Set(i + 2, aiBitangent.z());
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

    private static Material_ ProcessMaterial(AIMaterial aiMaterial, String texturesDir) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            AIColor4D colour = AIColor4D.create();

            Vector4_ diffuse = new Vector4();
            int result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, aiTextureType_NONE, 0,
                    colour);
            if (result == aiReturn_SUCCESS) {
                diffuse.Set(colour.r(), colour.g(), colour.b(), colour.a());
            }
            AIString aiTexturePath = AIString.calloc(stack);
            aiGetMaterialTexture(aiMaterial, aiTextureType_DIFFUSE, 0, aiTexturePath, (IntBuffer) null,
                    null, null, null, null, null);
            String texturePath = aiTexturePath.dataString();
            if (texturePath != null && texturePath.length() > 0) {
                texturePath = texturesDir + File.separator + new File(texturePath).getName();
                diffuse.Set(0.0, 0.0, 0.0, 0.0);
            }

            AIString aiNormalMapPath = AIString.calloc(stack);
            Assimp.aiGetMaterialTexture(aiMaterial, aiTextureType_NORMALS, 0, aiNormalMapPath, (IntBuffer) null,
                    null, null, null, null, null);
            String normalMapPath = aiNormalMapPath.dataString();
            if (normalMapPath != null && normalMapPath.length() > 0) {
                normalMapPath = texturesDir + File.separator + new File(normalMapPath).getName();
            }

            AIString aiMetallicRoughnessPath = AIString.calloc(stack);
            Assimp.aiGetMaterialTexture(aiMaterial, AI_MATKEY_GLTF_PBRMETALLICROUGHNESS_METALLICROUGHNESS_TEXTURE, 0, aiMetallicRoughnessPath, (IntBuffer) null,
                    null, null, null, null, null);
            String metallicRoughnessPath = aiMetallicRoughnessPath.dataString();
            if (metallicRoughnessPath != null && metallicRoughnessPath.length() > 0) {
                metallicRoughnessPath = texturesDir + File.separator + new File(metallicRoughnessPath).getName();
            }

            float[] metallicArr = new float[]{0.0f};
            int[] pMax = new int[]{1};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_METALLIC_FACTOR, aiTextureType_NONE, 0, metallicArr, pMax);
            if (result != aiReturn_SUCCESS) {
                metallicArr[0] = 1.0f;
            }

            float[] roughnessArr = new float[]{0.0f};
            result = aiGetMaterialFloatArray(aiMaterial, AI_MATKEY_ROUGHNESS_FACTOR, aiTextureType_NONE, 0, roughnessArr, pMax);
            if (result != aiReturn_SUCCESS) {
                roughnessArr[0] = 1.0f;
            }

            Material_ material = new Material();
            material.SetDiffuse(diffuse);
            material.SetNormalMapPath(normalMapPath);
            material.SetMetalRoughMap(metallicRoughnessPath);
            material.SetMetalic(metallicArr[0]);
            material.SetRoughness(roughnessArr[0]);

            return material;
        }
    }
}
