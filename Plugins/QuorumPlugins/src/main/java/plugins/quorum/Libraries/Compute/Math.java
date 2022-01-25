/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Compute;

/**
 * A plugin implementation for Libraries.Compute.Math
 * 
 * @author Andreas Stefik
 */
public class Math {
    public java.lang.Object me_ = null;
    public double Floor(double value){
        return java.lang.Math.floor(value); 
    }
    public double Ceiling(double value){ 
        return java.lang.Math.ceil(value); 
    }

    public double Sine(double value){ 
        return java.lang.Math.sin(value); 
    }

    public double Cosine(double value){ 
        return java.lang.Math.cos(value); 
    }

    public double Tangent(double value){ 
        return java.lang.Math.tan(value); 
    }

    public double HyperbolicSine(double value){ 
        return java.lang.Math.sinh(value);
    }

    public double HyperbolicCosine(double value){ 
        return java.lang.Math.cosh(value); 
    }

    public double HyperbolicTangent(double value){ 
        return java.lang.Math.tanh(value);
    }

    public double InverseSine(double value){ 
        return java.lang.Math.asin(value);
    }

    public double InverseCosine(double value){ 
        return java.lang.Math.acos(value);
    }

    public double InverseTangent(double x, double y)
    {
        return java.lang.Math.atan2(y, x);
    }
    
    public double InverseTangent(double value){ 
        return java.lang.Math.atan(value);
    }

    public double InverseHyperbolicSine(double value){
        //ln[z+(z^2 + 1)^1/2]
        //http://mathworld.wolfram.com/InverseHyperbolicFunctions.html
        double a = value*value;
        a = a + 1;
        a = java.lang.Math.sqrt(a);
        a = a + value;
        return java.lang.Math.log(a);
    }

    public double InverseHyperbolicCosine(double value){ 
        //ln[z+(z^2 -1)^1/2]
        double a = value*value;
        a = a-1;
        a = java.lang.Math.sqrt(a);
        a = a + value;
        return java.lang.Math.log(a);
    }

    public double InverseHyperbolicTangent(double value){ 
        // 1/2 * (ln(1+z) - ln (1-z))
        //http://mathworld.wolfram.com/InverseHyperbolicFunctions.html
        double left = java.lang.Math.log((1 + value));
        double right = java.lang.Math.log((1 - value));
        return 0.5 * (left - right); 
    }

    public double Logarithm(double value){ 
        return java.lang.Math.log10(value);
    }

    public double NaturalLogarithm(double value){ 
        return java.lang.Math.log(value);
    }

    public double SquareRoot(double value){ 
        return java.lang.Math.sqrt(value); 
    }

    public double Round(double value){ 
        return java.lang.Math.round(value); 
    }
    
    public double RoundToNearestInteger(double x) {
        double y = Floor(x);
        double d = x - y;

        if (d > 0.5) {
            if (y == -1.0) {
                return -0.0; // Preserve sign of operand
            }
            return y+1.0;
        }

        if (d < 0.5) {
            return y;
        }

        /* half way, round to even */
        long z = (long) y;
        return (z & 1) == 0 ? y : y + 1.0;
    }

    public double RaiseToPower(double value, double power){ 
        return java.lang.Math.pow(value, power); 
    }
    
    public double DegreesToRadians(double value){
        return java.lang.Math.toRadians(value);
    }
    
    public double RadiansToDegrees(double value){
        return java.lang.Math.toDegrees(value);
    }
}

