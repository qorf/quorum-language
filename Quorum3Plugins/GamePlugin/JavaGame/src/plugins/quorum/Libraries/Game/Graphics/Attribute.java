/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameRuntimeError;

/**
 *
 * @author alleew
 */
public class Attribute 
{
    public java.lang.Object me_ = null;
    
    public int /*long*/ type;
    public int typeBit;
    
    public static String[] types = new String[32];
    public static int typesSize = 0;
    
    public String GetAttributeAlias(int type)
    {
        return GetAttributeAliasStatic(type);
    }
    
    public static String GetAttributeAliasStatic(int type)
    {
        int idx = -1;
        while (type != 0 && ++idx < 31 && (((type >> idx) & 1) == 0))
            ;
        return (idx >= 0 && idx < typesSize) ? types[idx] : null;
    }
    
    public int GetAttributeType(String alias)
    {
        return GetAttributeTypeStatic(alias);
    }
    
    public static int GetAttributeTypeStatic(String alias)
    {
        for (int i = 0; i < typesSize; i++)
            if (types[i].equalsIgnoreCase(alias))
                return 1 << i;
        
        return 0;
    }
    
    public int Register(String alias)
    {
        return RegisterStatic(alias);
    }
    
    public static int RegisterStatic(String alias)
    {
        int result = GetAttributeTypeStatic(alias);
        if (result > 0)
            return result;
        
        if (typesSize >= 32)
            throw new GameRuntimeError("Can't register more than 32 types of attributes!");
        
        types[typesSize] = alias;
        typesSize++;
        return 1 << (typesSize - 1);
    }
}
