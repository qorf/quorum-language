/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.symbols.MethodDescriptor;

/**
 * This class stores the values of variables as the state of a program
 * changes. It allows for 1) register storage, 2) Activation Records,
 * 3) Heap Allocation, and 4) Omniscient Debugging. All data in this class
 * is time stamped to facilitate searching and profiling applications.
 * 
 * @author Andreas Stefik and Aaron Willows
 */
public class DataEnvironment {

    /**
     * The execution environment this data environment resides in.
     */
    private Execution execution;

    /** A copy of the virtual machine is stored so that the data environment
     * can reference the execution environment and the symbol table.
     */
    private AbstractVirtualMachine virtualMachine;

    /** A set of registers the environment can use.
     * 
     */
    private Vector<DataObject> registers;
    private HashMap<Integer, RuntimeObject> heap;
    private Integer tempObject = new Integer(0);
    private RuntimeObject thisObject;
    private int thisObjectID;
    private int currentHashKey = 1;
    private OmniscientStack<RuntimeScope> callStack;
    private OmniscientStack<BlockScope> checkScopeStack;
    private RuntimeScope currentScope;
    private OmniscientStack<DataObject> dataStack;
    private DataHistory dataHistory;
    private Stack<Integer> undoNearestActivationRecordNumbers;

    private OmniscientStack<RuntimeObject> callingClassStack;

    /** This is a stack of op-codes used for initializing objects.
     *
     */
    private OmniscientStack<Integer> objectInitStack;

    /** Instantiates a default DataEnvironment object.
     * 
     */
    public DataEnvironment() {
        registers = new Vector<DataObject>();
        callStack = new OmniscientStack<RuntimeScope>();
        heap = new HashMap<Integer, RuntimeObject>();
        currentScope = new NullRuntimeScope();
        dataStack = new OmniscientStack<DataObject>();
        dataHistory = new DataHistory();
        callingClassStack = new OmniscientStack<RuntimeObject>();
        objectInitStack = new OmniscientStack<Integer>();
        checkScopeStack = new OmniscientStack<BlockScope>();
        undoNearestActivationRecordNumbers = new Stack<Integer>();
    }

    /**
     * Sets the value of register i to the expression value, value.
     * Values are time stamped at the time that they are set, according
     * to the value currently in the execution environment.
     * @param i
     * @param value
     */
    public void setRegister(int i, ExpressionValue value) {
        RuntimeScope scope = this.getLocalScope();    
        TimeStamp stamp = new TimeStamp(execution.getTimeStamp());
        if(scope != null) {
            scope.pushRegisterValue(i, value, stamp);
        }
        else {
            if (i >= registers.size()) {//we need more registers!
                registers.setSize((i + 1) * 2);
            }
            //then check if there is a register stack at this location
            if (registers.elementAt(i) == null) {
                //add a new register stack
                DataObject o = new DataObject();
                registers.set(i, o);
            }
            registers.elementAt(i).pushValue(value, stamp);
        }
        
    }

    /**
     * If the debugger is currently omniscient, this method removes
     * the previous value of register i.
     * @param i
     * @return
     */
    public ExpressionValue popRegister(int i) {
        RuntimeScope scope = this.getLocalScope();    
        if(scope != null) {
            return scope.popRegisterValue(i);
        }
        else {
            return registers.elementAt(i).popValue();
        }
    }

    /**
     * Returns the value of a given register without altering its state. Null
     * is returned if no value exists.
     * @param i
     * @return
     */
    public ExpressionValue getRegister(int i) {
        RuntimeScope scope = this.getLocalScope();    
        if(scope != null) {
            return scope.getRegisterValue(i);
        }
        else {
            return registers.elementAt(i).getCurrentValue();
        }
    }

    /**
     * Safely determines whether a particular register currently has a value.
     * 
     * @param i
     * @return
     */
    public boolean hasRegisterValue(int i) {
        if(registers.isEmpty() || 
           registers.size() <= i) {
            return false;
        }
        
        if(registers.elementAt(i) == null ||
           registers.elementAt(i).getCurrentValue() == null) {
            return false;
        }
        return true;
    }

    /**
     * This method looks through the runtime environment to find the value
     * of the variable specified by the variable descriptor object
     * @param descriptor
     */
    public ExpressionValue getVariableValue(String key) {
            return currentScope.getVariable(key);
    }

    /**
     * Returns the number of variables currently in this scope.
     * 
     * @return the number of variables accessible from this scope
     */
    public int getNumberVariables() {
        if (!callStack.isEmpty()) {
            return currentScope.getNumberLocalVariables() + heap.get(tempObject).getNumberLocalVariables();
        }
        else {
            return heap.get(tempObject).getNumberLocalVariables();
        }
    }

    /** 
     * This method sets the value of a variable to a given value. If the 
     * debugger is currently omniscient, the previous values are retained.
     * @param descriptor
     * @param value
     */
    public void setVariableValue(String key, ExpressionValue value) {
         currentScope.addVariable(key, value);
         //regardless of scope, store this in our history
         DataObject object = new DataObject();
         object.setName(key);
         int time = execution.getTimeStamp();
         TimeStamp stamp = new TimeStamp(time);
         object.pushValue(value, stamp);
         dataHistory.push(object);
    }

    /**
     * This method sets the value of a variable to a given value. If the
     * debugger is currently omniscient, the previous values are retained.
     * In this case, regardless of whether the value exists in the parent
     * scope, this value is always added to the local scope only.
     * 
     * @param key
     * @param value
     */
    public void setVariableValueLocalScopeOnly(String key, ExpressionValue value) {
        currentScope.addVariableLocalScopeOnly(key, value);
        //regardless of scope, store this in our history
        DataObject object = new DataObject();
        object.setName(key);
        int time = execution.getTimeStamp();
        TimeStamp stamp = new TimeStamp(time);
        object.pushValue(value, stamp);
        dataHistory.push(object);
    }

    /**
     * Pushes the opcode number that instantiated an object onto
     * the stack.
     *
     * @param opcode
     */
    public void pushCreateObjectOpcode(int opcode) {
        objectInitStack.push(opcode);
    }

    /**
     * Pops the opcode number that instantiated an object off the stack
     * to be used in a jump statement.
     * @return
     */
    public int popCreateObjectOpcode() {
        return objectInitStack.pop();
    }
    
    /**
     * Undoes a pop operation from an object opcode.
     */
    public void undoPopCreateObjectOpcode() {
        objectInitStack.undo();
    }
    
    /**
     * Pops the last opcode number from the object stack and puts it into
     * an undo/redo as appropriate.
     * 
     * @return
     */
    public int undoCreateObjectOpcode() {
        objectInitStack.undo();
        if(!objectInitStack.isEmpty()) {
            return objectInitStack.peek();
        }
        else {
            return -1;
        }
    }

    /** 
     * This method pops off the current value for the given variable and
     * restores it to its previous state.
     *
     * TODO: Is the array implementation here actually correct? Seems as if
     * arrays are running through a different architecture.
     * 
     * @param descriptor
     * @return
     */
    public ExpressionValue popVariableValue(String key) {
        dataHistory.pop(key);
        return currentScope.undoAddVariable(key);
    }

/**
     * This method pops off the current value for the given variable and
     * restores it to its previous state.
     * @param descriptor
     * @return
     */
    public ExpressionValue popVariableValueLocalScopeOnly(String key) {
        dataHistory.pop(key);
        return currentScope.undoAddVariableLocalScopeOnly(key);
    }

    /**
     * This method pops off the current value for the given variable and
     * restores it to its previous state.
     * @param descriptor
     * @return
     */
    public ExpressionValue popArrayValue(String variableName,String key) {
        dataHistory.pop(key);

        if (!callStack.isEmpty()) {
            return callStack.peek().undoAddVariable(key);
        } else {
            return heap.get(tempObject).undoAddVariable(key);
        }
    }

    /**
     * Returns an iterator of all variables currently in scope.
     * @return
     */
    public Iterator<DataObject> getLocalVariables() {
       return currentScope.getLocalVariables();
    }

    /**
     * Returns an iterator of all variables that are in the current local scope
     * and any parent lexical scope contained in the current action.
     *
     * @return
     */
    public Iterator<DataObject> getVariablesInActionScope() {
        HashMap<String, DataObject> objects = new HashMap<String, DataObject>();
        Stack<RuntimeScope> stack = callStack.getStack();

        if(stack.isEmpty()) {
            return objects.values().iterator(); //empty, but that's fine.
        }

        for(int i = stack.size() - 1; i >= 0; i--) {
            RuntimeScope scope = stack.get(i);
            Iterator<DataObject> locals = scope.getLocalVariables();
            while(locals.hasNext()) {
                DataObject next = locals.next();
                if(!objects.containsKey(next.getName())) {
                    objects.put(next.getName(), next);
                }//otherwise, ignore it.
            }

            if(scope instanceof ActivationRecord) {
                return objects.values().iterator();
            }
        }
        return objects.values().iterator();
    }

    /**
     * Returns an iterator that contains all variables accessible
     * from the current scope, including those in a parent scope (e.g., object).
     *
     * @return
     */
    public Iterator<DataObject> getAllVariables() {
        return currentScope.getAllVariables();
    }

    /**
     * @return the virtualMachine
     */
    public AbstractVirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    /**
     * @param virtualMachine the virtualMachine to set
     */
    public void setVirtualMachine(AbstractVirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
        execution = virtualMachine.getExecution();
    }

    /**
     * Returns the number of registers the system has allocated.
     * @return
     */
    public int getNumberRegisters() {
        return registers.size();
    }

    /**
     * Destroys all data currently in this data environment.
     */
    public void clear() {
        registers.clear();
        heap = new HashMap<Integer, RuntimeObject>();
        currentScope = new NullRuntimeScope();
        callStack.clear();
        dataStack.clear();
        currentHashKey = 1;
        objectInitStack = new OmniscientStack<Integer>();
        dataHistory.clear();
        callingClassStack = new OmniscientStack<RuntimeObject>();
        checkScopeStack = new OmniscientStack<BlockScope>();
        undoNearestActivationRecordNumbers.clear();
    }

    /**
     * Pushes a value onto the stack
     * @param value
     */
    public void callStackPush(RuntimeScope value) {
        currentScope = value;
        callStack.push(value);
    }

    /**
     * Pops a value off the stack
     * @return
     */
    public RuntimeScope callStackPop() {
        RuntimeScope rec = callStack.peek();
        rec = callStack.pop();
        if(!callStack.isEmpty()){
            currentScope = callStack.peek();
        }
        return rec;
    }

    /**
     * Undoes whatever operation was last preformed on the stack
     */
    public void callStackUndo() {
        callStack.undo();
        if(!callStack.isEmpty()) {
            currentScope = callStack.peek();
        }
    }

    /**
     * Peeks into the current activation record.
     * 
     * @return
     */
    public RuntimeScope callStackPeek() {
        if(callStack.isEmpty()){
            return null;
        }
        return callStack.peek();
    }

    /**
     * Get the current activation record on the call stack.
     * 
     * @return 
     */
    public ActivationRecord peekNearestActivationRecord() {
        Stack<RuntimeScope> stack = callStack.getStack();
        int i = stack.size() - 1;
        while (i >= 0) {
            RuntimeScope scope = stack.get(i);
            if (scope instanceof ActivationRecord) {
                return (ActivationRecord) scope;
            }
            i--;
        }
        return null;
    }

    /**
     * Remove the current activation record from the call stack.
     */
    public void popNearestActivationRecord() {
        Stack<RuntimeScope> stack = callStack.getStack();
        int i = stack.size() - 1;
        int count = 0;
        while(!callStack.isEmpty()) {
            RuntimeScope scope = callStack.pop();
            count++;
            if(scope instanceof ActivationRecord) {
                if(!callStack.isEmpty()){
                    currentScope = callStack.peek();
                }
                undoNearestActivationRecordNumbers.push(count);
                return;
            }
        }
    }
    
    public void undoPopNearestActivationRecord(){
        if(!undoNearestActivationRecordNumbers.isEmpty()){
            Integer numberToUndo = undoNearestActivationRecordNumbers.pop();
            for(int i = 0; i < numberToUndo; i++){
                callStack.undo();
            }
            currentScope = callStack.peek();
        }
    }

    /**
     * Determines whether the top of the stack is an activation record.
     * 
     * @return
     */
    public boolean canPopActivationRecord() {
        RuntimeScope peek = callStack.peek();
        if(peek instanceof ActivationRecord) {
            return true;
        }
        else {
            return false;
        }
    }

    public Iterator<RuntimeScope> callStackIterator(){
        Stack<RuntimeScope> stack = callStack.getStack();
        return stack.iterator();
    }

    public boolean canPeekCallStack() {
        return !callStack.isEmpty();
    }

    /**
     * Pushes a value onto the stack
     * @param value
     */
    public void checkScopeStackPush(BlockScope value) {
        checkScopeStack.push(value);
    }

    /**
     * Pops a value off the check(try) scope stack.
     *
     * @return
     */
    public BlockScope checkScopeStackPop(){
        if(checkScopeStack.isEmpty()){
            return null;
        }else{
            return checkScopeStack.pop();
        }
    }

    /**
     * Undo the last operation on the check scope stack.
     */
    public void checkScopeStackUndo(){
        checkScopeStack.undo();
    }

    /**
     * Peeks into the containing check scope.
     * @return
     */
    public BlockScope checkScopeStackPeek(){
        if(checkScopeStack.isEmpty()){
            return null;
        }
        return checkScopeStack.peek();
    }

    /**
     * Returns a description of the local scope.
     * 
     * @return
     */
    public RuntimeScope getLocalScope() {
        return currentScope;
    }

    /**
     * Pushes a value onto the stack
     * @param value
     */
    public void dataStackPush(DataObject value) {
        getdataStack().push(value);
    }

    /**
     * Pops a value off the stack
     * @return
     */
    public DataObject dataStackPop() {
        return getdataStack().pop();
    }

    /**
     * Undoes whatever operation was last preformed on the stack
     */
    public void dataStackUndo() {
        getdataStack().undo();
    }

    /**
     * Peeks at the previous data object.
     * 
     * @return
     */
    public DataObject dataStackPeek() {
        return getdataStack().peek();
    }

    /**
     * @return the stack
     */
    private OmniscientStack<DataObject> getdataStack() {
        return dataStack;
    }

    /**
     * Returns the next available key for an object on the system
     * @return the currentHashKey
     */
    public int getCurrentHashKey() {
        return currentHashKey;
    }

    /**
     * This object is akin to "new" or "malloc" in that it carves out
     * memory space in the heap for an object.
     * @param object
     * @return
     */
    public int addNewObject(RuntimeObject object) {
        object.setHashKey(currentHashKey);

        //put it in the heap
        heap.put(currentHashKey, object);

        currentHashKey++;
        return currentHashKey - 1;
    }

    private Stack<MethodDescriptor> methodsCalled = new Stack<MethodDescriptor>();
    public void pushMethodCall(MethodDescriptor method) {
        methodsCalled.push(method);
    }

    public MethodDescriptor popMethodCall() {
        return methodsCalled.pop();
    }

    public int getNumberOfMethodsCalled() {
        return methodsCalled.size();
    }

    /**
     * This method effectively "deletes" an object from memory.
     * @param hash
     * @return
     */
    public RuntimeObject removeObject(int hash) {
        return heap.remove(hash);
    }

    /**
     * Returns a runtimeobject for a particular function call.
     * 
     * @param hash
     * @return
     */
    public RuntimeObject getObject(int hash) {
        return heap.get(hash);
    }

    /**
     * This method sets the current scope to that of a particular
     * object, outside of any particular activation record.
     * @param hash
     * @return
     */
    public boolean setToObjectScope(int hash) {
        RuntimeObject obj = heap.get(hash);
        if(obj != null) {
            thisObject = obj;
            thisObjectID = hash;
            this.callStackPush(obj);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method tries to set the current scope for the "this" pointer
     * to the object located at hash value "hash." This changes the
     * current runtime scope so that any function call or data is now
     * relative to the position of the new object.
     * @param hash
     * @return
     */
    public boolean setThisPointer(int hash) {
        RuntimeObject obj = heap.get(hash);
        if(obj != null) {
            thisObject = obj;
            thisObjectID = hash;
            
            //what else do we have to do?
            if(!currentScope.isValidScope()) {
                currentScope = obj; //it is the initial case, throw away the null
            }
            else {//do we check the scope walkers for the parent
                  //and change the "This" there as well?

            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the "this" pointer currently in the virtual machine.
     *
     * @return
     */
    public int getThisPointer() {
        return this.thisObjectID;
    }

    /**
     * Returns the object currently being pointed to by the "this" pointer.
     * 
     * @return
     */
    public RuntimeObject getThisObject() {
        return this.thisObject;
    }

    /**
     * @return the dataHistory
     */
    public DataHistory getDataHistory() {
        return dataHistory;
    }

    /**
     * @param dataHistory the dataHistory to set
     */
    public void setDataHistory(DataHistory dataHistory) {
        this.dataHistory = dataHistory;
    }

    /**
     * Push a this object onto the calling class stack. This stack maintains
     * the state of the class when an object is instantiated.
     * @param object
     */
    public void callingClassStackPush(RuntimeObject object){
        callingClassStack.push(object);
    }

    /**
     * Pop a this object off the calling class stack
     * @return
     */
    public RuntimeObject callingClassStackPop(){
        if(!callingClassStack.isEmpty())
            return callingClassStack.pop();
        return null;
    }

    /**
     * Undo what ever operation just happened on the stack.
     */
    public void callingClassStackUndo(){
        callingClassStack.undo();
    }

    /**
     * Peeks at the previous object calling class stack.
     * @return
     */
    public RuntimeObject callingClassStackPeek() {
        if(!callingClassStack.isEmpty())
            return getCallingClassStack().peek();
        return null;
    }

    /**
     *
     * @return the stack of this objects that instantiated an object.
     */
    private OmniscientStack<RuntimeObject> getCallingClassStack() {
        return callingClassStack;
    }

    /**
     * determines whether the calling class stack is empty. It is empty, true is returned.
     *
     * @return
     */
    public boolean isCallingClassStackEmpty(){
        return callingClassStack.isEmpty();
    }
}
