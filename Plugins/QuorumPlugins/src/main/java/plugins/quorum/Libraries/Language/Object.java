/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language;

import java.util.HashMap;

/**
 * 
 * @author Andreas Stefik
 */
public class Object {
    public java.lang.Object me_ = null;
    public final static HashMap<String, java.lang.Object> STATIC_OBJECTS = new  HashMap<String, java.lang.Object>();
    public int GetHashCode() {
        return me_.hashCode();
    }
    
    public static java.lang.Object GetStaticClass(String key) {
        return STATIC_OBJECTS.get(key);
    }
    
    public static void PutStaticClass(String key, java.lang.Object value) {
        STATIC_OBJECTS.put(key, value);
    }
}
