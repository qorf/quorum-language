/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

/**
 *
 * @author Morgoth
 */
public abstract class JumpBaseStep extends IntermediateStep {

   protected int jumpLocation;

    /**
     * @return the jumpLocation
     */
    public int getJumpLocation() {
        return jumpLocation;
    }

    /**
     * @param jumpLocation the jumpLocation to set
     */
    public void setJumpLocation(int jumpLocation) {
        this.jumpLocation = jumpLocation;
    }

    @Override
    public int nextStep() {
        return vm.getExecution().getExecutionPosition() + jumpLocation;        
    }

    @Override
    public String toString() {
        return "jump to: " + jumpLocation;
    }
}
