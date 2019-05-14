
package plugins.quorum.Libraries.Interface.Vibration;

import java.util.ArrayList;


public class PatternArray {
    public Object me_;
    ArrayList<Integer> content;
    
    public PatternArray() {
        content = new ArrayList<Integer>();
    }
    
    public void Add(int item) {
        content.add(item);
    }
    
    public void Remove(int index) {
        content.remove(index);
    }
    
    public int Get(int index) {
        return content.get(index);
    }
    
    public void RemoveAll() {
        content.clear();
    }

    public int GetSize() {
        return content.size();
    }
    
}
