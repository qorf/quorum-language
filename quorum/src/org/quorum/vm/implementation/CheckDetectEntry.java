/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.objectweb.asm.Label;

/**
 * Container class to store the labels and error type needed for a try catch table(bytecode generation).
 * 
 * @author Melissa Stefik
 */
class CheckDetectEntry {
    private Label from = new Label();
    private Label to = new Label();
    private Label target = new Label();
    private String type = "";

    public CheckDetectEntry(Label from, Label to, Label target, String type){
        this.from = from;
        this.to = to;
        this.target = target;
        this.type = type;
    }
    /**
     * @return the from
     */
    public Label getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Label from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Label getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Label to) {
        this.to = to;
    }

    /**
     * @return the target
     */
    public Label getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Label target) {
        this.target = target;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
