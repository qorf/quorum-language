/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.Renderable_;
//import com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider;
//import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
//import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;

public interface Shader
{
	void Initialize();

	int CompareTo (Shader other); // TODO: probably better to add some weight value to sort on

	boolean CanRender (Renderable_ instance);

	void Begin(Camera_ camera, RenderContext context);

	void Render(final Renderable_ renderable);

	void End();
        
        void Dispose();
}
