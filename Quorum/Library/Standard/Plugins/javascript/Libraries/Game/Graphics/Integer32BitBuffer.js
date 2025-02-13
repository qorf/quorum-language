class plugins_quorum_Libraries_Game_Graphics_Integer32BitBuffer_
{
    constructor() {
        this.intArray;
        this.size;
        this.writePosition;
    }

    SetSize$quorum_integer(newSize)
    {
        intArray = new Int32Array(newSize);
        size = newSize;
        writePosition = 0;
    };
    
    GetSize()
    {
        return size;
    };
    
    GetMaxSize()
    {
        return intArray.length;
    };
    
    Set$quorum_Libraries_Containers_Integer32BitArray(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            intArray[i] = array.Get$quorum_integer(i);
        }
        
        size = array.GetSize();
    };
    
    Set$quorum_integer$quorum_Libraries_Containers_Integer32BitArray$quorum_integer$quorum_integer(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            intArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    Dispose()
    {
        intArray = null;
    };
    
    GetArray()
    {
        return intArray;
    };
    
    ResetWritePosition()
    {
        writePosition = 0;
    };

    GetWritePosition()
    {
        return writePosition;
    };

    SetWritePosition(newPosition)
    {
        writePosition = newPosition;
    };

    Write(array)
    {
        for (var i = 0; i < array.length; i++, writePosition++)
        {
            intArray[writePosition] = array.Get$quorum_integer(i);
        }
    };
    
    Get$quorum_integer(index)
    {
        return intArray[index];
    };
}
