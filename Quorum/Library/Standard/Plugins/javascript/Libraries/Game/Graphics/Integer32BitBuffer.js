class plugins_quorum_Libraries_Game_Graphics_Integer32BitBuffer_
{
    constructor() {
        this.intArray;
        this.size;
        this.writePosition;
    }

    SetSize$quorum_integer(newSize)
    {
        this.intArray = new Int32Array(newSize);
        this.size = newSize;
        this.writePosition = 0;
    };
    
    GetSize()
    {
        return this.size;
    };
    
    GetMaxSize()
    {
        return this.intArray.length;
    };
    
    Set$quorum_Libraries_Containers_Integer32BitArray(array)
    {
        for (var i = 0; i < array.GetSize(); i++)
        {
            this.intArray[i] = array.Get$quorum_integer(i);
        }
        
        this.size = array.GetSize();
    };
    
    Set$quorum_integer$quorum_Libraries_Containers_Integer32BitArray$quorum_integer$quorum_integer(targetOffset, array, sourceOffset, count)
    {
        var source = sourceOffset, target = targetOffset;
        for (var i = 0; i < count; i++, source++, target++)
        {
            this.intArray[target] = array.Get$quorum_integer(source);
        }
    };
    
    Dispose()
    {
        this.intArray = null;
    };
    
    GetArray()
    {
        return this.intArray;
    };
    
    ResetWritePosition()
    {
        this.writePosition = 0;
    };

    GetWritePosition()
    {
        return this.writePosition;
    };

    SetWritePosition(newPosition)
    {
        this.writePosition = newPosition;
    };

    Write(array)
    {
        for (var i = 0; i < array.length; i++, writePosition++)
        {
            this.intArray[writePosition] = array.Get$quorum_integer(i);
        }
    };
    
    Get$quorum_integer(index)
    {
        return this.intArray[index];
    };
}
