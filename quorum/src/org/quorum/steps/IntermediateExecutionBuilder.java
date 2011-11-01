/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.execution.ExecutionStep;
import org.quorum.symbols.*;
import org.quorum.vm.implementation.OpcodeTracker;
import org.quorum.vm.implementation.OpcodeType;

/**
 *
 * @author Andreas Stefik
 */
public class IntermediateExecutionBuilder {

    private AbstractVirtualMachine vm;
    private String currentFileKey = "";
    private HashMap<String, ContainerExecution> containers;
    private int currentAddress;
    private ClassExecution currentClass;
    private MethodExecution currentMethod;
    private ContainerExecution currentContainer;
    private HashMap<String, Integer> labelFinalMarkerPositionHash = new HashMap<String, Integer>();
    private HashMap<String, JumpBucket> unresolvedJump = new HashMap<String, JumpBucket>();

    public IntermediateExecutionBuilder() {
        containers = new HashMap<String, ContainerExecution>();
    }

    /**
     * Manually adds a container execution object to the builder, from
     * the container used in a class descriptor object.
     * 
     * @param descriptor
     */
    public ContainerExecution addContainer(ClassDescriptor descriptor) {
        if(!containers.containsKey(descriptor.getContainer().getContainer())) {
            ContainerExecution cex = new ContainerExecution();
            cex.setVm(vm);
            cex.setStaticKey(descriptor.getContainer().getContainer());
            containers.put(descriptor.getContainer().getContainer(), cex);
            return cex;
        }
        return null;
    }
    
    /**
     * Add the step label to the opcode tracker for expressions, loops, assignments,
     * and method calls.
     * 
     * @param stepType 
     */
    public void addStepLabel(OpcodeType stepType){
        int position = -1;
        OpcodeTracker tracker = null;
        if(getCurrentMethod() == null){
            ClassExecution clazz = this.getCurrentClass();
            tracker = clazz.getTracker();
            position = clazz.getStepCount();
            if(stepType.equals(OpcodeType.ROOT_EXPRESSION)){
                tracker.addBeginIndex(position, stepType);
            } else if (stepType.equals(OpcodeType.SOLO_METHOD_CALL)) {
                // We need to go "over" the DataStackPopStep, otherwise, we
                // skip the CallStep.
                tracker.addBeginIndex(position - 2, stepType);
            }
            else{
                tracker.addBeginIndex(position - 1, stepType);
            }
        }else{
            MethodExecution method = this.getCurrentMethod();
            tracker = method.getTracker();
            position = method.getStepCount();
            if(stepType.equals(OpcodeType.ROOT_EXPRESSION)){
                tracker.addBeginIndex(position, stepType);
            } else if (stepType.equals(OpcodeType.SOLO_METHOD_CALL)) {
                // We need to go "over" the DataStackPopStep, otherwise, we
                // skip the CallStep.
                tracker.addBeginIndex(position - 2, stepType);
            }
            else{
                tracker.addBeginIndex(position - 1, stepType);
            }
        }
    }
    
    public int addParameterLabel(){
        int position = -1;
        if(getCurrentMethod() == null){
            ClassExecution clazz = this.getCurrentClass();
            position = clazz.getStepCount();
        }else{
            MethodExecution method = this.getCurrentMethod();
            position = method.getStepCount();
        }
        return position;
    }
    
    public void addCallLabel(int parameterPosition){
        int position = -1;
        if(getCurrentMethod() == null){
            ClassExecution clazz = this.getCurrentClass();
            position = clazz.getStepCount();
            clazz.getTracker().addFunctionParameterMapping(parameterPosition, position);
        }else{
            MethodExecution method = this.getCurrentMethod();
            position = method.getStepCount();
            method.getTracker().addFunctionParameterMapping(parameterPosition, position);
        }
    }

    /**
     * Adds a label pointing to the next ExecutionStep
     * @param label the label to add
     */
    public void addLabel(String label) {
        if (getCurrentMethod() == null) {
            getCurrentClass().addLabel(label);
        } else {
            getCurrentMethod().addLabel(label);
        }
    }

    /**
     * sets a JumpBaseStep's jumpLocation to the current execution location.
     * @param step
     */
    public void addLabel(JumpBaseStep step) {
        if (getCurrentMethod() == null) {
            getCurrentClass().addLabel(step);
        } else {
            getCurrentMethod().addLabel(step);
        }
    }

    /**
     * Convert the jump step.
     * @param step
     */
    public void convertBackJump(JumpBaseStep step) {
        if (getCurrentMethod() == null) {
            getCurrentClass().convertBackJump(step);
        } else {
            getCurrentMethod().convertBackJump(step);
        }
    }

    /**
     * Add a marker that marks a location.
     * @param name
     * unique name of the marker.
     * @param location
     * Location (step number) of the marker.
     */
    public void addMarker(String name, int location) {
        if (getCurrentMethod() == null) {
            getCurrentClass().addMarker(name, location);
        } else {
            getCurrentMethod().addMarker(name, location);
        }

    }

    /**
     * Add a marker by name.
     *
     * @param name
     */
    public void addMarker(String name) {
        if (getCurrentMethod() == null) {
            getCurrentClass().addMarker(name);
        } else {
            getCurrentMethod().addMarker(name);
        }
    }

    /**
     * Add a step
     * 
     * @param step
     */
    public void add(IntermediateStep step) {
        if (getCurrentMethod() == null) {
            getCurrentClass().add(step);
        } else {
            getCurrentMethod().add(step);
        }

        step.setFileKey(currentFileKey);
    }
    
    /**
     * This function looks for a previously unresolved label that was added to
     * the build. These labels indicate the actual build position where
     * a particular jump ultimately go to.
     *
     * @param label
     */
    public void addJumpLabelAndResolveSteps(String label) {
        if(labelFinalMarkerPositionHash.containsKey(label)) {
            throw new RuntimeException("Compiler Bug: Jump Label's defined by the compiler"
                    + " are non-unique.");
        }
        else {
            int finalPosition = -1;
            if(this.getCurrentMethod() == null) {
                finalPosition = this.getCurrentClass().getStepCount();
            }
            else {
                finalPosition = this.getCurrentMethod().getStepCount();
            }

            labelFinalMarkerPositionHash.put(label, finalPosition);
            JumpBucket bucket = unresolvedJump.get(label);
            bucket.setFinalMarkerPosition(finalPosition);
            bucket.resolveJumpSteps();
        }
    }

    /**
     * This method associates a particular jump step with a possibly non-existent
     * label. For example, a detect statement might indicate that it will jump
     * to an always block, but that block may not yet have been processed on the
     * system, and therefore does not yet exist. Any jump step that needs
     * to go to a particular label should be entered here. Unique labels
     * may have multiple jump steps associated with them.
     * 
     * @param label
     * @param step
     */
    public void addUnresolvedJumpStep(String label, JumpBaseStep step) {
        JumpBucket bucket = unresolvedJump.get(label);
        if(bucket == null) {
            bucket = new JumpBucket();
            bucket.setLabel(label);
            if(this.getCurrentMethod() == null) {
                bucket.setExecution(this.getCurrentClass());
            }
            else {
                bucket.setExecution(this.getCurrentMethod());
            }
            unresolvedJump.put(label, bucket);
        }

        bucket.addJumpStep(step);
    }

    
    private void setupStep(IntermediateStep step) {
        step.setVirtualMachine(vm);
    }

    /**
     * Prepares a new method execution to have steps added to it. All steps added until endMethod is called wil be added to this method
     * @param method
     */
    public void begin(MethodDescriptor method) {
        MethodExecution builder;
        if(currentClass.getMethod(method.getStaticKey()) != null) {
            builder = currentClass.getMethod(method.getStaticKey());
            builder.setMethodDescriptor(method);
            builder.setVm(vm);
        }
        else {
            builder = new MethodExecution();
            builder.setMethodDescriptor(method);
            builder.setVm(vm);
            currentClass.add(builder);
        }        
        currentMethod = builder;

    }

    /**
     * Adds the current method to the current class then sets it to null
     */
    public void endMethod() {
        currentMethod.setBuilt(true);
        getCurrentClass().add(getCurrentMethod());
        currentMethod = null;
    }

    /**
     * Starts building the execution of this class.
     * @param cl
     */
    public void begin(ClassDescriptor cl) {
        ClassExecution builder = new ClassExecution();
        builder.setClassDescriptor(cl);
        builder.setVm(vm);
        builder.setBuilt(false);

        currentClass = builder;

        //if this class is in a container that hasn't been
        //created yet, make a new one and add it.
        //otherwise, set the current container to the one for this class.
        if (!containers.containsKey(cl.getContainer().getContainer())) {
            ContainerExecution exe = new ContainerExecution();
            exe.setVm(vm);
            exe.setStaticKey(cl.getContainer().getContainer());
            containers.put(cl.getContainer().getContainer(), exe);
            currentContainer = exe;
        } else {
            currentContainer = containers.get(cl.getContainer().getContainer());
        }

        currentContainer.addClass(currentClass);
    }

    /**
     * Finalizes building the class.
     */
    public void endClass() {
        currentClass.setBuilt(true);
        currentClass.setAddress(currentAddress);
        currentAddress += currentClass.getStepCount();
        currentClass = null;
    }

    /**
     * gets a list of all the steps in the builder including class and method steps
     * @return the steps
     */
    public Vector<ExecutionStep> getSteps() {
        Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
        //adds all steps from each class into the total program

        for (String key : containers.keySet()) {
            steps.addAll(containers.get(key).getSteps());
        }
        return steps;
    }

    /**
     * @param vm the vm to set
     */
    public void setVirtualMachine(AbstractVirtualMachine vm) {
        this.vm = vm;
    }

    public AbstractVirtualMachine getVirtualMachine() {
        return vm;
    }

    /**
     * Resets the builder to remove all existing steps.
     */
    public void clear() {
        currentAddress = 0;
        currentContainer = null;
        currentClass = null;
        currentMethod = null;
        containers.clear();
        unresolvedJump.clear();
        labelFinalMarkerPositionHash.clear();
    }

    /**
     * @return the currentClass
     */
    public ClassExecution getCurrentClass() {
        return currentClass;
    }

    /**
     * @return the currentMethod
     */
    public MethodExecution getCurrentMethod() {
        return currentMethod;
    }

    /**
     * @return the currentContainer
     */
    public ContainerExecution getCurrentContainer() {
        return currentContainer;
    }

    /**
     * Returns the set of ClassExecution objects for a particular container (package).
     * @param key
     * @return
     */
    public ContainerExecution getContainer(String key) {
        return containers.get(key);
    }

    /**
     * @return the currentFileKey
     */
    public String getCurrentFileKey() {
        return currentFileKey;
    }

    public Iterator<ContainerExecution> getContainers() {
        return containers.values().iterator();
    }

    /**
     * @param currentFileKey the currentFileKey to set
     */
    public void setCurrentFileKey(String currentFileKey) {
        this.currentFileKey = currentFileKey;
    }
}
