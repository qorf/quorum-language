/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

/**
 *
 * @author alleew
 */
public class Game 
{
    public java.lang.Object me_ = null;
    
    public double GetSecondsBetweenFrames()
    {
        return GameState.GetDisplay().GetSecondsBetweenFrames();
    }
    
}
