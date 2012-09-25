/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.LineInformation;

/**
 *
 * @author Andreas Stefik and Melissa Stefik
 */
public class MethodDescriptor extends Descriptor implements Scopable {

    protected Parameters parameters = new Parameters();
    private Vector<BlockDescriptor> children;
    private HashMap<String, VariableParameterCommonDescriptor> variables;
    private HashMap<Integer, Object> statementList;
    private int currentBlock;
    protected String key = "";
    protected TypeDescriptor returnType;
    private HashMap<String, TypeDescriptor> mappedTemplateType = new HashMap<String, TypeDescriptor>();
    protected Scopable parent;
    protected AccessModifierEnum accessModifier;
    private MethodLocation location;
    private boolean isResolved = true;
    private boolean isConstructor = false;
    private LinkedList<Integer> registers;
    private HashMap<String, ArrayList<String>> checkBlocks;

    /**
     * Constructor
     */
    public MethodDescriptor() {
        children = new Vector<BlockDescriptor>();
        variables = new HashMap<String, VariableParameterCommonDescriptor>();
        statementList = new HashMap<Integer, Object>();
        location = new MethodLocation();
        registers = new LinkedList<Integer>();
        checkBlocks = new HashMap<String, ArrayList<String>>();
    }

    public CompilerError add(VariableDescriptor descriptor) {
        CompilerError error = super.isDefined(descriptor, getVariables());
        if (error == null) {
            getVariables().put(descriptor.getStaticKey(), descriptor);
            descriptor.setVariableNumber(getNumberOfVariables());
        }

        return error;
    }

    /**
     * Adds a register that is being used by the system during this function call.
     *
     * @param reg
     */
    public void addUsedRegister(int reg) {
        registers.add(reg);
    }

    /**
     * Clears out the registers being used by this method during a function call.
     */
    public void clearUsedRegisters() {
        registers.clear();
    }

    /**
     * Returns an iterator of the registers being used by this function call.
     * 
     * @return
     */
    public Iterator<Integer> getUsedRegisters() {
        return registers.iterator();
    }

    /** This method adds a parameter to this method. It also
     * handles doing the activation layout so that the VM can appropriately
     * layout the activation records.
     * @param d
     */
    public CompilerError add(ParameterDescriptor d) {
        //Bug Fix: #522 melissa stefik
        //TODO: add a warning for main methods with parameters
//        if(isMainMethod()) {
//            CompilerError mainError = new CompilerError();
//            mainError.setLineNumber(d.getLineBegin());
//            mainError.setColumn(d.getColumnBegin());
//            mainError.setError("Methods named " + getName() +
//                    " may not have parameters.");
//            return mainError;
//        }

        CompilerError error = null;
        //if a parameter is added that is not a primitive, this
        //action will need to be resolved later
        if(d.getType() != null){
            if (!d.getType().isPrimitiveType()) {
                this.isResolved = false;
            }
        }else{
            this.isResolved = false;
        }

        //if this is not a main method, try to add the parameter.
        error = getParameters().add(d);
        if (error != null) {
            return error;
        } else {
            variables.put(d.getName(), d);
           // d.setVariableNumber(getNumberOfVariables());
            return null;
        }
    }

    public CompilerError add(BlueprintDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a blueprint to a method.");
    }

    public CompilerError add(SystemActionDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a system function to a method.");
    }

    public CompilerError add(ClassDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a class to a method.");
    }

    public CompilerError add(MethodDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a action to a block.");
    }

    public ClassDescriptor getClass(String key) {
        return null;
    }

    public MethodDescriptor getMethod(String key) {
        if (parent != null) {
            return parent.getMethod(key);
        } else {
            return null;
        }
    }

    public BlueprintDescriptor getBlueprint(String key) {
        return null;
    }

    public VariableParameterCommonDescriptor getVariable(String key) {
        VariableParameterCommonDescriptor var = getVariables().get(key);
        if (var == null) {
            var = parent.getVariable(key);
        }
        return var;
    }

    @Override
    public LineInformation getLineInformation(){
        LineInformation info = new LineInformation();
        info.setStartLine(getLineBegin());
        info.setStartColumn(getColumnBegin());
        info.setEndLine(getLineEnd());
        info.setEndColumn(getColumnEnd());
        info.setClassName(parent.getScopeString());
        info.setFile(((ClassDescriptor)parent).getFile().getStaticKey());
        info.setMethodName(getStaticKey());
        return info;
    }

    /**
     * Ultimately, replace this method with autoGenerateKey.
     * 
     * @deprecated
     * @param name
     * @param types
     * @return
     */
    @Deprecated
    public static String generateKey(String name, Vector<String> types) {
        String key = "";
        key += name;
        for (int i = 0; i < types.size(); i++) {
            key += ":";
            key += types.elementAt(i);
        }
        return key;
    }

    /**
     * A method that generates a key signature for a method with a given name and
     * set of arguments.
     *
     * @param name
     * @param arguments
     * @return
     */
    public static String autoGenerateKey(String name, Vector<TypeDescriptor> arguments) {
        String key = "";
        key += name;
        for (int i = 0; i < arguments.size(); i++) {
            key += ":";
            if(arguments.elementAt(i) != null)
                key += arguments.elementAt(i).getStaticKey();
        }
        return key;
    }

    @Override
    public String getStaticKey() {
        key = generateKey(getName(), parameters);
        return key;
    }

    private static String generateKey(String name, Parameters parameters) {
        String key = "";
        key += name;
        for (int i = 0; i < parameters.size(); i++) {
            key += ":";
            ParameterDescriptor descriptor = parameters.get(i);
            TypeDescriptor type = descriptor.getType();
            if(type != null) {
                key += parameters.get(i).getType().getStaticKey();
            }//if this is null, the user may have a compiler error in their source.
        }
        return key;
    }

    public String getMethodSignature(boolean printVariableNames) {
        String key = "";
        key += this.getName() + "(";
        for (int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor descriptor = parameters.get(i);
            TypeDescriptor type = descriptor.getType();
            if(type != null) {
                key += parameters.get(i).getType().getStaticKey();
            }//if this is null, the user may have a compiler error in their source.
            
            if(printVariableNames) {
                key += " " + descriptor.getName();
            }
            
            if(i != parameters.size() - 1) {
                key += ", ";
            }
        }
        key += ")";
        
        TypeDescriptor myReturn = this.getReturnType();
        key += " " + myReturn.getStaticKey();
        
        return key;
    }
    
    /**
     * This method returns an appropriately formatted string of subtypes for a
     * generic descriptor.
     * 
     * @param type
     * @return 
     */
    private String getSubtypeString(TypeDescriptor type) {
        if(!type.hasSubTypes()) {
            return "";
        }
        String typeString = "<";
        Iterator<GenericDescriptor> subit = type.getSubTypes();
        while(subit.hasNext()) {
            GenericDescriptor next = subit.next();
            TypeDescriptor sub = next.getType();
            if(sub.hasSubTypes()) {
                typeString += sub.getStaticKey() + getSubtypeString(sub);
            } else {
                typeString += sub.getStaticKey();
            }
            
            if(subit.hasNext()) {
                typeString += ", ";
            }
            else {
                typeString += ">";
            }
        }
        return typeString;
    }
    
    /**
     * This method returns a method signature. If this method is templated,
     * variable must be non-null and pass a variable with correct templated
     * types. If this method does not use any templated types in its parameter
     * list, this value will have no effect. This method does not impact
     * the correctness of the compiler in any way, and is used only as a 
     * pretty printing algorithm.
     * 
     * @param printVariableNames
     * @param variable
     * @return 
     */
    public String getMethodSignature(boolean printVariableNames, 
            VariableParameterCommonDescriptor variable, ClassDescriptor clazz) {
        if(variable == null) {
            return getMethodSignature(printVariableNames);
        }        
        
        HashMap<String, String> templates = new HashMap<String, String>();
        Iterator<GenericDescriptor> generics = variable.getTemplateTypes();
        Iterator<GenericDescriptor> templateNames = clazz.getTemplateVariables();
        while(generics.hasNext()) {
            //this one is the actual template value (e.g., text)
            GenericDescriptor generic = generics.next();
            
            //this one is the template name (e.g., Key, Value)
            GenericDescriptor name = templateNames.next();
            
            String theKey = name.getType().getTemplateName();
            String value = generic.getType().getStaticKey();
            
            //fill this out with subtype information for testing
            if(generic.getType().hasSubTypes()) {
                value += getSubtypeString(generic.getType());
            }
            
            
            templates.put(theKey, value);
        }
        String theKey = "";
        theKey += this.getName() + "(";
        for (int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor descriptor = parameters.get(i);
            TypeDescriptor type = descriptor.getType();
            if(type != null) {
                String templateName = type.getTemplateName();
                if(templateName != null && templates.containsKey(templateName)) {
                    theKey += templates.get(templateName);
                } else {
                    theKey += parameters.get(i).getType().getStaticKey();
                }
            }//if this is null, the user may have a compiler error in their source.
            
            if(printVariableNames) {
                theKey += " " + descriptor.getName();
            }
            
            if(i != parameters.size() - 1) {
                theKey += ", ";
            }
        }
        theKey += ")";
        TypeDescriptor myReturn = this.getReturnType();
        String returnTemplateName = null;
        returnTemplateName = myReturn.getTemplateName();
        if(returnTemplateName != null && templates.containsKey(returnTemplateName) &&
                !myReturn.isVoid()) {
            theKey += " returns " + templates.get(returnTemplateName);
        } else if(!myReturn.isVoid()){
            theKey += " " + myReturn.getStaticKey();
        }
        
        
        return theKey;
    }
    
    /**
     * Inserts a flag at a given line for this file, indicating that
     * a step over command should stop here, as it is the beginning of a new
     * statement.
     * @param line
     */
    public void addStatementLine(int line) {
        statementList.put(line, null);
    }

    /**
     * Removes a given line from the list of valid statements.
     *
     * @param line
     * @return
     */
    public boolean removeStatementLine(int line) {
        if (statementList.containsKey(line)) {
            statementList.remove(line);
            return true;
        }
        return false;
    }

    /**
     * Determines whether a given line has a statement associated with it. If
     * it does, a step over call will stop at this location.
     * @param line
     * @return
     */
    public boolean hasStatementLine(int line) {
        return statementList.containsKey(line);
    }

    /**
     * Searches through the list of lines in this class and tries
     * to find one that is strictly larger than the line given. If there is
     * a larger line, this method returns true, otherwise it returns false.
     * @param line
     * @return
     */
    public boolean findLargerLine(int line) {
        //TODO: Optimize this
        Iterator<Integer> ints = getStatementLines();
        while (ints.hasNext()) {
            if (ints.next() > line) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches through the list of lines in this class and tries
     * to find one that is strictly larger than the line given. If there is
     * a larger line, this method returns true, otherwise it returns false.
     * @param line
     * @return
     */
    public boolean findSmallerLine(int line) {
        //TODO: Optimize this
        Iterator<Integer> ints = getStatementLines();
        while (ints.hasNext()) {
            if (ints.next() < line) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a complete list of all keys stored for statements in this
     * file. Do not use this method unless you actually need the entire set.
     * Otherwise, inspect on a line by line basis using
     * hasStatementLine(int).
     * @return
     */
    public Iterator<Integer> getStatementLines() {
        return statementList.keySet().iterator();
    }

    /**
     * @return the returnType
     */
    public TypeDescriptor getReturnType() {
        return returnType;
    }

    /**
     * @param returnType the returnType to set
     */
    public void setReturnType(TypeDescriptor returnType) {
        this.returnType = returnType;
    }

    /**
     * @return the parent
     */
    public Scopable getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Scopable parent) {
        this.parent = parent;
    }

    public void add(BlockDescriptor descriptor) {
        descriptor.setParent(this);
        children.add(descriptor);
    }

    /**
     * Gets the next block in this MethodDescriptor's children.
     * @deprecated use getChildren() instead
     * @return
     * 
     */
    @Deprecated
    public BlockDescriptor getNextBlock() {
        return children.get(currentBlock++);
    }

    public String getScopeString() {
        if (parent == null) {
            return getName();
        } else {
            return parent.getScopeString() + ":" + getName();
        }
    }

    /**
     * Returns the number of local variables that have been added
     * to this method descriptor, not including parameters.
     * @return 
     */
    public int getNumberVariables() {
        return variables.size();
    }
    
    /**
     * @return the variables
     */
    private HashMap<String, VariableParameterCommonDescriptor> getVariables() {
        return variables;
    }

    /**
     * @return the child scopes one level lower than this MethodDescriptor's scope
     */
    public Iterator<BlockDescriptor> getChildren() {
        return children.iterator();
    }

    /**
     * @return the parameters
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * @return the location
     */
    public MethodLocation getLocation() {
        return location;
    }

    /**
     * Determines if this method is a main method.
     * @return
     */
    public boolean isMainMethod() {
        return this.getName().compareToIgnoreCase("Main") == 0;
    }

    /**
     * Determines whether this method needs to be resolved. This only
     * occurs if a method has been added includes a non-primitive type.
     * 
     * @return the isResolved
     */
    public boolean isResolved() {
        return isResolved;
    }

    /**
     * Compare a method to a blueprint.
     *
     * @param blue
     * @return true if the method and blueprint signatures match and false
     * if they do not.
     */
    public boolean compare(BlueprintDescriptor blue) {
        return blue.compare(this);
    }

    /**
     * @return the accessModifier
     */
    public AccessModifierEnum getAccessModifier() {
        return accessModifier;
    }

    /**
     * @param accessModifier the accessModifier to set
     */
    public void setAccessModifier(AccessModifierEnum accessModifier) {
        this.accessModifier = accessModifier;
    }

    /**
     * allows the method descriptor to track which try blocks and attached detect
     * types are in the method.
     *
     * @param checkStaticKey
     * @param detectType
     */
    public void addDetectType(String checkStaticKey, String detectType){
        ArrayList<String> detectTypesInTry = checkBlocks.get(checkStaticKey);
        if(detectTypesInTry != null){
            detectTypesInTry.add(detectType);
        }else{
            detectTypesInTry = new ArrayList<String>();
            detectTypesInTry.add(detectType);
            checkBlocks.put(checkStaticKey, detectTypesInTry);
        }
    }

    /**
     * get the keys associated with the check blocks in this method.
     *
     * @return HashMap<String checkStaticKey,ArrayList<String detectType>>
     */
    public HashMap<String,ArrayList<String>> getCheckBlocks(){
        return checkBlocks;
    }
    
    /**
     * Flags the method as a constructor.
     */
    public void flagMethodAsConstructor(){
        isConstructor = true;
    }

    /**
     * Used to determine if a method is a constructor method.
     * 
     * @return true if this method is a constructor and false if it is not
     * a constructor.
     */
    public boolean isConstructor(){
        return isConstructor;
    }

    @Override
    public int getNumberOfVariables() {
        return parent.getNumberOfVariables() + getVariables().size();
    }

    /**
     * @return the mappedTemplateType
     */
    public TypeDescriptor getMappedTemplateType(String templateName) {
        return mappedTemplateType.get(templateName);
    }

    /**
     * @param mappedTemplateType the mappedTemplateType to set
     */
    public void addMappedTemplateType(String templateName, TypeDescriptor type) {
        this.mappedTemplateType.put(templateName, type);
    }
}
