/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

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
    
    public int PrimitiveGetLength(String self) {
        return self.length();
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
    
    public int PrimitiveGetHashCode(String self) {
        return self.hashCode();
    }
    
    public static String PrimitiveGetCharacter(String self, int index){
        return "" + self.charAt(index);
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
