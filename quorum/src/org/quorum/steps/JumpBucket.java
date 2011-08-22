/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.ArrayList;

/**
 * A Jump bucket is a general storage location for handling label and a
 * set of jump steps that may ultimately go to that label.
 *
 * @author Andreas Stefik
 */
public class JumpBucket {
    private String label;
    private LinearExecution execution;

    /**
     * This variable represents the FINAL position the label "label" has
     * in a particular LinearExecution. For example, suppose a check-detect
     * block was defined in a function. This label may represent the
     * actual op-code position represented by the label of either the always
     * block or the first op-code outside of a detect block.
     */
    private int finalMarkerPosition = -1;
    public static final int INVALID_FINAL_MARKER_POSITION = -1;
    private ArrayList<JumpBaseStep> jumps = new ArrayList<JumpBaseStep>();
    private ArrayList<Integer> markers = new ArrayList<Integer>();

    /**
     * This adds a particular jump step to the bucket. It may not be yet resolved,
     * meaning that the final marker position is not yet known for a particular
     * label, as that position may not have been processed yet by the tree
     * walker.
     * 
     * @param step
     */
    public void addJumpStep(JumpBaseStep step) {
        jumps.add(step);
        int marker = getExecution().getStepCount() - 1;
        markers.add(marker);
    }

    /**
     * This method should resolve all jumps according to their LinearExecution
     * and their final marker positions. If these positions are not known,
     * an exception is thrown.
     */
    public void resolveJumpSteps() {
        if(finalMarkerPosition == INVALID_FINAL_MARKER_POSITION) {
            throw new RuntimeException("Compiler Bug: Tried to process a final marker position"
                    + " for a label that has not yet been processed by the tree walker. "
                    + "This might possibly be caused by forgetting to set the value in "
                    + "the corresponding JumpBucket.");
        }

        for(int i = 0; i < this.jumps.size(); i++) {
            JumpBaseStep jump = jumps.get(i);
            int marker = this.markers.get(i);
            if(finalMarkerPosition > marker) {
                jump.setJumpLocation(finalMarkerPosition - marker);
            }
            else {
                jump.setJumpLocation(marker - finalMarkerPosition);
            }
        }
    }

    /**
     * Set the label for the jump.
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get the label.
     * 
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the execution
     */
    public LinearExecution getExecution() {
        return execution;
    }

    /**
     * @param execution the execution to set
     */
    public void setExecution(LinearExecution execution) {
        this.execution = execution;
    }

    /**
     * @return the finalMarkerPosition
     */
    public int getFinalMarkerPosition() {
        return finalMarkerPosition;
    }

    /**
     * @param finalMarkerPosition the finalMarkerPosition to set
     */
    public void setFinalMarkerPosition(int finalMarkerPosition) {
        this.finalMarkerPosition = finalMarkerPosition;
    }
}
