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
    
    public String GetCharacterNative(int index) {
        String ret = "" + text.charAt(index);
        return ret;
    }
    
    public String GetSubstringNative(int startIndex, int endIndex) {
        return text.substring(startIndex, endIndex);
    }
    
    public int CompareInt(String left, String right) {
        return left.compareTo(right);
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
