/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.Camera;
import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.Color;
import quorum.Libraries.Game.Graphics.Texture;
import quorum.Libraries.Game.Graphics.Mesh;
import quorum.Libraries.Game.Graphics.ModelData.MeshPart;
import quorum.Libraries.Game.Graphics.VertexAttribute;
import quorum.Libraries.Game.Graphics.VertexAttributes;
import quorum.Libraries.Game.Graphics.Attributes;
import quorum.Libraries.Game.Graphics.Renderable;
import quorum.Libraries.Game.Graphics.Renderable_;
//import com.badlogic.gdx.graphics.g3d.Shader;              // Already in package
//import com.badlogic.gdx.graphics.g3d.utils.RenderContext; // Already in package
import quorum.Libraries.Game.Graphics.TextureDescriptor;
import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;
import quorum.Libraries.Compute.Matrix3;
import quorum.Libraries.Compute.Matrix4; // COMMENTED OUT DUE TO PARSING ERROR
import quorum.Libraries.Compute.Vector2;
import quorum.Libraries.Compute.Vector3;
import plugins.quorum.Libraries.Game.libGDX.Array;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.libGDX.IntArray;
import plugins.quorum.Libraries.Game.libGDX.IntIntMap;
import quorum.Libraries.Game.Graphics.VertexData_;

public abstract class BaseShader implements Shader 
{
    public interface Validator 
    {
        /** @return True if the input is valid for the renderable, false otherwise. */
        boolean Validate (final BaseShader shader, final int inputID, final Renderable renderable);
    }

    public interface Setter 
    {
        /** @return True if the uniform only has to be set once per render call, false if the uniform must be set for each renderable. */
        boolean IsGlobal(final BaseShader shader, final int inputID);

        void Set(final BaseShader shader, final int inputID, final Renderable renderable, final Attributes combinedAttributes);
    }

    public abstract static class GlobalSetter implements Setter 
    {
        @Override
        public boolean IsGlobal (final BaseShader shader, final int inputID) 
        {
            return true;
        }
    }

    public abstract static class LocalSetter implements Setter 
    {
        @Override
        public boolean IsGlobal (final BaseShader shader, final int inputID) 
        {
            return false;
        }
    }

    public static class Uniform implements Validator 
    {
        public final String alias;
        public final long materialMask;
        public final long environmentMask;
        public final long overallMask;

        public Uniform (final String alias, final long materialMask, final long environmentMask, final long overallMask) 
        {
            this.alias = alias;
            this.materialMask = materialMask;
            this.environmentMask = environmentMask;
            this.overallMask = overallMask;
        }

        public Uniform (final String alias, final long materialMask, final long environmentMask) 
        {
            this(alias, materialMask, environmentMask, 0);
        }

        public Uniform (final String alias, final long overallMask) 
        {
            this(alias, 0, 0, overallMask);
        }

        public Uniform (final String alias) 
        {
            this(alias, 0, 0);
        }

        @Override
        public boolean Validate (final BaseShader shader, final int inputID, final Renderable renderable) 
        {
            final long matFlags = (renderable != null && renderable.material != null) ? renderable.material.GetMask() : 0;
            final long envFlags = (renderable != null && renderable.environment != null) ? renderable.environment.GetMask() : 0;
            return ((matFlags & materialMask) == materialMask) && ((envFlags & environmentMask) == environmentMask)
                && (((matFlags | envFlags) & overallMask) == overallMask);
        }
    }

    private final Array<String> uniforms = new Array<String>();
    private final Array<Validator> validators = new Array<Validator>();
    private final Array<Setter> setters = new Array<Setter>();
    private int locations[];
    private final IntArray globalUniforms = new IntArray();
    private final IntArray localUniforms = new IntArray();
    private final IntIntMap attributes = new IntIntMap();

    public ShaderProgram program;
    public RenderContext context;
    public Camera camera;
    private Mesh currentMesh;
    
    private Attributes combinedAttributes = new Attributes();
    private final IntArray tempArray = new IntArray();

    /** Register an uniform which might be used by this shader. Only possible prior to the call to init().
     * @return The ID of the uniform to use in this shader. */
    public int Register (final String alias, final Validator validator, final Setter setter) 
    {
        if (locations != null) 
            throw new GameRuntimeError("Cannot register an uniform after initialization");

        final int existing = GetUniformID(alias);
        if (existing >= 0) 
        {
            validators.set(existing, validator);
            setters.set(existing, setter);
            return existing;
        }
        
        uniforms.add(alias);
        validators.add(validator);
        setters.add(setter);
        return uniforms.size - 1;
    }

    public int Register (final String alias, final Validator validator) 
    {
        return Register(alias, validator, null);
    }

    public int Register (final String alias, final Setter setter) 
    {
        return Register(alias, null, setter);
    }

    public int Register (final String alias) 
    {
        return Register(alias, null, null);
    }

    public int Register (final Uniform uniform, final Setter setter) 
    {
        return Register(uniform.alias, uniform, setter);
    }

    public int Register (final Uniform uniform) 
    {
        return Register(uniform, null);
    }

    /** @return the ID of the input or negative if not available. */
    public int GetUniformID (final String alias) 
    {
        final int n = uniforms.size;
        for (int i = 0; i < n; i++)
                if (uniforms.get(i).equals(alias)) 
                    return i;
        
        return -1;
    }

    /** @return The input at the specified id. */
    public String GetUniformAlias (final int id) 
    {
        return uniforms.get(id);
    }

    /** Initialize this shader, causing all registered uniforms/attributes to be fetched. */
    public void Initialize(final ShaderProgram program, final Renderable renderable) 
    {
        if (locations != null)
            throw new GameRuntimeError("Already initialized");
        if (!program.isCompiled())
            throw new GameRuntimeError(program.getLog());
            
        this.program = program;

        final int n = uniforms.size;
        locations = new int[n];
        
        for (int i = 0; i < n; i++) 
        {
            final String input = uniforms.get(i);
            final Validator validator = validators.get(i);
            final Setter setter = setters.get(i);
        
            if (validator != null && !validator.Validate(this, i, renderable))
                locations[i] = -1;
            else 
            {
                locations[i] = program.fetchUniformLocation(input, false);
            
                if (locations[i] >= 0 && setter != null) 
                {
                    if (setter.IsGlobal(this, i))
                        globalUniforms.add(i);
                    else
                        localUniforms.add(i);
                }
            }
            if (locations[i] < 0) 
            {
                validators.set(i, null);
                setters.set(i, null);
            }
        }
        
        if (renderable != null) 
        {
            final VertexAttributes attrs = (VertexAttributes)((MeshPart)renderable.meshPart).mesh.GetVertexAttributes();
            final int c = attrs.GetSize();
            
            for (int i = 0; i < c; i++) 
            {
                final VertexAttribute attr = (VertexAttribute)attrs.GetAttribute(i);
                final int location = program.getAttributeLocation(attr.alias);
                if (location >= 0)
                    attributes.put(attr.GetKey(), location);
            }
        }
    }

    @Override
    public void Begin(Camera_ camera, RenderContext context) 
    {
        this.camera = (Camera)camera;
        this.context = context;
        program.begin();
        currentMesh = null;

        for (int u, i = 0; i < globalUniforms.size; ++i)
            if (setters.get(u = globalUniforms.get(i)) != null)
                setters.get(u).Set(this, u, null, null);
    }

    private final int[] GetAttributeLocations(final VertexAttributes attrs) 
    {
        tempArray.clear();
        final int n = attrs.GetSize();
        for (int i = 0; i < n; i++) 
        {
            tempArray.add(attributes.get(attrs.GetAttribute(i).GetKey(), -1));
        }

        return tempArray.items;
    }

    @Override
    public void Render (Renderable_ param) 
    {
        Renderable renderable = (Renderable)param;
        if (renderable.worldTransform.Determinant3x3() == 0)
            return;
        
        combinedAttributes.Empty();
        
        if (renderable.environment != null)
            combinedAttributes.Add(renderable.environment);
        if (renderable.material != null)
            combinedAttributes.Add(renderable.material);

        Render(renderable, combinedAttributes);
    }

    public void Render (Renderable renderable, final Attributes combinedAttributes) 
    {
        for (int u, i = 0; i < localUniforms.size; ++i)
            if (setters.get(u = localUniforms.get(i)) != null)
                setters.get(u).Set(this, u, renderable, combinedAttributes);
            
            if (currentMesh != ((Mesh)((MeshPart)renderable.meshPart).mesh))
            {
                if (currentMesh != null)
                    currentMesh.plugin_.Unbind(program, tempArray.items);
                
                currentMesh = ((Mesh)((MeshPart)renderable.meshPart).mesh);
                
                int[] temp = GetAttributeLocations((VertexAttributes)renderable.meshPart.Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_().GetVertexAttributes());
                currentMesh.plugin_.Bind(program, temp);
            }

            MeshPart meshPart = ((MeshPart)renderable.meshPart);
            ((Mesh)meshPart.mesh).plugin_.Render(program, meshPart.primitiveType, meshPart.indexOffset, meshPart.verticesCount, false);            
    }

    @Override
    public void End () 
    {
        if (currentMesh != null) 
        {
            currentMesh.plugin_.Unbind(program, tempArray.items);
            currentMesh = null;
        }
        program.end();
    }

    @Override
    public void Dispose () 
    {
        program = null;
        uniforms.clear();
        validators.clear();
        setters.clear();
        localUniforms.clear();
        globalUniforms.clear();
        locations = null;
    }

    /** Whether this Shader instance implements the specified uniform, only valid after a call to init(). */
    public final boolean Has (final int inputID) 
    {
        return inputID >= 0 && inputID < locations.length && locations[inputID] >= 0;
    }

    public final int Location (final int inputID) 
    {
        return (inputID >= 0 && inputID < locations.length) ? locations[inputID] : -1;
    }

    /* FIX ME:
    public final boolean Set (final int uniform, final Matrix4 value) 
    {
        if (locations[uniform] < 0)
            return false;

        program.setUniformMatrix(locations[uniform], value);
        return true;
    }
    */

    /* FIX ME:
    public final boolean set (final int uniform, final Matrix3 value) {
            if (locations[uniform] < 0) return false;
            program.setUniformMatrix(locations[uniform], value);
            return true;
    }
    */

    /* FIX ME:
    public final boolean set (final int uniform, final Vector3 value) {
            if (locations[uniform] < 0) return false;
            program.setUniformf(locations[uniform], value);
            return true;
    }
    */

    /* FIX ME:
    public final boolean set (final int uniform, final Vector2 value) {
            if (locations[uniform] < 0) return false;
            program.setUniformf(locations[uniform], value);
            return true;
    }
    */

    /* FIX ME:
    public final boolean set (final int uniform, final Color value) {
            if (locations[uniform] < 0) return false;
            program.setUniformf(locations[uniform], value);
            return true;
    }
    */

    public final boolean Set(final int uniform, final float value) 
    {
        if (locations[uniform] < 0)
            return false;

        program.setUniformf(locations[uniform], value);
        return true;
    }

    public final boolean Set(final int uniform, final float v1, final float v2) 
    {
        if (locations[uniform] < 0)
            return false;

        program.setUniformf(locations[uniform], v1, v2);
        return true;
    }

    public final boolean Set(final int uniform, final float v1, final float v2, final float v3) 
    {
        if (locations[uniform] < 0)
            return false;

        program.setUniformf(locations[uniform], v1, v2, v3);
        return true;
    }

    public final boolean Set(final int uniform, final float v1, final float v2, final float v3, final float v4) 
    {
        if (locations[uniform] < 0) 
            return false;
        program.setUniformf(locations[uniform], v1, v2, v3, v4);
        return true;
    }

    public final boolean Set(final int uniform, final int value) 
    {
        if (locations[uniform] < 0)
            return false;
        
        program.setUniformi(locations[uniform], value);
        return true;
    }

    public final boolean Set(final int uniform, final int v1, final int v2) 
    {
        if (locations[uniform] < 0)
            return false;
            
        program.setUniformi(locations[uniform], v1, v2);
        return true;
    }

    public final boolean Set(final int uniform, final int v1, final int v2, final int v3) 
    {
        if (locations[uniform] < 0)
            return false;
            
        program.setUniformi(locations[uniform], v1, v2, v3);
        return true;
    }

    public final boolean Set(final int uniform, final int v1, final int v2, final int v3, final int v4) 
    {
        if (locations[uniform] < 0)
            return false;
            
        program.setUniformi(locations[uniform], v1, v2, v3, v4);
        return true;
    }

    /* FIX ME:
    public final boolean set (final int uniform, final TextureDescriptor textureDesc) {
            if (locations[uniform] < 0) return false;
            program.setUniformi(locations[uniform], context.textureBinder.bind(textureDesc));
            return true;
    }
    */

    /* FIX ME:
    public final boolean set (final int uniform, final GLTexture texture) {
            if (locations[uniform] < 0) return false;
            program.setUniformi(locations[uniform], context.textureBinder.bind(texture));
            return true;
    }
    */
}