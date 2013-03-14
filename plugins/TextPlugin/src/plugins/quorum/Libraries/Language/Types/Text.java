/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Language.Errors.Error;
import quorum.Libraries.Language.Errors.InvalidLocationError;
import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.Language.Types.Text$Interface;
import quorum.Libraries.Language.Errors.UndefinedObjectError;

/**
 * A plugin for handling text system functions.
 * 
 * @author Andreas Stefik
 */
public class Text {
    public java.lang.Object $me = null;
    private String text = "";
    
    public void SetValueNative(String value) {
        text = value;
    }
    
    public boolean ContainsNative(String lhv, String rhv){
        return lhv.contains(rhv);
    }
    
    public String GetCharacterNative(int index) {
        String ret = "" + text.charAt(index);
        return ret;
    }
    
    public String GetSubstringNative(int startIndex, int endIndex) {
        return text.substring(startIndex, endIndex);
    }
    
    public int CompareInt(String left, String right, boolean isIgnoringCase) {
        if(isIgnoringCase){
            return left.compareToIgnoreCase(right);
        }else{
            return left.compareTo(right);
        }
    }
    
    public boolean EndsWithNative(String left, String suffix){
        return left.endsWith(suffix);
    }
    
    public boolean StartsWithNative(String left, String prefix){
        return left.endsWith(prefix);
    }
    
    public boolean EqualsIgnoringCaseNative(String left, String right){
        return left.equalsIgnoreCase(right);
    }
    
    public int IndexOfNative(String left, String right){
        return left.indexOf(right);
    }
    
    public int IndexOfNative(String left, String right, int index){
        return left.indexOf(right, index);
    }
    
    public boolean IsEmptyNative(String left){
        return left.isEmpty();
    }
    
    public String ReplaceNative(String left, String old, String replacement){
        return left.replace(old, replacement);
    }
    
    public String SubtextNative(String left, int start, int end){
        return left.substring(start, end);
    }
    
    public String GetSubtextNative(String left, int start){
        return left.substring(start);
    }
    
    public String ToLowerCase(){
        return text.toLowerCase();
    }
    
    public String ToUpperCase(){
        return text.toUpperCase();
    }
    
    public String TrimNative(String left){
        return left.trim();
    }
    
    public int GetLength() {
        return text.length();
    }

    /**
     * If the value is valid, return its hashcode. Otherwise, return the 
     * generic hash for the object.
     * 
     * @return 
     */
    public int GetHashCode() {
        if(text != null) {
            return text.hashCode();
        }
        else {
            return $me.hashCode();
        }
    }
    
    public static int PrimitiveGetLength(String self) throws Error {
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        return self.length();
    }
    
    public static int PrimitiveGetHashCode(String self) throws Error {
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        return self.hashCode();
    }
    
    public static String PrimitiveGetCharacter(String self, int index) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        int length = self.length();
        if(index < 0 || index >= length){
            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
            error.SetErrorMessage("Text index out of range, " + index);
            throw(error);
        }
        return "" + self.charAt(index);
    }
    
    public static String PrimitiveAppend(String self, String addition) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        StringBuilder s = new StringBuilder();
        s.append(self);
        s.append(addition);
        return s.toString();
    }
    
    public static Array$Interface PrimitiveSplit(String self, String delimiter) throws Error{
        
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        String[] split = self.split(delimiter);
        quorum.Libraries.Containers.Array a = new quorum.Libraries.Containers.Array();
        
        for(int i = 0; i < split.length; i++){
            Text$Interface t = new quorum.Libraries.Language.Types.Text();
            t.SetValue(split[i]);
            a.AddNative((Object$Interface)t);
        }
        return a;
    }
    
    public static boolean PrimitiveEquals(String self, Object$Interface obj) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        Text$Interface t = (Text$Interface)obj;
        return self.equals(t.GetValue());
    }
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompare(String self, quorum.Libraries.Language.Object$Interface obj) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        quorum.Libraries.Language.Support.CompareResult r = new quorum.Libraries.Language.Support.CompareResult();
        Text$Interface t = (Text$Interface)obj;
        int result = self.compareTo(t.GetValue());
        if(result == 0){
            r.result = r.EQUAL;
        } else if(result > 0){
            r.result = r.LARGER;
        } else {
            r.result = r.SMALLER;
        }
        return r;
        
    }
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompareIgnoringCase(String self, quorum.Libraries.Language.Object$Interface obj) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        quorum.Libraries.Language.Support.CompareResult r = new quorum.Libraries.Language.Support.CompareResult();
        Text$Interface t = (Text$Interface)obj;
        int result = self.compareToIgnoreCase(t.GetValue());
        if(result == 0){
            r.result = r.EQUAL;
        } else if(result > 0){
            r.result = r.LARGER;
        } else {
            r.result = r.SMALLER;
        }
        return r;
        
    }
    
    public static boolean PrimitiveContains(String self, String right) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.contains(right); 
    }
    
    public static boolean PrimitiveEndsWith(String self, String suffix) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.endsWith(suffix);
    }
    
    public static boolean PrimitiveStartsWith(String self, String prefix) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.startsWith(prefix);
    }
    
    public static boolean PrimitiveEqualsIgnoringCase(String self, Object$Interface right) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        Text$Interface t = (Text$Interface)right;
        return self.equalsIgnoreCase(t.GetValue());
    }
    
    public static int PrimitiveIndexOf(String self, String right) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.indexOf(right);
    }
    
    public static int PrimitiveIndexOf(String self, String right, int index) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        int length = self.length();
        if(index < 0 || index >= length){
            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
            error.SetErrorMessage("Text index out of range, " + index);
            throw(error);
        }
        return self.indexOf(right, index);
    }
    
    public static boolean PrimitiveIsEmpty(String self) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.isEmpty();
    }
    
    public static String PrimitiveReplace(String self, String old, String replacement) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.replace(old, replacement);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex, int endIndex) throws quorum.Libraries.Language.Errors.Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        int length = self.length();
        if(startIndex < 0 || startIndex >= length || endIndex < startIndex || endIndex >= length){
            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
            error.SetErrorMessage("Text index out of range, " + startIndex + " - " + endIndex);
            throw(error);
        }
        return self.substring(startIndex, endIndex);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        int length = self.length();
        if(startIndex < 0 || startIndex >= length){
            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
            error.SetErrorMessage("Text index out of range, " + startIndex);
            throw(error);
        }
        return self.substring(startIndex);
    }
    
    public static String PrimitiveToLowerCase(String self) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.toLowerCase();
    }
    
    public static String PrimitiveToUpperCase(String self) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.toUpperCase();
    }
    
    public static String PrimitiveTrim(String self) throws Error{
        if(self == null){
            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
            error.SetErrorMessage("Text variable is undefined.");
            throw(error);
        }
        
        return self.trim();
    }
//    
//    public static void main(String[] args) {
//        Text text = new Text();
//        Text text2 = new Text();
//        text.SetValueNative("Hi");
//        text2.SetValueNative("Hi");
//        
//        int hash = text.GetHashCode();
//        int hash2 = text2.GetHashCode();
//    }
}
