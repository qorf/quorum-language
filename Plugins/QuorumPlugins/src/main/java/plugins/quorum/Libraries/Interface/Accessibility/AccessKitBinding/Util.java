package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

import java.nio.charset.StandardCharsets;

final class Util {
    /* Throws if a native pointer has been freed or given away. */
    static void checkActive(long ptr) {
        if (ptr == 0) {
            throw new IllegalStateException("already dropped");
        }
    }

    /* UTF-8 bytes for the natives; null stays null (the native clears the property). */
    static byte[] bytesFromString(String value) {
        if (value == null) {
            return null;
        }
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
