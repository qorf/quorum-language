
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.DesktopDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Compute.Matrix4;
import quorum.Libraries.Game.Graphics.Mesh_;
import quorum.Libraries.Game.Graphics.Mesh;
import quorum.Libraries.Game.Graphics.IndexArray;
import quorum.Libraries.Compute.Vector3;
import quorum.Libraries.Game.GameDisplay_;

/**
 *
 * @author alleew
 */
public class Painter2D 
{
    public java.lang.Object me_ = null;
    
    private Mesh_ mesh;
    
    final static int SPRITE_SIZE = 20;
    
    int index = 0;
    float inverseTexWidth = 0;
    float inverseTexHeight = 0;
    
    private final Matrix4_ transformMatrix = new Matrix4();
    private final Matrix4_ projectionMatrix = new Matrix4();
    private final Matrix4_ combinedMatrix = new Matrix4();
    
    // Used for ApplyCamera.
    private final static Matrix4_ calcMatrix = new Matrix4();
    
    private boolean blendingDisabled = false;
    private int blendSourceFunction = GraphicsManager.GL_SRC_ALPHA;
    private int blendDestFunction = GraphicsManager.GL_ONE_MINUS_SRC_ALPHA;
    
    private ShaderProgram shader;
    private ShaderProgram customShader = null;
    private boolean ownsShader;

    /*
    Resources used for the font shader.
    */
    private ShaderProgram fontShader;
    private boolean useFontShader = false;
    
    float colorValue; // Initialized by constructor.
    
    boolean isClipping = false;
    // A vector used for calculating points via the combined view/projection matrix.
    private final static Vector3 clipPoint = new Vector3();
    
    public Painter2D()
    {
        // The default constructor in Java does nothing. Note that the default
        // constructor in Quorum, however, will call LoadDefaultPainter().
    }
    
    public void LoadDefaultPainter(Mesh_ quorumMesh)
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        SetColor(quorumBatch.color);
        
        mesh = quorumMesh;

        if (GameStateManager.display == null)
            throw new GameRuntimeError("I couldn't create the Painter2D because the display hasn't been initialized!");
        
        projectionMatrix.SetToOrthographic2D(0, 0, GameStateManager.display.GetWidth(), GameStateManager.display.GetHeight());
        
        shader = CreateDefaultShader();
        fontShader = CreateFontShader();
        ownsShader = true;
    }
    
    /** Returns a new instance of the default shader used by Painter2D for GL2 when no shader is specified. */
    static public ShaderProgram CreateDefaultShader () 
    {
	String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "uniform mat4 u_projTrans;\n" //
		+ "varying vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "\n" //
		+ "void main()\n" //
		+ "{\n" //
		+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
		+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "}\n";
	String fragmentShader = "#ifdef GL_ES\n" //
		+ "#define LOWP lowp\n" //
		+ "precision mediump float;\n" //
		+ "#else\n" //
		+ "#define LOWP \n" //
		+ "#endif\n" //
		+ "varying LOWP vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "uniform sampler2D u_texture;\n" //
		+ "void main()\n"//
		+ "{\n" //
		+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
		+ "}";

	ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
	if (shader.IsCompiled() == false)
            throw new IllegalArgumentException("Error compiling default shader: " + shader.GetLog());
	return shader;
    }
    
    /** Returns a new instance of the shader used by the Painter2D for fonts. */
    static public ShaderProgram CreateFontShader () 
    {
	String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "uniform mat4 u_projTrans;\n" //
		+ "varying vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "\n" //
		+ "void main()\n" //
		+ "{\n" //
		+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
		+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "}\n";
	String fragmentShader = "#ifdef GL_ES\n" //
		+ "#define LOWP lowp\n" //
		+ "precision mediump float;\n" //
		+ "#else\n" //
		+ "#define LOWP \n" //
		+ "#endif\n" //
		+ "varying LOWP vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
                + "uniform vec4 u_fontColor;\n"
		+ "uniform sampler2D u_texture;\n" //
		+ "void main()\n"//
		+ "{\n" //
                + "  vec4 compute = texture2D(u_texture, v_texCoords);\n"
                + "  compute.a = compute.a * u_fontColor.a;\n"
                + "  compute.rgb = u_fontColor.rgb;\n"
		+ "  gl_FragColor = v_color * compute;\n" //
		+ "}";

	ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
	if (shader.IsCompiled() == false)
            throw new IllegalArgumentException("Error compiling font shader: " + shader.GetLog());
	return shader;
    }
    
    public void Begin()
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        if (quorumBatch.IsDrawing())
            throw new GameRuntimeError("This painter is already drawing! Call End() before calling Begin() again.");
        
        GameStateManager.nativeGraphics.glDepthMask(true);
        GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_DEPTH_TEST);
        GameStateManager.nativeGraphics.glDepthFunc(GraphicsManager.GL_LEQUAL);
        GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        
        if (useFontShader)
            fontShader.Begin();
        else if (customShader != null)
            customShader.Begin();
        else
            shader.Begin();
        SetupMatrices();
        quorumBatch.drawing = true;
        if (isClipping)
        {
            UpdateClipping();
            GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_SCISSOR_TEST);
        }
        else
        {
            GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_SCISSOR_TEST);
        }
    }
    
    public void SystemEnd()
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        if (!quorumBatch.IsDrawing())
            throw new GameRuntimeError("This painter isn't drawing yet! Call Begin() before calling End().");
        
        if (index > 0)
            Flush();
        
        quorumBatch.lastTexture = null;
        quorumBatch.drawing = false;
        
        GraphicsManager gl = GameStateManager.nativeGraphics;
        gl.glDepthMask(true);
        if (IsBlendingEnabled())
            gl.glDisable(GraphicsManager.GL_BLEND);

        if (isClipping)
            gl.glDisable(GraphicsManager.GL_SCISSOR_TEST);
        
        if (useFontShader)
            fontShader.End();
        else if (customShader != null)
            customShader.End();
        else
            shader.End();
    }
    
    public void SetColor(quorum.Libraries.Game.Graphics.Color_ newColor)
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        quorumBatch.color = newColor;
        colorValue = (float)quorumBatch.color.EncodeColorAsNumber();
    }
    
    public void SetColor(double r, double g, double b, double a)
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        quorumBatch.color.SetColor(r, g, b, a);
        colorValue = (float)quorumBatch.color.EncodeColorAsNumber();
    }
        
    public void SystemDraw(quorum.Libraries.Game.Graphics.Drawable_ drawable)
    {
        
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
	if (!quorumBatch.IsDrawing())
            throw new GameRuntimeError("Painter2D:Begin() must be called before Draw.");

        int verticesLength = quorumBatch.GetVertices().GetSize();
        if (drawable.GetTexture() != quorumBatch.lastTexture) 
        {
            SwitchTexture(drawable.GetTexture());
        }
        else 
        {
            if (verticesLength - index < drawable.Get_Libraries_Game_Graphics_Drawable__DRAWABLE_SIZE_()) 
            {
                Flush();
            }
        }
        
        // This will update the vertices stored in the Drawable. These vertices
        // will be used when Flush() is called (which is when the actual drawing
        // occurs).
        drawable.PrepareVertices();
        
        if (!drawable.UsingCustomTint())
        {
            for (int i = drawable.Get_Libraries_Game_Graphics_Drawable__C1_(); i < drawable.Get_Libraries_Game_Graphics_Drawable__DRAWABLE_SIZE_(); i = i + drawable.Get_Libraries_Game_Graphics_Drawable__VERTEX_SIZE_())
                drawable.SetVertex(i, colorValue);
        }
        
        for (int i = 0; i < drawable.Get_Libraries_Game_Graphics_Drawable__DRAWABLE_SIZE_(); i++)
        {
            quorumBatch.SetVertex(index, (float)drawable.GetVertex(i));
            index++;
        }
    }
    
    public void Flush()
    {
        if (index == 0)
            return;

        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        // Index is divided by the value of DRAWABLE_SIZE.
        int count = (index / 24) * 6;
        
        quorumBatch.lastTexture.Bind();
        
        mesh.SetVertices(quorumBatch.GetVertices());//, 0, index);
        ((IndexArray)mesh.GetIndexData()).plugin_.GetBuffer().position(0);
        ((IndexArray)mesh.GetIndexData()).plugin_.GetBuffer().limit(count);
        
        if (blendingDisabled)
            GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        else
        {
            GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_BLEND);
            if (blendSourceFunction != -1)
                GameStateManager.nativeGraphics.glBlendFunc(blendSourceFunction, blendDestFunction);
        }
        
        if (useFontShader)
            ((Mesh)mesh).plugin_.Render(fontShader, GraphicsManager.GL_TRIANGLES, 0, count);
        else if (customShader != null)
            ((Mesh)mesh).plugin_.Render(customShader, GraphicsManager.GL_TRIANGLES, 0, count);
        else
            ((Mesh)mesh).plugin_.Render(shader, GraphicsManager.GL_TRIANGLES, 0, count);
        
        index = 0;
        
    }

    public void Dispose() 
    {
	mesh.Dispose();
	if (ownsShader && shader != null)
            shader.Dispose();
        
        if (ownsShader && fontShader != null)
            fontShader.Dispose();
    }
        
    protected void SwitchTexture (quorum.Libraries.Game.Graphics.Texture_ texture) 
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        Flush();
        
        quorum.Libraries.Game.Graphics.Texture t = (quorum.Libraries.Game.Graphics.Texture)texture;
        if (t.plugin_.fontColor != null)
        {
            if (useFontShader == false)
            {
                useFontShader = true;
                if (quorumBatch.IsDrawing())
                {
                    if (customShader != null)
                        customShader.End();
                    else
                        this.shader.End();
                    
                    fontShader.Begin();
                }
                
                SetupMatrices();
            }
            fontShader.SetUniform("u_fontColor", t.plugin_.fontColor);
        }
        else
        {
            if (useFontShader == true)
            {
                useFontShader = false;
                if (quorumBatch.IsDrawing())
                {
                    fontShader.End();
                    
                    if (customShader != null)
                        customShader.Begin();
                    else
                        this.shader.Begin();
                }
                
                SetupMatrices();
            }
        }
        
        quorumBatch.lastTexture = texture;
        inverseTexWidth = 1.0f / (float)texture.GetWidth();
        inverseTexHeight = 1.0f / (float)texture.GetHeight();
    }
        
    public Matrix4_ GetProjectionMatrix () 
    {
        return projectionMatrix;
    }

    public Matrix4_ GetTransformMatrix () 
    {
        return transformMatrix;
    }

    public void SetProjectionMatrix (Matrix4_ projection) 
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
	if (quorumBatch.IsDrawing()) 
            Flush();
	projectionMatrix.Set(projection);
	if (quorumBatch.IsDrawing())
            SetupMatrices();
    }

    public void SetTransformMatrix (Matrix4 transform) 
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
	if (quorumBatch.IsDrawing()) 
                Flush();
    	transformMatrix.Set(transform);
	if (quorumBatch.IsDrawing())
            SetupMatrices();
    }

    private void SetupMatrices () 
    {
	combinedMatrix.Set(projectionMatrix).Multiply(transformMatrix);
        
        if (useFontShader)
        {
            fontShader.SetUniformMatrix("u_projTrans", combinedMatrix);
            fontShader.SetUniform("u_texture", 0);
        }
        else if (customShader != null) 
        {
            customShader.SetUniformMatrix("u_projTrans", combinedMatrix);
            customShader.SetUniform("u_texture", 0);
	}
        else 
        {
            shader.SetUniformMatrix("u_projTrans", combinedMatrix);
            shader.SetUniform("u_texture", 0);
	}
    }

    public void SetShader (ShaderProgram shader) 
    {
        final quorum.Libraries.Game.Graphics.Painter2D quorumBatch = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
	if (quorumBatch.IsDrawing() && !useFontShader) 
        {
            Flush();
            if (customShader != null)
                customShader.End();
            else
                this.shader.End();
	}
	customShader = shader;
	if (quorumBatch.IsDrawing() && !useFontShader) 
        {
            if (customShader != null)
		customShader.Begin();
            else
		this.shader.Begin();
            SetupMatrices();
	}
    }

    public boolean IsBlendingEnabled () 
    {
    	return !blendingDisabled;
    }
    
    public void ApplyCamera(Camera_ camera)
    {
        SetProjectionMatrix(camera.GetCombinedMatrix());
    }
    
    public void SetClipping(boolean clip)
    {
        if (clip == isClipping)
            return;
        
        isClipping = clip;
        
        final quorum.Libraries.Game.Graphics.Painter2D quorumPainter = (quorum.Libraries.Game.Graphics.Painter2D) me_;
        
        if (quorumPainter.drawing)
        {
            UpdateClipping();

            GraphicsManager graphics = GameStateManager.nativeGraphics;
        
            if (clip)
            {
                graphics.glEnable(GraphicsManager.GL_SCISSOR_TEST);
            }
            else
            {
                Flush();
                graphics.glDisable(GraphicsManager.GL_SCISSOR_TEST);
            }
        }
    }
    
    public void UpdateClipping()
    {
        quorum.Libraries.Game.Graphics.Painter2D_ painter = ((quorum.Libraries.Game.Graphics.Painter2D_)me_);
        
        if (!painter.Get_Libraries_Game_Graphics_Painter2D__drawing_() || !isClipping)
            return;
        
        Flush();
        
        GameDisplay_ display = GameStateManager.display;
        
        clipPoint.Set(painter.GetClipX(), painter.GetClipY(), 0);
        
        /*
        The result of the multiplication will produce a vector containing
        values between -1 and 1. We want to adjust the range to 0 to 1 and then
        use the display's width and height to find the actual pixel values
        the clip point will apply to.
        */
        clipPoint.Multiply(combinedMatrix);
        int x = (int)((clipPoint.GetX() + 1) / 2.0 * display.GetWidth());
        int y = (int)((clipPoint.GetY() + 1) / 2.0 * display.GetHeight());
        
        clipPoint.Set(painter.GetClipX2(), painter.GetClipY2(), 0);
        
        clipPoint.Multiply(combinedMatrix);
        int x2 = (int)((clipPoint.GetX() + 1) / 2.0 * display.GetWidth());
        int y2 = (int)((clipPoint.GetY() + 1) / 2.0 * display.GetHeight());
        
        int width, height;
        
        if (x2 >= x)
        {
            width = x2 - x;
        }
        else
        {
            width = x - x2;
            x = x2;
        }
        
        if (y2 >= y)
        {
            height = y2 - y;
        }
        else
        {
            height = y - y2;
            y = y2;
        }
        
        GameStateManager.nativeGraphics.glScissor(x, y, width, height);
    }
    
    public boolean IsClipping()
    {
        return isClipping;
    }
        
    /*
    Returns a single value of a matrix given an integer representing the
    index of a column-major array.
    */
//    private static float GetMatrixValue(Matrix4_ matrix, int index)
//    {
//        switch(index)
//        {
//            case 0:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row0column0_();
//            case 1:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row1column0_();
//            case 2:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row2column0_();
//            case 3:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row3column0_();
//            case 4:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row0column1_();
//            case 5:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row1column1_();
//            case 6:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row2column1_();
//            case 7:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row3column1_(); 
//            case 8:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row0column2_();
//            case 9:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row1column2_();
//            case 10:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row2column2_();
//            case 11:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row3column2_();
//            case 12:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row0column3_();
//            case 13:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row1column3_();
//            case 14:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row2column3_();
//            default:
//                return (float)matrix.Get_Libraries_Compute_Matrix4__row3column3_();
//        }
//    }
}

