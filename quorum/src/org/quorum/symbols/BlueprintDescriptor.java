/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

import java.util.ArrayList;
import java.util.Iterator;
import org.quorum.vm.interfaces.CompilerError;

/**
 * Describes a blueprint or prototype for a method.
 *
 * @author Melissa Stefik
 */
public class BlueprintDescriptor extends MethodDescriptor {

    /**
     * A method that generates a key signature for a method with a given name and
     * set of arguments.
     *
     * @param name
     * @param arguments
     * @return
     */
    public static String autoGenerateKey(String name, ArrayList<TypeDescriptor> arguments) {
        String key = "";
        key += name;
        for (int i = 0; i < arguments.size(); i++) {
            key += ":";
            key += arguments.get(i).getName();
        }
        return key;
    }

    /**
     *
     * @param name
     * @param parameters
     * @return
     */
    private static String generateKey(String name, Parameters parameters) {
        String key = "";
        key += name;
        for (int i = 0; i < parameters.size(); i++) {
            key += ":";
            key += parameters.get(i).getType().getStaticKey();
        }
        return key;
    }

    @Override
    public String getStaticKey() {
        key = generateKey(getName(), parameters);
        return key;
    }

    @Override
    public TypeDescriptor getReturnType() {
        return returnType;
    }

    @Override
    public void setReturnType(TypeDescriptor returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean isMainMethod() {
        return this.getName().compareToIgnoreCase("Main") == 0;
    }

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public Scopable getParent() {
        return parent;
    }

    @Override
    public void setParent(Scopable parent) {
        this.parent = parent;
    }

    @Override
    public CompilerError add(ParameterDescriptor descriptor) {
        //if this is not a main method, try to add the parameter.
        CompilerError error = getParameters().add(descriptor);
        if (error != null) {
            return error;
        } else {
            return null;
        }
    }

    /**
     * Compare the blueprint to a methods signature. If they match return true
     * otherwise return false.
     * @param method
     * @return
     */
    public boolean compare(MethodDescriptor method) {
        return compare(this, method);
    }

    /**
     * Compare bp (BlueprintDescriptor) and md (MethodDescriptor) to see if
     * their signatures match. This is done by comparing their static keys
     * and their return types.
     *
     * @param bp
     * @param md
     * @return true if the blueprint and method are matching and false if they are not.
     */
    private static boolean compare(BlueprintDescriptor bp, MethodDescriptor md) {
        if (bp != null && md != null) {
            if (bp.getStaticKey().equals(md.getStaticKey()) && bp.getReturnType().getStaticKey().equals(md.getReturnType().getStaticKey())) { 
                return true;
            }else if(bp.getStaticKey().equals(md.getStaticKey()) && bp.getReturnType().isTemplated()){
                MethodDescriptor resolvedTemplatedMethod = ((ClassDescriptor)md.getParent()).getResolvedTemplatedMethod((ClassDescriptor)bp.getParent(), bp);
                if(resolvedTemplatedMethod != null && resolvedTemplatedMethod.getReturnType().compare(md.getReturnType())){
                    if(md.getReturnType().hasSubTypes() && resolvedTemplatedMethod.getReturnType().hasSubTypes()){
                        Iterator<GenericDescriptor> bpSubTypes = resolvedTemplatedMethod.getReturnType().getSubTypes();
                        Iterator<GenericDescriptor> mdSubTypes = md.getReturnType().getSubTypes();
                        GenericDescriptor bpNext = null;
                        GenericDescriptor mdNext = null;
                        while(bpSubTypes.hasNext() && mdSubTypes.hasNext()){
                            bpNext = bpSubTypes.next();
                            mdNext = mdSubTypes.next();
                        }
                        if(bpNext != null && mdNext != null){
                            return bpNext.getType().compare(mdNext.getType());
                        }else{
                            return false;
                        }
                    }
                    return true;
                }
            }
        } else if (bp == null && md == null) {
            return true;
        }

        return false;
    }

    @Override
    public AccessModifierEnum getAccessModifier() {
        return accessModifier;
    }

    @Override
    public void setAccessModifier(AccessModifierEnum accessModifier) {
        this.accessModifier = accessModifier;
    }

    /**
     * Returns true if the blueprint has a modifier of "private".
     * @return
     */
    public boolean isPrivate() {
        return accessModifier.equals(AccessModifierEnum.PRIVATE);
    }
}
