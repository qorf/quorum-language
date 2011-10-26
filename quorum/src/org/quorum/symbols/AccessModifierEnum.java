/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 *  Enumeration lists the access modifiers( eg. public, private) available
 *  to change the access to variables and methods.
 * 
 * @author Melissa Stefik
 */
public enum AccessModifierEnum {
    PUBLIC("public"),
    PRIVATE("private");

    String accessModifier;
    AccessModifierEnum(String st) {
        accessModifier = st;
    }

    @Override
    public String toString() {
        return accessModifier;
    }
}
