/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

/**
 * The OpcodeType class gives the broad category for what kind of
 * opcode a particular value is. This basically allows us to categorize
 * opcodes broadly and avoids using long lists of instance of operations.
 * 
 * @author Andreas Stefik
 */
public enum OpcodeType {
    ROOT_EXPRESSION,
    ASSIGNMENT,
    METHOD_CALL,
    LOOP;
    //fill in other categories as/if needed.
}
