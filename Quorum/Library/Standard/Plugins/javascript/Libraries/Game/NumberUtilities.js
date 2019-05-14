function plugins_quorum_Libraries_Game_NumberUtilities_() 
{
    
    this.EncodeNumberAsInteger$quorum_number = function(target) 
    {
        return target;
    };
    
    this.EncodeIntegerAsNumber$quorum_integer = function(target) 
    {
        var buffer = new ArrayBuffer(4);
        (new Uint32Array(buffer))[0] = target;
        var returnValue = new Float32Array(buffer)[0];
        
        return returnValue;
    };
}
