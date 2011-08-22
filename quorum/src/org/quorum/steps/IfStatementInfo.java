/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.ArrayList;
import org.quorum.vm.interfaces.LineInformation;

/**
 *
 * @author Melissa Stefik
 */
public class IfStatementInfo {

    public boolean hasElse = false;

    public String ifStartLabel;
    public String ifFalseLabel;
    public ArrayList<String> elseIfStartLabels;
    public ArrayList<String> elseIfFalseLabels;
    public ArrayList<String> elseIfEndLabels;
    public String elseStartLabel;
    public String endLabel;

    public JumpStep ifJumpStep;
    public ConditionalJumpIfStep ifConditionalStep;
    public LineInformation ifLocation;

    public ArrayList<JumpBaseStep> elseIfJumpSteps;
    public ArrayList<ConditionalJumpIfStep> elseIfConditionalSteps;
    public ArrayList<LineInformation> elseIfLocations;
    public JumpStep elseJumpStep;

    //labels
    public final String START = "_start";
    public final String FALSE = "_false";
    public final String END = "_end";

    public IfStatementInfo() {
        elseIfStartLabels = new ArrayList<String>();
        elseIfFalseLabels = new ArrayList<String>();
        elseIfEndLabels = new ArrayList<String>();
        elseIfJumpSteps = new ArrayList<JumpBaseStep>();
        elseIfConditionalSteps = new ArrayList<ConditionalJumpIfStep>();
        elseIfLocations = new ArrayList<LineInformation>();
        ifJumpStep = new JumpStep();
        ifLocation = new LineInformation();
        elseJumpStep = new JumpStep();
    }
}
