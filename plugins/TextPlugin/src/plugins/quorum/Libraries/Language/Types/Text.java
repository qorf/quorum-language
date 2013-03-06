/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.Language.Types.Text$Interface;

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
    
    public static int PrimitiveGetLength(String self) {
        return self.length();
    }
    
    public static int PrimitiveGetHashCode(String self) {
        return self.hashCode();
    }
    
    public static String PrimitiveGetCharacter(String self, int index){
        return "" + self.charAt(index);
    }

    public static String PrimitiveGetSubstring(String self,int startIndex, int endIndex){
        return "" + self.substring(startIndex, endIndex);
    }
    
    public static String PrimitiveAppend(String self, String addition){
        StringBuilder s = new StringBuilder();
        s.append(self);
        s.append(addition);
        return s.toString();
    }
    
    public static Array$Interface PrimitiveSplit(String self, String delimiter){
        String[] split = self.split(delimiter);
        quorum.Libraries.Containers.Array a = new quorum.Libraries.Containers.Array();
        
        for(int i = 0; i < split.length; i++){
            Text$Interface t = new quorum.Libraries.Language.Types.Text();
            t.SetValue(split[i]);
            a.AddNative((Object$Interface)t);
        }
        return a;
    }
    
    public static boolean PrimitiveEquals(String self, Object$Interface obj){
        Text$Interface t = (Text$Interface)obj;
        return self.equals(t.GetValue());
    }
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompare(String self, quorum.Libraries.Language.Object$Interface obj){
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
    
    public static quorum.Libraries.Language.Support.CompareResult$Interface PrimitiveCompareIgnoringCase(String self, quorum.Libraries.Language.Object$Interface obj){
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
    
    public static boolean PrimitiveContains(String self, String right){
        return self.contains(right); 
    }
    
    public static boolean PrimitiveEndsWith(String self, String suffix){
        return self.endsWith(suffix);
    }
    
    public static boolean PrimitiveStartsWith(String self, String prefix){
        return self.startsWith(prefix);
    }
    
    public static boolean PrimitiveEqualsIgnoringCase(String self, Object$Interface right){
        Text$Interface t = (Text$Interface)right;
        return self.equalsIgnoreCase(t.GetValue());
    }
    
    public static int PrimitiveIndexOf(String self, String right){
        return self.indexOf(right);
    }
    
    public static int PrimitiveIndexOf(String self, String right, int index){
        return self.indexOf(right, index);
    }
    
    public static boolean PrimitiveIsEmpty(String self){
        return self.isEmpty();
    }
    
    public static String PrimitiveReplace(String self, String old, String replacement){
        return self.replace(old, replacement);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex, int endIndex){
        return self.substring(startIndex, endIndex);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex){
        return self.substring(startIndex);
    }
    
    public static String PrimitiveToLowerCase(String self){
        return self.toLowerCase();
    }
    
    public static String PrimitiveToUpperCase(String self){
        return self.toUpperCase();
    }
    
    public static String PrimitiveTrim(String self){
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
