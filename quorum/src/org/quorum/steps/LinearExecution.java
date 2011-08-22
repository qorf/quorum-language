/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.HashMap;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.execution.ExecutionStep;
import org.quorum.symbols.SymbolTable;

/**
 *  This is a test class, experimenting with the idea of having a parent class
 * handle execution for scope, which will be inherited by several classes,
 * perhaps the intermediate execution builder and methodExecution.
 * I'm not sure if this is a good or bad idea yet ...
 * 
 * @author Andy
 */
public class LinearExecution {
    protected Vector<ExecutionStep> steps;
    private AbstractVirtualMachine vm;
    private SymbolTable symbolTable;
    private HashMap<String, Integer> labels;
    private boolean built;
    private int address;

    public LinearExecution() {
        steps = new Vector<ExecutionStep>();
        labels = new HashMap<String, Integer>();
    }

    /**
     * Adds the location of the last execution step added to the label hashmap.
     * It can then set to its jump location by calling addLabel()
     * @param name the name of the label to add, must be unique
     */
    public void addMarker(String name) {
        labels.put(name, steps.size() - 1);
    }
    /**
     * Sets the label to refrence the location in the execution order suplied by location
     * @param name
     * @param location
     */
    public void addMarker(String name, int location){
        labels.put(name, location);

    }
    /**
     * Sets the jump location of the jump step  that is refrenced by the given label to the next step
     * this is steps.size()
     * @param name
     */
    public void addLabel(String name) {
        //get index where the jump step is located
        int jump_pointer = labels.get(name);
        //get jump step to modify
        JumpBaseStep jump = (JumpBaseStep)steps.get(jump_pointer);
        //set distance of step to jump to
        jump.setJumpLocation(steps.size() - jump_pointer);

        if(jump.getBeginLine() == 0) {
            //try to access where this is jumping to and set its line to that
            ExecutionStep step = steps.get(jump_pointer - 1);
            jump.setBeginLine(step.getBeginLine());
        }
    }
    public void addLabel(JumpBaseStep step) {

        step.jumpLocation = steps.size();
    }
    public void convertBackJump(JumpBaseStep step) {
        int relative_jump = step.getJumpLocation() - steps.size();
        step.setJumpLocation(relative_jump);
    }

    public void add(IntermediateStep step) {
        setupStep(step);
        steps.add(step);
    }

   

    private void setupStep(IntermediateStep step) {
        step.setVirtualMachine(vm);
    }

    
    /**
     * @return the vm
     */
    public AbstractVirtualMachine getVm() {
        return vm;
    }

    /**
     * @param vm the vm to set
     */
    public void setVm(AbstractVirtualMachine vm) {
        this.vm = vm;
        symbolTable = vm.getSymbolTable();
    }

    public Vector<ExecutionStep> getSteps() {
        return steps;
    }

    public int getStepCount() {
        return steps.size();
    }

    /**
     * @return the built
     */
    /**
     * @return the built
     */
    public boolean isBuilt() {
        return built;
    }

    /**
     * @param built the built to set
     */
    /**
     * @param built the built to set
     */
    public void setBuilt(boolean built) {
        this.built = built;
    }

    /**
     * @return the location in the ClassExecution where this method starts
     */
    /**
     * @return the location in the ClassExecution where this method starts
     */
    public int getAddress() {
        return address;
    }

    /**
     * @param adress the location in the ClassExecution where this method starts
     */
    /**
     * @param adress the location in the ClassExecution where this method starts
     */
    public void setAddress(int adress) {
        this.address = adress;
    }
}
