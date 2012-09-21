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
}
