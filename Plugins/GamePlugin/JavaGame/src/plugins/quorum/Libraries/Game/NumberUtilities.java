/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import plugins.quorum.Libraries.Game.GameRuntimeError;

/**
 *
 * @author alleew
 */
public class NumberUtilities 
{
    public java.lang.Object me_ = null;
    
    public double EncodeIntegerAsNumber(int target)
    {
        return Float.intBitsToFloat(target);
    }
    
    public int EncodeNumberAsInteger(double target)
    {
        if (target > Float.MAX_VALUE || target < Float.MIN_VALUE)
            throw new GameRuntimeError("Number was too large for encoding!");
        
        return Float.floatToRawIntBits((float)target);
    }
    
}
