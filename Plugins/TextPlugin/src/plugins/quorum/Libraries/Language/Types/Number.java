/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Language.Object_;
import quorum.Libraries.Language.Types.Number_;

/**
 * Number plugin for handling Number class functions for objects and primitives.
 * 
 * @author Melissa Stefik
 */
public class Number {
    public java.lang.Object me_ = null;
    private double number = -1;
    
    public static double ParseNumber(String value) throws quorum.Libraries.Language.Errors.CastError{
        try {
            return java.lang.Double.parseDouble(value);
        } catch(NumberFormatException e) {
            throw new quorum.Libraries.Language.Errors.CastError();
        }
    }
    
    public static double ConvertObjectToNumber(Object_ object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        Number_ val = (Number_) object;
        return val.GetValue();
    }
    
    public static Object_ ConvertNumberToObject(double value) {
        Number_ i = new quorum.Libraries.Language.Types.Number();
        i.SetValue(value);
        return i;
    }
    
    public static double ConvertNumberObjectToNumber(Number_ object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        return object.GetValue();
    }
    
    public static Number_ ConvertNumberToNumberObject(double value) {
        Number_ i = new quorum.Libraries.Language.Types.Number();
        i.SetValue(value);
        return i;
    }
    
    public double GetMaximumValue(){
        return Double.MAX_VALUE;
    }
    
    public static double PrimitiveGetMaximumValue(double self){
        return Double.MAX_VALUE;
    }
    
    public double GetMinimumValue(){
        return -Double.MAX_VALUE;
    }
    
    public static double PrimitiveGetMinimumValue(double self){
        return -Double.MAX_VALUE;
    }
    
    public double GetMinimumPositiveValue(){
        return Double.MIN_VALUE;
    }
    
    public static double PrimitiveGetMinimumPositiveValue(double self){
        return Double.MIN_VALUE;
    }
    
    public double GetNotANumberValue(){
        return Double.NaN;
    }
    
    public static double PrimitiveGetNotANumberValue(double self){
        return Double.NaN;
    }
    
    public double GetNegativeInfinityValue(){
        return Double.NEGATIVE_INFINITY;
    }
    
    public static double PrimitiveGetNegativeInfinityValue(double self){
        return Double.NEGATIVE_INFINITY;
    }
    
    public double GetPositiveInfinityValue(){
        return Double.POSITIVE_INFINITY;
    }
    
    public static double PrimitiveGetPositiveInfinityValue(double self){
        return Double.POSITIVE_INFINITY;
    }
    
    public double GetNumberOfBits(){
        return Double.SIZE;
    }
    
    public static int PrimitiveGetNumberOfBits(double self){
        return Double.SIZE;
    }
    
    public void SetValueNative(double value){
        number = value;
    }
    
    public static boolean PrimitiveEquals(double self, Object_ obj) throws quorum.Libraries.Language.Errors.Error{
        Number_ t = (Number_)obj;
        return self == t.GetValue();
    }
    
    public static int PrimitiveGetInteger(double self){
        return (int)self;
    }
    
    public static String PrimitiveGetText(double self){
        return "" + self;
    }
    
    public static int PrimitiveCompare(double self, quorum.Libraries.Language.Object_ obj){
        Number_ t = (Number_)obj;
        if(self == t.GetValue()){
            return 0;
        } else if(self > t.GetValue()){
            return 1;
        } else {
            return -1;
        }
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
