class plugins_quorum_Libraries_Game_Graphics_Number32BitBuffer_ 
{
    constructor() {
        this.floatArray;
        this.size;
    }
    
    SetSize$quorum_integer(newSize)
    {
        floatArray = new Float32Array(newSize);
        size = newSize;
    };
    
    GetSize()
    {
        return size;
    };
    
    GetMaxSize()
    {
        return floatArray.length;
    };
    
    Set$quorum_Libraries_Containers_Number32BitArray(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            floatArray[i] = array.Get$quorum_integer(i);
        }
        
        size = array.GetSize();
    };
    
    Set$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            floatArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    Dispose()
    {
        floatArray = null;
    };
    
    GetArray()
    {
        return floatArray;
    };
    
    Get$quorum_integer(index)
    {
        return floatArray[index];
    };
}