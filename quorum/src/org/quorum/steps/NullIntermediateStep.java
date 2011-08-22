/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

/**
 * This class represents an execution step that does nothing. It's purpose
 * is to include it in various event listeners that require an
 * execution step but that should not alter the virtual machine in
 * any way.
 *
 * @author Andreas Stefik
 */
public class NullIntermediateStep extends IntermediateStep {

    @Override
    public void execute() {
    }

    @Override
    public void unexecute() {
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.VIRTUAL_MACHINE_ERROR.getName();
    }
}
