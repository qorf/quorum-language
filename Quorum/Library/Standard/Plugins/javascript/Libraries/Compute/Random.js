function plugins_quorum_Libraries_Compute_Random_() {
    this.InitializeNative = function () {};
    
    this.RandomIntegerNative$quorum_integer = function (maximum) {
        return Math.floor(Math.random() * (maximum + 1));
    };
    
    this.SetSeed$quorum_number = function (seed) {};
    
    this.RandomInteger = function () {
        return Math.floor(Math.random() * (Number.MAX_VALUE + 1));
    };
    
    this.RandomNumber = function () {
        return Math.random();
    };

    this.RandomBoolean = function () {
        if (Math.random() < 0.5)
            return true;
        else
            return false;
    };

}