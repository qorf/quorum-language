function plugins_quorum_Libraries_Compute_BitwiseOperations_() {
    this.And$quorum_integer$quorum_integer = function (left, right) {
        return left & right;
    };
    
    this.Or$quorum_integer$quorum_integer = function (left, right) {
        return left | right;
    };
    
    this.ExclusiveOr$quorum_integer$quorum_integer = function (left, right) {
        return left ^ right;
    };
    
    this.Negate$quorum_integer = function (value) {
        return ~value;
    };
    
    this.ShiftLeft$quorum_integer$quorum_integer = function (value, amount) {
        return value << amount;
    };
    
    this.ShiftRight$quorum_integer$quorum_integer = function (value, amount) {
        return value >> amount;
    };
    
    this.ShiftRightPositive$quorum_integer$quorum_integer = function (value, amount) {
        return value >>> amount;
    };
    
}
