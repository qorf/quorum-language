package org.quorum.plugins;

import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Structure;
import org.quorum.symbols.TypeDescriptor;
import java.util.HashMap;

/**
 *
 * @author Elliot
 */
public class DateTimePlugin implements Plugin{
    public static final String KEY = "Libraries.System.DateTime";
    public static final String GET_YEAR = "GetYear";
    public static final String GET_MONTH = "GetMonth";
    public static final String GET_DAY_OF_MONTH = "GetDayOfMonth";
    public static final String GET_DAY_OF_WEEK = "GetDayOfWeek";
    public static final String GET_HOUR = "GetHour";
    public static final String GET_MINUTE = "GetMinute";
    public static final String GET_SECOND = "GetSecond";
    public static final String GET_TIMEZONE = "GetTimeZone";
    public static final String IS_DST = "IsDaylightSavings";
    public static final String GET_EPOCH_TIME = "GetEpochTime";
    public static final String SET_EPOCH_TIME = "SetEpochTime:number";
    public static final String SET_TIME_ZONE = "SetTimeZone:integer"; 
    
    private HashMap<Integer, QuorumDateTime> instances;
    
    public DateTimePlugin() {
        instances = new HashMap<Integer, QuorumDateTime>();
    }
    
     public PluginReturn debug(PluginCall call) {
        //return execute(call);
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
        return "DateTime";
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
        instances = new HashMap<Integer, QuorumDateTime>();
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        QuorumDateTime inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Create it.
            inst = new QuorumDateTime();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        if (action.equals(GET_YEAR)) {
            setPluginReturnValue(ret, inst.GetYear());
        }
        else if (action.equals(GET_MONTH)) {
            setPluginReturnValue(ret, inst.GetMonth());
        }
        else if (action.equals(GET_DAY_OF_MONTH)) {
            setPluginReturnValue(ret, inst.GetDayOfMonth());
        }
        else if (action.equals(GET_DAY_OF_WEEK)) {
            setPluginReturnValue(ret, inst.GetDayOfWeek());
        }
        else if (action.equals(GET_HOUR)) {
            setPluginReturnValue(ret, inst.GetHour());
        }
        else if (action.equals(GET_MINUTE)) {
            setPluginReturnValue(ret, inst.GetMinute());
        }
        else if (action.equals(GET_SECOND)) {
            setPluginReturnValue(ret, inst.GetSecond());
        }
        else if (action.equals(GET_TIMEZONE)) {
            setPluginReturnValue(ret, inst.GetTimeZone());
        }
        else if (action.equals(IS_DST)) {
            setPluginReturnValue(ret, inst.IsDaylightSavings());
        }
        else if (action.equals(GET_EPOCH_TIME)) {   
            setPluginReturnValue(ret, System.currentTimeMillis());
        }
        else if (action.equals(SET_EPOCH_TIME)) {
            // Set the internal representation of time to the given epoch time.
            long epochTime = (long)call.getArgument("epochTime").getResult().number;
            inst.SetEpochTime(epochTime);
        }
        else if (action.equals(SET_TIME_ZONE)) {
            int offset = call.getArgument("timeZoneOffset").getResult().integer;
            inst.SetTimeZone(offset);
        }   
        return ret;
    }

    private void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    private void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
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

}
