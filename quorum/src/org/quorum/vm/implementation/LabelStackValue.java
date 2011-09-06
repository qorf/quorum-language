/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.objectweb.asm.Label;

/**
 *
 * @author melissa
 */
public class LabelStackValue {
    private LabelTypeEnum labelType;
    private int jumpType;
    private Label label;

    LabelStackValue(LabelTypeEnum labelTypeEnum, int jumpType, Label label) {
        labelType = labelTypeEnum;
        this.jumpType = jumpType;
        this.label = label;
    }

    /**
     * @return the labelType
     */
    public LabelTypeEnum getLabelType() {
        return labelType;
    }

    /**
     * @param labelType the labelType to set
     */
    public void setLabelType(LabelTypeEnum labelType) {
        this.labelType = labelType;
    }

    /**
     * @return the jumpType
     */
    public int getJumpType() {
        return jumpType;
    }

    /**
     * @param jumpType the jumpType to set
     */
    public void setJumpType(int jumpType) {
        this.jumpType = jumpType;
    }

    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Label label) {
        this.label = label;
    }
}
