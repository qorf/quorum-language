/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author Andreas Stefik and Elliot Motl
 */
public class MathPlugin implements Plugin{
    public static final String KEY = "Libraries.Compute.Math";
    private static final String COSINE = "Cosine:number";
    private static final String SINE = "Sine:number";
    private static final String TANGENT = "Tangent:number";
    private static final String HYPERBOLIC_COSINE = "HyperbolicCosine:number";
    private static final String HYPERBOLIC_SINE = "HyperbolicSine:number";
    private static final String HYPERBOLIC_TANGENT = "HyperbolicTangent:number";
    private static final String INVERSE_COSINE = "InverseCosine:number";
    private static final String INVERSE_SINE = "InverseSine:number";
    private static final String INVERSE_TANGENT = "InverseTangent:number";
    private static final String INVERSE_HYPERBOLIC_COSINE = "InverseHyperbolicCosine:number";
    private static final String INVERSE_HYPERBOLIC_SINE = "InverseHyperbolicSine:number";
    private static final String INVERSE_HYPERBOLIC_TANGENT = "InverseHyperbolicTangent:number";
    private static final String LOGARITHM = "Logarithm:number";
    private static final String NATURAL_LOGARITHM = "NaturalLogarithm:number";
    private static final String SQUARE_ROOT = "SquareRoot:number";
    private static final String POWER = "RaiseToPower:number:number";
    private static final String FLOOR = "Floor:number";
    private static final String CEILING = "Ceiling:number";
    private static final String ROUND = "Round:number";

    @Override
    public boolean canDebugBackward() {
        return true;
    }

    @Override
    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();


        if(action.equals(COSINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.cos(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(SINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.sin(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(TANGENT)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.tan(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(HYPERBOLIC_COSINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.cosh(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(HYPERBOLIC_SINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.sinh(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(HYPERBOLIC_TANGENT)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.tanh(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(INVERSE_COSINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.acos(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(INVERSE_SINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.asin(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(INVERSE_TANGENT)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            arg = Math.atan(arg);
            setPluginReturnValue(ret, arg);
        }
        else if(action.equals(INVERSE_HYPERBOLIC_COSINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            //log[z+(z^2 -1)^1/2]
            double a = arg*arg;
            a = a-1;
            a = Math.sqrt(a);
            a = a + arg;
            setPluginReturnValue(ret, Math.log(a));
        }
        else if(action.equals(INVERSE_HYPERBOLIC_SINE)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            //log[z+(z^2 + 1)^1/2]
            double a = arg*arg;
            a = a+1;
            a = Math.sqrt(a);
            a = a + arg;
            setPluginReturnValue(ret, Math.log(a));
        }
        else if(action.equals(INVERSE_HYPERBOLIC_TANGENT)) {
            ExpressionValue argument = call.getArgument("value");
            double arg = argument.getResult().number;
            //1/2(log((1+z)/(1-z)))
            double a = Math.log((1 + arg) / (1 - arg));
            setPluginReturnValue(ret, a/2);
        }
        else if(action.equals(LOGARITHM)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret,Math.log10(argument.getResult().number));
        }
        else if(action.equals(NATURAL_LOGARITHM)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret,Math.log(argument.getResult().number));
        }
        else if(action.equals(SQUARE_ROOT)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret,Math.sqrt(argument.getResult().number));
        }
        else if(action.equals(POWER)){
            ExpressionValue argument = call.getArgument("value");
            ExpressionValue exponent = call.getArgument("power");
            setPluginReturnValue(ret, Math.pow(argument.getResult().number, exponent.getResult().number));
        }
        else if(action.equals(FLOOR)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret, Math.floor(argument.getResult().number));
        }
        else if(action.equals(CEILING)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret, Math.ceil(argument.getResult().number));
        }
        else if(action.equals(ROUND)){
            ExpressionValue argument = call.getArgument("value");
            setPluginReturnValue(ret, ((double)Math.round(argument.getResult().number)));
        }

        return ret;
    }

    private void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    @Override
    public boolean isValidCall(PluginCall call) {
        return false;
    }

    @Override
    public String getName() {
        return "Math";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    public PluginReturn unexecute(PluginCall call) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public void reset() {
    }

}
