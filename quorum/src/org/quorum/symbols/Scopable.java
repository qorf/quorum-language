/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import org.quorum.vm.interfaces.CompilerError;

/**
 *
 * @author Aaron Wilows and Melissa Stefik
 */
public interface Scopable {
    /**
     * Get a variable descriptor from the current scope given a static key
     * for that variable.
     * @param key is the static key representing the variable
     * @return the VariableParameterCommonDescriptor with the static key matching key
     */
    public VariableParameterCommonDescriptor getVariable(String key);

    /**
     * Get a method descriptor from the current scope given a static key
     * for that method.
     * @param key is the static key representing the method
     * @return the MethodDescriptor with the static key matching key
     */
    public MethodDescriptor getMethod(String key);

    /**
     * Get a blueprint descriptor from the current scope given a static key
     * for that blueprint.
     * @param key is the static key representing the blueprint
     * @return the BlueprintDescriptor with the static key matching key
     */
    public BlueprintDescriptor getBlueprint(String key);

    /**
     * Get a class given the name of the class
     * @param key is the static key representing a class
     * @return the classDescriptor of a class with the key
     */
    public ClassDescriptor getClass(String key);

    /**
     * Get the next code block.
     *
     * @return the BlockDescriptor
     */
    public BlockDescriptor getNextBlock();

    /**
     * Add a Variable to the scope.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(VariableDescriptor descriptor);

    /**
     * Add a parameter to the scope.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(ParameterDescriptor descriptor);

    /**
     * Add a method to the scope.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(MethodDescriptor descriptor);

    /**
     * Add a blueprint and return a compiler error if unsuccessful.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(BlueprintDescriptor descriptor);

    /**
     * Add a system action and return a compiler error if unsuccessful.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(SystemActionDescriptor descriptor);
    
    /**
     * Add a Class to the scope.
     * @param descriptor
     * @return a compiler error if one is generated. If one is not generated
     * null is returned.
     */
    public CompilerError add(ClassDescriptor descriptor);

    /**
     * Add a Block to the scope.
     * @param descriptor
     */
    public void add(BlockDescriptor descriptor);

    /**
     * 
     * @return a string representing the scope of the scopable item
     */
    public String getScopeString();
    

    /**
     * Set the parent of this scopable item
     * @param parent
     */
    public void setParent(Scopable parent);

    /**
     * Get the parent of this scopable item
     * @return scopable item
     */
    public Scopable getParent();
}
