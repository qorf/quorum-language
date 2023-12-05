package plugins.quorum.Libraries.Language.Support;

public class TextBuilder {
    public java.lang.Object me_ = null;
    StringBuilder builder = new StringBuilder();

    public void Append(String value) {
        builder.append(value);
    }
    public int GetMaxSize() {
        return builder.capacity();
    }
    public void SetMaxSize(int size) {
        builder.ensureCapacity(size);
    }
    public int GetSize() {
        return builder.length();
    }
    public String ToText() {
        return builder.toString();
    }
}
