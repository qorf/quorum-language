/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.ArrayList;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.vm.interfaces.LineInformation;

/**
 *
 * @author Andreas Stefik and Melissa Stefik
 */
public class ExceptionInfo {
    //labels
    public String checkStartLabel;
    public String checkEndLabel;
    public ArrayList<String> detectStartLabels;
    public ArrayList<String> detectEndLabels;
    public String alwaysStartLabel;
    public String alwaysEndLabel;
    public boolean hasAlways;
    public LineInformation location;

    //jumps and types
    public ArrayList<JumpBaseStep> detectJumps;
    public ArrayList<DetectParameter> detectParameters;
    
    public JumpBaseStep checkJump;

    //labels
    public final String START = "_start";
    public final String END = "_end";
    public final String ALWAYS = "always";
    
    public ExceptionInfo() {
        detectStartLabels = new ArrayList<String>();
        detectEndLabels = new ArrayList<String>();
        detectJumps = new ArrayList<JumpBaseStep>();
        detectParameters = new ArrayList<DetectParameter>();
        checkJump = new JumpStep();
    }

    /**
     * Add the label for the detect.
     * @param label
     * a string representing the label for a detect block.
     */
    public void addDetectLabel(String label) {
        detectStartLabels.add(label);
    }

    /**
     * Add detect types, "parameters", to the detect block.
     *
     * @param param
     * The parameter of a detect statement, type of error or exception.
     */
    public void addDetectParameter(DetectParameter param) {
        detectParameters.add(param);
    }
}
