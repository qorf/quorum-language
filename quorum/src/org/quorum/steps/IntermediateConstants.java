/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

/**
 *
 * @author Andreas Stefik
 */
public enum IntermediateConstants {
    ASSIGNMENT_STEP ("AssignmentStep"),
    ASSIGNMENT_AUTOBOX_STEP ("AssignmentAutoBoxStep"),
    MOVE_STEP       ("MoveStep"),
    CONDITIONAL_DETECT_JUMP_STEP     ("ConditionalDetectJumpStep"),
    CONDITIONAL_FALSE_JUMP_STEP     ("ConditionalFalseJumpStep"),
    CONDITIONAL_JUMP_STEP   ("ConditionalJumpStep"),
    PRINT_STEP("PrintStep"),
    SPEAK_STEP("SpeakStep"),
    INPUT_STEP("InputStep"),
    CALL_STEP ("CallStep"),
    CREATE_OBJECT_STEP("CreateObjectStep"),
    CREATE_MAIN_OBJECT_STEP("CreateMainObjectStep"),
    RETURN_STEP("ReturnStep"),
    DATA_STRUCTURE_ASSIGNMENT_STEP("DataStructureAssignmentStep"),
    DATA_STRUCTURE_CREATION_STEP("DataStructureCreationStep"),
    EXECUTION_START("ExecutionStart"),
    EXECUTION_END("ExecutionEnd"),
    VIRTUAL_MACHINE_ERROR("VirtualMachineError"),
    MOVE_NULL_TO_OBJET_VARIABLE_STEP("MoveNullToObjectVariableStep");

    private String name;
    IntermediateConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
