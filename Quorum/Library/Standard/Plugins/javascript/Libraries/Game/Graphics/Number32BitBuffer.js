function plugins_quorum_Libraries_Game_Graphics_Number32BitBuffer_() 
{
    var floatArray;
    var size;
    
    this.SetSize$quorum_integer = function(newSize)
    {
        floatArray = new Float32Array(newSize);
        size = newSize;
    };
    
    this.GetSize = function()
    {
        return size;
    };
    
    this.GetMaxSize = function()
    {
        return floatArray.length;
    };
    
    this.Set$quorum_Libraries_Containers_Number32BitArray = function(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            floatArray[i] = array.Get$quorum_integer(i);
        }
        
        size = array.GetSize();
    };
    
    this.Set$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer = function(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            floatArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    this.Dispose = function()
    {
        floatArray = null;
    };
    
    this.GetArray = function()
    {
        return floatArray;
    };
    
    this.Get$quorum_integer = function(index)
    {
        return floatArray[index];
    };
}