/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Language.Object_;
import quorum.Libraries.Language.Types.Boolean_;
/**
 * Boolean plugin for handling Boolean class functions for objects and primitives.
 * @author Melissa Stefik
 */
public class Boolean {
    public java.lang.Object me_ = null;
    private boolean bool = false;
    private static final int trueHash = 1231;
    private static final int falseHash = 1237;
    
    public static boolean ParseBoolean(String value) throws quorum.Libraries.Language.Errors.CastError{
        if(value == null) {
            throw new quorum.Libraries.Language.Errors.CastError();
        }
        
        if(value.compareTo("true") == 0) {
            return true;
        } else if(value.compareTo("false") == 0) {
            return false;
        }
        
        throw new quorum.Libraries.Language.Errors.CastError();
    }
    
    public static boolean ConvertObjectToBoolean(Object_ object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        Boolean_ val = (Boolean_) object;
        return val.GetValue();
    }
    
    public static Object_ ConvertBooleanToObject(boolean value) {
        Boolean_ i = new quorum.Libraries.Language.Types.Boolean();
        i.SetValue(value);
        return i;
    }
    
    public static boolean ConvertBooleanObjectToBoolean(Boolean_ object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        return object.GetValue();
    }
    
    public static Boolean_ ConvertBooleanToBooleanObject(boolean value) {
        Boolean_ i = new quorum.Libraries.Language.Types.Boolean();
        i.SetValue(value);
        return i;
    }
    
    public void SetValueNative(boolean value){
        bool = value;
    }
    
    public static boolean PrimitiveEquals(boolean self, Object_ obj) throws quorum.Libraries.Language.Errors.Error{
        Boolean_ t = (Boolean_)obj;
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
    
    public static int PrimitiveCompare(boolean self, quorum.Libraries.Language.Object_ obj){
        Boolean_ t = (Boolean_)obj;
        if(self == t.GetValue()){
            return 0;
        } else if(self == false && t.GetValue() == true){
            return -1;
        } else {
            return 1;
        }
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