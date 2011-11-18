/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Structure;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author Jeff Wilson
 */
public class RandomPlugin implements Plugin {
    public static final String KEY = "Libraries.Compute.Random";
    private static Random random = new Random();
    private static final String INITIALIZE_NATIVE = "InitializeNative";
    private static final String SET_SEED = "SetSeed:number";
    private static final String RANDOM_INTEGER = "RandomInteger";
    private static final String RANDOM_INTEGER_NATIVE = "RandomIntegerNative:integer";
    private static final String RANDOM_NUMBER = "RandomNumber";
    private static final String RANDOM_BOOLEAN = "RandomBoolean";
    private static final Logger logger = Logger.getLogger(RandomPlugin.class.getName());

    public PluginReturn debug(PluginCall call) {
        return null;
    }

    public PluginReturn undebug(PluginCall call) {
        return null;
    }

    public boolean canDebugBackward() {
        return false;
    }

    public boolean isValidCall(PluginCall call) {
        return false;
    }

    public String getName() {
        return "Random";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public void reset() {
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        if (action.equals(INITIALIZE_NATIVE)) {
            // Get the current UNIX time in milliseconds and use that as our seed.
            long seed = System.currentTimeMillis();
            random.setSeed(seed);
        }
        else if (action.equals(SET_SEED)) {
            ExpressionValue seedArgument = call.getArgument("seed");
            long seed = (long)seedArgument.getResult().number;
            
            random.setSeed(seed);
        }
        else if (action.equals(RANDOM_INTEGER)) {
            // 32-bit signed integers in quorum means a maximum of 2^31 - 1.
            int randomNumber = random.nextInt(2147483647);
            
            try {
                setPluginReturnValue(ret, randomNumber);
            }
            catch (IllegalArgumentException exception) {
                logger.log(Level.INFO, "Illegal argument.", exception);
            }
        }
        else if (action.equals(RANDOM_INTEGER_NATIVE)) {
            ExpressionValue maximumArgument = call.getArgument("maximum");
            int maximum = maximumArgument.getResult().integer;
            int randomNumber = random.nextInt(maximum + 1);

            try {
                setPluginReturnValue(ret, randomNumber);
            }
            catch (IllegalArgumentException exception) {
                logger.log(Level.INFO, "Illegal argument.", exception);
            }
        }
        else if (action.equals(RANDOM_NUMBER)) {
            double randomNumber = random.nextDouble();
            
            try {
                setPluginReturnValue(ret, randomNumber);
            }
            catch (IllegalArgumentException exception) {
                logger.log(Level.INFO, "Illegal argument.", exception);
            }
        }
        else if (action.equals(RANDOM_BOOLEAN)) {
            boolean randomBool = random.nextBoolean();
            
            try {
                setPluginReturnValue(ret, randomBool);
            }
            catch (IllegalArgumentException exception) {
                logger.log(Level.INFO, "Illegal argument.", exception);
            }
        }

        return ret;
    }
    
    private void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
        ret.setReturnValue(value);
    }
    
    private void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    private void setPluginReturnValue(PluginReturn ret, String str){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().text = str;
        ret.setReturnValue(value);
    }

    private void setPluginReturnValue(PluginReturn ret, boolean bool){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = bool;
        ret.setReturnValue(value);
    }

    private void setPluginReturnValue(PluginReturn ret, Structure obj){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().structure = obj;
        ret.setReturnValue(value);
    }

    private void setExpressionValue(ExpressionValue exp, String str){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        exp.getResult().text = str;
    }

    private void setExpressionValue(ExpressionValue exp, double num){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        exp.getResult().number = num;
    }

    private void setExpressionValue(ExpressionValue exp, Boolean bool){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        exp.getResult().boolean_value = bool;
    }
}