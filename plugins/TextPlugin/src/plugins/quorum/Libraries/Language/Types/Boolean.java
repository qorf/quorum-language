/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.Language.Types.Boolean$Interface;
/**
 * Boolean plugin for handling Boolean class functions for objects and primitives.
 * @author Melissa Stefik
 */
public class Boolean {
    public java.lang.Object $me = null;
    private boolean bool = false;
    
    public void SetValueNative(boolean value){
        bool = value;
    }
    
    public static boolean PrimitiveEquals(boolean self, Object$Interface obj) throws quorum.Libraries.Language.Errors.Error{
        Boolean$Interface t = (Boolean$Interface)obj;
        return self == t.GetValue();
    }
   
    public static String PrimitiveGetText(boolean self){
        return "" + self;
    }
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompare(boolean self, quorum.Libraries.Language.Object$Interface obj){
        quorum.Libraries.Language.Support.CompareResult r = new quorum.Libraries.Language.Support.CompareResult();
        Boolean$Interface t = (Boolean$Interface)obj;
        if(self == t.GetValue()){
            r.result = r.EQUAL;
        } else if(self == false && t.GetValue() == true){
            r.result = r.SMALLER;
        } else {
            r.result = r.LARGER;
        }
        return r;
    }
}
