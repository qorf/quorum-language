/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.execution.ExecutionStep;

/**
 *
 * @author Aaron Willows
 */
public class ContainerExecution extends LinearExecution {
    private HashMap<String, ClassExecution> classes;
    /**
     * This ensures that classes are added to the container in the same order they are added in the program
     */
    private Vector<String> classKeys;

    private String staticKey;

    public ContainerExecution() {
        classes = new HashMap<String, ClassExecution>();
        classKeys = new Vector<String>();
    }

    public void addClass(ClassExecution exe) {
        classes.put(exe.getStaticKey(), exe);
        classKeys.add(exe.getStaticKey());
    }

    public void clear() {
        classes.clear();
        classKeys.clear();
    }


       /**
     * @return the staticKey
     */
    public String getStaticKey() {
        return staticKey;
    }

    /**
     * @param staticKey the staticKey to set
     */
    public void setStaticKey(String staticKey) {
        this.staticKey = staticKey;
    }

    @Override
    public Vector<ExecutionStep> getSteps() {
        Vector<ExecutionStep> ret = new Vector<ExecutionStep>();
       //adds all steps from each class into the total program
        for(String key : classKeys) {
            ret.addAll(classes.get(key).getSteps());
        }
        return ret;
    }

    public Iterator<ClassExecution> getClasses() {
        return classes.values().iterator();
    }

    /**
     * Returns a ClassExecution object from this container.
     * @param key
     * @return
     */
    public ClassExecution getClassExecution(String key) {
        return classes.get(key);
    }
}
