/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.symbols.ClassDescriptor;

/**
 * This class represents a container for the op-codes generated
 * for all methods in a particular class.
 * 
 * @author Andreas Stefik and Aaron Willows
 */
public class ClassExecution extends LinearExecution{
    /**
     * A hash of all executions for the particular methods on the system.
     */
    private HashMap<String, MethodExecution> methods;
    
    /**
     * This ensures that methods are added to the class in the same order they are added in the program
     */
    private Vector<String> methodKeys;
    
    /**
     * The actual class descriptor from the symbol table for the
     * current ClassExecution object.
     */
    private ClassDescriptor classDescriptor;
    
    /**
     * This is an offset relative to the start of the classes execution steps
     */
    int currentStepCount;

    

    public ClassExecution() {
        methods = new HashMap<String, MethodExecution>();
        methodKeys = new Vector<String>();
    }
    
    /**
     * Returns the key for the class descriptor represented by this ClassExecution
     * object.
     * 
     * @return the staticKey
     */
    public String getStaticKey() {
        return classDescriptor.getStaticKey();
    }


    /**
     * Adds the actual op-codes that will be executed for a particular function
     * that is inside of this class.
     * @param method
     */
    public void add(MethodExecution method) {
        MethodExecution me = methods.get(method.getStaticKey());
        method.setMainMethod(detectMain(method.getStaticKey()));
        if(method.isBuilt()) {
            if(me == null) { //method is built and has no CallSteps referencing it
                method.setAddress(currentStepCount);
                currentStepCount += method.getStepCount();
                methods.put(method.getStaticKey(), method);
                methodKeys.add(method.getStaticKey());
            }
            else { //method is built and has CallSteps referencing it
                me.setAddress(currentStepCount);
                me.steps = method.steps;
                me.setBuilt(true);
                currentStepCount += method.getStepCount();
                methodKeys.add(method.getStaticKey());
            }
        }
        else {
            if(me == null) { //method is not built and has no CallSteps referencing it
                methods.put(method.getStaticKey(), method);
            }
            else { //method is not built and has CallSteps referencing it
                
            }
        }
    }

    /**
     * Checks a string to see if it is a valid main method name
     * @param key the method name
     * @return true if this is a main method
     */
    private boolean detectMain(String key) {
        String main1 = "main";
        boolean res = main1.equalsIgnoreCase(key);
        return res;
    }

    @Override
    public String toString() {
        return "class: " + this.getStaticKey();
    }

    @Override
    public int getStepCount() {
        int total = super.getStepCount();
        for(String key : methodKeys) {
            total += methods.get(key).getStepCount();
        }
        total++; //to account for the jump
        return total;
    }
    
    /**
     * Determines whether this class has a custom constructor.
     * 
     * @return 
     */
    public boolean hasConstructor() {
        return this.classDescriptor.hasConstructor();
    }
    
    /**
     * If this class has a custom constructor, this class returns the 
     * execution for it.
     * 
     * @return 
     */
    public MethodExecution getConstructor() {
        return this.getMethod("on create");
    }
    

    /**
     * Return the MethodExecution object that is mapped through the given
     * key. This should match a MethodDescriptor's getStaticKey method.
     * 
     * @param key
     * @return
     */
    public MethodExecution getMethod(String key) {
        return methods.get(key);
    }

    /**
     * This retrieves the actual class descriptor from the symbol table,
     * allowing one to retrieve static analysis regarding this class's execution.
     * @return the classDescriptor
     */
    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }

    /**
     * Sets the class descriptor used by this class execution.
     * 
     * @param classDescriptor the classDescriptor to set
     */
    public void setClassDescriptor(ClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }

    /**
     * Return all the methods that execute in this class.
     * 
     * @return
     */
    public Iterator<MethodExecution> getMethods() {
        return methods.values().iterator();
    }
   
}
