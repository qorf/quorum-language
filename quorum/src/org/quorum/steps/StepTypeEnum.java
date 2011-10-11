/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

/**
 *
 * Labels for each step stored in the expression location table. This is used 
 * for the bytecode generation wherever a swop might be needed or wherever you 
 * need to look forward to determine when to process an expression's bytecode 
 * operations.
 * 
 * @author Melissa Stefik
 */
public enum StepTypeEnum {
    EXPRESSION("expression"),
    ASSIGNMENT("assignment"),
    METHOD_CALL("method_call"),
    JUMP_STEP("jump_step");

    String stepType;
    StepTypeEnum(String st) {
        stepType = st;
    }

    @Override
    public String toString() {
        return stepType;
    }
}
