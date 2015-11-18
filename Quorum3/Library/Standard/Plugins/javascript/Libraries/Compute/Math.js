function plugins_quorum_Libraries_Compute_Math_() {
    this.Floor$quorum_number = function (value) {
        return Math.floor(value);
    };

    this.Ceiling$quorum_number = function (value) {
        return Math.ceil(value);
    };

    this.Sine$quorum_number = function (value) {
        return Math.sin(value);
    };

    this.Cosine$quorum_number = function (value) {
        return Math.cos(value);
    };

    this.Tangent$quorum_number = function (value) {
        return Math.tan(value);
    };

    this.HyperbolicSine$quorum_number = function (value) {
        return (Math.pow(Math.E, value) - Math.pow(Math.E, -1 * value)) / 2.0;
    };

    this.HyperbolicCosine$quorum_number = function (value) {
        return (Math.pow(Math.E, value) + Math.pow(Math.E, -1 * value)) / 2.0;
    };

    this.HyperbolicTangent$quorum_number = function (value) {
        return this.HyperbolicSine$quorum_number(value) / this.HyperbolicCosine$quorum_number(value);
    };

    this.InverseSine$quorum_number = function (value) {
        return Math.asin(value);
    };

    this.InverseCosine$quorum_number = function (value) {
        return Math.acos(value);
    };

    this.InverseTangent$quorum_number = function (value) {
        return Math.atan(value);
    };

    this.InverseTangent$quorum_number$quorum_number = function (x, y) {
        return Math.atan(x, y);
    };

    this.InverseHyperbolicSine$quorum_number = function (value) {
        if (value === -Math.Infinity) {
            return value;
        } else {
            return Math.log(value + Math.sqrt(value * value + 1));
        }
    };

    this.InverseHyperbolicCosine$quorum_number = function (value) {
        return Math.log(value + Math.sqrt(value * value - 1));
    };

    this.InverseHyperbolicTangent$quorum_number = function (value) {
        return Math.log((1+value)/(1-value)) / 2;
    };

    this.Logarithm$quorum_number = function (value) {
        return Math.log(value) / Math.LN10;
    };

    this.NaturalLogarithm$quorum_number = function (value) {
        return Math.log(value);
    };

    this.SquareRoot$quorum_number = function (value) {
        return Math.sqrt(value);
    };

    this.Round$quorum_number = function (value) {
        return Math.round(value);
    };

    this.RaiseToPower$quorum_number$quorum_number = function (value, power) {
        return Math.pow(value, power);
    };

    this.DegreesToRadians$quorum_number = function (degrees) {
        return degrees * Math.PI / 180;
    };

    this.RadiansToDegrees$quorum_number = function (radians) {
        return 180 * radians  / Math.PI;
    };
}