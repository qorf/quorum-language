/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameStateManager;
//import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;
import quorum.Libraries.Compute.Matrix3;
import quorum.Libraries.Compute.Matrix3_;
import quorum.Libraries.Compute.Matrix4;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Compute.Vector3;
import quorum.Libraries.Game.Graphics.AmbientCubemap;
import quorum.Libraries.Game.Graphics.Attributes;
import quorum.Libraries.Game.Graphics.BlendingAttribute;
import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.ColorAttribute;
import quorum.Libraries.Game.Graphics.NumberAttribute;
import quorum.Libraries.Game.Graphics.IntegerAttribute;
import quorum.Libraries.Game.Graphics.DepthTestAttribute;
import quorum.Libraries.Game.Graphics.DirectionalLight;
import quorum.Libraries.Game.Graphics.DirectionalLightsAttribute;
import quorum.Libraries.Game.Graphics.Environment_;
import quorum.Libraries.Game.Graphics.OrthographicCamera_;
import quorum.Libraries.Game.Graphics.PointLight_;
import quorum.Libraries.Game.Graphics.PointLightsAttribute;
import quorum.Libraries.Game.Graphics.Renderable_;
import quorum.Libraries.Game.Graphics.TextureAttribute;
import quorum.Libraries.Game.Graphics.VertexAttribute;
import quorum.Libraries.Game.Graphics.VertexAttributes;

public class DefaultShader extends BaseShader 
{
    public static class Config 
    {
        /** The uber vertex shader to use, null to use the default vertex shader. */
        public String vertexShader = null;
        /** The uber fragment shader to use, null to use the default fragment shader. */
        public String fragmentShader = null;
        /** The number of directional lights to use */
        public int numDirectionalLights = 2;
        /** The number of point lights to use */
        public int numPointLights = 5;
        /** The number of spot lights to use */
        public int numSpotLights = 0;
        /** The number of bones to use */
        public int numBones = 12;
        /** */
        public boolean ignoreUnimplemented = true;
        /** Set to 0 to disable culling, -1 to inherit from {@link DefaultShader#defaultCullFace} */
        public int defaultCullFace = -1;
        /** Set to 0 to disable depth test, -1 to inherit from {@link DefaultShader#defaultDepthFunc} */
        public int defaultDepthFunc = -1;

		
        public Config () 
        {
	
        }

        public Config (final String vertexShader, final String fragmentShader) 
        {
            this.vertexShader = vertexShader;
            this.fragmentShader = fragmentShader;
        }
	
    }

    public static class Inputs 
    {
        public final static Uniform projTrans = new Uniform("u_projTrans");
        public final static Uniform viewTrans = new Uniform("u_viewTrans");
        public final static Uniform projViewTrans = new Uniform("u_projViewTrans");
        public final static Uniform cameraPosition = new Uniform("u_cameraPosition");
        public final static Uniform cameraDirection = new Uniform("u_cameraDirection");
        public final static Uniform cameraUp = new Uniform("u_cameraUp");

        public final static Uniform worldTrans = new Uniform("u_worldTrans");
        public final static Uniform viewWorldTrans = new Uniform("u_viewWorldTrans");
        public final static Uniform projViewWorldTrans = new Uniform("u_projViewWorldTrans");
        public final static Uniform normalMatrix = new Uniform("u_normalMatrix");
        public final static Uniform bones = new Uniform("u_bones");

        public final static Uniform shininess;
        public final static Uniform opacity;
        public final static Uniform diffuseColor;
        public final static Uniform diffuseTexture;
        public final static Uniform diffuseUVTransform;
        public final static Uniform specularColor;
        public final static Uniform specularTexture;
        public final static Uniform specularUVTransform;
        public final static Uniform emissiveColor;
        public final static Uniform emissiveTexture;
        public final static Uniform emissiveUVTransform;
        public final static Uniform reflectionColor;
        public final static Uniform reflectionTexture;
        public final static Uniform reflectionUVTransform;
        public final static Uniform normalTexture;
        public final static Uniform normalUVTransform;
        public final static Uniform ambientTexture;
        public final static Uniform ambientUVTransform;
        public final static Uniform alphaTest = new Uniform("u_alphaTest");

        public final static Uniform ambientCube = new Uniform("u_ambientCubemap");
        public final static Uniform dirLights = new Uniform("u_dirLights");
        public final static Uniform pointLights = new Uniform("u_pointLights");
        public final static Uniform spotLights = new Uniform("u_spotLights");
        public final static Uniform environmentCubemap = new Uniform("u_environmentCubemap");
        
        static
        {
            ColorAttribute color = new ColorAttribute();
            TextureAttribute texture = new TextureAttribute();
            
            shininess = new Uniform("u_shininess", new NumberAttribute().GetShininessValue());
            opacity = new Uniform("u_opacity", new BlendingAttribute().GetBlendedValue());
            diffuseColor = new Uniform("u_diffuseColor", color.GetDiffuseValue());
            diffuseTexture = new Uniform("u_diffuseTexture", texture.GetDiffuseValue());
            diffuseUVTransform = new Uniform("u_diffuseUVTransform", texture.GetDiffuseValue());
            specularColor = new Uniform("u_specularColor", color.GetSpecularValue());
            specularTexture = new Uniform("u_specularTexture", texture.GetSpecularValue());
            specularUVTransform = new Uniform("u_specularUVTransform", texture.GetSpecularValue());
            emissiveColor = new Uniform("u_emissiveColor", color.GetEmissiveValue());
            emissiveTexture = new Uniform("u_emissiveTexture", texture.GetEmissiveValue());
            emissiveUVTransform = new Uniform("u_emissiveUVTransform", texture.GetEmissiveValue());
            reflectionColor = new Uniform("u_reflectionColor", color.GetReflectionValue());
            reflectionTexture = new Uniform("u_reflectionTexture", texture.GetReflectionValue());
            reflectionUVTransform = new Uniform("u_reflectionUVTransform", texture.GetReflectionValue());
            normalTexture = new Uniform("u_normalTexture", texture.GetNormalValue());
            normalUVTransform = new Uniform("u_normalUVTransform", texture.GetNormalValue());
            ambientTexture = new Uniform("u_ambientTexture", texture.GetAmbientValue());
            ambientUVTransform = new Uniform("u_ambientUVTransform", texture.GetAmbientValue());
        }
    }

	public static class Setters 
        {
            public final static Setter projTrans = new GlobalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, shader.camera.Get_Libraries_Game_Graphics_Camera__projection_());
                }
            };

            public final static Setter viewTrans = new GlobalSetter() 
            {
                private final Matrix4_ matrix = new Matrix4();
                
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    //shader.Set(inputID, shader.camera.Get_Libraries_Game_Graphics_Camera__view_());
                    
                    matrix.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__view_());
                    DefaultShader.InvertViewZ(matrix);
                    shader.Set(inputID, matrix);
                }
            };
            
            public final static Setter projViewTrans = new GlobalSetter() 
            {
                private final Matrix4_ matrix = new Matrix4();
                
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    //shader.Set(inputID, shader.camera.Get_Libraries_Game_Graphics_Camera__combined_());
                    
                    matrix.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__combined_());
                    DefaultShader.InvertViewZ(matrix);
                    shader.Set(inputID, matrix);
                }
            };
            
            public final static Setter cameraPosition = new GlobalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, (float)shader.camera.Get_Libraries_Game_Graphics_Camera__position_().GetX(),
                        (float)shader.camera.Get_Libraries_Game_Graphics_Camera__position_().GetY(), 
                        (float)shader.camera.Get_Libraries_Game_Graphics_Camera__position_().GetZ(),
                        1.1881f / (float)(shader.camera.Get_Libraries_Game_Graphics_Camera__far_() * shader.camera.Get_Libraries_Game_Graphics_Camera__far_()));
                }
            };
            
            public final static Setter cameraDirection = new GlobalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, shader.camera.Get_Libraries_Game_Graphics_Camera__direction_());
                }
            };
                
            public final static Setter cameraUp = new GlobalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, shader.camera.Get_Libraries_Game_Graphics_Camera__up_());
                }
            };
                
            public final static Setter worldTrans = new LocalSetter() 
            {
                private final Matrix4_ matrix = new Matrix4();
                
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    //shader.Set(inputID, renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    
                    matrix.Set(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    DefaultShader.InvertPositionZ(matrix);
                    
//                    System.out.println("worldTrans:");
//                    OutputMatrix4(matrix);
                    
                    shader.Set(inputID, matrix);
                }
            };
            
            public final static Setter viewWorldTrans = new LocalSetter() 
            {
                final Matrix4_ temp = new Matrix4();
                final Matrix4_ temp2 = new Matrix4();

                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    // temp.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__view_());
                    // temp.Multiply(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    // shader.Set(inputID, temp);
                    
                    temp.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__view_());
                    temp2.Set(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    DefaultShader.InvertViewZ(temp);
                    DefaultShader.InvertPositionZ(temp2);
                    temp.Multiply(temp2);
                    shader.Set(inputID, temp);
                }
            };
            
            public final static Setter projViewWorldTrans = new LocalSetter() 
            {
                final Matrix4_ temp = new Matrix4();
                final Matrix4_ temp2 = new Matrix4();

                @Override
                public void Set (BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    //temp.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__combined_());
                    //temp.Multiply(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    //shader.Set(inputID, temp)
                    
                    temp.Set(shader.camera.Get_Libraries_Game_Graphics_Camera__combined_());
                    temp2.Set(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    DefaultShader.InvertViewZ(temp);
                    DefaultShader.InvertPositionZ(temp2);
                    temp.Multiply(temp2);
                    shader.Set(inputID, temp);
                }
            };
            
            public final static Setter normalMatrix = new LocalSetter() 
            {
                private final Matrix3_ temp = new quorum.Libraries.Compute.Matrix3();

                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    temp.Set(renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_());
                    temp.Inverse();
                    temp.Transpose();
                    shader.Set(inputID, temp);
                }
            };

            public static class Bones extends LocalSetter 
            {
                private final static Matrix4_ idtMatrix = new Matrix4();
                public final float bones[];

                public Bones (final int numBones) 
                {
                    this.bones = new float[numBones * 16];
                }

                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    for (int i = 0; i < bones.length; i++) 
                    {
                        
                        final int idx = i / 16;
                        bones[i] = (renderable.Get_Libraries_Game_Graphics_Renderable__bones_() == null
                            || idx >= renderable.Get_Libraries_Game_Graphics_Renderable__bones_().GetSize() 
                            || renderable.Get_Libraries_Game_Graphics_Renderable__bones_().Get(idx) == null)
                            ? GetMatrixValue(idtMatrix, i % 16)
                            : GetMatrixValue(((Matrix4_)renderable.Get_Libraries_Game_Graphics_Renderable__bones_().Get(idx)), i % 16);
                        
                    }
                    shader.program.SetUniformMatrix4(shader.Location(inputID), bones, 0, bones.length);
                }
            }

            public final static Setter shininess = new LocalSetter() 
            {   
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, (float)((NumberAttribute)(combinedAttributes.GetAttribute(numberAttribute.GetShininessValue()))).value);
                }
            };
                
            public final static Setter diffuseColor = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, ((ColorAttribute)(combinedAttributes.GetAttribute(colorAttribute.GetDiffuseValue()))).color);
                }
            };
            
            public final static Setter diffuseTexture = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetDiffuseValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
                
            public final static Setter diffuseUVTransform = new LocalSetter() 
            {   
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetDiffuseValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };
                
            public final static Setter specularColor = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, ((ColorAttribute)(combinedAttributes.GetAttribute(colorAttribute.GetSpecularValue()))).color);
                }
            };
            
            public final static Setter specularTexture = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetSpecularValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
                
            public final static Setter specularUVTransform = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetSpecularValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };
                
            public final static Setter emissiveColor = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, ((ColorAttribute)(combinedAttributes.GetAttribute(colorAttribute.GetEmissiveValue()))).color);
                }
            };
            
            public final static Setter emissiveTexture = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetEmissiveValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
            
            public final static Setter emissiveUVTransform = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetEmissiveValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };
            
            public final static Setter reflectionColor = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    shader.Set(inputID, ((ColorAttribute)(combinedAttributes.GetAttribute(colorAttribute.GetReflectionValue()))).color);
                }
            };
		
            public final static Setter reflectionTexture = new LocalSetter() 
            {
                @Override
                public void Set (BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetReflectionValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
		
            public final static Setter reflectionUVTransform = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetReflectionValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };
		
            public final static Setter normalTexture = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetNormalValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
		
            public final static Setter normalUVTransform = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetNormalValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };
		
            public final static Setter ambientTexture = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final int unit = shader.context.textureBinder.Bind(((TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetAmbientValue()))).descriptor);
                    shader.Set(inputID, unit);
                }
            };
            
            public final static Setter ambientUVTransform = new LocalSetter() 
            {
                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    final TextureAttribute ta = (TextureAttribute)(combinedAttributes.GetAttribute(textureAttribute.GetAmbientValue()));
                    shader.Set(inputID, (float)ta.offsetU, (float)ta.offsetV, (float)ta.scaleU, (float)ta.scaleV);
                }
            };

            public static class ACubemap extends LocalSetter 
            {
                private final static float ones[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
                private final AmbientCubemap cacheAmbientCubemap = new AmbientCubemap();
                private static Vector3 tmpV1;
                public final int dirLightsOffset;
                public final int pointLightsOffset;
                private static DirectionalLightsAttribute directionalLightsAttribute = new DirectionalLightsAttribute();
                private static PointLightsAttribute pointLightsAttribute = new PointLightsAttribute();
                
                public ACubemap (final int dirLightsOffset, final int pointLightsOffset) 
                {
                    this.dirLightsOffset = dirLightsOffset;
                    this.pointLightsOffset = pointLightsOffset;
                }

                @Override
                public void Set(BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    if (renderable.Get_Libraries_Game_Graphics_Renderable__environment_() == null || !renderable.Get_Libraries_Game_Graphics_Renderable__environment_().ContainsLighting())
                        shader.program.SetUniformVector3(shader.Location(inputID), ones, 0, ones.length);
                    else 
                    {
                        tmpV1 = (Vector3)renderable.Get_Libraries_Game_Graphics_Renderable__worldTransform_().GetTranslation();
                        tmpV1.Scale(1, 1, -1);
                        if (combinedAttributes.HasAttribute(colorAttribute.GetAmbientLightValue()))
                            cacheAmbientCubemap.SetColor(((ColorAttribute)combinedAttributes.GetAttribute(colorAttribute.GetAmbientLightValue())).color);

                        if (combinedAttributes.HasAttribute(directionalLightsAttribute.GetDirectionalLightsValue())) 
                        {
                            quorum.Libraries.Containers.Array_ lights = ((DirectionalLightsAttribute)combinedAttributes
                                .GetAttribute(directionalLightsAttribute.GetDirectionalLightsValue())).lights;
                            for (int i = dirLightsOffset; i < lights.GetSize(); i++)
                                cacheAmbientCubemap.Add(((DirectionalLight)lights.Get(i)).GetColor(),
                                    ((DirectionalLight)lights.Get(i)).direction);
                        }

                        if (combinedAttributes.HasAttribute(pointLightsAttribute.GetPointLightsValue())) 
                        {
                            quorum.Libraries.Containers.Array_ lights = ((PointLightsAttribute)combinedAttributes.GetAttribute(pointLightsAttribute.GetPointLightsValue())).lights;
                            for (int i = pointLightsOffset; i < lights.GetSize(); i++)
                                cacheAmbientCubemap.Add(((PointLight_)lights.Get(i)).GetColor(), ((PointLight_)lights.Get(i)).GetGlobalPosition(),
                                    tmpV1, ((PointLight_)lights.Get(i)).Get_Libraries_Game_Graphics_PointLight__intensity_());
                        }
                        
                        cacheAmbientCubemap.Clamp();
                        float[] temp = new float[cacheAmbientCubemap.data.GetSize()];
                        for (int i = 0; i < temp.length; i++)
                        {
                            temp[i] = (float)cacheAmbientCubemap.GetDataAtIndex(i);
                        }
                        
                        shader.program.SetUniformVector3(shader.Location(inputID), temp, 0, temp.length);
                    }
                }
            }
            
            /*
            public final static Setter environmentCubemap = new LocalSetter() 
            {
                @Override
                public void Set (BaseShader shader, int inputID, Renderable_ renderable, Attributes combinedAttributes) 
                {
                    if (combinedAttributes.HasAttribute(CubemapAttribute.EnvironmentMap)) 
                    {
                        shader.Set(inputID, shader.context.textureBinder.bind(((CubemapAttribute)combinedAttributes
                            .get(CubemapAttribute.EnvironmentMap)).textureDescription));
                    }
                }
            };
            */
	}

	private static String defaultVertexShader = null;

	public static String GetDefaultVertexShader () 
        {
            if (defaultVertexShader == null)
                defaultVertexShader = DefaultGLSLStrings.GetDefaultVertexShader();
            return defaultVertexShader;
	}

	private static String defaultFragmentShader = null;

	public static String GetDefaultFragmentShader () 
        {
            if (defaultFragmentShader == null)
                defaultFragmentShader = DefaultGLSLStrings.GetDefaultFragmentShader();
            return defaultFragmentShader;
	}

	protected static long implementedFlags;
        
	/** @deprecated Replaced by {@link Config#defaultCullFace} Set to 0 to disable culling */
	@Deprecated public static int defaultCullFace = GraphicsManager.GL_BACK;
	/** @deprecated Replaced by {@link Config#defaultDepthFunc} Set to 0 to disable depth test */
	@Deprecated public static int defaultDepthFunc = GraphicsManager.GL_LEQUAL;
        
	// Global uniforms
	public final int u_projTrans;
	public final int u_viewTrans;
	public final int u_projViewTrans;
	public final int u_cameraPosition;
	public final int u_cameraDirection;
	public final int u_cameraUp;
	public final int u_time;
	// Object uniforms
	public final int u_worldTrans;
	public final int u_viewWorldTrans;
	public final int u_projViewWorldTrans;
	public final int u_normalMatrix;
	public final int u_bones;
	// Material uniforms
	public final int u_shininess;
	public final int u_opacity;
	public final int u_diffuseColor;
	public final int u_diffuseTexture;
	public final int u_diffuseUVTransform;
	public final int u_specularColor;
	public final int u_specularTexture;
	public final int u_specularUVTransform;
	public final int u_emissiveColor;
	public final int u_emissiveTexture;
	public final int u_emissiveUVTransform;
	public final int u_reflectionColor;
	public final int u_reflectionTexture;
	public final int u_reflectionUVTransform;
	public final int u_normalTexture;
	public final int u_normalUVTransform;
	public final int u_ambientTexture;
	public final int u_ambientUVTransform;
	public final int u_alphaTest;
	// Lighting uniforms
	protected final int u_ambientCubemap;
	//protected final int u_environmentCubemap;
	protected final int u_dirLights0color = Register(new Uniform("u_dirLights[0].color"));
	protected final int u_dirLights0direction = Register(new Uniform("u_dirLights[0].direction"));
	protected final int u_dirLights1color = Register(new Uniform("u_dirLights[1].color"));
	protected final int u_pointLights0color = Register(new Uniform("u_pointLights[0].color"));
	protected final int u_pointLights0position = Register(new Uniform("u_pointLights[0].position"));
	protected final int u_pointLights0intensity = Register(new Uniform("u_pointLights[0].intensity"));
	protected final int u_pointLights1color = Register(new Uniform("u_pointLights[1].color"));
	protected final int u_spotLights0color = Register(new Uniform("u_spotLights[0].color"));
	protected final int u_spotLights0position = Register(new Uniform("u_spotLights[0].position"));
	protected final int u_spotLights0intensity = Register(new Uniform("u_spotLights[0].intensity"));
	protected final int u_spotLights0direction = Register(new Uniform("u_spotLights[0].direction"));
	protected final int u_spotLights0cutoffAngle = Register(new Uniform("u_spotLights[0].cutoffAngle"));
	protected final int u_spotLights0exponent = Register(new Uniform("u_spotLights[0].exponent"));
	protected final int u_spotLights1color = Register(new Uniform("u_spotLights[1].color"));
	protected final int u_fogColor = Register(new Uniform("u_fogColor"));
	protected final int u_shadowMapProjViewTrans = Register(new Uniform("u_shadowMapProjViewTrans"));
	protected final int u_shadowTexture = Register(new Uniform("u_shadowTexture"));
	protected final int u_shadowPCFOffset = Register(new Uniform("u_shadowPCFOffset"));
        // FIXME Cache vertex attribute locations...

	protected int dirLightsLoc;
	protected int dirLightsColorOffset;
	protected int dirLightsDirectionOffset;
	protected int dirLightsSize;
	protected int pointLightsLoc;
	protected int pointLightsColorOffset;
	protected int pointLightsPositionOffset;
	protected int pointLightsIntensityOffset;
	protected int pointLightsSize;
	protected int spotLightsLoc;
	protected int spotLightsColorOffset;
	protected int spotLightsPositionOffset;
	protected int spotLightsDirectionOffset;
	protected int spotLightsIntensityOffset;
	protected int spotLightsCutoffAngleOffset;
	protected int spotLightsExponentOffset;
	protected int spotLightsSize;

	protected final boolean lighting;
	//protected final boolean environmentCubemap;
	//protected final boolean shadowMap;
	protected final AmbientCubemap ambientCubemap = new AmbientCubemap();
	protected final DirectionalLight directionalLights[];
	protected final PointLight_ pointLights[];
	//protected final SpotLight spotLights[];
        
	/** The renderable used to create this shader, invalid after the call to init */
	private Renderable_ renderable;
	/** The attributes that this shader supports */
	protected final long attributesMask;
	private long vertexMask;
	protected final Config config;
        
        /** Attributes which are not required but always supported. */
	private final static long optionalAttributes;
        
        public final static BlendingAttribute blendingAttribute = new BlendingAttribute();
        public final static TextureAttribute textureAttribute = new TextureAttribute();
        public final static ColorAttribute colorAttribute = new ColorAttribute();
        public final static NumberAttribute numberAttribute = new NumberAttribute();
        public final static IntegerAttribute integerAttribute = new IntegerAttribute();
        public final static DepthTestAttribute depthTestAttribute = new DepthTestAttribute();
        public final static PointLightsAttribute pointLightsAttribute = new PointLightsAttribute();
        public final static DirectionalLightsAttribute directionalLightsAttribute = new DirectionalLightsAttribute();
        
        private final static Attributes tmpAttributes = new Attributes();
        private final static VertexAttributes usage = new VertexAttributes();
        
        private Matrix3 normalMatrix = new Matrix3();
	private Camera_ camera;
	private float time;
	private boolean lightsSet;
        private final Vector3 tmpV1 = new Vector3();
        
        
        static
        {   
            implementedFlags = blendingAttribute.GetBlendedValue() | textureAttribute.GetDiffuseValue()
                | colorAttribute.GetDiffuseValue() | colorAttribute.GetSpecularValue() 
                | numberAttribute.GetShininessValue();
            
            optionalAttributes = integerAttribute.GetCullFaceValue() | depthTestAttribute.GetDepthTestValue();
        }

	public DefaultShader (final Renderable_ renderable) 
        {
            this(renderable, new Config());
	}

	public DefaultShader (final Renderable_ renderable, final Config config) 
        {
            this(renderable, config, CreatePrefix(renderable, config));
	}

	public DefaultShader (final Renderable_ renderable, final Config config, final String prefix) 
        {
            this(renderable, config, prefix, config.vertexShader != null ? config.vertexShader : GetDefaultVertexShader(),
                config.fragmentShader != null ? config.fragmentShader : GetDefaultFragmentShader());
	}

	public DefaultShader (final Renderable_ renderable, final Config config, final String prefix, final String vertexShader, final String fragmentShader) 
        {
            this(renderable, config, new ShaderProgram(prefix + vertexShader, prefix + fragmentShader));
	}

	public DefaultShader (final Renderable_ renderable, final Config config, final ShaderProgram shaderProgram) 
        {
            final Attributes attributes = CombineAttributes(renderable);
            this.config = config;
            this.program = shaderProgram;
            this.lighting = renderable.Get_Libraries_Game_Graphics_Renderable__environment_() != null;
            /*
            this.environmentCubemap = attributes.has(CubemapAttribute.EnvironmentMap)
                    || (lighting && attributes.has(CubemapAttribute.EnvironmentMap));
            this.shadowMap = lighting && renderable.environment.shadowMap != null;
            */
            this.renderable = renderable;
            attributesMask = attributes.GetMask() | optionalAttributes;
            vertexMask = renderable.Get_Libraries_Game_Graphics_Renderable__meshPart_()
                    .Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_().GetVertexAttributes().GetMask();

            this.directionalLights = new DirectionalLight[lighting && config.numDirectionalLights > 0 ? config.numDirectionalLights : 0];
            for (int i = 0; i < directionalLights.length; i++)
                    directionalLights[i] = new DirectionalLight();
            this.pointLights = new PointLight_[lighting && config.numPointLights > 0 ? config.numPointLights : 0];
            for (int i = 0; i < pointLights.length; i++)
                    pointLights[i] = new quorum.Libraries.Game.Graphics.PointLight();
            /*
            this.spotLights = new SpotLight[lighting && config.numSpotLights > 0 ? config.numSpotLights : 0];
            for (int i = 0; i < spotLights.length; i++)
                    spotLights[i] = new SpotLight();
            */
            
            if (!config.ignoreUnimplemented && (implementedFlags & attributesMask) != attributesMask)
                    throw new GameRuntimeError("Some attributes not implemented yet (" + attributesMask + ")");

            // Global uniforms
            u_projTrans = Register(Inputs.projTrans, Setters.projTrans);
            u_viewTrans = Register(Inputs.viewTrans, Setters.viewTrans);
            u_projViewTrans = Register(Inputs.projViewTrans, Setters.projViewTrans);
            u_cameraPosition = Register(Inputs.cameraPosition, Setters.cameraPosition);
            u_cameraDirection = Register(Inputs.cameraDirection, Setters.cameraDirection);
            u_cameraUp = Register(Inputs.cameraUp, Setters.cameraUp);
            u_time = Register(new Uniform("u_time"));
            // Object uniforms
            u_worldTrans = Register(Inputs.worldTrans, Setters.worldTrans);
            u_viewWorldTrans = Register(Inputs.viewWorldTrans, Setters.viewWorldTrans);
            u_projViewWorldTrans = Register(Inputs.projViewWorldTrans, Setters.projViewWorldTrans);
            u_normalMatrix = Register(Inputs.normalMatrix, Setters.normalMatrix);
            u_bones = (renderable.Get_Libraries_Game_Graphics_Renderable__bones_() != null && config.numBones > 0)
                ? Register(Inputs.bones, new Setters.Bones(config.numBones)) : -1;

            u_shininess = Register(Inputs.shininess, Setters.shininess);
            u_opacity = Register(Inputs.opacity);
            u_diffuseColor = Register(Inputs.diffuseColor, Setters.diffuseColor);
            u_diffuseTexture = Register(Inputs.diffuseTexture, Setters.diffuseTexture);
            u_diffuseUVTransform = Register(Inputs.diffuseUVTransform, Setters.diffuseUVTransform);
            u_specularColor = Register(Inputs.specularColor, Setters.specularColor);
            u_specularTexture = Register(Inputs.specularTexture, Setters.specularTexture);
            u_specularUVTransform = Register(Inputs.specularUVTransform, Setters.specularUVTransform);
            u_emissiveColor = Register(Inputs.emissiveColor, Setters.emissiveColor);
            u_emissiveTexture = Register(Inputs.emissiveTexture, Setters.emissiveTexture);
            u_emissiveUVTransform = Register(Inputs.emissiveUVTransform, Setters.emissiveUVTransform);
            u_reflectionColor = Register(Inputs.reflectionColor, Setters.reflectionColor);
            u_reflectionTexture = Register(Inputs.reflectionTexture, Setters.reflectionTexture);
            u_reflectionUVTransform = Register(Inputs.reflectionUVTransform, Setters.reflectionUVTransform);
            u_normalTexture = Register(Inputs.normalTexture, Setters.normalTexture);
            u_normalUVTransform = Register(Inputs.normalUVTransform, Setters.normalUVTransform);
            u_ambientTexture = Register(Inputs.ambientTexture, Setters.ambientTexture);
            u_ambientUVTransform = Register(Inputs.ambientUVTransform, Setters.ambientUVTransform);
            u_alphaTest = Register(Inputs.alphaTest);

            u_ambientCubemap = lighting ? Register(Inputs.ambientCube, new Setters.ACubemap(config.numDirectionalLights,
                    config.numPointLights)) : -1;
            //u_environmentCubemap = environmentCubemap ? register(Inputs.environmentCubemap, Setters.environmentCubemap) : -1;
	}
        
	@Override
	public void Initialize() 
        {
            final ShaderProgram program = this.program;
            this.program = null;
            Initialize(program, renderable);
            renderable = null;

            dirLightsLoc = Location(u_dirLights0color);
            dirLightsColorOffset = Location(u_dirLights0color) - dirLightsLoc;
            dirLightsDirectionOffset = Location(u_dirLights0direction) - dirLightsLoc;
            dirLightsSize = Location(u_dirLights1color) - dirLightsLoc;
            if (dirLightsSize < 0) 
                dirLightsSize = 0;

            pointLightsLoc = Location(u_pointLights0color);
            pointLightsColorOffset = Location(u_pointLights0color) - pointLightsLoc;
            pointLightsPositionOffset = Location(u_pointLights0position) - pointLightsLoc;
            pointLightsIntensityOffset = Has(u_pointLights0intensity) ? Location(u_pointLights0intensity) - pointLightsLoc : -1;
            pointLightsSize = Location(u_pointLights1color) - pointLightsLoc;
            if (pointLightsSize < 0)
                pointLightsSize = 0;

            spotLightsLoc = Location(u_spotLights0color);
            spotLightsColorOffset = Location(u_spotLights0color) - spotLightsLoc;
            spotLightsPositionOffset = Location(u_spotLights0position) - spotLightsLoc;
            spotLightsDirectionOffset = Location(u_spotLights0direction) - spotLightsLoc;
            spotLightsIntensityOffset = Has(u_spotLights0intensity) ? Location(u_spotLights0intensity) - spotLightsLoc : -1;
            spotLightsCutoffAngleOffset = Location(u_spotLights0cutoffAngle) - spotLightsLoc;
            spotLightsExponentOffset = Location(u_spotLights0exponent) - spotLightsLoc;
            spotLightsSize = Location(u_spotLights1color) - spotLightsLoc;
            if (spotLightsSize < 0)
                spotLightsSize = 0;
	}

	private static final boolean And (final long mask, final long flag) {
		return (mask & flag) == flag;
	}

	private static final boolean Or (final long mask, final long flag) {
		return (mask & flag) != 0;
	}

	// TODO: Perhaps move responsibility for combining attributes to RenderableProvider?
	private static final Attributes CombineAttributes (final Renderable_ renderable) 
        {
            tmpAttributes.Empty();
            if (renderable.Get_Libraries_Game_Graphics_Renderable__environment_() != null)
                tmpAttributes.Add(renderable.Get_Libraries_Game_Graphics_Renderable__environment_());
            if (renderable.Get_Libraries_Game_Graphics_Renderable__material_() != null)
                tmpAttributes.Add(renderable.Get_Libraries_Game_Graphics_Renderable__material_());

            return tmpAttributes;
	}

	public static String CreatePrefix(final Renderable_ renderable, final Config config) 
        {   
            final Attributes attributes = CombineAttributes(renderable);
            String prefix = "";
            final long attributesMask = attributes.GetMask();
            final long vertexMask = renderable.Get_Libraries_Game_Graphics_Renderable__meshPart_()
                .Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_().GetVertexAttributes().GetMask();
            if (And(vertexMask, usage.POSITION))
                prefix += "#define positionFlag\n";
            if (Or(vertexMask, usage.COLOR_PACKED | usage.COLOR_UNPACKED))
                prefix += "#define colorFlag\n";
            if (And(vertexMask, usage.BINORMAL))
                prefix += "#define binormalFlag\n";
            if (And(vertexMask, usage.TANGENT))
                prefix += "#define tangentFlag\n";
            if (And(vertexMask, usage.NORMAL))
                prefix += "#define normalFlag\n";
            if (And(vertexMask, usage.NORMAL) || And(vertexMask, usage.TANGENT | usage.BINORMAL)) 
            {
                if (renderable.Get_Libraries_Game_Graphics_Renderable__environment_() != null) 
                {
                    prefix += "#define lightingFlag\n";
                    prefix += "#define ambientCubemapFlag\n";
                    prefix += "#define numDirectionalLights " + config.numDirectionalLights + "\n";
                    prefix += "#define numPointLights " + config.numPointLights + "\n";
                    prefix += "#define numSpotLights " + config.numSpotLights + "\n";
                    if (attributes.HasAttribute(colorAttribute.GetFogValue())) 
                    {
                        prefix += "#define fogFlag\n";
                    }
                    //if (renderable.Get_Libraries_Game_Graphics_Renderable__environment_().shadowMap != null) prefix += "#define shadowMapFlag\n";
                    //if (attributes.has(CubemapAttribute.EnvironmentMap)) prefix += "#define environmentCubemapFlag\n";
                }
            }
            
            final int n = renderable.Get_Libraries_Game_Graphics_Renderable__meshPart_().Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_()
                .GetVertexAttributes().GetSize();
            
            for (int i = 0; i < n; i++) 
            {
                final VertexAttribute attr = (VertexAttribute)renderable.Get_Libraries_Game_Graphics_Renderable__meshPart_()
                    .Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_().GetVertexAttributes().GetAttribute(i);
                if (attr.usage == usage.BONE_WEIGHT)
                    prefix += "#define boneWeight" + attr.unit + "Flag\n";
                else if (attr.usage == usage.TEXTURE_COORDINATES)
                    prefix += "#define texCoord" + attr.unit + "Flag\n";
            }
            
            if ((attributesMask & blendingAttribute.GetBlendedValue()) == blendingAttribute.GetBlendedValue())
                prefix += "#define " + blendingAttribute.ALIAS + "Flag\n";
            if ((attributesMask & textureAttribute.GetDiffuseValue()) == textureAttribute.GetDiffuseValue()) 
            {
                prefix += "#define " + textureAttribute.DIFFUSE_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.DIFFUSE_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & textureAttribute.GetSpecularValue()) == textureAttribute.GetSpecularValue()) 
            {
                prefix += "#define " + textureAttribute.SPECULAR_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.SPECULAR_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & textureAttribute.GetNormalValue()) == textureAttribute.GetNormalValue()) 
            {
                prefix += "#define " + textureAttribute.NORMAL_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.NORMAL_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & textureAttribute.GetEmissiveValue()) == textureAttribute.GetEmissiveValue()) 
            {
                prefix += "#define " + textureAttribute.EMISSIVE_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.EMISSIVE_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & textureAttribute.GetReflectionValue()) == textureAttribute.GetReflectionValue())
            {
                prefix += "#define " + textureAttribute.REFLECTION_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.REFLECTION_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & textureAttribute.GetAmbientValue()) == textureAttribute.GetAmbientValue()) 
            {
                prefix += "#define " + textureAttribute.AMBIENT_ALIAS + "Flag\n";
                prefix += "#define " + textureAttribute.AMBIENT_ALIAS + "Coord texCoord0\n"; // FIXME implement UV mapping
            }
            if ((attributesMask & colorAttribute.GetDiffuseValue()) == colorAttribute.GetDiffuseValue())
                prefix += "#define " + colorAttribute.DIFFUSE_ALIAS + "Flag\n";
            if ((attributesMask & colorAttribute.GetSpecularValue()) == colorAttribute.GetSpecularValue())
                prefix += "#define " + colorAttribute.SPECULAR_ALIAS + "Flag\n";
            if ((attributesMask & colorAttribute.GetEmissiveValue()) == colorAttribute.GetEmissiveValue())
                prefix += "#define " + colorAttribute.EMISSIVE_ALIAS + "Flag\n";
            if ((attributesMask & colorAttribute.GetReflectionValue()) == colorAttribute.GetReflectionValue())
                prefix += "#define " + colorAttribute.REFLECTION_ALIAS + "Flag\n";
            if ((attributesMask & numberAttribute.GetShininessValue()) == numberAttribute.GetShininessValue())
                prefix += "#define " + numberAttribute.SHININESS_ALIAS + "Flag\n";
            if ((attributesMask & numberAttribute.GetAlphaTestValue()) == numberAttribute.GetAlphaTestValue())
                prefix += "#define " + numberAttribute.ALPHA_TEST_ALIAS + "Flag\n";
            if (renderable.Get_Libraries_Game_Graphics_Renderable__bones_() != null && config.numBones > 0)
                prefix += "#define numBones " + config.numBones + "\n";
            
            return prefix;
	}

	@Override
	public boolean CanRender (final Renderable_ renderable) 
        {
            final Attributes attributes = CombineAttributes(renderable);
            return (attributesMask == (attributes.GetMask() | optionalAttributes)) && (vertexMask == renderable.
                Get_Libraries_Game_Graphics_Renderable__meshPart_().Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_()
                .GetVertexAttributes().GetMask()) && (renderable.Get_Libraries_Game_Graphics_Renderable__environment_() != null) == lighting;
	}

	@Override
	public int CompareTo(Shader other) 
        {
            if (other == null) 
                return -1;
            if (other == this) 
                return 0;
            
            return 0; // FIXME compare shaders on their impact on performance
	}

	@Override
	public boolean equals (Object obj)
        {
            return (obj instanceof DefaultShader) ? equals((DefaultShader)obj) : false;
	}

	public boolean equals (DefaultShader obj) 
        {
            return (obj == this);
	}
        
	@Override
	public void Begin(final Camera_ camera, final RenderContext context) 
        {
            super.Begin(camera, context);

            for (final DirectionalLight dirLight : directionalLights)
            {
                dirLight.SetColor(0, 0, 0, 1);
                dirLight.SetDirection(0, -1, 0);
            }
            for (final PointLight_ pointLight : pointLights)
            {
                pointLight.SetColor(0, 0, 0, 0);
                pointLight.SetPosition(0, 0, 0);
                pointLight.SetIntensity(0);
            }
            /*
            for (final SpotLight spotLight : spotLights)
                    spotLight.set(0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, 0);
            */
            lightsSet = false;

            if (Has(u_time))
                Set(u_time, time += (float)GameStateManager.display.GetSecondsBetweenFrames());
	}

	@Override
	public void Render(Renderable_ renderable, Attributes combinedAttributes) 
        {
            if (!combinedAttributes.HasAttribute(blendingAttribute.GetBlendedValue()))
                context.SetBlending(false, GraphicsManager.GL_SRC_ALPHA, GraphicsManager.GL_ONE_MINUS_SRC_ALPHA);
            BindMaterial(combinedAttributes);
            if (lighting)
                BindLights(renderable, combinedAttributes);

            super.Render(renderable, combinedAttributes);
	}

	@Override
	public void End () 
        {
            super.End();
	}
        
	protected void BindMaterial (final Attributes attributes) 
        {
            int cullFace = config.defaultCullFace == -1 ? defaultCullFace : config.defaultCullFace;
            int depthFunc = config.defaultDepthFunc == -1 ? defaultDepthFunc : config.defaultDepthFunc;
            float depthRangeNear = 0f;
            float depthRangeFar = 1f;
            boolean depthMask = true;

            quorum.Libraries.Containers.Array_ attributeArray = attributes.GetAttributeArray();
            
            for (int i = 0; i < attributeArray.GetSize(); i++)
            {
                quorum.Libraries.Game.Graphics.Attribute_ attr = (quorum.Libraries.Game.Graphics.Attribute_)attributeArray.Get(i);
                
                final long t = attr.Get_Libraries_Game_Graphics_Attribute__type_();
                if ((t & (long)blendingAttribute.GetBlendedValue()) == t) 
                {
                    context.SetBlending(true, ((BlendingAttribute)attr).sourceFunction, ((BlendingAttribute)attr).destFunction);
                    Set(u_opacity, (float)((BlendingAttribute)attr).opacity);
                }
                else if ((t & integerAttribute.GetCullFaceValue()) == integerAttribute.GetCullFaceValue())
                    cullFace = ((IntegerAttribute)attr).value;
                else if ((t & numberAttribute.GetAlphaTestValue()) == numberAttribute.GetAlphaTestValue())
                    Set(u_alphaTest, (float)((NumberAttribute)attr).value);
                else if ((t & depthTestAttribute.GetDepthTestValue()) == depthTestAttribute.GetDepthTestValue()) 
                {
                    DepthTestAttribute dta = (DepthTestAttribute)attr;
                    depthFunc = dta.depthFunction;
                    depthRangeNear = dta.depthRangeNear;
                    depthRangeFar = dta.depthRangeFar;
                    depthMask = dta.depthMask;
                } 
                else if (!config.ignoreUnimplemented)
                    throw new GameRuntimeError("Unknown material attribute: " + attr.toString());
            }

            context.SetCullFace(cullFace);
            context.SetDepthTest(depthFunc, depthRangeNear, depthRangeFar);
            context.SetDepthMask(depthMask);
	}

	protected void BindLights (final Renderable_ renderable, final Attributes attributes) 
        {
            final Environment_ lights = renderable.Get_Libraries_Game_Graphics_Renderable__environment_();
            final DirectionalLightsAttribute dla = (DirectionalLightsAttribute)attributes.GetAttribute(directionalLightsAttribute.GetDirectionalLightsValue());
            final quorum.Libraries.Containers.Array_ dirs = dla == null ? null : dla.lights;
            final PointLightsAttribute pla = (PointLightsAttribute)attributes.GetAttribute(pointLightsAttribute.GetPointLightsValue());
            final quorum.Libraries.Containers.Array_ points = pla == null ? null : pla.lights;
            /*
            final SpotLightsAttribute sla = attributes.get(SpotLightsAttribute.class, SpotLightsAttribute.Type);
            final Array<SpotLight> spots = sla == null ? null : sla.lights;
            */
                
            if (dirLightsLoc >= 0) 
            {
                for (int i = 0; i < directionalLights.length; i++) 
                {
                    if (dirs == null || i >= dirs.GetSize()) 
                    {
                        if (lightsSet && (float)directionalLights[i].GetColor().GetRed() == 0f 
                            && (float)directionalLights[i].GetColor().GetGreen() == 0f
                            && (float)directionalLights[i].GetColor().GetBlue() == 0f)
                            continue;
                        
                        directionalLights[i].GetColor().SetColor(0, 0, 0, 1);
                    } 
                    else if (lightsSet && directionalLights[i].equals(dirs.Get(i)))
                        continue;
                    else
                    {
                        DirectionalLight tempDir = (DirectionalLight)dirs.Get(i);
                        directionalLights[i].SetLight(tempDir.GetColor(), tempDir.GetDirection());
                    }
                    
                    int idx = dirLightsLoc + i * dirLightsSize;
                    
//                    System.out.println("\ndirectionalLight " + i);
//                    System.out.println("Color 1: " + (idx + dirLightsColorOffset));
//                    System.out.println("Color 2: " + directionalLights[i].GetColor().GetRed());
//                    System.out.println("Color 3: " + directionalLights[i].GetColor().GetGreen());
//                    System.out.println("Color 4: " + directionalLights[i].GetColor().GetBlue());
//
//                    System.out.println("\nDirection 1: " + (idx + dirLightsDirectionOffset));
//                    System.out.println("Direction 2: " + directionalLights[i].direction.GetX());
//                    System.out.println("Direction 3: " + directionalLights[i].direction.GetY());
//                    System.out.println("Direction 4: " + directionalLights[i].direction.GetZ());
                    
                    program.SetUniform(idx + dirLightsColorOffset, (float)directionalLights[i].GetColor().GetRed(), 
                        (float)directionalLights[i].GetColor().GetGreen(), (float)directionalLights[i].GetColor().GetBlue());
                    //program.setUniformf(idx + dirLightsDirectionOffset, (float)directionalLights[i].direction.GetX(),
                    //    (float)directionalLights[i].direction.GetY(), (float)directionalLights[i].direction.GetZ());
                    program.SetUniform(idx + dirLightsDirectionOffset, (float)directionalLights[i].direction.GetX(),
                        (float)directionalLights[i].direction.GetY(), (float)(directionalLights[i].direction.GetZ() * -1));
                    
                    if (dirLightsSize <= 0) 
                        break;
                }
            }

            if (pointLightsLoc >= 0) 
            {
                for (int i = 0; i < pointLights.length; i++) 
                {
                    if (points == null || i >= points.GetSize()) 
                    {
                        if (lightsSet && pointLights[i].GetIntensity() == 0) 
                            continue;
                        pointLights[i].SetIntensity(0);
                    }
                    else if (lightsSet && pointLights[i].equals(points.Get(i)))
                        continue;
                    else
                    {
                        PointLight_ tempPoint = (PointLight_)points.Get(i);
                        pointLights[i].SetLight(tempPoint.GetColor(), tempPoint.GetPosition(), tempPoint.GetIntensity());
                        pointLights[i].SetOffset(tempPoint.GetOffsetX(), tempPoint.GetOffsetY(), tempPoint.GetOffsetZ());
                    }
                    
                    int idx = pointLightsLoc + i * pointLightsSize;
                    
                    double intensity = pointLights[i].GetIntensity();
                    
                    program.SetUniform(idx + pointLightsColorOffset, (float)(pointLights[i].GetColor().GetRed() * intensity),
                        (float)(pointLights[i].GetColor().GetGreen() * intensity), (float)(pointLights[i].GetColor().GetBlue() * intensity));
                    //program.setUniformf(idx + pointLightsPositionOffset, (float)pointLights[i].GetGlobalX(), (float)pointLights[i].GetGlobalY(),
                    //    (float)pointLights[i].GetGlobalZ());
                    program.SetUniform(idx + pointLightsPositionOffset, (float)pointLights[i].GetGlobalX(), (float)pointLights[i].GetGlobalY(),
                       (float)pointLights[i].GetGlobalZ() * -1);
                    
                    if (pointLightsIntensityOffset >= 0)
                        program.SetUniform(idx + pointLightsIntensityOffset, (float)pointLights[i].GetIntensity());
                    
                    if (pointLightsSize <= 0) 
                        break;
                }
            }

            /*
            if (spotLightsLoc >= 0) 
            {
                for (int i = 0; i < spotLights.length; i++) 
                {
                    if (spots == null || i >= spots.size) 
                    {
                        if (lightsSet && spotLights[i].intensity == 0f) 
                            continue;
                        spotLights[i].intensity = 0f;
                    } 
                    else if (lightsSet && spotLights[i].equals(spots.get(i)))
                        continue;
                    else
                        spotLights[i].set(spots.get(i));

                    int idx = spotLightsLoc + i * spotLightsSize;
                    program.setUniformf(idx + spotLightsColorOffset, spotLights[i].color.r * spotLights[i].intensity,
                        spotLights[i].color.g * spotLights[i].intensity, spotLights[i].color.b * spotLights[i].intensity);
                    program.setUniformf(idx + spotLightsPositionOffset, spotLights[i].position);
                    program.setUniformf(idx + spotLightsDirectionOffset, spotLights[i].direction);
                    program.setUniformf(idx + spotLightsCutoffAngleOffset, spotLights[i].cutoffAngle);
                    program.setUniformf(idx + spotLightsExponentOffset, spotLights[i].exponent);
                    if (spotLightsIntensityOffset >= 0)
                        program.setUniformf(idx + spotLightsIntensityOffset, spotLights[i].intensity);
                    if (spotLightsSize <= 0) break;
                }
            }
            */

            if (attributes.HasAttribute(colorAttribute.GetFogValue())) 
            {
                Set(u_fogColor, ((ColorAttribute)attributes.GetAttribute(colorAttribute.GetFogValue())).color);
            }

            /*
            if (lights != null && lights.shadowMap != null) 
            {
                set(u_shadowMapProjViewTrans, lights.shadowMap.getProjViewTrans());
                set(u_shadowTexture, lights.shadowMap.getDepthMap());
                set(u_shadowPCFOffset, 1.f / (2f * lights.shadowMap.getDepthMap().texture.getWidth()));
            }
            */

            lightsSet = true;
	}

	@Override
	public void Dispose ()
        {
            program.Dispose();
            super.Dispose();
	}

	public int GetDefaultCullFace () 
        {
            return config.defaultCullFace == -1 ? defaultCullFace : config.defaultCullFace;
	}

	public void SetDefaultCullFace (int cullFace) 
        {
            config.defaultCullFace = cullFace;
	}

	public int GetDefaultDepthFunc () 
        {
            return config.defaultDepthFunc == -1 ? defaultDepthFunc : config.defaultDepthFunc;
	}

	public void SetDefaultDepthFunc (int depthFunc) 
        {
            config.defaultDepthFunc = depthFunc;
	}
        
        /*
        Returns a single value of a matrix given an integer representing the
        index of a column-major array.
        */
        private static float GetMatrixValue(Matrix4_ matrix, int index)
        {
            switch(index)
            {
                case 0:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row0column0_();
                case 1:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row1column0_();
                case 2:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row2column0_();
                case 3:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row3column0_();
                case 4:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row0column1_();
                case 5:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row1column1_();
                case 6:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row2column1_();
                case 7:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row3column1_(); 
                case 8:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row0column2_();
                case 9:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row1column2_();
                case 10:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row2column2_();
                case 11:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row3column2_();
                case 12:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row0column3_();
                case 13:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row1column3_();
                case 14:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row2column3_();
                default:
                    return (float)matrix.Get_Libraries_Compute_Matrix4__row3column3_();
            }
        }
        
        /*
        Negates the first row and third column of the matrix. This is used for
        the camera's view matrix or the combined projection/view matrix, to
        effectively invert the z-axis.
        */
        public static void InvertViewZ(Matrix4_ m)
        {
            m.Set_Libraries_Compute_Matrix4__row0column0_(m.Get_Libraries_Compute_Matrix4__row0column0_() * -1);
            m.Set_Libraries_Compute_Matrix4__row0column1_(m.Get_Libraries_Compute_Matrix4__row0column1_() * -1);
            m.Set_Libraries_Compute_Matrix4__row0column3_(m.Get_Libraries_Compute_Matrix4__row0column3_() * -1);
            
            m.Set_Libraries_Compute_Matrix4__row1column2_(m.Get_Libraries_Compute_Matrix4__row1column2_() * -1);
            m.Set_Libraries_Compute_Matrix4__row2column2_(m.Get_Libraries_Compute_Matrix4__row2column2_() * -1);
            m.Set_Libraries_Compute_Matrix4__row3column2_(m.Get_Libraries_Compute_Matrix4__row3column2_() * -1);
        }
        
        /*
        Negates the Z coordinate of a world transform for a Renderable or object
        with a similar transform.
        */
        public static void InvertPositionZ(Matrix4_ m)
        {
            m.Set_Libraries_Compute_Matrix4__row2column3_(m.Get_Libraries_Compute_Matrix4__row2column3_() * -1);
        }
        
        /*
        Used to output the contents of a matrix. For debugging purposes only.
        */
//        private static float[] OutputMatrix4(Matrix4_ matrix)
//        {
//            Matrix4 m = (Matrix4)matrix;
//            // OpenGL expects the matrix to be stored in column-major format.
//            float[] temp = {(float)m.row0column0, (float)m.row1column0, (float)m.row2column0, (float)m.row3column0,
//                            (float)m.row0column1, (float)m.row1column1, (float)m.row2column1, (float)m.row3column1,
//                            (float)m.row0column2, (float)m.row1column2, (float)m.row2column2, (float)m.row3column2,
//                            (float)m.row0column3, (float)m.row1column3, (float)m.row2column3, (float)m.row3column3};
//            
//            for (int i = 0; i < 16; i++)
//            {
//                if (i % 4 == 0)
//                    System.out.println("");
//                System.out.print(temp[i] + ", ");
//            }
//            
//            System.out.println("");
//            
//            return temp;
//        }
}