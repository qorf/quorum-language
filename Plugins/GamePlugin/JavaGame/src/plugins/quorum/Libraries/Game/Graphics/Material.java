/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

/**
 *
 * @author Bill
 */
public class Material 
{
    public java.lang.Object me_ = null;
    
    private static int counter = 0;
    
    public String GenerateDefaultID()
    {
        return "mtl" + (++counter);
    }
}
