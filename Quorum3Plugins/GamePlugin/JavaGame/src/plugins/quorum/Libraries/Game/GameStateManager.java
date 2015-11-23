/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;
import javax.swing.JOptionPane;
import quorum.Libraries.Game.Graphics.GraphicsManager_;
import quorum.Libraries.Game.Application_;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.InputMonitor_;

/**
 * In order to get around Quorum's lack of statics, we will manage the
 * GameState class with what amounts to a singleton.
 * 
 * 
 * @author Taylor Bockman
 */
public class GameStateManager {
  public java.lang.Object me_ = null;
  private static GameState gs = new GameState();

    public void SetApplication(Application_ app){
      gs.SetApp(app);
      gs.fileHandler = new LWJGLFileHandler();
    }

    public Application_ GetApplication(){
      return gs.GetApp();
    }

    public void SetGameDisplay(GameDisplay_ disp){
      gs.SetDisplay(disp);
    }

    public GameDisplay_ GetGameDisplay(){
      return gs.GetDisplay();
    }

    public void SetGameGraphics(GraphicsManager_ gl20){
      //JOptionPane.showMessageDialog(null, "Getting game graphics: ");
      gs.SetGameGraphics(gl20);
    }

    public GraphicsManager_ GetGameGraphics(){
      //JOptionPane.showMessageDialog(null, "Getting game graphics: ");
      return gs.GetGameGraphics();
    }
  
    public void SetNativePath(String path)
    {
        gs.SetNativePath(path);
    }
    
    public String GetNativePath()
    {
        return gs.GetNativePath();
    }
    
    public void SetOperatingSystem(String os)
    {
        gs.SetOperatingSystem(os);
    }
    
    public String GetOperatingSystem()
    {
        return gs.GetOperatingSystem();
    }
  
}
