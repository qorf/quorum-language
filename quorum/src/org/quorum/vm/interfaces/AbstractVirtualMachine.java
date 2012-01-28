/*
 * AbstractVirtualMachine.java
 *
 * Created on January 17, 2007, 7:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

import org.quorum.plugins.RuntimeError;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.event.VirtualMachineErrorState;
import org.quorum.event.VirtualMachineStopEvent;
import org.quorum.execution.*;
import org.quorum.steps.IntermediateConstants;
import org.quorum.symbols.FileDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.SymbolTable;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.TypeChecker;
import org.quorum.symbols.VirtualMethodDescriptor;
import org.quorum.steps.BeginDetectScopeStep;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.steps.CheckLandingPads;
import org.quorum.steps.DetectInfo;
import org.quorum.symbols.TypeDescriptor;

/**
 *  This source represents a virtual machine, implemented in some language.
 *  The idea is that this class will present common virtual machine operations,
 *  like building from source, executing source, etc. 
 *  Subclasses underneath this file will implement the operations for a 
 *  particular language, like C, C++, Java, or others.
 *
 * @author Andreas Stefik
 */
public abstract class AbstractVirtualMachine {

    /**
     * A flag indicating whether the virtual machine is currently in
     * an error state.
     */
    protected boolean errorState;

    /**
     * A manager of the runtime errors encountered on the virtual machine.
     */
    private ExceptionManager runtimeErrors;

    /**
     * Stores a class virtual table (v-table) for all callable methods
     * on the system.
     */
    protected HashMap<String, VirtualMethodDescriptor> vTable;
    /**
     * Stores a table of check statements and their corresponding detect statements.
     */
    private HashMap<String, CheckLandingPads> exceptionHandlingTable;
    /**
     * The set of listeners listening to this virtual machine.
     */
    private Vector<VirtualMachineListener> listeners;
    /**
     * This is a copy of the machine, pointing to "this" the current object, 
     * used by any methods that employ threading.
     */
    private AbstractVirtualMachine machine;
    /**
     * The execution model for the program.
     */
    private Execution execution;
    /**
     * Manages all breakpoints currently set in this virtual machine.
     */
    private BreakpointManager breakpointManager;
    /** This string represents the main method that will be used to begin
     * execution. Since there can be a "main" in any class, this string
     * represents the absolute location of the method that begins execution.
     * This action is looked up in a general hash behind the scenes to determine
     * where in the total execution the main is located.
     */
    protected String main = "";
    
    /** This is a symbol table that represents and collects any static and
     * dynamic analysis for the system. This includes storing variables,
     * methods, classes, values, types, and the relationships between
     * all of these attributes.
     */
    protected SymbolTable table;
    /** This class represents the data stored on this virtual machine. It
     * includes all activation records, stacks, heaps, and virtual registers
     * necessary to store data in the machine.
     *
     */
    private DataEnvironment dataEnvironment;
    /**
     * A standard library index, which stores information about all
     * classes and packages that are available on the system. This variable
     * sounds heavyweight, but it only stores an index, not complete parses.
     */
    protected StandardLibrary standardLibrary;

    /**
     * Manages any plugins loaded into the Virtual Machine.
     */
    protected PluginManager plugins = new PluginManager();
    
    /**
     * If this flag is set, the virtual machine will throw events
     * to any listener objects.
     */
    private boolean throwingHighlightEvents = true;
    
    /**
     * A flag indicating when a thrown exception was caught.
     */
    private boolean caughtException = false;

    /**
     * Handles type checking on the system.
     */
    protected TypeChecker typeChecker;

    /** Manages the threaded execution of Quorum programs.
     * 
     */
    protected ExecutionManager executionManager;
    
    //All of these are runnable classes that can be sent to
    //the execution manager to be run.
    private Run run = new Run();
    private StepInto stepInto = new StepInto();
    private StepOver stepOver = new StepOver();
    private StepBackInto stepBackInto = new StepBackInto();
    private StepBackOver stepBackOver = new StepBackOver();
    private StepToBreakpoint stepToBreakpoint = new StepToBreakpoint();
    private UnstepToBreakpoint unstepToBreakpoint = new UnstepToBreakpoint();

    private VirtualMachineEvent lastEvent = null;
    private boolean fireDebuggerEvents = true;
    private boolean isRunning = false;
    private boolean isAlwaysJumpStep = false;
    
    ArrayList<DebuggerListener> debuggerListeners = new ArrayList<DebuggerListener>();
    
    protected CodeGenerator generator;
    
    
    /**
     * Determines whether some kind of machine or byte code should be generated
     * if a build is requested.
     */
    private boolean generateCode = false;
    
    /**
     * This represents the absolute path for the folder in which documentation
     * will be generated.
     */
    protected String documentationPath = "";
    
    /** Creates a new instance of AbstractParser */
    public AbstractVirtualMachine() {
        execution = new Execution();
        listeners = new Vector<VirtualMachineListener>();
        machine = this;
        breakpointManager = new BreakpointManager();
        table = new SymbolTable();
        table.setVirtualMachine(this);
        dataEnvironment = new DataEnvironment();
        dataEnvironment.setVirtualMachine(this);
        executionManager = new ExecutionManager();
        exceptionHandlingTable = new HashMap<String,CheckLandingPads>();
        runtimeErrors = new ExceptionManager();
        caughtException = false;
    }

    public void addListener(DebuggerListener listener) {
        debuggerListeners.add(listener);
    }

    public void removeListener(DebuggerListener listener) {
        debuggerListeners.remove(listener);
    }

    /**
     * add a landing pad for the exception handling table. A landing pad is the
     * place that identifies where execution should step to if an exception occurs
     * in a try block.
     *
     * @param checkName
     * @param landingPads
     */
    public void addCheckLandingPads(String checkName, CheckLandingPads landingPads){
        exceptionHandlingTable.put(checkName, landingPads);
    }

    /**
     * Get the landing pad given the name of the check(try) block.
     *
     * @param checkName
     * @return
     */
    public CheckLandingPads getCheckLandingPads(String checkName){
        return exceptionHandlingTable.get(checkName);
    }

    /**
     * Trigger an alert: this method will determine where to jump when an
     * alert is called. In the process of determining the location to jump,
     * the vm state will be restored to the appropriate values given the
     * jump location.
     *
     * Jumps will either go to an always or detect statement, if none are
     * found execution will end. If jumping to a detect an error object is
     * either generated or passed to that detect scope.
     *
     * @param error that is thrown by the alert statement.
     * @return Global error state, reset to false if the exception is caught.
     */
    public boolean alertException(ExpressionValue error){
        boolean inErrorState = true;

        if(getExceptions().hasAlerts()){
            getExceptions().alertStackUndo();
        }

        //get the error object from the alert
        RuntimeObject ro = dataEnvironment.getObject(error.getObjectHash());
        ClassDescriptor errorClazz = ro.getClazz();

        BlockScope checkBlock = dataEnvironment.checkScopeStackPop();

        while(checkBlock != null && inErrorState){

            cleanCallStackForException(checkBlock);

            //get the landing pad and jump to that location.
            CheckLandingPads detectBlock = exceptionHandlingTable.get(checkBlock.getBlockName());
            if(detectBlock != null){
                //search for actual error type
                DetectInfo landingPad = detectBlock.getLandingPad(errorClazz.getStaticKey());

                //search for the general error type.
                if(landingPad == null){
                    landingPad = detectBlock.getLandingPad(ErrorTypeDescriptor.ERROR.toString());
                }

                //jump to the location and return if it still produces an error state.
                inErrorState = execution.jumpToLandingPad(landingPad);
                dataEnvironment.callStackPop();

                //set the caught exception flag.
                if(!inErrorState){
                    //mark as handled exception
                    caughtException = true;
                    isAlwaysJumpStep = false;

                    //if the position is a detect statement set it's variable
                    ExecutionStep currentStep = execution.getCurrentStep();
                    if (currentStep instanceof BeginDetectScopeStep) {
                        BeginDetectScopeStep detectScope = (BeginDetectScopeStep) currentStep;
                        error.setName(landingPad.getDetectParameter().getName());
                        detectScope.setDetectVariable(error);
                        currentStep.execute();
                    }
                    
                    //return the error state
                    return false;
                }else{
                    //mark unhandled error
                    getRuntimeErrors().alertStackPush(error);

                    //search for always
                    landingPad = detectBlock.getLandingPad(ErrorTypeDescriptor.ALWAYS.toString());
                    if(!execution.jumpToLandingPad(landingPad)){
                        isAlwaysJumpStep = true;
                        if(dataEnvironment.checkScopeStackPeek() != null){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
                checkBlock = dataEnvironment.checkScopeStackPop();
            }
        }
        //mark unhandled error
        getRuntimeErrors().alertStackPush(error);
        return true;
    }

    public void unAlertException(RuntimeScope callState){

        //undo any clean up the call stack
        dataEnvironment.checkScopeStackUndo();

        if(dataEnvironment.checkScopeStackPeek() != null){
            dataEnvironment.callStackUndo();
        }
        getRuntimeErrors().alertStackPop();
        RuntimeScope currentCall = dataEnvironment.callStackPeek();

        if(currentCall instanceof ActivationRecord){
            ActivationRecord call = (ActivationRecord)currentCall;
            dataEnvironment.setThisPointer(call.getThisPointer());
        }

        while(callState != null && !callState.equals(currentCall)){

            dataEnvironment.callStackUndo();
            currentCall = dataEnvironment.callStackPeek();

            if(currentCall instanceof ActivationRecord){
                ActivationRecord call = (ActivationRecord)currentCall;
                dataEnvironment.setThisPointer(call.getThisPointer());
            }

            if(currentCall instanceof BlockScope){
                dataEnvironment.checkScopeStackUndo();
            }
        }
    }

    /**
     * Restores the call stack to a check scope that matches an exeception
     * type that has been thrown.
     *
     * @param checkBlock the check scope to return to.
     */
    private void cleanCallStackForException(BlockScope checkBlock){
        //clean up the call stack
            RuntimeScope currentCall = dataEnvironment.callStackPeek();
            while(currentCall != null && !currentCall.equals(checkBlock)){

                //if there is a activation record clean up the this pointer and registars.
                if(currentCall instanceof ActivationRecord){
                    ActivationRecord call = (ActivationRecord)currentCall;

                    dataEnvironment.setThisPointer(currentCall.getPreviousThisPointer());

                    //set the This pointer back to the mode it was in
                    //it should gather this information from th activation record
                    //which would set this information in call step
                    int hash = call.getThisPointer();
                    RuntimeObject ro = dataEnvironment.getObject(hash);
                    if(ro != null) {
                        ro.setMode(ro.getPreviousMode());
                    }
                    //restore all of the registers to their previous state
                    Iterator<Integer> registers = call.getRegisters();
                    while(registers.hasNext()) {
                        int i = registers.next();
                        ExpressionValue register = call.getRegister(i);
                        if(register != null) {
                            dataEnvironment.setRegister(i, register);
                        }
                    }
                }

                //pop the item off the call stack and reset the current call variable.
                dataEnvironment.callStackPop();
                if(dataEnvironment.canPeekCallStack()){
                    currentCall = dataEnvironment.callStackPeek();
                }else{
                    currentCall = null;
                }
            }
    }

    /**
     * Throw an exception and return the resulting inErrorState.If an exception
     * is caught and handled false will be returned. If the exception is not handled
     * the error state will remain true.
     *
     * @param error
     * Quorum exception type being thrown.
     *
     * @return
     * Global error state, reset to false if the exception is caught.
     */
    public boolean throwException(RuntimeError error){
        boolean inErrorState = true;

        if(getExceptions().hasErrors()){
            getExceptions().exceptionStackUndo();
        }

        BlockScope checkBlock = dataEnvironment.checkScopeStackPop();

        while(checkBlock != null && inErrorState){
            
            cleanCallStackForException(checkBlock);

            //get the landing pad and jump to that location.
            CheckLandingPads detectBlock = exceptionHandlingTable.get(checkBlock.getBlockName());
            if(detectBlock != null){
                //search for actual error type
                DetectInfo landingPad = detectBlock.getLandingPad(error.getStaticKey());

                //search for the general error type.
                if(landingPad == null){
                    landingPad = detectBlock.getLandingPad(ErrorTypeDescriptor.ERROR.toString());
                }

                //jump to the location and return if it still produces an error state.
                inErrorState = execution.jumpToLandingPad(landingPad);

                //close the scope
                dataEnvironment.callStackPop();
                
                //set the caught exception flag.
                if(!inErrorState){
                    //mark as handled exception
                    caughtException = true;
                    isAlwaysJumpStep = false;
                    getRuntimeErrors().exceptionStackPush(error);

                    //create the error object available in the detect block
                    TypeDescriptor type = landingPad.getDetectParameter().getType();
                    ExpressionValue exceptionObject = this.createExpressionValue(type);
                    exceptionObject.setName(landingPad.getDetectParameter().getName());

                    //if the position is a detect statement set it's variable
                    //Warning: Not the best solution but I see no other options.
                    ExecutionStep currentStep = execution.getCurrentStep();
                    if (currentStep instanceof BeginDetectScopeStep) {
                        BeginDetectScopeStep detectScope = (BeginDetectScopeStep) currentStep;
                        detectScope.setDetectVariable(exceptionObject);
                        currentStep.execute();
                    }

                    getRuntimeErrors().exceptionStackUndo();

                    //return the error state
                    return false;
                }else{

                    //mark unhandled error
                    getRuntimeErrors().exceptionStackPush(error);

                    //search for always
                    landingPad = detectBlock.getLandingPad(ErrorTypeDescriptor.ALWAYS.toString());
                    if(!execution.jumpToLandingPad(landingPad)){
                        isAlwaysJumpStep = true;
                        if(dataEnvironment.checkScopeStackPeek() != null){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }

                checkBlock = dataEnvironment.checkScopeStackPop();
            }
        }
        //mark unhandled error
        getRuntimeErrors().exceptionStackPush(error);
        return true;
    }

    /**
     *
     * Reverse a throw exception step (since throw is not an opcode), resetting
     * the necessary data environment variables and stacks.
     * @param error
     * The exception that was thrown.
     * @param callState
     * The RuntimeScope that was at the top of the call stack when the
     * exception was thrown.
     *
     */
    public void unThrowException(RuntimeScope callState){

        if(!this.errorState && this.caughtException){
            this.undoCreateExpressionValue(dataEnvironment.getThisObject());
        }

        //undo any clean up the call stack
        dataEnvironment.checkScopeStackUndo();

        if(dataEnvironment.checkScopeStackPeek() != null){
            dataEnvironment.callStackUndo();
        }
        getRuntimeErrors().exceptionStackPop();
        RuntimeScope currentCall = dataEnvironment.callStackPeek();

        if(currentCall instanceof ActivationRecord){
            ActivationRecord call = (ActivationRecord)currentCall;
            dataEnvironment.setThisPointer(call.getThisPointer());
        }

        while(callState != null && !callState.equals(currentCall)){

            dataEnvironment.callStackUndo();
            currentCall = dataEnvironment.callStackPeek();

            if(currentCall instanceof ActivationRecord){
                ActivationRecord call = (ActivationRecord)currentCall;
                dataEnvironment.setThisPointer(call.getThisPointer());
            }

            if(currentCall instanceof BlockScope){
                dataEnvironment.checkScopeStackUndo();
            }
        }
    }

    /**
     * Returns true if the virtual machine has caught an exception and false if it has
     * an uncaught exception.
     *
     * Will also return false if no exception has occurred.
     * @return
     */
    public boolean hasCaughtException(){
        return caughtException;
    }

    private void updateDebuggerListeners() {
        if(fireDebuggerEvents) {
            for(int i = 0; i < debuggerListeners.size(); i++) {
                debuggerListeners.get(i).update();
            }
        }
    }

    /**
     * Builds or rebuilds one single file on the system.
     * 
     * @param file
     */
    public abstract void buildSingle(File file);

    /**
     * This method parses a file if necessary and stores
     * information about its contents into the symbol table. This method does
     * not complete a full build for the file, like buildSingle does.
     *
     * @param file
     */
    public abstract void parseSingle(File file);

    /**
     * Removes one file from the current build.
     * 
     * @param file
     */
    public abstract void removeFromBuild(File file);

    /**
      * This operation builds an internal representation of a program from 
      *  source. This build operation builds from a collection of source files.
      * If a boolean value of true
      * is issued to this function, this function will force the virtual machine
      * to block on the thread that passed the request.
     * 
      * @param source A series of source code files to build. 
      * @param block 
      */
    public abstract void build(File[] source, boolean block);
    
    /**
     * This operation builds an internal representation of a program from 
     *  source. This build operation builds from a collection of source files.
     * @param source A series of source code files to build.
     */
    public abstract void build(File[] source);

    /**
     * This function generates documentation for the current project on
     * the system.
     * 
     */
    public abstract boolean generateDocumentation();
    
    /**
     * This method requests code completion details from the system.
     * 
     * @param request
     * @return 
     */
    public abstract CodeCompletionResult requestCodeCompletionResult(CodeCompletionRequest request);
    
    
    /**
     * Executes the built program from start to finish without interruption.
     */
    public synchronized void run() {
        executionManager.add(run);
    }

    /**
     * This method causes the virtual machine to jump to the next breakpoint.
     * If no next breakpoint exists, the VM will run to the end of the program
     * and halt.
     */
    public synchronized void stepToBreakpoint() {
        executionManager.add(stepToBreakpoint);
    }

    /**
     * Causes the program to run backward until either a breakpoint is hit
     * or the beginning of the execution is reached.
     * 
     */
    public synchronized void unstepToBreakpoint() {
        executionManager.add(unstepToBreakpoint);
    }

    /**
     * Takes one stepInto of execution.
     */
    public synchronized void stepInto() {
        executionManager.add(stepInto);
    }

    /**
     * Steps over whatever information is at the current line of code.
     *
     */
    public void stepOver() {
        executionManager.add(stepOver);
    }

    /**
     * This method causes the entire system to rollback to a particular
     * point in time. The point to which it rolls back is a machine time stamp
     * (an op-code number), not a date and time. Date and time rollback
     * might be added at a later date. This function is guaranteed to halt.
     * Worst case, this function will rollback to the beginning of the
     * program.
     *
     * @param stamp
     */
    public void rollBackward(int stamp) {
        RollBackward back = new RollBackward();
        back.setStamp(stamp);
        executionManager.add(back);
    }

    /**
     * This function rolls the execution forward to a particular point
     * in time. The point to which it rolls back is a machine time stamp
     * (an op-code number), not a date and time. Date and time rollback
     * might be added at a later date. This function is guaranteed to
     * halt. In cases like this, this is similar to never reaching a breakpoint.
     *
     * @param stamp
     */
    public void rollForward(int stamp) {
        RollForward back = new RollForward();
        back.setStamp(stamp);
        executionManager.add(back);
    }

    /**
     * This method uses a class descriptor and a method descriptor to obtain
     * a value from the virtual table. This effectively allows the inheritance
     * system to function.
     *
     * @param clazz The class of an object, which has a method.
     * @param method The method to call in that class.
     * @return
     */
    public VirtualMethodDescriptor getVirtualMethodDescriptor(ClassDescriptor clazz,
            MethodDescriptor method) {
        String key = clazz.getStaticKey();
        key += ":" + method.getStaticKey();

        return vTable.get(key);
    }

    /**
     * Queries the current value of a variable.
     * 
     * @param variable
     * @return
     */
    public abstract VariableWatch getVariableValue(String variable);

    /** Returns the "type" of an expression.
     *
     * @param value
     * @return
     */
    public abstract String getType(ExpressionValue value);

    /** Returns a null execution step.
     *
     * @return
     */
    public abstract ExecutionStep getNullExecutionStep();

    /**
     * Unsteps one piece of execution. Example, a++ becomes a--.
     */
    public synchronized void stepBackInto() {
        executionManager.add(stepBackInto);
    }

    /**
     * This method is functionally identical to a step over call, but in
     * reverse.
     */
    public synchronized void stepBackOver() {
        executionManager.add(stepBackOver);
    }

    /**
     * Determines, from the position of the cursor, where to execute
     * the code to.
     * 
     * @param fileKey
     * @param line
     */
    public synchronized void runToCursor(String fileKey, long line) {
        RunToCursor runnable = new RunToCursor();
        runnable.setFileKey(fileKey);
        runnable.setLine(line);
        executionManager.add(runnable);
    }

    /**
     * Determines, from the position of the cursor, where to back up the execution
     * to.
     * 
     * @param fileKey
     * @param line
     */
    public synchronized void runBackToCursor(String fileKey, long line) {
        RunBackToCursor runnable = new RunBackToCursor();
        runnable.setFileKey(fileKey);
        runnable.setLine(line);
        executionManager.add(runnable);
    }

    /**
     * Gets the current line number for the current stepInto on the system.
     * @return The current line number.
     */
    public synchronized int getLineNumber() {
        return getExecution().getLineNumber();
    }

    /**
     * Gets the current line number for the current stepInto on the system.
     * @return The current line number.
     */
    public synchronized int getColumnNumber() {
        return getExecution().getColumnNumber();
    }

    /**
     * Returns the execution sequence for the program.
     * @return The execution sequence.
     */
    public synchronized Execution getExecution() {
        return execution;
    }

    /**
     * Returns true if the program successfully built without compilation errors.
     * @return True if there were no errors.
     */
    public synchronized boolean wasBuildSuccessful() {
        return this.getCompilerErrors().isCompilationErrorFree();
    }

    public ExceptionManager getExceptions(){
        return getRuntimeErrors();
    }

    /**
     * Returns a list of Compiler errors.
     * @return Compiler errors on the system.
     */
    public abstract CompilerErrorManager getCompilerErrors();

    /**
     * Adds an event listener to the virtual machine. Events are thrown
     * whenever a step occurs, forwards or backwards, in the virtual
     * machine.
     * @param listener The listener to add.
     */
    public void addListener(VirtualMachineListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes an event listener to the virtual machine.
     * @param listener The listener to remove.
     */
    public void removeListener(VirtualMachineListener listener) {
        listeners.remove(listener);
    }

    /**
     * Removes all event listeners from the virtual machine.
     */
    public void removeAllListeners() {
        listeners.clear();
    }

    /**
     * Throws an event to all listening objects.
     * @param event The event to throw to listeners.
     */
    protected synchronized void throwEventToListeners(VirtualMachineEvent event) {
        this.lastEvent = event;
        for (int i = 0; i < listeners.size(); i++) {
            VirtualMachineListener listener = listeners.elementAt(i);
            listener.actionPerformed(event);
        }
    }

    /** Determines where breakpoints are located on the system.
     * 
     * @return
     */
    public BreakpointManager getBreakpointManager() {
        return breakpointManager;
    }

    /**
     * This is the absolute path of the file that represents the "Main file" on
     * the system. The main file is the one in which the execution of the
     * program will begin.
     *
     * @return the main
     */
    public String getMain() {
        return main;
    }

    /**
     * Set the absolute path of the file that represents the "Main file" on
     * the system. The main file is the one in which the execution of the
     * program will begin.
     *
     * @param main the main to set
     */
    public abstract void setMain(String main);

    /**
     * The Symbol Table stores all relevant information about the compile. This
     * includes all static analysis related information.
     *
     * 
     * @return the table
     */
    public SymbolTable getSymbolTable() {
        return table;
    }

    /**
     * The Data Environment stores all runtime information about the system,
     * including information about variables, scoping information, and other
     * types of dynamic analysis.
     * 
     * @return the dataEnvironment
     */
    public DataEnvironment getDataEnvironment() {
        return dataEnvironment;
    }

    /**
     * Returns a complete history of all variables that have ever been
     * on the system.
     * 
     * @return the dataHistory
     */
    public DataHistory getDataHistory() {
        return dataEnvironment.getDataHistory();
    }

    /**
     * Restarts the program currently being executed by the virtual machine
     * to its initial state. This does not destroy any parse related information,
     * just runtime state and execution information.
     */
    public void stop() {
        executionManager.stopNow();
        getDataEnvironment().clear();
        getExecution().restartExecution();
        this.errorState = false;
        this.caughtException = false;
        this.getRuntimeErrors().clear();
    }

    /**
     * This function cleans the current build. If a boolean value of true
     * is issued to this function, this function will force the virtual machine
     * to block on the thread that passed the request.
     */
    public abstract void clean(boolean block);
    
    /**
     * This function cleans the current build.
     */
    public abstract void clean();

    /**
     * This method creates a valid expression value according to its type. If
     * the type is a primitive, it creates the expression value and returns it.
     * If the value is an object, it creates the object on the heap and executes
     * that objects initialization op-codes. In other words, for object values,
     * this function is NOT guaranteed to halt! As such, this function
     * should not be called unless the caller knows what they are doing.
     *
     * Users that want an expression value for a null pointer to an object
     * of the appropriate type, as opposed to a new object directly instantiated
     * and put on the heap, should instead call
     * static ExpressionValue ExpressionValue.getObjectDefault(TypeDescriptor type)
     *
     * @param type
     * @return
     */
    public abstract ExpressionValue createExpressionValue(TypeDescriptor type);

    /**
     *
     *
     * @param type
     * @return
     */
    public abstract void undoCreateExpressionValue(RuntimeObject variable);
    
    /**
     * This function returns an object that allows you to query for information
     * regarding the standard library for this programming language.
     * 
     * @return the standardLibrary
     */
    public StandardLibrary getStandardLibrary() {
        return standardLibrary;
    }

    /**
     * Returns the plugin manager for this virtual machine.
     * 
     * @return
     */
    public PluginManager getPluginManager() {
        return plugins;
    }
    
    /**
     * @return the throwingHighlightEvents
     */
    public boolean isThrowingHighlightEvents() {
        return throwingHighlightEvents;
    }

    /**
     * Determines whether the virtual machine is running a program outside of
     * the debugger.
     * 
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @return the typeChecker
     */
    public TypeChecker getTypeChecker() {
        return typeChecker;
    }

    public String getCurrentFileBeingExecuted() {
        return getExecution().getCurrentFileBeingExecuted();
    }

    /**
     * @return the runtimeErrors
     */
    public ExceptionManager getRuntimeErrors() {
        return runtimeErrors;
    }

    /**
     * @return the documentationPath
     */
    public String getDocumentationPath() {
        return documentationPath;
    }

    /**
     * @param documentationPath the documentationPath to set
     */
    public void setDocumentationPath(String documentationPath) {
        this.documentationPath = documentationPath;
    }

    /**
     * @return the generator
     */
    protected CodeGenerator getCodeGenerator() {
        return generator;
    }

    /**
     * @return the buildFolder
     */
    public File getBuildFolder() {
        return getCodeGenerator().getBuildFolder();
    }

    /**
     * @param buildFolder the buildFolder to set
     */
    public abstract void setBuildFolder(File buildFolder);
    
    /**
     * The folder that will be used to produce the final executable code.
     * 
     * @return 
     */
    public File getDistributionFolder() {
        return getCodeGenerator().getDistributionFolder();
    }
    
    /**
     * Sets the folder that will create the final executable package.
     * @param distributionFolder 
     */
    public abstract void setDistributionFolder(File distributionFolder);
    
    /**
     * Returns the name of the final executable that will be created
     * by the system.
     * 
     * @return 
     */
    public String getDistributionName() {
        return getCodeGenerator().getDistributionName();
    }
    
    /**
     * Sets the name of the final executable that will be created by
     * the system.
     * @param name 
     */
    public abstract void setDistributionName(String name);
    
    
    /**
     * Adds a dependency into the distributed code for the target platform.
     * If the file passed is a folder, the entire folder will be copied 
     * verbatim to the dependency folder, but no attempt will be made to
     * write the dependencies into the target execution. As such, dependencies
     * must be managed individually, but folders (e.g., images or resources), 
     * can be copied raw.
     * 
     * @param file 
     */
    public abstract void addDependency(File file);
    
    /**
     * This method allows the user to add a dependency into a folder of the 
     * user's choice. No attempt will be made to inject these dependencies
     * into the target's execution (e.g., a jar file's manifest). This is useful
     * if you want to copy a series of files, one-by-one, into a specified
     * folder.
     * 
     * @param file
     * @param relativePath 
     */
    public abstract void addDependency(File file, String relativePath);
    
    /**
     * Clears out all dependencies on the target system.
     * 
     */
    public abstract void clearDependencies();
    
    /**
     * Returns the number of dependencies currently loaded on the target
     * platform.
     * 
     * @return 
     */
    public int getNumberOfDependencies() {
        return getCodeGenerator().getNumberOfDependencies();
    }
    
    /**
     * Sets the folder which will grab any relevant plugins on the system.
     * @param file 
     */
    public abstract void setPluginFolder(File file);
    
    /**
     * Gets the folder which houses any plugins for the system.
     * @return 
     */
    public File getPluginFolder() {
        return getCodeGenerator().getPluginFolder();
    }
    
    /**
     * @return the generateCode
     */
    public boolean isGenerateCode() {
        return generateCode;
    }

    /**
     * @param generateCode the generateCode to set
     */
    public void setGenerateCode(boolean generateCode) {
        this.generateCode = generateCode;
    }

    private class Run implements Runnable {
        public void run() {
            getPluginManager().setDebugMode(false);
            Execution exec = getExecution();
            try {
                isRunning = true;
                while (!exec.isExecutionFinished() && !errorState) {
                    ExecutionStep step = exec.step();
                    errorState = step.isInErrorState();
                }
                
                if(errorState){
                            //throw an event indicating that the execution cannot
                            //continue because it is in an error state
                            ExecutionStep s = getNullExecutionStep();
                            VirtualMachineErrorState event = new VirtualMachineErrorState(
                                    s, machine, true, getRuntimeErrors());
                            throwEventToListeners(event);
                }
                
                isRunning = false;
            } catch (Exception exc) {
                //don't do anything.
                ExecutionStep s = getNullExecutionStep();
                VirtualMachineErrorState event = new VirtualMachineErrorState(
                        s, machine, true, getRuntimeErrors());
                throwEventToListeners(event);
            }

            stop();
            VirtualMachineStopEvent event = new VirtualMachineStopEvent();
            throwEventToListeners(event);
        }
    }

    private class StepInto implements Runnable {
        public void run() {
            getPluginManager().setDebugMode(true);
            if (!getExecution().isExecutionFinished() && !errorState) {
                //remove before a release, for testing
                //for testing, try having the debugger stepInto by lines:
                long line = getExecution().getLineNumber();
                String file = getExecution().getCurrentStep().getFileKey();

                boolean notDone = true;
                while (notDone) {
                    ExecutionStep methodCheckStep = getExecution().getCurrentStep();
                    MethodDescriptor method = null;
                    long newLine = getExecution().getLineNumber();
                    boolean hasLine = false;
                    if (methodCheckStep != null) {
                        method = methodCheckStep.getMethodDescriptor();
                    }

                    if (method != null) {
                        hasLine = method.hasStatementLine((int) newLine);
                    }

                    if (getExecution().isExecutionFinished()
                            || (getExecution().getLineNumber() != line
                            || getExecution().getCurrentStep().getFileKey().compareTo(file) != 0)
                            && hasLine) {
                        notDone = false;
                    }else if (errorState) {
                        //throw an event indicating that the execution cannot
                        //continue because it is in an error state
                        notDone = false;
                    } else {
                        ExecutionStep step = getExecution().step();
                        errorState = step.isInErrorState();
                        if(errorState){
                            notDone = false;
                            //throw an event indicating that the execution cannot
                            //continue because it is in an error state
                            ExecutionStep s = getNullExecutionStep();
                            VirtualMachineErrorState event = new VirtualMachineErrorState(
                                    s, machine, true, getRuntimeErrors());
                            throwEventToListeners(event);

                        }else{
                            VirtualMachineEvent event = new VirtualMachineEvent(step, machine, true);
                            throwEventToListeners(event);
                        }

                    }
                }
            } else if (errorState) {
                //throw an event indicating that the execution cannot
                //continue because it is in an error state
                ExecutionStep s = getNullExecutionStep();
                VirtualMachineErrorState event = new VirtualMachineErrorState(
                        s, machine, true, getRuntimeErrors());
                throwEventToListeners(event);
            } else {
                //throw an event indicating that the execution is finished
                //so the user knows "What's up?"
                ExecutionStep s = getNullExecutionStep();
                VirtualMachineStopEvent event = new VirtualMachineStopEvent(
                        s, machine, true, true);
                event.setEventKey(IntermediateConstants.EXECUTION_END.getName());
                throwEventToListeners(event);
            }
            //only fire an event to the annotator after everything is finished
            if(!errorState) {
                updateDebuggerListeners();
            }
        }
    }

    private class StepOver implements Runnable {

        public void run() {
            getPluginManager().setDebugMode(true);
            if (!getExecution().isExecutionFinished() && !errorState) {
                //remove before a release, for testing
                //for testing, try having the debugger stepInto by lines:
                long line = getExecution().getLineNumber();
                String fileKey = getExecution().getCurrentStep().getFileKey();
                FileDescriptor desc = getSymbolTable().getFileDescriptor(fileKey);
                MethodDescriptor oldMethod = getExecution().getCurrentStep().getMethodDescriptor();
                
                RuntimeScope oldActivationRecord = machine.dataEnvironment.peekNearestActivationRecord();
                RuntimeScope newActivationRecord = oldActivationRecord;
                
                boolean notDone = true;
                while (notDone) {
                    if (getExecution().isExecutionFinished()) {
                        notDone = false;
                    } else if (errorState) {
                        //throw an event indicating that the execution cannot
                        //continue because it is in an error state
                        notDone = false;
                    } else  {
                        ExecutionStep step = getExecution().step();
                        
                        RuntimeScope tempRecord = machine.dataEnvironment.peekNearestActivationRecord();
                        if(tempRecord != null){
                            newActivationRecord = tempRecord;
                        }
                        
                        errorState = step.isInErrorState();
                        if(errorState){
                            notDone = false;
                            //throw an event indicating that the execution cannot
                            //continue because it is in an error state
                            ExecutionStep s = getNullExecutionStep();
                            VirtualMachineErrorState event = new VirtualMachineErrorState(
                                    s, machine, true, getRuntimeErrors());
                            throwEventToListeners(event);
                        }

                        String newFileKey = "";
                        ExecutionStep temp = getExecution().getCurrentStep();
                        if(temp != null){
                            newFileKey = temp.getFileKey();
                        }
                        long newLine = getExecution().getLineNumber();
                        Breakpoint key = new Breakpoint(newFileKey, (int)newLine);
                        boolean breakHere = getBreakpointManager().isBreakpointAtPosition(key);

                        //is this a stopping point?
                        if(breakHere && (fileKey.compareTo(newFileKey) != 0 || newLine != line)){
                            notDone = false;
                        }else if (!getExecution().isExecutionFinished()) {//first check if we are actually finished with the program

                            //first check if it is the same method
                            MethodDescriptor newMethod = step.getMethodDescriptor();

                            if (oldMethod != null) {
                                //is this a stopping line?

                                if (oldMethod.hasStatementLine((int) newLine) && newLine != line
                                        && fileKey.compareTo(newFileKey) == 0) {
                                    if(newMethod != null && newActivationRecord.hashCode() == oldActivationRecord.hashCode()){
                                        notDone = false;
                                    }
                                    else if(newMethod == null || !oldMethod.equals(newMethod)){
                                        notDone = false;
                                    }
                                }

                                //this isn't the line, but are there any even left
                                //in this method?
                                if (newMethod != null) {
                                    FileDescriptor newDesc = getSymbolTable().getFileDescriptor(newFileKey);
                                    boolean hasStatement = newDesc.hasStatementLine(getExecution().getLineNumber());
                                    boolean linesLeft = oldMethod.findLargerLine((int) line);
                                    ExecutionStep nextStep = getExecution().getNextStep();

                                    if(nextStep != null){//there is a next step
                                        MethodDescriptor nextMethod = nextStep.getMethodDescriptor();

                                        if(!linesLeft){//at the end of a method
                                            if (hasStatement && (fileKey.compareTo(newFileKey) != 0
                                                    || newLine != line)) {
                                                notDone = false;
                                            }
                                        }else if (caughtException){
                                            caughtException = false;
                                            //getExecution().step();
                                            notDone = false;
                                        }else if (isAlwaysJumpStep){
                                            isAlwaysJumpStep = false;
                                            getExecution().step();
                                            notDone = false;
                                        } else if (step.isReturnStep()) {//at a return 
                                            if(nextMethod != null && nextMethod.equals(oldMethod) && newActivationRecord.hashCode() == oldActivationRecord.hashCode() && fileKey.compareTo(newFileKey) == 0
                                                    && oldMethod.hasStatementLine((int)newLine) && newLine != line){
                                                //done stepping over a method call
                                                notDone = false;
                                            }else if(nextMethod != null && !nextMethod.equals(newMethod) && oldMethod.equals(newMethod)){
                                                //done stepping over a return that exits a method.
                                                notDone = false;
                                            }
                                        }
                                    }
                                }
                            } else {

                                boolean hasLine = false;
                                ExecutionStep methodCheckStep = getExecution().getCurrentStep();

                                if (methodCheckStep != null) {//if the current step has an executionStep get the method
                                    oldMethod = methodCheckStep.getMethodDescriptor();
                                    ActivationRecord tempRec = machine.dataEnvironment.peekNearestActivationRecord();
                                    if(tempRec != null){
                                        oldActivationRecord = tempRec;
                                    }   
                                }
                                if (oldMethod != null) {//if there is a method descriptor determine if it has a line
                                    hasLine = oldMethod.hasStatementLine((int) newLine);
                                }
                                if ((getExecution().getLineNumber() != line
                                        || getExecution().getCurrentStep().getFileKey().compareTo(fileKey) != 0)
                                        && hasLine) {//we are done when there is a line and the lines or the files do not match
                                        notDone = false;
                                }
                            }
                        }else  {
                            notDone = false;
                        }
                        VirtualMachineEvent event = new VirtualMachineEvent(step, machine, true);
                        throwEventToListeners(event);
                    }
                }
            } else if (errorState) {
                //throw an event indicating that the execution cannot
                //continue because it is in an error state
                ExecutionStep s = getNullExecutionStep();
                VirtualMachineErrorState event = new VirtualMachineErrorState(
                        s, machine, true, getRuntimeErrors());
                throwEventToListeners(event);
            } else {
                //throw an event indicating that the execution is finished
                //so the user knows "What's up?"
                ExecutionStep s = getNullExecutionStep();
                VirtualMachineStopEvent event = new VirtualMachineStopEvent(
                        s, machine, true, true);
                event.setEventKey(IntermediateConstants.EXECUTION_END.getName());
                throwEventToListeners(event);
            }
            //only fire an event to the annotator after everything is finished
            if(!errorState) {
                updateDebuggerListeners();
            }
        }
    }

    private class StepToBreakpoint implements Runnable {
        StepInto step = new StepInto();
        public void run() {
            getPluginManager().setDebugMode(true);
            fireDebuggerEvents = false;
            boolean stop = false;
            String filename;
            while (!stop) {
                step.run();
                //if we're at a breakpoint, or at the end, halt
                if (getExecution().getCurrentStep() == null) {
                    fireDebuggerEvents = true;
                    updateDebuggerListeners();
                    return;
                }

                int line = getLineNumber();
                filename = getExecution().getCurrentStep().getFileKey();
                Breakpoint key = new Breakpoint(filename, line);
                //for testing
                boolean breakHere = getBreakpointManager().isBreakpointAtPosition(key);
                boolean done = getExecution().isExecutionFinished();
                if (breakHere || done || errorState) {
                    stop = true;
                }
            }
            //only fire an event to the annotator after everything is finished
            fireDebuggerEvents = true;
            updateDebuggerListeners();
        }
    }

    private class UnstepToBreakpoint implements Runnable {
        StepBackInto step = new StepBackInto();
        public void run() {
            getPluginManager().setDebugMode(true);
            fireDebuggerEvents = false;
            boolean stop = false;
            while (!stop) {
                step.run();
                //if we're at a breakpoint, or at the end, halt

                if (getExecution().getCurrentStep() == null) {
                    fireDebuggerEvents = true;
                    updateDebuggerListeners();
                    return;
                }

                int line = getLineNumber();
                String filename = getExecution().getCurrentStep().getFileKey();
                Breakpoint key = new Breakpoint(filename, line); //for testing
                boolean breakHere = getBreakpointManager().isBreakpointAtPosition(key);
                boolean done = getExecution().isExecutionAtBeginning();
                if (breakHere || done) {
                    stop = true;
                }
            }
            fireDebuggerEvents = true;
            updateDebuggerListeners();
        }
    }

    private class RollToStamp {

        protected int stamp = -1;

        /**
         * @return the stamp
         */
        public int getStamp() {
            return stamp;
        }

        /**
         * @param stamp the stamp to set
         */
        public void setStamp(int stamp) {
            this.stamp = stamp;
        }
    }

    private class RollBackward extends RollToStamp implements Runnable {

        public void run() {
            execution.rollback(stamp);
        }
    }

    private class RollForward extends RollToStamp implements Runnable {

        public void run() {
            execution.rollForward(stamp);
        }
    }

    private class StepBackInto implements Runnable {

        public void run() {
            getPluginManager().setDebugMode(true);
            if (!getExecution().isExecutionAtBeginning()) {
                long line = -1;
                MethodDescriptor oldMethod = null;
                if(getExecution().isExecutionFinished()) {
                    getExecution().unstep();
                }

                line = getExecution().getCurrentStep().getBeginLine();
                    oldMethod = getExecution().getCurrentStep().getMethodDescriptor();
                long nextStatement = -1;
                boolean notDone = true;
                while (notDone) {
                    
                    ExecutionStep methodCheckStep = getExecution().getCurrentStep();
                    long newLine = methodCheckStep.getBeginLine();
                    long newCurrentLine = getExecution().getLineNumber();
                    MethodDescriptor method = null;
                    boolean hasLine = false;
                    if (methodCheckStep != null) {
                        method = methodCheckStep.getMethodDescriptor();
                    }

                    if (method != null) {
                        hasLine = method.hasStatementLine((int) newCurrentLine);
                        if (hasLine && nextStatement == -1) {
                            nextStatement = newCurrentLine;
                            hasLine = false;
                        }//we want to jump to the end of the statement line
                        //immediately proceeding the first statement line
                        //the system finds.
                    }
                    //add code checking the previous line here, to see if
                    //it's flagged as one that we stop at.
                    if (((newLine != line || method != oldMethod) && hasLine) || getExecution().isExecutionAtBeginning()) {
                        notDone = false;
                    } else {
                        ExecutionStep step = getExecution().unstep();
                        errorState = step.isInErrorState();
                        if(errorState){
                            getRuntimeErrors().exceptionStackPush(step.getRuntimeError());
                        }

                        VirtualMachineEvent event = new VirtualMachineEvent(step, machine, false);
                        throwEventToListeners(event);
                    }
                }
            } else {
                //throw an event indicating that the execution is finished
                //so the user knows "What's up?"
                VirtualMachineStopEvent event = new VirtualMachineStopEvent(
                        getNullExecutionStep(), machine, true, false);
                event.setEventKey(IntermediateConstants.EXECUTION_START.getName());
                throwEventToListeners(event);
            }
            updateDebuggerListeners();
        }
    }

    private class StepBackOver implements Runnable {

        public void run() {
            getPluginManager().setDebugMode(true);
            if (!getExecution().isExecutionAtBeginning()) {
                //remove before a release, for testing
                //for testing, try having the debugger stepInto by lines:
                long endLine = getExecution().getPreviousLineNumber();
                long startLine = getExecution().getLineNumber();

                String fileKey = "";
                MethodDescriptor oldMethod = null;
                if (getExecution().getCurrentStep() != null) {
                    fileKey = getExecution().getCurrentStep().getFileKey();
                    oldMethod = getExecution().getCurrentStep().getMethodDescriptor();
                }

                FileDescriptor desc = getSymbolTable().getFileDescriptor(fileKey);
                boolean notDone = true;
                boolean notFound = true;
                boolean isCreate = false;

                while (notDone) {
                    if (getExecution().isExecutionAtBeginning()) {
                        notDone = false;
                    } else {
                        MethodDescriptor lastMethod;
                        if(getExecution().isExecutionFinished()) {
                            lastMethod = getExecution().getPreviousStep().getMethodDescriptor();
                            getExecution().unstep();
                        }
                        else {
                            lastMethod = getExecution().getCurrentStep().getMethodDescriptor();
                        }
                        ExecutionStep step = null;
                        try {
                        step = getExecution().unstep();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                        //is this a stopping point?
                        if (oldMethod != null) {

                            long newLine = getExecution().getLineNumber();
                            MethodDescriptor newMethod = step.getMethodDescriptor();

                            if (newMethod != null) {
                                ExecutionStep previousStep = getExecution().getPreviousStep();
                                boolean linesLeft = oldMethod.findSmallerLine((int) startLine);

                                if (oldMethod == newMethod && oldMethod.hasStatementLine((int)newLine)
                                        && newLine != startLine) {
                                    if (notFound) {
                                        notFound = false;
                                    } else if (newLine != getExecution().getPreviousLineNumber()){
                                        notDone = false;
                                    }

                                    //check if at the beginning of a method before moving back a step
                                    if(newLine != getExecution().getPreviousLineNumber()){
                                        if (!notFound && !newMethod.findSmallerLine((int)newLine)) {
                                            notDone = false;
                                        } else if((isCreate || oldMethod == previousStep.getMethodDescriptor()) && startLine != getExecution().getPreviousLineNumber()){
                                            notDone = false;
                                        }else if(!notFound && lastMethod != oldMethod){
                                            notDone = false;
                                        }
                                    }else if(!notDone && startLine != newLine && newLine == previousStep.getBeginLine()){
                                        notDone = true;
                                    }
                                }else if(oldMethod != newMethod && !linesLeft && notFound){
                                    notDone = false;
                                }


                                if (!linesLeft && previousStep != null) {//at the start of a method
                                    MethodDescriptor previousMethod = previousStep.getMethodDescriptor();
                                    if (endLine == newLine && previousMethod == newMethod && oldMethod != newMethod) {
                                        notDone = false;
                                    }
                                }
                            }else{
                                isCreate = true;
                            }

                        } else {
                            FileDescriptor newDesc = getSymbolTable().getFileDescriptor(step.getFileKey());
                            boolean hasStatement = newDesc.hasStatementLine(getExecution().getLineNumber());
                            if (hasStatement) {
                                notDone = false;
                            }
                        }
                        VirtualMachineEvent event = new VirtualMachineEvent(step, machine, false);
                        throwEventToListeners(event);
                    }
                }
            } else {
                //throw an event indicating that the execution is finished
                //so the user knows "What's up?"
                VirtualMachineStopEvent event = new VirtualMachineStopEvent(
                        getNullExecutionStep(), machine, true, false);
                event.setEventKey(IntermediateConstants.EXECUTION_START.getName());
                throwEventToListeners(event);
            }
            updateDebuggerListeners();
        }
    }

    private class RunTo {

        protected String fileKey;
        protected long line;

        /**
         * @return the fileKey
         */
        public String getFileKey() {
            return fileKey;
        }

        /**
         * @param fileKey the fileKey to set
         */
        public void setFileKey(String fileKey) {
            this.fileKey = fileKey;
        }

        /**
         * @return the line
         */
        public long getLine() {
            return line;
        }

        /**
         * @param line the line to set
         */
        public void setLine(long line) {
            this.line = line;
        }
    }

    private class RunToCursor extends RunTo implements Runnable {
        StepInto step = new StepInto();
        public void run() {
            getPluginManager().setDebugMode(true);
            fireDebuggerEvents = false;
            //first get the file
            FileDescriptor fd = getSymbolTable().getFileDescriptor(fileKey);
            //step until we hit the line. If we never do, run forever
            int currentLine;
            String currentFileKey;
            if (execution.getCurrentStep() != null) {
                currentLine = execution.getLineNumber();
                currentFileKey = execution.getCurrentStep().getFileKey();
            } else {
                currentLine = -1;
                currentFileKey = "";
            }

            boolean breakHere = false;

            while (!getExecution().isExecutionFinished()
                    && !breakHere && (currentFileKey.compareTo(fileKey) != 0
                    || currentLine != (int) line) && !errorState) {
                step.run();
                if (execution.getCurrentStep() != null) {
                    currentLine = execution.getLineNumber();
                    currentFileKey = execution.getCurrentStep().getFileKey();

                    //check for breakpoint
                    Breakpoint key = new Breakpoint(currentFileKey, currentLine);
                    breakHere = getBreakpointManager().isBreakpointAtPosition(key);
                }
            }
            fireDebuggerEvents = true;
            updateDebuggerListeners();
        }
    }

    private class RunBackToCursor extends RunTo implements Runnable {
        StepBackInto step = new StepBackInto();
        public void run() {
            getPluginManager().setDebugMode(true);
            fireDebuggerEvents = false;
            if (getExecution().isExecutionAtBeginning()) {
                VirtualMachineStopEvent event = new VirtualMachineStopEvent(
                        getNullExecutionStep(), machine, true, false);
                event.setEventKey(IntermediateConstants.EXECUTION_START.getName());
                throwEventToListeners(event);
                return;
            }

            //first get the file
            FileDescriptor fd = getSymbolTable().getFileDescriptor(fileKey);
            //step until we hit the line. If we never do, run forever

            int currentLine;
            String currentFileKey;
            if (execution.getCurrentStep() != null) {
                currentLine = execution.getLineNumber();
                currentFileKey = execution.getCurrentStep().getFileKey();
            } else {
                currentLine = -1;
                currentFileKey = "";
            }

            while (!getExecution().isExecutionAtBeginning()
                    && (currentFileKey.compareTo(fileKey) != 0
                    || currentLine != (int) line)) {
                step.run();
                if (execution.getCurrentStep() != null) {
                    currentLine = execution.getLineNumber();
                    currentFileKey = execution.getCurrentStep().getFileKey();
                }
            }
            fireDebuggerEvents = true;
            updateDebuggerListeners();
        }
    }
}
