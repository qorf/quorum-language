/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Variables and parameters have some differences, but this class is a
 * general superclass that stores an interface for what they hold in common.
 * @author Andreas Stefik
 */
public class VariableParameterCommonDescriptor extends Descriptor{

    private Scopable parent;
    private TypeDescriptor type;
    private ArrayList<GenericDescriptor> templateTypes = new ArrayList<GenericDescriptor>();
    private boolean initialized = false;
    private boolean isField;
    private boolean isConstant = false;

    /**
     * A numeric value that indicates the order in which a particular 
     * variable was added to a function or class.
     */
    private int variableNumber = -1;
    
    /**
     * @return the type
     */
    public TypeDescriptor getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeDescriptor type) {
        this.type = type;
    }

    /**
     * Add a GenericDescriptor, that represents a type of Object or some subclass
     * of Object, to a variable. For example, a variable "Array<Integer> a" can
     * be defined and a GenericDescriptor is generated to represent "Integer" then
     * it is added to the variable, "a" in this case.
     *
     *
     * @param gd
     */
    public void addTemplateType(GenericDescriptor gd){
        templateTypes.add(gd);
    }

    /**
     *
     * @return Iterator<GenericDescriptor> which is an iterator over template types
     * in a variable.
     */
    public Iterator<GenericDescriptor> getTemplateTypes(){
        return templateTypes.iterator();
    }

    /**
     *
     * @return number of template types are defined in this variable.
     */
    public int getNumberOfTemplateTypes(){
        return templateTypes.size();
    }

    @Override
    public String getStaticKey() {
        String key = "";
        if(parent == null) {
            key = this.getName();
        }
        else {
            key = parent.getScopeString() + ":" + this.getName();           
        }
        return key;
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

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * @param initialized the initialized to set
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    
    /**
     * @return the variableNumber
     */
    public int getVariableNumber() {
        return variableNumber;
    }

    /**
     * @param variableNumber the variableNumber to set
     */
    public void setVariableNumber(int variableNumber) {
        this.variableNumber = variableNumber;
    }
    
    /**
     * Determines whether this is a field variable or defined in another scope.
     * 
     * @return 
     */
    public boolean isFieldVariable() {
        return isField;
    }

    /**
     * Sets whether this variable is a field variable. This should only be true
     * if it is stored in the scope of a class descriptor.
     * 
     * @param isField the isField to set
     */
    public void setIsFieldVariable(boolean isField) {
        this.isField = isField;
    }

    /**
     * Flag the variable as a constant variable. If this is a field variable this
     * is similar to a static final variable in java. If this is local variable
     * the variable is final, as in java.
     * 
     * @param isConstant 
     */
    public void setIsConstant(boolean isConstant){
        this.isConstant = isConstant;
    }
    
    /**
     * Returns true if the variable is a constant variable.
     * 
     * @return 
     */
    public boolean isConstant() {
        return isConstant;
    }
}
