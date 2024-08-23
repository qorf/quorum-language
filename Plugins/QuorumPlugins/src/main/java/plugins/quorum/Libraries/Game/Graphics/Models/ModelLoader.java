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

import static org.lwjgl.assimp.Assimp.*; //for the flags, but you could do this a different way too

public class ModelLoader {
    public java.lang.Object me_ = null;

    public ModelData_ Load(File_ filePath, File_ texturesPath) {
        String path = filePath.GetAbsolutePath();
        String textures = texturesPath.GetAbsolutePath();
        return Load(path, textures);
    }

    public ModelData_ Load(String path, String textures) {
        int flags = aiProcess_GenSmoothNormals | aiProcess_JoinIdenticalVertices |
                aiProcess_Triangulate | aiProcess_FixInfacingNormals | aiProcess_CalcTangentSpace |
                aiProcess_PreTransformVertices;
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

            Material_ material = new Material();
            material.SetDiffuse(diffuse);

            return material;
        }
    }
}
