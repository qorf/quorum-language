package plugins.quorum.Libraries.Language;

import quorum.Libraries.Language.Object_;

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

    /* This action is equivalent to the GetHashCode action in Libraries.Language.Object and is used to route
        calls from this place in Quorum-11's inheritance system.
     */
    public static int ObjectGetHashCode(Object_ object) {
        return object.hashCode();
    }

    /* This action is equivalent to the Equals action in Libraries.Language.Object and is used to route
        calls from this place in Quorum-11's inheritance system.
     */
    public static boolean ObjectEquals(Object_ me, Object_ object) {
        int myHash = me.GetHashCode();
        int otherHash = object.GetHashCode();
        return myHash == otherHash;
    }

    /* This action is equivalent to the Compare action in Libraries.Language.Object and is used to route
        calls from this place in Quorum-11's inheritance system.
     */
    public static int ObjectCompare(Object_ me, Object_ object) {
        int myHash = me.GetHashCode();
        int oHash = object.GetHashCode();

        if (myHash < oHash) {
            return -1;
        } else if (myHash == oHash) {
            return 0;
        } else {
            return 1;
        }
    }
}
