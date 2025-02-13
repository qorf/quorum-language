class plugins_quorum_Libraries_Game_Graphics_Number32BitBuffer_ 
{
    constructor() {
        this.floatArray;
        this.size;
    }
    
    SetSize$quorum_integer(newSize)
    {
        this.floatArray = new Float32Array(newSize);
        this.size = newSize;
    };
    
    GetSize()
    {
        return this.size;
    };
    
    GetMaxSize()
    {
        return this.floatArray.length;
    };
    
    Set$quorum_Libraries_Containers_Number32BitArray(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            this.floatArray[i] = array.Get$quorum_integer(i);
        }
        
        this.size = array.GetSize();
    };
    
    Set$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            this.floatArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    Dispose()
    {
        this.floatArray = null;
    };
    
    GetArray()
    {
        return this.floatArray;
    };
    
    Get$quorum_integer(index)
    {
        return this.floatArray[index];
    };
}