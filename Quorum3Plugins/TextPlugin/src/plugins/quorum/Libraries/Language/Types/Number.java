/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.Language.Types.Number$Interface;

/**
 * Number plugin for handling Number class functions for objects and primitives.
 * 
 * @author Melissa Stefik
 */
public class Number {
    public java.lang.Object $me = null;
    private double number = -1;
    
    public static double ParseNumber(String value) throws quorum.Libraries.Language.Errors.CastError{
        try {
            return java.lang.Double.parseDouble(value);
        } catch(NumberFormatException e) {
            throw new quorum.Libraries.Language.Errors.CastError();
        }
    }
    
    public static double ConvertObjectToNumber(Object$Interface object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        Number$Interface val = (Number$Interface) object;
        return val.GetValue();
    }
    
    public static Object$Interface ConvertNumberToObject(double value) {
        Number$Interface i = new quorum.Libraries.Language.Types.Number();
        i.SetValue(value);
        return i;
    }
    
    public static double ConvertNumberObjectToNumber(Number$Interface object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        return object.GetValue();
    }
    
    public static Number$Interface ConvertNumberToNumberObject(double value) {
        Number$Interface i = new quorum.Libraries.Language.Types.Number();
        i.SetValue(value);
        return i;
    }
    
    public double GetMaximumValue(){
        return Double.MAX_VALUE;
    }
    
    public static double PrimitiveGetMaximumValue(){
        return Double.MAX_VALUE;
    }
    
    public double GetMinimumValue(){
        return Double.MIN_VALUE;
    }
    
    public static double PrimitiveGetMinimumValue(){
        return Double.MIN_VALUE;
    }
    
    public double GetNotANumberValue(){
        return Double.NaN;
    }
    
    public static double PrimitiveGetNotANumberValue(){
        return Double.NaN;
    }
    
    public double GetNegativeInfinityValue(){
        return Double.NEGATIVE_INFINITY;
    }
    
    public static double PrimitiveGetNegativeInfinityValue(){
        return Double.NEGATIVE_INFINITY;
    }
    
    public double GetPositiveInfinityValue(){
        return Double.POSITIVE_INFINITY;
    }
    
    public static double PrimitiveGetPositiveInfinityValue(){
        return Double.POSITIVE_INFINITY;
    }
    
    public double GetNumberOfBits(){
        return Double.SIZE;
    }
    
    public static int PrimitiveGetNumberOfBits(){
        return Double.SIZE;
    }
    
    public void SetValueNative(double value){
        number = value;
    }
    
    public static boolean PrimitiveEquals(double self, Object$Interface obj) throws quorum.Libraries.Language.Errors.Error{
        Number$Interface t = (Number$Interface)obj;
        return self == t.GetValue();
    }
    
    public static int PrimitiveGetInteger(double self){
        return (int)self;
    }
    
    public static String PrimitiveGetText(double self){
        return "" + self;
    }
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompare(double self, quorum.Libraries.Language.Object$Interface obj){
        quorum.Libraries.Language.Support.CompareResult r = new quorum.Libraries.Language.Support.CompareResult();
        Number$Interface t = (Number$Interface)obj;
        if(self == t.GetValue()){
            r.result = r.EQUAL;
        } else if(self > t.GetValue()){
            r.result = r.LARGER;
        } else {
            r.result = r.SMALLER;
        }
        return r;
    }
    
    public String GetHex(){
        return java.lang.Double.toHexString(number);
    }
    
    public static String PrimitiveGetHex(double self){
        return java.lang.Double.toHexString(self);
    }
    
    public boolean IsInfinite(){
        return java.lang.Double.isInfinite(number);
    }
    
    public static boolean PrimitiveIsInfinite(double self){
        return java.lang.Double.isInfinite(self);
    }
    
    public boolean IsNotANumber(){
        return java.lang.Double.isNaN(number);
    }
    
    public static boolean PrimitiveIsNotANumber(double self){
        return java.lang.Double.isNaN(self);
    }
    
    public int GetHashCode() {
        long returnMe = Double.doubleToLongBits(number);
        return (int)(returnMe^(returnMe>>>32));
    }
    
    public int PrimitiveGetHashCode(double self) {
        long returnMe = Double.doubleToLongBits(self);
        return (int)(returnMe^(returnMe>>>32));
    }
}
