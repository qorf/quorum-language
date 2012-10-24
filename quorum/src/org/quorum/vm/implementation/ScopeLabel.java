/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.objectweb.asm.Label;

/**
 * This class maintains a start and end label for a scope. These labels are used
 * to create a local variable table in Java bytecode.
 * 
 * @author melissa stefik
 */
class ScopeLabel {
    public Label startLabel = new Label();
    public Label endLabel = new Label();

    ScopeLabel(Label startLabel, Label endLabel) {
        this.startLabel = startLabel;
        this.endLabel = endLabel;
    }
}
