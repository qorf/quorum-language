/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

/**
 *
 * @author Andreas Stefik
 */
public enum CodeCompletionType {
    LOCAL_VARIABLE, 
    PARAMETER,
    PRIVATE_FIELD_VARIABLE, 
    PUBLIC_FIELD_VARIABLE,
    PRIVATE_ACTION, 
    PUBLIC_ACTION,
    PRIVATE_SYSTEM_ACTION, 
    PUBLIC_SYSTEM_ACTION,
    PRIVATE_BLUEPRINT_ACTION,
    PUBLIC_BLUEPRINT_ACTION,
    CLASS,
    PRIMITIVE,
    CONTROL_STRUCTURE,
    PACKAGE;
}
