function plugins_quorum_Libraries_Game_NumberUtilities_() 
{
    // These functions are typically used to convert values between data types
    // without modifying the bits. Since JavaScript isn't statically typed, this
    // isn't an issue here.
    
    this.EncodeNumberAsInteger$quorum_number = function(target) 
    {
        return target;
    };
    
    this.EncodeIntegerAsNumber$quorum_integer = function(target) 
    {
        return target;
    };
}
