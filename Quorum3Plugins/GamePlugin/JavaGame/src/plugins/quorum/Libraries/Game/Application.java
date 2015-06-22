/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

//import javax.swing.JOptionPane;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;

/**
 *
 * @author stefika, Taylor Bockman
 */
public class Application {

    public java.lang.Object me_ = null;

    // protected Thread mainloopthread;
    // Currently this thread is never used.

    /*Fire off the thread with main loop in it.
    public void NativeSetup() {
        final quorum.Libraries.Game.Application app = (quorum.Libraries.Game.Application) me_;
        //app.display.SetVSync(app.display.GetConfig().vSyncEnabled);
        app.Get_Libraries_Game_Application_display()
                .SetVSync(app.Get_Libraries_Game_Application_display()
                        .GetConfig()
                        .Get_Libraries_Game_ApplicationConfiguration_vSyncEnabled());
        app.MainLoop();
    } */
    
    // USED FOR DEBUGGING PURPOSES ONLY
    public void CheckForErrors()
    {
        int x;
        x = GL11.glGetError();
        //if (x != 0)
            System.out.println(Util.translateGLErrorString(x));
    }
}
