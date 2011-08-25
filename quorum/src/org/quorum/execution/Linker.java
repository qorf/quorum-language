/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.execution;

import org.quorum.vm.interfaces.CodeGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.steps.CheckLandingPads;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ContainerExecution;
import org.quorum.steps.CreateMainObjectStep;
import org.quorum.steps.DetectInfo;
import org.quorum.steps.IntermediateExecutionBuilder;
import org.quorum.steps.MainCallStep;
import org.quorum.steps.MethodExecution;
import org.quorum.steps.ObjectInitParentStep;
import org.quorum.steps.ObjectInitPopStep;
import org.quorum.steps.OnCreateStep;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VirtualMethodDescriptor;
import org.quorum.vm.interfaces.ErrorType;

/**
 * This class takes an execution tree and flattens it
 * @author Andreas Stefik, Melissa Stefik, and Aaron Willows
 */
public class Linker {

    private Vector<MainMethod> entryPoints;
    private boolean linked;
    private Vector<ExecutionStep> linkedSteps;
    private HashMap<String, VirtualMethodDescriptor> vTable = new HashMap<String, VirtualMethodDescriptor>();
    private AbstractVirtualMachine machine;
    private int classPosition = 2;
    private int methodPosition = 0;

    public Linker() {
        clear();
    }

    /**
     * This method flattens the tree of classes etc. in an IntermediateExecutionBuilder
     * into a Vector of ExecutionSteps
     * @param builder
     * @return
     */
    public void link(IntermediateExecutionBuilder builder) {
        Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
        steps.add(null); // placeholder for the call step
        steps.add(null);
        Iterator<ContainerExecution> containers = builder.getContainers();
        while (containers.hasNext()) {
            ContainerExecution exe = containers.next();
            link(steps, exe);
        }

        //For now just set the first main method as the entry point
        if (entryPoints.size() > 0) {
            boolean found = false;
            for (int i = 0; i < entryPoints.size(); i++) {
                MainMethod main = entryPoints.elementAt(i);
                if (main.getFile().compareTo(this.machine.getMain()) == 0) {
                    addEntry(i, steps);
                    i = entryPoints.size() + 1;
                    found = true;
                }
            }

            if (!found) {
                addCannotFindMainMethodError();
            }
        } else {
            addCannotFindMainMethodError();
        }

        linkedSteps = steps;
        linked = true;
    }

    private void addCannotFindMainMethodError() {
        CompilerError error = new CompilerError();
        error.setColumn(1);
        error.setLineNumber(1);
        error.setError("Cannot start the program, as no main method is defined.");
        error.setErrorType(ErrorType.MISSING_MAIN);
        machine.getCompilerErrors().setErrorKey(machine.getMain());
        error.setFile(machine.getMain());
        machine.getCompilerErrors().addError(error);
        machine.getCompilerErrors().setErrorKey("");
    }

    public void setEntryPoint(int mainIndex) {
        addEntry(mainIndex, linkedSteps);
    }

    private void addEntry(int index, Vector<ExecutionStep> steps) {
        MainMethod entry = entryPoints.get(index);
        ExecutionStep cStep = steps.get(entry.getAddress());

        MainCallStep step = new MainCallStep();
        step.setClassDescriptor(entry.getClassDescriptor());
        step.setFileKey(entry.getFile());

        ActivationRecord record = new ActivationRecord();
        ExpressionValue ret = new ExpressionValue();
        ret.setType(TypeDescriptor.getVoidType());
        record.setReturnValue(ret);
        record.build(entry.getDescriptor());
        record.setReturnValueAbsolute(-2);
        step.setActivationRecord(record);
        step.setMethodCallee(entry.getDescriptor());

        step.setVirtualMachine(machine);
        step.setLineInformation(cStep.getLineInformation());
        step.setBeginColumn(cStep.getBeginColumn());
        step.setBeginLine(entry.getDescriptor().getLineBegin());
        step.setEndColumn(cStep.getEndColumn());
        step.setEndLine(cStep.getEndLine());

        //add in some initialization into the VM.
        CreateMainObjectStep objStep = new CreateMainObjectStep();
        objStep.setClazz(entry.getClassDescriptor());
        objStep.setLineInformation(step.getLineInformation());
        objStep.setFileKey(step.getFileKey());
        objStep.setVirtualMachine(machine);

        steps.set(0, objStep);
        //now call the proper main method, once an object is setup.
        steps.set(1, step);
    }

    private void link(Vector<ExecutionStep> steps, ContainerExecution exe) {
        Iterator<ClassExecution> classes = exe.getClasses();
        while (classes.hasNext()) {
            ClassExecution c = classes.next();

            c.getClassDescriptor().getLocation().setStart(classPosition);

            Vector<ExecutionStep> classSteps = c.getSteps();
            
            //initialize parent classes
            Iterator<ClassDescriptor> ancestors = c.getClassDescriptor().getFlattenedListOfParents();
            while (ancestors.hasNext()) {
                ClassDescriptor parent = ancestors.next();
                ObjectInitParentStep step = new ObjectInitParentStep();
                step.setVirtualMachine(machine);
                step.setFileKey(c.getClassDescriptor().getFile().getStaticKey());
                step.setParent(parent);
                if (!classSteps.isEmpty()) {
                    ExecutionStep lastStep = classSteps.lastElement();
                    step.setLineInformation(lastStep.getLineInformation());
                } else {
                    step.setLineInformation(parent.getLineInformation());
                }
                steps.add(step);
                classPosition += 1;
            }
            
            c.setAddress(steps.size());
            steps.addAll(classSteps);
            classPosition += classSteps.size();

            //add constructor steps
            linkConstructor(steps, c);

            //add in a ObjectInitPopStep, to jump back to where
            //the object was initialized from.
            ObjectInitPopStep step = new ObjectInitPopStep();
            step.setVirtualMachine(machine);
            step.setFileKey(c.getClassDescriptor().getFile().getStaticKey());
            if (!classSteps.isEmpty()) {
                ExecutionStep lastStep = classSteps.lastElement();
                step.setLineInformation(lastStep.getLineInformation());
            } else {
                step.setLineInformation(c.getClassDescriptor().getLineInformation());
            }
            steps.add(step);
            classPosition += 1;

            link(steps, c);

            
            //generate vTable
            Iterator<MethodDescriptor> vMethods = c.getClassDescriptor().getVirtualMethods();
            while (vMethods.hasNext()) {
                MethodDescriptor method = vMethods.next();
                String key = c.getStaticKey() + ":" + method.getStaticKey();

                //generate the virtualmethodDescriptor
                VirtualMethodDescriptor vMethod = new VirtualMethodDescriptor(method);
                vTable.put(key, vMethod);
            }

            classPosition += methodPosition;
            methodPosition = 0;
        }
    }

    private void link(Vector<ExecutionStep> steps, ClassExecution exe) {
        Iterator<MethodExecution> methods = exe.getMethods();
        while (methods.hasNext()) {
            MethodExecution c = methods.next();
            c.getMethodDescriptor().getLocation().setStart(classPosition + methodPosition);

            c.setAddress(steps.size());
            if (c.isMainMethod()) {
                MainMethod m = new MainMethod();
                m.setClassDescriptor(exe.getClassDescriptor());
                String path = exe.getClassDescriptor().getFile().getFile().getAbsolutePath();
                m.setFile(path);
                m.setAddress(c.getAddress());
                m.setDescriptor(exe.getClassDescriptor().getMethod(c.getStaticKey()));
                m.setExecution(c);
                entryPoints.add(m);
            }

            //generate the virtualmethodDescriptor
            String key = exe.getStaticKey() + ":" + c.getMethodDescriptor().getStaticKey();
            VirtualMethodDescriptor vMethod = new VirtualMethodDescriptor(c.getMethodDescriptor());
            vTable.put(key, vMethod);

            Vector<ExecutionStep> methodSteps = c.getSteps();
            steps.addAll(methodSteps);
            methodPosition += methodSteps.size();

            //generate global jump positions for try-catch(check-detect) blocks
            HashMap<String, ArrayList<String>> checkBlocks = c.getMethodDescriptor().getCheckBlocks();
            Iterator<String> keyIterator = checkBlocks.keySet().iterator();
            while(keyIterator.hasNext()){
                String checkName = keyIterator.next();
                Iterator<String> detectTypesIterator = checkBlocks.get(checkName).iterator();
                CheckLandingPads checkLandingPads = machine.getCheckLandingPads(checkName);

                while(detectTypesIterator.hasNext()){//set the global position for each detect statement
                    String detectType = detectTypesIterator.next();
                    DetectInfo landingPad = checkLandingPads.getLandingPad(detectType);
                    landingPad.setGlobalLocation(c.getAddress() + landingPad.getLocalLocation());
                }
            }
        }
    }

    /**
     * Link the constructor steps after init and before popping the create steps.
     *
     * @param steps
     * @param c
     */
    private void linkConstructor(Vector<ExecutionStep> steps, ClassExecution c){
            //constructor
            MethodDescriptor constructor = c.getClassDescriptor().getConstructor();
            if(constructor != null){//has constructor
                OnCreateStep constructStep = new OnCreateStep();
                constructStep.setVirtualMachine(machine);
                constructStep.setFileKey(c.getClassDescriptor().getFile().getStaticKey());

                //set line information
                Vector<ExecutionStep> classSteps = c.getSteps();
                if (!classSteps.isEmpty()) {
                    ExecutionStep lastStep = classSteps.lastElement();
                    constructStep.setLineInformation(lastStep.getLineInformation());
                } else {
                    constructStep.setLineInformation(constructor.getLineInformation());
                }

                //return value creation will always be void type
                ExpressionValue ret = new ExpressionValue();
                ret.setType(TypeDescriptor.getVoidType());
                ret.setResult(new Result());

                //create the activation record
                ActivationRecord record = new ActivationRecord();
                record.setReturnValue(ret);
                record.setReturnValueAbsolute(classPosition + methodPosition);
                record.setLineInformation(constructor.getLineInformation());
                record.build(constructor);

                //add the step and increment classPosition
                constructStep.setActivationRecord(record);
                steps.add(constructStep);
                classPosition += 1;
            }
    }
    
    /**
     * Clears all data used by the link() method
     */
    public void clear() {
        entryPoints = new Vector<MainMethod>();
        linkedSteps = null;
        linked = false;
    }

    /**
     * @return the linked
     */
    public boolean isLinked() {
        return linked;
    }

    /**
     * 
     * @return virtual method table
     */
    public HashMap<String, VirtualMethodDescriptor> getVTable() {
        return vTable;
    }

    /**
     * @return the linkedSteps
     */
    public Vector<ExecutionStep> getLinkedSteps() {
        return linkedSteps;
    }

    /**
     * @return the machine
     */
    public AbstractVirtualMachine getMachine() {
        return machine;
    }

    /**
     * @param machine the machine to set
     */
    public void setMachine(AbstractVirtualMachine machine) {
        this.machine = machine;
    }
}
