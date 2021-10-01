function plugins_quorum_Libraries_Game_Graphics_Integer32BitBuffer_() 
{
    var intArray;
    var size;
    var writePosition;
    
    this.SetSize$quorum_integer = function(newSize)
    {
        intArray = new Int32Array(newSize);
        size = newSize;
        writePosition = 0;
    };
    
    this.GetSize = function()
    {
        return size;
    };
    
    this.GetMaxSize = function()
    {
        return intArray.length;
    };
    
    this.Set$quorum_Libraries_Containers_Integer32BitArray = function(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            intArray[i] = array.Get$quorum_integer(i);
        }
        
        size = array.GetSize();
    };
    
    this.Set$quorum_integer$quorum_Libraries_Containers_Integer32BitArray$quorum_integer$quorum_integer = function(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            intArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    this.Dispose = function()
    {
        intArray = null;
    };
    
    this.GetArray = function()
    {
        return intArray;
    };
    
    this.ResetWritePosition = function()
    {
        writePosition = 0;
    };

    this.GetWritePosition = function()
    {
        return writePosition;
    };

    this.SetWritePosition = function(newPosition)
    {
        writePosition = newPosition;
    };

    this.Write = function(array)
    {
        for (var i = 0; i < array.length; i++, writePosition++)
        {
            intArray[writePosition] = array.Get$quorum_integer(i);
        }
    };
}
