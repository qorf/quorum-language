package plugins.quorum.Libraries.Game.Graphics.Models;

import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import org.lwjgl.system.MemoryStack;
import quorum.Libraries.Compute.Vector4;
import quorum.Libraries.Compute.Vector4_;
import quorum.Libraries.Containers.*;
import quorum.Libraries.Game.Graphics.Models.*;
import quorum.Libraries.System.File_;

import java.io.File;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*; //for the flags, but you could do this a different way too

public class ModelLoader {
    public java.lang.Object me_ = null;

    public ModelData_ Load(File_ filePath, File_ texturesPath, boolean animation) {
        String path = filePath.GetAbsolutePath();
        String textures = texturesPath.GetAbsolutePath();
        return Load(path, textures,false);
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

        aiReleaseImport(aiScene);
        return data;
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
            material.SetMettalic(metallicArr[0]);
            material.SetRoughness(roughnessArr[0]);

            return material;
        }
    }
}
