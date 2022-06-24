package plugins.quorum.Libraries.Language;

import java.util.HashMap;

/*
    This class manages shared classes in Quorum. It is a helper that is not directly exposed from the Quorum side.
    As such, one should not create a class with this exact package and name, like any plugin.
 */
public class SharedClass {
    public final static HashMap<String, java.lang.Object> STATIC_OBJECTS = new  HashMap<String, java.lang.Object>();
    public static java.lang.Object GetStaticClass(String key) {
        return STATIC_OBJECTS.get(key);
    }
    public static void PutStaticClass(String key, java.lang.Object value) {
        STATIC_OBJECTS.put(key, value);
    }
}
