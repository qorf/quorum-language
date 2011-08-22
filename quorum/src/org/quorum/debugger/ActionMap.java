/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import java.util.HashMap;
import org.quorum.debugger.audio.VirtualMachineErrorAction;
import org.quorum.vm.interfaces.VirtualMachineEvent;
import org.quorum.event.VirtualMachineErrorState;
import org.quorum.execution.ExecutionStep;

/**
 *
 * @author nsamsan
 */
public abstract class ActionMap {
    protected HashMap<String, Action> actions;
    public abstract void loadMap();
    public ActionMap(){
        actions=new HashMap<String, Action>();
        loadMap();
    }

    public Action getAction(VirtualMachineEvent event) {
        ExecutionStep step = event.getStep();
        if(step != null) {
            String key = step.getStaticKey();
            Action action = actions.get(key);
            if(action != null) {
                if(event instanceof VirtualMachineErrorState){
                   ((VirtualMachineErrorAction)action).setThrownException(((VirtualMachineErrorState) event).getErrorManager());
                }
                action.setStep(step);
                action.setForward(event.isExecuteEvent());
                return action;
            }
        }
        else {
            return actions.get(event.getEventKey());
        }
        return null;
    }

}
