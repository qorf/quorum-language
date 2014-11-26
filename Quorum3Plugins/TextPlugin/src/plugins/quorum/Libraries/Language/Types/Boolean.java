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
    private static final int trueHash = 1231;
    private static final int falseHash = 1237;
    
    public static boolean ConvertObjectToBoolean(Object$Interface object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        Boolean$Interface val = (Boolean$Interface) object;
        return val.GetValue();
    }
    
    public static Object$Interface ConvertBooleanToObject(boolean value) {
        Boolean$Interface i = new quorum.Libraries.Language.Types.Boolean();
        i.SetValue(value);
        return i;
    }
    
    public static boolean ConvertBooleanObjectToBoolean(Boolean$Interface object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        return object.GetValue();
    }
    
    public static Boolean$Interface ConvertBooleanToBooleanObject(boolean value) {
        Boolean$Interface i = new quorum.Libraries.Language.Types.Boolean();
        i.SetValue(value);
        return i;
    }
    
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
    
    public static boolean NumberToBooleanCast(double number) {
        boolean result = false;
        if(number != 0) {
            result = true;
        }
        return result;
    }
    
    public static double BooleanToNumberCast(boolean bool) {
        double result = 0.0;
        if(bool == true) {
            result = 1.0;
        }
        return result;
    }
    
    public static boolean IntegerToBooleanCast(int number) {
        boolean result = false;
        if(number != 0) {
            result = true;
        }
        return result;
    }
    
    public static int BooleanToIntegerCast(boolean bool) {
        int result = 0;
        if(bool == true) {
            result = 1;
        }
        return result;
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
    
    public int GetHashCode() {
        if(bool) {
            return trueHash;
        } else {
            return falseHash;
        }
    }
    
    public int PrimitiveGetHashCode(boolean self) {
        if(self) {
            return trueHash;
        } else {
            return falseHash;
        }
    }
}
