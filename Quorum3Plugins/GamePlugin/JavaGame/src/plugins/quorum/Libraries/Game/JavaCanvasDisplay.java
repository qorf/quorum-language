/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.awt.Canvas;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author alleew
 */
public class JavaCanvasDisplay extends DesktopDisplay
{
    public java.lang.Object me_ = null;
    
    public Canvas canvas;
    
    public void SetupDisplay()
    {
        if (canvas == null)
        {
            super.SetupDisplay();
            return;
        }
        
//        try
//        {
//            Display.setParent(canvas);
//        }
//        catch (LWJGLException ex)
//        {
//            throw new GameRuntimeError("Could not set up SwingDisplay: " + ex.getMessage());
//        }
        
        quorum.Libraries.Game.JavaCanvasDisplay quorumDisplay = (quorum.Libraries.Game.JavaCanvasDisplay) me_;
        quorum.Libraries.Game.DesktopConfiguration_ config = quorumDisplay.Get_Libraries_Game_DesktopDisplay__config_();
        
//        Display.setTitle(config.Get_Libraries_Game_DesktopConfiguration__title_());
//        Display.setResizable(config.Get_Libraries_Game_DesktopConfiguration__resizable_());
//        Display.setInitialBackground((float)config.Get_Libraries_Game_DesktopConfiguration__initialBackgroundColor_().GetRed(),
//            (float)config.Get_Libraries_Game_DesktopConfiguration__initialBackgroundColor_().GetGreen(), 
//            (float)config.Get_Libraries_Game_DesktopConfiguration__initialBackgroundColor_().GetBlue());
        
//        Display.setLocation(config.Get_Libraries_Game_DesktopConfiguration__x_(), config.Get_Libraries_Game_DesktopConfiguration__y_());
        //createDisplayPixelFormat();
        //InitiateGLInstances();
    }
    
    protected void createDisplayPixelFormat() 
    {
        //Create the display
//        try 
//        {
//            quorum.Libraries.Game.JavaCanvasDisplay quorumDisplay = (quorum.Libraries.Game.JavaCanvasDisplay) me_;
//            quorum.Libraries.Game.DesktopConfiguration_ config = quorumDisplay.Get_Libraries_Game_DesktopDisplay__config_();
//
//            if (config.Get_Libraries_Game_DesktopConfiguration__useGL30_()) 
//            {
//                throw new GameRuntimeError("The desktop configuration requested GL30 support, but this isn't supported yet.");
//            }
//            else 
//            {
////                Display.setParent(canvas);
////                Display.create(new PixelFormat(
////                    config.Get_Libraries_Game_ApplicationConfiguration__r_()
////                    + config.Get_Libraries_Game_ApplicationConfiguration__g_()
////                    + config.Get_Libraries_Game_ApplicationConfiguration__b_(),
////                    config.Get_Libraries_Game_ApplicationConfiguration__a_(),
////                    config.Get_Libraries_Game_ApplicationConfiguration__depth_(),
////                    config.Get_Libraries_Game_ApplicationConfiguration__stencil_(),
////                    config.Get_Libraries_Game_ApplicationConfiguration__samples_()));
//                
//             
//            }
//
//        }
//        catch (LWJGLException e) 
//        {
//            e.printStackTrace();
//            Display.destroy();
//            throw new GameRuntimeError("createDisplayPixelFormat crashed!");
//
//            //Other important clean up stuff
//        }
    }
    
    public double GetSecondsBetweenFrames()
    {
        return super.GetSecondsBetweenFrames();
    }
}
