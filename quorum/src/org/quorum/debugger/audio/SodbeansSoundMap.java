/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.ActionMap;
import org.quorum.steps.IntermediateConstants;

/**
 * Generates sound for various parts of the compiler as a program is executing.
 * 
 * @author Andreas Stefik and Neelima Samsani
 */
public class SodbeansSoundMap extends ActionMap{

    @Override
    public void loadMap() {
        actions.put(IntermediateConstants.MOVE_STEP.getName(), new MoveAction());
        actions.put(IntermediateConstants.ASSIGNMENT_STEP.getName(), new AssignmentAction());
        actions.put(IntermediateConstants.ASSIGNMENT_AUTOBOX_STEP.getName(), new AssignmentAutoBoxAction());
        actions.put(IntermediateConstants.CONDITIONAL_DETECT_JUMP_STEP.getName(), new ConditionalJumpDetectAction());
        actions.put(IntermediateConstants.CONDITIONAL_FALSE_JUMP_STEP.getName(), new ConditionalJumpIfAction());
        actions.put(IntermediateConstants.CONDITIONAL_JUMP_STEP.getName(), new ConditionalJumpLoopAction());
        actions.put(IntermediateConstants.CALL_STEP.getName(), new CallAction());
        actions.put(IntermediateConstants.CREATE_OBJECT_STEP.getName(), new CreateObjectAction());
        actions.put(IntermediateConstants.RETURN_STEP.getName(), new ReturnAction());
        actions.put(IntermediateConstants.EXECUTION_START.getName(), new ExecutionStartAction());
        actions.put(IntermediateConstants.EXECUTION_END.getName(), new ExecutionEndAction());
        actions.put(IntermediateConstants.PRINT_STEP.getName(), new PrintStepAction());
        actions.put(IntermediateConstants.VIRTUAL_MACHINE_ERROR.getName(), new VirtualMachineErrorAction());
    }

}
