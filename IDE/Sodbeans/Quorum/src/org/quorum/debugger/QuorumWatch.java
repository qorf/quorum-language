/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import org.debugger.Variable;
import org.netbeans.api.debugger.Watch;

/**
 *
 * @author Andreas Stefik
 */
public class QuorumWatch {
    private Variable variable;
    private Watch watch;

    /**
     * @return the variable
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    /**
     * @return the watch
     */
    public Watch getWatch() {
        return watch;
    }

    /**
     * @param watch the watch to set
     */
    public void setWatch(Watch watch) {
        this.watch = watch;
    }
    
    public void remove() {
        if(watch != null) {
            watch.remove();
        }
    }
}
