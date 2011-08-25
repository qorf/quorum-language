/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.ErrorType;
import org.quorum.vm.interfaces.LineInformation;

/**
 *
 * @author Andreas Stefik and Melissa Stefik
 */
public class ClassDescriptor extends Descriptor implements Scopable {

    /** This represents the constructor for this class*/
    private MethodDescriptor constructor;
    /** This represents the methods that are defined inside of this class. */
    private HashMap<String, MethodDescriptor> methods;
    /** This represents the variables defined in this class. */
    private HashMap<String, VariableDescriptor> variables;
    /** This class represents the package that this particular class is in. */
    private ContainerDescriptor container = new ContainerDescriptor();
    /** This class represents uses that are listed in the user's description of
     * the class. They have not been validated and may not represent real
     * classes on the system.
     */
    private HashMap<String, UseDescriptor> uses = new HashMap<String, UseDescriptor>();
    /** This hash stores the already validated uses that reference actual
     * classes on the system.
     */
    private HashMap<String, ClassDescriptor> validUses = new HashMap<String, ClassDescriptor>();
    private HashMap<String, ClassDescriptor> validFullyQualifiedUses = new HashMap<String, ClassDescriptor>();
    private HashMap<String, ClassDescriptor> parents = new HashMap<String, ClassDescriptor>();
    private ArrayList<ClassDescriptor> flatListOfParents = new ArrayList<ClassDescriptor>();
    private ArrayList<GenericDescriptor> templateVariables = new ArrayList<GenericDescriptor>();
    private HashMap<String, MethodDescriptor> resolvedTypeVirtualMethods;
    /**
     * This hash stores the methods inherited by this class.
     */
    private HashMap<String, MethodDescriptor> virtualMethods;
    /**
     * This hash stores the blueprints contained in this class.
     */
    private HashMap<String, BlueprintDescriptor> blueprints;

    /**
     * This hash stores the blueprints contained in this class.
     */
    private HashMap<String, SystemActionDescriptor> systemActions;


    /** The file this class is defined in. */
    private FileDescriptor file;
    private ClassLocation location;
    private boolean implementable = true;
    private HashMap<String, ArrayList<GenericDescriptor>> unresolvedParentNameSet = new HashMap<String, ArrayList<GenericDescriptor>>();

    /**
     * This list stores a list of names of variables that are initialized in
     * this class's constructor. The point of this list is to mark
     * any class variables that have been initialized as so.
     */
    private ArrayList<String> constructorInitList = new ArrayList<String>();

    public ClassDescriptor() {
        virtualMethods = new HashMap<String, MethodDescriptor>();
        methods = new HashMap<String, MethodDescriptor>();
        variables = new HashMap<String, VariableDescriptor>();
        location = new ClassLocation();
        resolvedTypeVirtualMethods = new HashMap<String, MethodDescriptor>();
        blueprints = new HashMap<String, BlueprintDescriptor>();
        systemActions = new HashMap<String, SystemActionDescriptor>();
        
        initUses();
    }

    /**
     * Add an unresolved parent to this class. The qualifiedNameDescriptor, qn,
     * is passed to the class descriptor and added to the unresolved parent set.
     * @param qn
     * @param templates associated with the current class that will be inserted into the vtable
     */
    public void addUnresolvedParentClassNames(QualifiedNameDescriptor qn, ArrayList<GenericDescriptor> templates) {

        unresolvedParentNameSet.put(qn.getStaticKey(), templates);
    }

    /**
     *
     * @return the unresolved parent keys for this class descriptor.
     */
    public Iterator<String> getUnresolvedParentKeys() {
        return unresolvedParentNameSet.keySet().iterator();
    }

    /**
     *
     * @param key name of the parent
     * @return The list of template values the parent will be using
     */
    public ArrayList<GenericDescriptor> getUnresolvedParentTemplate(String key){
        return unresolvedParentNameSet.get(key);
    }

    /** This method adds a method to the class. It is assumed that all
     * parameters have been appropriately added to MethodDescriptor object
     * before passing it to the class. This is done so that the class
     * can check for overloading.
     * @param descriptor
     * @return
     */
    public CompilerError add(MethodDescriptor descriptor) {
        //this takes into account the potential for overloaded methods.
        CompilerError error = isDefined(descriptor, methods);
        if (error != null) {
            return error;
        } else {
            error = isDefined(descriptor, blueprints);
            if (error != null) {
                return error;
            } else if(descriptor.isConstructor()){//if it is a constructor
                //if it is a constructor
                error = hasConstructor(descriptor);
                if(error == null){
                    descriptor.setParent(this);
                    constructor = descriptor;
                    return null;
                }
                return error;
            }else {
                descriptor.setParent(this);
                methods.put(descriptor.getStaticKey(), descriptor);
                return null;
            }
        }
    }

    public CompilerError add(ClassDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a class to a class.");
    }

    public CompilerError add(ParameterDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a parameter to a class.");
    }

    public CompilerError add(VariableDescriptor descriptor) {
        CompilerError error = isDefined(descriptor, variables);
        if (error != null) {
            return error;
        }
        variables.put(descriptor.getStaticKey(), descriptor);
        return null;
    }

    public CompilerError add(BlueprintDescriptor descriptor) {

        //make sure the blueprint is not set to private
        if (descriptor.isPrivate()) {
            CompilerError error = new CompilerError(getLineBegin(),
                    "The private modifier cannot be used here.", ErrorType.INHERITANCE_MODIFIER_DOWNGRADE);
            CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
            errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
            return error;
        }

        //check the blueprint hasn't already been defined.
        CompilerError error = isDefined(descriptor, blueprints);
        if (error != null) {
            return error;
        } else {
            error = isDefined(descriptor, methods);
            if (error != null) {
                return error;
            } else {
                descriptor.setParent(this);
                blueprints.put(descriptor.getStaticKey(), descriptor);
                return null;
            }
        }
    }

    public CompilerError add(SystemActionDescriptor descriptor) {
        //check the system action hasn't already been defined.
        CompilerError error = isDefined(descriptor, systemActions);
        if (error != null) {
            return error;
        } else {
            error = isDefined(descriptor, methods);
            if (error != null) {
                return error;
            } else {
                descriptor.setParent(this);
                this.systemActions.put(descriptor.getStaticKey(), descriptor);
                return null;
            }
        }
    }


    public void addTemplateVariables(GenericDescriptor gd){
        templateVariables.add(gd);
    }

    public Iterator<GenericDescriptor> getTemplateVariables(){
        return templateVariables.iterator();
    }

    public GenericDescriptor getTemplateVariable(String name){
        for(int i = 0; i < templateVariables.size(); i++){
            GenericDescriptor element = templateVariables.get(i);
            if(element.getName().compareTo(name) == 0){
                return element;
            }
        }
        return null;
    }

    public int getNumberOfTemplateVariables(){
        return templateVariables.size();
    }

    /**
     * Adds a parent class to this class.
     * 
     * @param descriptor
     * @return
     */
    public boolean addParent(ClassDescriptor descriptor) {
        if (descriptor != null) {
            if (this.getStaticKey().equals(descriptor.getStaticKey())) {
                CompilerError error = new CompilerError(getLineBegin(),
                        "Class " + this.getName() + " inherits from itself. Circular inheritance is not allowed.", ErrorType.INHERITANCE_CIRCULAR);
                CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                errorManager.addError(error);
                return false;
            } else {
                parents.put(descriptor.getStaticKey(), descriptor);
                checkForImplementations(descriptor);
                addVirtualMethods(descriptor);
                return true;
            }
        } else {
            CompilerError error = new CompilerError(getLineBegin(),
                    "Class " + this.getName() + " cannot have a parent of type null.", ErrorType.INHERITANCE_NULL);
            CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
            errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
            errorManager.addError(error);

            return false;
        }
    }

    /**
     * Add a class descriptor for a class that is an ancestor.
     *
     * @param descriptor
     */
    public void addAncestorToInit(ClassDescriptor descriptor) {
        if (descriptor != null) {
            if (!flatListOfParents.contains(descriptor)) {
                flatListOfParents.add(descriptor);
            }

        }
    }

    /**
     * Check to make sure all the parents blue prints have been implemented.
     * If they have not, a compiler error is created for each.
     * @param descriptor
     */
    private void checkForImplementations(ClassDescriptor parent) {

        if (parent != null) {
            Iterator<BlueprintDescriptor> parentBlueprints = parent.getBlueprints();
            while (parentBlueprints.hasNext()) {
                parent.setImplementable(false);
                BlueprintDescriptor parentBlueprint = parentBlueprints.next();
                MethodDescriptor implMethod = getMethod(parentBlueprint.getStaticKey());
                SystemActionDescriptor implSystemMethod = getSystemAction(parentBlueprint.getStaticKey()); 

                //check if the access modifier is erroniously set to private
                if (implMethod!=null && implMethod.getAccessModifier().equals(AccessModifierEnum.PRIVATE)) {
                    CompilerError error = new CompilerError(implMethod.getLineBegin(),
                            "The blueprint method " + implMethod.getStaticKey() + " cannot be private.", ErrorType.INHERITANCE_MODIFIER_DOWNGRADE);
                    CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                    errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                    errorManager.addError(error);

                } else {
                    //check for inherited blueprints that have not been implmented
                    if (!parentBlueprint.compare(implMethod)) {
                        if(!parentBlueprint.compare(implSystemMethod)){
                            this.setImplementable(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a single virtual method to the system.
     * 
     * @param parent
     * @param method
     */
    private void addVirtualMethod(ClassDescriptor parent, MethodDescriptor method) {
        String mName = method.getStaticKey();
        if (virtualMethods.containsKey(mName)) {
            //check method return type incompatability
            MethodDescriptor curMethod = virtualMethods.get(mName);
            if (!hasMethodIncompatability(method, curMethod)) {
                //check for ambiguous inheritance
                if (this.getMethod(mName) == null && !curMethod.getParent().getScopeString().equals(method.getParent().getScopeString())) {
                    CompilerError error = new CompilerError(getLineBegin(),
                            "Ambiguous inheritance of method " + mName + " from the classes "
                            + method.getParent().getScopeString() + " and "
                            + curMethod.getParent().getScopeString()
                            + ". You must implement this method in the class "
                            + getName(), ErrorType.INHERITANCE_AMBIGUOUS);
                    CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                    errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                    errorManager.addError(error);

                }
            }
        }

        if(method.getAccessModifier().equals(AccessModifierEnum.PUBLIC)){//if the method is public
            //change the static key to the resolved static key
            MethodDescriptor resolvedMethod = getResolvedTemplatedMethod(parent, method);
            if(resolvedMethod != null){
                mName = resolvedMethod.getStaticKey();
            }else{
                mName= method.getStaticKey();
            }

            MethodDescriptor curMethod = this.getMethod(mName);
            if(curMethod != null){//if we found a parent method in the child methods
                if(resolvedMethod != null){
                    if(hasMethodOverridingIncompatability(resolvedMethod, curMethod)){
                        virtualMethods.put(mName, method);
                    }
                    resolvedTypeVirtualMethods.put(mName, resolvedMethod);
                }else if(hasMethodOverridingIncompatability(method, curMethod)){
                        virtualMethods.put(mName, method);
                }
            }else{//if we did not find a parent method in the child methods
                virtualMethods.put(mName, method);
                if(resolvedMethod != null)
                    resolvedTypeVirtualMethods.put(mName, resolvedMethod);
            }

        }
    }

    /**
     * Populates the classes virtual methods.
     * @param parent
     * @return
     */
    private void addVirtualMethods(ClassDescriptor parent) {
        Iterator<MethodDescriptor> methodIterator = parent.getMethods();
        while (methodIterator.hasNext()) {
             MethodDescriptor method = methodIterator.next();
             addVirtualMethod(parent, method);
        }

        Iterator<SystemActionDescriptor> systemIterator = parent.getSystemActions();
        while (systemIterator.hasNext()) {
             MethodDescriptor method = systemIterator.next();
             addVirtualMethod(parent, method);
        }
    }

    /**
     * Passes virtual methods down the inheritance chain
     */
    protected void addParentsVirtualMethods() {
        Iterator<ClassDescriptor> parentz = getImmediateParents();
        while (parentz.hasNext()) {
            ClassDescriptor parent = parentz.next();
            Iterator<MethodDescriptor> vMethods = parent.getVirtualMethods();
            while (vMethods.hasNext()) {
                MethodDescriptor method = vMethods.next();


                if (virtualMethods.containsKey(method.getStaticKey())) {
                    MethodDescriptor typedMethod = resolvedTypeVirtualMethods.get(method.getStaticKey());
                    typedMethod = getResolvedTemplatedMethod(parent, typedMethod);
                    //check method return type incompatability
                    MethodDescriptor curMethod = virtualMethods.get(method.getStaticKey());
                    if (!hasMethodIncompatability(method, curMethod)) {

                        //check for ambiguous inheritance of methods
                        if (parent.getMethod(method.getStaticKey()) == null && this.getMethod(method.getStaticKey()) == null && !curMethod.getParent().getScopeString().equals(method.getParent().getScopeString())) {
                            CompilerError error = new CompilerError(getLineBegin(),
                                    "Ambiguous inheritance of method " + method.getStaticKey() + " from the classes "
                                    + method.getParent().getScopeString() + " and "
                                    + curMethod.getParent().getScopeString()
                                    + ". You must implement this method in the class "
                                    + getName(), ErrorType.INHERITANCE_AMBIGUOUS);
                            CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                            errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                            errorManager.addError(error);
                        }
                    }
                } else {


                    if(method.getAccessModifier().equals(AccessModifierEnum.PUBLIC)){//if the method is public
                        //change the static key to the resolved static key
                        String mName = "";
                        MethodDescriptor resolvedMethod = getResolvedTemplatedMethod(parent, method);
                        if(resolvedMethod != null){
                            mName = resolvedMethod.getStaticKey();
                        }else{
                            mName= method.getStaticKey();
                        }

                        //get parent and current method (if they exist)
                        MethodDescriptor thisMethod = this.getMethod(mName);
                        MethodDescriptor parentMethod = parent.getMethod(mName);

                        if (parentMethod == null && thisMethod == null) { //if neither exists add the method to the vtable
                           if(resolvedMethod != null){
                                virtualMethods.put(method.getStaticKey(), method);
                                resolvedTypeVirtualMethods.put(mName, resolvedMethod);
                            }
                        } else if (parentMethod != null) { //check method return type incompatability
                            if(resolvedMethod != null){
                                hasMethodIncompatability(resolvedMethod, parentMethod);
                            }else{
                                hasMethodIncompatability(method, parentMethod);
                            }

                        } else if (thisMethod != null) { //check method return type incompatability
                            if(resolvedMethod != null){
                                if (hasMethodOverridingIncompatability(resolvedMethod, thisMethod)) {
                                    virtualMethods.put(mName, method);
                                }
                                resolvedTypeVirtualMethods.put(mName, resolvedMethod);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is used by the parser to inform the class descriptor
     * that a particular class variable is initialized outside of
     * its variable list initialization block, but inside of
     * the class's constructor.
     *
     * @param name
     */
    public void addToConstructorInitializationList(String name) {
        this.constructorInitList.add(name);
    }

    public void checkClassVariableInitialization() {
        Iterator<String> inits = constructorInitList.iterator();
        while(inits.hasNext()) {
            String var = inits.next();
            VariableDescriptor variable = this.getVariable(var);
            if(variable != null) {
                variable.setIsInitializedClassVariable(true);
            }
        }
        //This list is no longer necessary, so clear it out.
        constructorInitList.clear();
    }

    private boolean hasMethodIncompatability(MethodDescriptor method1, MethodDescriptor method2) {
        if(method1 != null && method2 != null){
            if (!method1.getReturnType().getStaticKey().equals(method2.getReturnType().getStaticKey())) {
                CompilerError error = new CompilerError(getLineBegin(),
                        "Invalid return types for inherited methods."
                        + "Method " + method1.getStaticKey() + "(return type " + method1.getReturnType().getStaticKey()
                        + ") in class " + method1.getParent().getScopeString() + " and method " + method2.getStaticKey()
                        + "(return type " + method2.getReturnType().getStaticKey()
                        + ") in class " + method2.getParent().getScopeString() + " are incompatible.", ErrorType.INHERITANCE_MISSMATCHED_RETURN);
                CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                errorManager.addError(error);
                return true;
            }
        }
        return false;
    }

    private boolean hasMethodOverridingIncompatability(MethodDescriptor originalMethod, MethodDescriptor overridingMethod) {
        if(overridingMethod != null && originalMethod != null){
            if (!originalMethod.getReturnType().getStaticKey().equals(overridingMethod.getReturnType().getStaticKey())) {
                CompilerError error = new CompilerError(overridingMethod.getLineBegin(),
                        "Invalid return type for overriding method. "
                        + "Method " + originalMethod.getStaticKey() + "(return type " + originalMethod.getReturnType().getStaticKey()
                        + ") in class " + originalMethod.getParent().getScopeString() + " and method "
                        + overridingMethod.getStaticKey() + "(return type " + overridingMethod.getReturnType().getStaticKey()
                        + ") in class " + overridingMethod.getParent().getScopeString() + " are incompatible.  ", ErrorType.INHERITANCE_MISSMATCHED_RETURN);
                CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                errorManager.addError(error);
                return true;
            } else if (originalMethod.getAccessModifier().equals(AccessModifierEnum.PUBLIC) && overridingMethod.getAccessModifier().equals(AccessModifierEnum.PRIVATE)) {
                CompilerError error = new CompilerError(overridingMethod.getLineBegin(),
                        "The method " + originalMethod.getStaticKey() + " is public, but the overriding method " 
                        + overridingMethod.getStaticKey() + " is private. The overriding method must be public.", ErrorType.INHERITANCE_MODIFIER_DOWNGRADE);
                CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                errorManager.addError(error);
                return true;
            } else if (!originalMethod.getAccessModifier().equals(overridingMethod.getAccessModifier())) {
                return true;
            }
        }
        return false;
    }

    private MethodDescriptor getResolvedTemplatedMethod(ClassDescriptor clazz, MethodDescriptor method){
        MethodDescriptor newMethod = new MethodDescriptor();

        //if clazz is a parent of the current class
        if(this.getParent(clazz.getStaticKey()) != null){

            ArrayList<GenericDescriptor> unresolvedParentTemplate = getUnresolvedParentTemplate(clazz.getName());
            ClassDescriptor parent = getParent(clazz.getStaticKey());

            if(unresolvedParentTemplate != null && parent != null){
                Iterator<GenericDescriptor> thisTemplate = unresolvedParentTemplate.iterator();
                Iterator<GenericDescriptor> parentTemplate = parent.getTemplateVariables();

                while(parentTemplate.hasNext() && thisTemplate.hasNext()){

                    GenericDescriptor parentTemplateValue = parentTemplate.next();
                    GenericDescriptor thisTemplateValue = thisTemplate.next();

                    TypeDescriptor parentType = parentTemplateValue.getType();
                    TypeDescriptor thisType = thisTemplateValue.getType();

                    if(parentType != null && thisType != null){

                        String parentTemplateName = parentType.getTemplateName();
                        String thisTemplateName = thisType.getTemplateName();

                        //get the resolved virtual method, if one has been resolved.
                        MethodDescriptor resolvedMethod = clazz.getResolvedTypeVirtualMethod(method.getStaticKey());
                        String resolvedTypeName = null;

                        //generate virtual method with inherited, templated types
                        newMethod.setName(method.getName());
                        newMethod.setAccessModifier(method.getAccessModifier());
                        newMethod.setParent(method.getParent());

                        //parameter types
                        Parameters parameters = method.getParameters();
                        Iterator<ParameterDescriptor> iterator = parameters.iterator();

                        //resolved parameter types
                        Parameters resolvedParameters  = null;
                        Iterator<ParameterDescriptor> resolvedIterator = null;
                        if(resolvedMethod != null){
                            resolvedParameters = resolvedMethod.getParameters();
                            resolvedIterator = resolvedParameters.iterator();
                        }

                        //while there are still parameters in the iterator validate the types.
                        while(iterator.hasNext() ){

                            //get the parameter type
                            ParameterDescriptor next = iterator.next();
                            ParameterDescriptor newParam = new ParameterDescriptor();
                            TypeDescriptor type = new TypeDescriptor(next.getType());

                            //get the resolved parameter type
                            ParameterDescriptor nextResolved = null;
                            if(resolvedIterator != null && resolvedIterator.hasNext()){
                                nextResolved = resolvedIterator.next();
                                TypeDescriptor resolvedType = nextResolved.getType();
                                resolvedTypeName = resolvedType.getTemplateName();
                            }

                            newParam.setName(next.getName());
                            newParam.setType(type);
                            String templateName = type.getTemplateName();

                            //if the parent type and current type match or
                            //if the parent type and current type do not match,
                            //check that the parent and the "this" class has a
                            //modified version that match. Then change type to "this".
                            if(thisTemplateName == null){
                                if(templateName == null){//no resolving necessary
                                    newParam.setType(type);
                                }else{
                                    if(parentTemplateName.compareTo(templateName) == 0){//resolve if the type names match
                                        newParam.setType(resolveTemplateType(thisType));
                                    }
                                }
                            }else{
                                if((templateName != null && parentTemplateName.compareTo(templateName) == 0)
                                        || (resolvedTypeName != null && parentTemplateName.compareTo(resolvedTypeName)==0)){
                                    type.setTemplateName(thisTemplateName);
                                    newParam.setType(type);
                                }
                            }

                            newMethod.add(newParam);
                        }

                        //return types
                        TypeDescriptor returnType = method.getReturnType();
                        TypeDescriptor type = null;
                        String returnTemplateName = "";
                        if(returnType != null){
                            returnTemplateName = returnType.getTemplateName();
                            type = new TypeDescriptor(returnType);
                        }
                        
                        if(resolvedMethod != null){
                            
                            TypeDescriptor resolvedType = resolvedMethod.getReturnType();
                            
                            if(resolvedType != null){
                                
                                resolvedTypeName = resolvedType.getTemplateName();
                            }
                        }

                        //if the parent type and current type match or
                        //if the parent type and current type do not match,
                        //check that the parent and the "this" class has a
                        //modified version that match. Then change type to "this".
                        if(thisTemplateName == null){
                                if(returnTemplateName == null){//no resolving necessary
                                    newMethod.setReturnType(type);
                                }else{
                                    if(parentTemplateName.compareTo(returnTemplateName) == 0){//resolve if the type names match
                                        newMethod.setReturnType(resolveTemplateType(thisType));
                                    }
                                }
                        }else{
                            if((returnTemplateName != null && parentTemplateName.compareTo(returnTemplateName)==0)
                                    || (resolvedTypeName != null && parentTemplateName.compareTo(resolvedTypeName)==0)){
                                type.setTemplateName(thisTemplateName);
                                newMethod.setReturnType(type);
                            }

                        }
                    }
                }
            }else if (parent != null){
                return method;
            }
        }
        return newMethod;
    }

    /**
     * Returns s virtual method of this class.
     * @return
     */
    public MethodDescriptor getVirtualMethod(String key) {
        return virtualMethods.get(key);
    }

    /**
     * Returns an iterator of all virtual methods listed for this class.
     * @return
     */
    public Iterator<MethodDescriptor> getVirtualMethods() {
        return virtualMethods.values().iterator();
    }

    /**
     * Searches for a parent of this class by the key. This will search
     * the entire parent hierarchy for the parent.
     *
     * @param key
     * @return
     */
    public ClassDescriptor getParent(String key) {
        ClassDescriptor parent = parents.get(key);
        Iterator<String> keyIt = parents.keySet().iterator();
        while(keyIt.hasNext() && parent == null){
            parent = parents.get(keyIt.next()).getParent(key);
        }
        return parent;
        
    }
    
    /**
     * This method attempts to resolve an unresolved parent name and, if uniquely
     * found, returns the full static key for that parent. For example, if an object
     * has a parent of class Object, and this is passed as a key, this method
     * would return Libraries.Language.Object. This method returns null
     * if a parent of the unresolved name is either not found or found to be
     * non-unique (e.g., more than one parent exists with the name object). If a fully
     * resolved name is sent to this method, it will return the same name.
     * 
     * @param key an unresolved parent name. For example, Object instead of Libraries.Language.Object.
     * @return 
     */
    public String resolveParentName(String key) {
        String result = null;
        ClassDescriptor parent = null;
        int parentsFound = 0;
        Iterator<ClassDescriptor> parents = this.getFlattenedListOfParents();
        while(parents.hasNext()){
            parent = parents.next();
            if(parent != null) {
                String name = parent.getName();
                if(name.equals(key) || key.equals(parent.getStaticKey())) {
                    result = parent.getStaticKey();
                    parentsFound++;
                }
            }
        }
        if(parentsFound == 1) {
            return result;
        }
        else {
            return null;
        }
    }

    /**
     * Returns an iterator of all immediate parents listed for this class. For
     * example, if you have a class Animal is a Mammal and Dog is a Animal, Object 
     * the list of parents for Dog will be Animal and Object.
     * 
     * @return
     */
    public Iterator<ClassDescriptor> getImmediateParents() {
        return parents.values().iterator();
    }

    /**
     * Returns true if a class descriptor has parents and false
     * if the class descriptor does not have parents
     * @return
     */
    public boolean hasParents() {
        if (parents.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns a method of a given name, given actual types the user is
     * attempting to pass to a method.
     * 
     * @param name
     * @param types
     * @return
     */
    public MethodDescriptor getResolvedMethod(String name, Vector<TypeDescriptor> types, VariableParameterCommonDescriptor callee, LineInformation callInformation) {
        MethodDescriptor method = null;

        method = checkMethodIterator(name, types, methods.values(), callee, callInformation);

        if (method == null) { //do the parents have a method?
            method = checkMethodIterator(name, types, virtualMethods.values(), callee, callInformation);
        }

        if (method != null) {
            if (method.getAccessModifier() != null) {//temporary fix: this should never be null
                if (callee != null && method.getAccessModifier().equals(AccessModifierEnum.PRIVATE)
                        && method.getParent().getScopeString().compareTo(callee.getType().getStaticKey()) == 0) {
                    method = null;
                }
            }
        }

        return method;
    }

    /**
     * Returns a method of a given name, given actual types the user is
     * attempting to pass to a method.
     * 
     * @param name
     * @param types
     * @param callee
     * @return
     */
    public SystemActionDescriptor getResolvedSystemMethod(String name, Vector<TypeDescriptor> types, VariableParameterCommonDescriptor callee, LineInformation callInformation) {
        MethodDescriptor method = null;

        Vector<MethodDescriptor> methodVector = new Vector<MethodDescriptor>();

        methodVector.addAll(systemActions.values());

        method = checkMethodIterator(name, types, methodVector, callee, callInformation);

        if (method == null) { //do the parents have a method?
            method = checkMethodIterator(name, types, virtualMethods.values(), callee, callInformation);
        }

        if (method != null) {
            if (method.getAccessModifier() != null) {//temporary fix: this should never be null
                if (callee != null && method.getAccessModifier().equals(AccessModifierEnum.PRIVATE)
                        && method.getParent().getScopeString().compareTo(callee.getType().getStaticKey()) == 0) {
                    method = null;
                }
            }
        }

        if (method instanceof SystemActionDescriptor) {
            return (SystemActionDescriptor) method;
        } else {
            return null;
        }
    }

    /**
     * This method checks to see if an action's signature matches either a
     * called method's signature.
     * 
     * @param name The name of the action
     * @param types The actual argument types when attempting to call a method.
     *      These types will not show up as templated, because they are the actual arguments
     *      , not the specified types in a declared method.
     *
     * @param methods The MethodDescriptors to search through
     * @param vd The variable on which this particular method was called. This variable may be null.
     * @param clazz 
     * @return
     */
    private MethodDescriptor checkMethodIterator(String name, Vector<TypeDescriptor> types,
            Collection<MethodDescriptor> methods, VariableParameterCommonDescriptor vd, LineInformation callLocation) {
        ArrayList<Integer> matchScores = new ArrayList<Integer>();
        ArrayList<MethodDescriptor> matches = new ArrayList<MethodDescriptor>();

        Iterator<MethodDescriptor> mit = methods.iterator();
        MethodDescriptor method = null;
        while (mit.hasNext()) {
            MethodDescriptor m = mit.next();
            if (m.getName().compareTo(name) == 0) { //the name matches. Do the parameters match?
                if (types.isEmpty() && m.getParameters().size() == 0) {
                    return m; //the simple case
                } else { //otherwise test all parameters to see if they work
                    boolean match = false;
                    int methodScore = 0;

                    TypeChecker checker = this.getVirtualMachine().getTypeChecker();
                    if (types.size() == m.getParameters().size()) {
                        boolean atLeastOneFailed = false;

                        for (int i = 0; i < types.size(); i++) {
                            TypeDescriptor specified = new TypeDescriptor(m.getParameters().get(i).getType());
                            TypeDescriptor actual = types.get(i);

                            //if we are dealing with templated parameters
                            //set the template name of the specified param
                            //then convert the actual param if it is a primitive.
                            if (!templateVariables.isEmpty() && specified.getTemplateName() != null) {
                                Iterator<GenericDescriptor> clazzTemplates = this.getTemplateVariables();


                                if (vd != null) {
                                    Iterator<GenericDescriptor> variableTemplates = vd.getTemplateTypes();

                                    TypeDescriptor resolvedTypeVirtualMethod = null;
                                    if (!((ClassDescriptor) m.getParent()).equals(this)) {
                                        resolvedTypeVirtualMethod = this.getResolvedTypeVirtualMethod(m.getStaticKey()).getParameters().get(i).getType();
                                    }

                                    while (clazzTemplates.hasNext() && variableTemplates.hasNext()) {
                                        //change the specified type from Object to the actual specified value
                                        GenericDescriptor clazzTemplate = clazzTemplates.next();
                                        GenericDescriptor variableTemplate = variableTemplates.next();
                                        if (clazzTemplate != null && clazzTemplate.getName() != null && specified.getTemplateName() != null) {
                                            if ((clazzTemplate.getName().compareTo(specified.getTemplateName()) == 0)
                                                    || (resolvedTypeVirtualMethod != null && clazzTemplate.getName().compareTo(resolvedTypeVirtualMethod.getTemplateName()) == 0)) {
                                                specified = new TypeDescriptor(variableTemplate.getType());
                                            } else if (resolvedTypeVirtualMethod != null) {
                                                specified = new TypeDescriptor(resolvedTypeVirtualMethod);
                                            }
                                        }
                                    }
                                }
                            } else if (templateVariables.isEmpty() && specified.getTemplateName() != null) {
                                //determine the specified type if the type is templated and inherited.
                                String templateName = specified.getTemplateName();
                                ClassDescriptor parent = (ClassDescriptor) m.getParent();
                                String clazzName = parent.getName();
                                ArrayList<GenericDescriptor> templateTypes = this.unresolvedParentNameSet.get(clazzName);
                                Iterator<GenericDescriptor> actualTypesIterator = templateTypes.iterator();
                                Iterator<GenericDescriptor> typesNameIterator = parent.templateVariables.iterator();

                                //while actual and parent templates have next element
                                while (actualTypesIterator.hasNext() && typesNameIterator.hasNext()) {
                                    GenericDescriptor actualType = actualTypesIterator.next();
                                    GenericDescriptor typeName = typesNameIterator.next();
                                    String type = typeName.getName();

                                    //if the template names correspond set the type
                                    if (type != null && type.compareTo(templateName) == 0) {
                                        specified = actualType.getType();

                                    }
                                }

                            }


                            TypeCheckerResult result = checker.check(specified, actual, OperationEnum.IMPLICIT_CAST, false);
                            if (result.getResult() == null) {
                                specified.convertToPrimitive();
                                if (actual.getName().compareTo(specified.getName()) == 0) {
                                    result = checker.checkAutobox(actual);
                                    if (result.getResult() == null) {
                                        CompilerError error = new CompilerError(getLineBegin(), result.getErrorMessage(), result.getErrorType());
                                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                                        errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                                        errorManager.addError(error);
                                        atLeastOneFailed = true;
                                    } else {//add the results score to the method score
                                        methodScore += result.getConversionScore().getScore();
                                    }

                                } else {
                                    if (result.getConversionResult().compareTo(TypeConversionResults.None) == 0) {
                                        CompilerError error = new CompilerError(getLineBegin(), result.getErrorMessage(), result.getErrorType());
                                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                                        errorManager.setErrorKey(this.getFile().getFile().getAbsolutePath());
                                        errorManager.addError(error);
                                    }
                                    atLeastOneFailed = true;
                                }
                            } else {//add the results score to the method score
                                methodScore += result.getConversionScore().getScore();
                            }
                        }
                        if (!atLeastOneFailed && methodScore == TypeConversionEnum.EXACT_MATCH.getScore()) {//exact match to a method was made
                            return m;
                        }
                        if (!atLeastOneFailed) {
                            match = true;
                        }
                    }
                    if (match) {//a match was found
                        if (matchScores.isEmpty()) {//add the method and score
                            matchScores.add(methodScore);
                            matches.add(m);
                        } else {//add the method and score conditionally
                            int lowScore = matchScores.get(matchScores.size() - 1);
                            if (methodScore <= lowScore) {//if we have a new low or equal match score
                                if (methodScore < lowScore) {//if the score is not equal clear the array lists
                                    matchScores.clear();
                                    matches.clear();
                                }

                                //add the new low score
                                matchScores.add(methodScore);
                                matches.add(m);
                            }
                        }
                    }
                }

            }
        }
        if (matches.isEmpty()) {//return null
            return method;
        } else {//return the match or a compiler error is added

            if (matchScores.size() == 1) {//if there is only one match return it.
                return matches.get(0);
            } else {//ambiguous method selection
                String message = "Ambiguous method call. Which method should be called? ";

                //build the error string that lists the ambiguous methods.
                Iterator<MethodDescriptor> matchIt = matches.iterator();
                while (matchIt.hasNext()) {
                    MethodDescriptor next = matchIt.next();
                    message += " " + next.getStaticKey();
                }

                //add the compiler error
                CompilerError error = new CompilerError(callLocation.getStartLine(), message, ErrorType.METHOD_CALL_AMBIGUOUS);
                error.setFile(callLocation.getFile());
                CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                errorManager.setErrorKey(callLocation.getFile());
                errorManager.addError(error);

                //return null
                return method;
            }
        }
    }

    /**
     * Removes and reads all methods into this ClassDescriptor, in order
     * to resolve any type name problems in the function.
     */
    public void resolveMethods() {
        ArrayList<MethodDescriptor> list = new ArrayList<MethodDescriptor>();

        Iterator<MethodDescriptor> mit = this.getMethods();
        while (mit.hasNext()) {
            MethodDescriptor m = mit.next();
            list.add(m);
        }

        mit = list.iterator();
        this.methods.clear();
        while (mit.hasNext()) {
            MethodDescriptor m = mit.next();

            //resolve all of the parameters
            Parameters params = m.getParameters();
            for (int i = 0; i < params.size(); i++) {
                ParameterDescriptor param = params.get(i);
                resolve(param);
            }
            TypeDescriptor returnType = m.getReturnType();
            if (!returnType.isPrimitiveType()) {
                ClassDescriptor clazz = this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                        this.getStaticKey(), returnType.getName());

                if (clazz != null) {
                    if(!returnType.hasSubTypes()){
                        m.setReturnType(TypeDescriptor.getClassType(clazz));
                    }else{
                        returnType.setName(clazz.getStaticKey());
                        resolveSubTypes(returnType.getSubTypes());
                    }
                }
            }

            methods.put(m.getStaticKey(), m);
        }

        VariableDescriptor[] vit = variables.values().toArray(new VariableDescriptor[0]);
        for (int i = 0; i < vit.length; i++) {
            VariableDescriptor var = vit[i];
            resolve(var);
        }
    }

    /**
     * Removes and reads all blueprints into this ClassDescriptor, in order
     * to resolve any type name problems in the function.
     */
    public void resolveBlueprints() {
        ArrayList<BlueprintDescriptor> list = new ArrayList<BlueprintDescriptor>();

        Iterator<BlueprintDescriptor> blueprintIt = this.getBlueprints();
        while (blueprintIt.hasNext()) {
            BlueprintDescriptor m = blueprintIt.next();
            list.add(m);
        }

        blueprintIt = list.iterator();
        this.blueprints.clear();
        while (blueprintIt.hasNext()) {
            BlueprintDescriptor m = blueprintIt.next();

            //resolve all of the parameters
            Parameters params = m.getParameters();
            for (int i = 0; i < params.size(); i++) {
                ParameterDescriptor param = params.get(i);
                resolve(param);
            }
            TypeDescriptor returnType = m.getReturnType();
            if (!returnType.isPrimitiveType()) {
                ClassDescriptor clazz = this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                        this.getStaticKey(), returnType.getName());

                if (clazz != null) {
                    if(!returnType.hasSubTypes()){
                        m.setReturnType(TypeDescriptor.getClassType(clazz));
                    }else{
                        returnType.setName(clazz.getStaticKey());
                        resolveSubTypes(returnType.getSubTypes());
                    }
                }
            }

            blueprints.put(m.getStaticKey(), m);
        }
    }

    /**
     * Removes and reads all blueprints into this ClassDescriptor, in order
     * to resolve any type name problems in the function.
     */
    public void resolveSystemActions() {
        ArrayList<SystemActionDescriptor> list = new ArrayList<SystemActionDescriptor>();

        Iterator<SystemActionDescriptor> actionIt = this.getSystemActions();
        while (actionIt.hasNext()) {
            SystemActionDescriptor m = actionIt.next();
            list.add(m);
        }

        actionIt = list.iterator();
        this.systemActions.clear();
        while (actionIt.hasNext()) {
            SystemActionDescriptor m = actionIt.next();

            //resolve all of the parameters
            Parameters params = m.getParameters();
            for (int i = 0; i < params.size(); i++) {
                ParameterDescriptor param = params.get(i);
                resolve(param);
            }
            TypeDescriptor returnType = m.getReturnType();
            if (!returnType.isPrimitiveType()) {
                ClassDescriptor clazz = this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                        this.getStaticKey(), returnType.getName());

                if (clazz != null) {
                    if(!returnType.hasSubTypes()){
                        m.setReturnType(TypeDescriptor.getClassType(clazz));
                    }else{
                        returnType.setName(clazz.getStaticKey());
                        resolveSubTypes(returnType.getSubTypes());
                    }
                }
            }

            systemActions.put(m.getStaticKey(), m);
        }
    }

    public void resolveSubTypes(Iterator<GenericDescriptor> subTypes){
        while(subTypes.hasNext()){
            GenericDescriptor type = subTypes.next();

            ClassDescriptor clazz =
                    this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                    this.getStaticKey(), type.getName());
            if(clazz != null){
                type.setName(clazz.getStaticKey());
                if(type.getType() != null){
                    type.getType().setName(clazz.getStaticKey());
                }
            }

            if(type.getType().hasSubTypes()){
                resolveSubTypes(type.getType().getSubTypes());
            }

        }
    }

    private TypeDescriptor resolveTemplateType(TypeDescriptor templateType){
        if(!templateType.isPrimitiveType()){
            ClassDescriptor clazz =
                    this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                    this.getStaticKey(), templateType.getName());
            if (clazz != null) {
                if(templateType.hasSubTypes()){
                    templateType.setName(clazz.getStaticKey());
                    resolveSubTypes(templateType.getSubTypes());
                }else{
                    return TypeDescriptor.getClassType(clazz);
                }
            }
        }
        return templateType;
    }

    private void resolve(VariableParameterCommonDescriptor vpd) {
        if (!vpd.getType().isPrimitiveType()) {
            ClassDescriptor clazz =
                    this.getVirtualMachine().getSymbolTable().findClassDescriptorFromClass(
                    this.getStaticKey(), vpd.getType().getName());
            if (clazz != null) {
                if(vpd.getType().hasSubTypes()){
                    vpd.getType().setName(clazz.getStaticKey());
                    resolveSubTypes(vpd.getType().getSubTypes());
                }else{
                    vpd.setType(TypeDescriptor.getClassType(clazz));
                }
            }
        }
    }

    /**
     * This method adds a "validated" use into the class. In other words,
     * this method takes a string and a class descriptor and assumes
     * that the string represents the valid name of a class and the class
     * descriptor represents a real class descriptor that actually exists
     * and has been parsed somewhere on the system. The user of this method
     * should take caution as it should only be called from within the
     * symbol table.
     * 
     * @param st
     * @param cd
     * @return
     */
    public CompilerError addValidatedUse(ClassDescriptor cd, UseDescriptor use) {
        ClassDescriptor current = validUses.get(cd.getName());
        if (current == null) { //no problem, just add it
            validUses.put(cd.getName(), cd);
            validFullyQualifiedUses.put(cd.getStaticKey(), cd);
            return null;
        } else { //if it's not null, check to see if the user just put the same use more than once
            String containerOld = current.getContainer().getContainer();
            String containerNew = cd.getContainer().getContainer();
            if (containerOld.compareTo(containerNew) == 0) {
                return null; //ignore the request, the user is "using" the same class twice
            } else { //There's an actual name conflict, compiler error!
                CompilerError error = new CompilerError(use.getLineBegin(),
                        "There is a naming conflict." + " You have asked to use "
                        + "the class " + cd.getName() + " from the package " + containerOld
                        + " and the package " + containerNew + ".", ErrorType.USE_AMBIGUOUS);
                error.setFile(getFile().getName());
                return error;
            }
        }
    }

    /** Takes a string that represents a particular class and check
     * to see if the name of the class is a valid usage from within this class.
     * If it is, return the class that is being referenced, otherwise
     * return null.
     * @param clazz
     * @return
     */
    public ClassDescriptor getValidatedClassUse(String clazz) {
        ClassDescriptor cd = validUses.get(clazz);
        return cd;
    }

    /**
     * Takes the fully qualified name of a particular class and checks
     * if the name of the class is a valid usage from within this class.
     * 
     * @param qualifiedName
     * @return 
     */
    public ClassDescriptor getValidatedClassUseFromQualifiedName(String qualifiedName){
        return validFullyQualifiedUses.get(qualifiedName);
    }
    
    public VariableDescriptor getVariable(String key) {
        return variables.get(key);
    }

    /**
     *
     * @return the iterator over all VariableDescriptors in this class.
     */
    public Iterator<VariableDescriptor> getVariables() {
        return variables.values().iterator();
    }

    public ClassDescriptor getClass(String key) {
        return null;
    }

    public MethodDescriptor getMethod(String key) {
        return methods.get(key);
    }
    
    /**
     * This method aggregates and returns all methods known by this class, 
     * including blueprints and system actions.
     * 
     * @return 
     */
    public Collection<MethodDescriptor> getAllMethods() {
        ArrayList<MethodDescriptor> allMethods = new ArrayList<MethodDescriptor>();
        allMethods.addAll(methods.values());
        allMethods.addAll(blueprints.values());
        allMethods.addAll(systemActions.values());
        return allMethods;
    }
    
    /**
     * This method aggregates and returns all methods known by this class, 
     * including blueprints and system actions.
     * 
     * @return 
     */
    public Collection<MethodDescriptor> getAllMethods(AccessModifierEnum access) {
        ArrayList<MethodDescriptor> allMethods = new ArrayList<MethodDescriptor>();
        Iterator<MethodDescriptor> meth = methods.values().iterator();
        while(meth.hasNext()) {
            MethodDescriptor next = meth.next();
            if(next.getAccessModifier() == access) {
                allMethods.add(next);
            }
        }
        
        Iterator<BlueprintDescriptor> blue = blueprints.values().iterator();
        while(blue.hasNext()) {
            MethodDescriptor next = blue.next();
            if(next.getAccessModifier() == access) {
                allMethods.add(next);
            }
        }
        
        Iterator<SystemActionDescriptor> actions = this.systemActions.values().iterator();
        while(actions.hasNext()) {
            MethodDescriptor next = actions.next();
            if(next.getAccessModifier() == access) {
                allMethods.add(next);
            }
        }
        return allMethods;
    }
    

    /**
     *
     * @return the iterator over all MethodDescriptors in this class
     */
    public Iterator<MethodDescriptor> getMethods() {
        return methods.values().iterator();
    }

    /**
     * Returns the number of implemented methods in this class.
     *
     * @return
     */
    public int getNumberMethods() {
        return methods.size();
    }

    /**
     * Returns the number of methods that are declared as blueprints, but
     * that are not implemented in this class.
     *
     * @return
     */
    public int getNumberBlueprintMethods() {
        return blueprints.size();
    }

    /**
     * returns the number of methods that are declared as system methods in
     * this class.
     * 
     * @return
     */
    public int getNumberSystemMethods() {
        return systemActions.size();
    }

    /**
     * Returns whether or not this class has declared a constructor.
     * 
     * @return
     */
    public boolean hasConstructor() {
        return this.constructor != null;
    }

    public BlueprintDescriptor getBlueprint(String key) {
        return blueprints.get(key);
    }

    /**
     *
     * @return iterator of BlueprintDescriptors (blueprint methods).
     */
    public Iterator<BlueprintDescriptor> getBlueprints() {
        return blueprints.values().iterator();
    }

    public SystemActionDescriptor getSystemAction(String key) {
        return systemActions.get(key);
    }

    /**
     *
     * @return iterator of BlueprintDescriptors (blueprint methods).
     */
    public Iterator<SystemActionDescriptor> getSystemActions() {
        return systemActions.values().iterator();
    }

    /**
     * returns true if the class has blueprints.
     * @return
     */
    public boolean hasBlueprints() {
        return !blueprints.isEmpty();
    }

    public void setParent(Scopable parent) {
        throw new UnsupportedOperationException("A class cannot have a parent yet");
    }

    public Scopable getParent() {
        return null;
    }

    public void add(BlockDescriptor descriptor) {
        throw new UnsupportedOperationException("Classes cannont contain arbitrary blocks");
    }

    public BlockDescriptor getNextBlock() {
        return null;
    }

    public String getScopeString() {
        return getStaticKey();
    }

    /**
     * @return the container
     */
    public ContainerDescriptor getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(ContainerDescriptor container) {
        this.container = container;
    }

    /**
     * This function analyzes a QualifiedNameDescriptor object and
     * checks to see if it is valid. If it is, it will create a
     * ContainerDescriptor object and put it in this class.
     * @param d
     */
    public void setContainerErrorCheck(QualifiedNameDescriptor d) {
        if (d == null) {
            return;
        }
        String st = d.getName();
        if (st == null) {
            return;
        }

        ContainerDescriptor cd = new ContainerDescriptor();
        cd.setContainer(st);
        setContainer(cd);
    }

    private void initUses(){

        UseDescriptor use = new UseDescriptor();
        use.setUse(TypeDescriptor.OBJECT);
        use.setName("Object");
        uses.put(use.getName(), use);

        use = new UseDescriptor();
        use.setUse(TypeDescriptor.INTEGER_OBJECT);
        use.setName("Integer");
        uses.put(use.getName(), use);

        use = new UseDescriptor();
        use.setUse(TypeDescriptor.NUMBER_OBJECT);
        use.setName("Number");
        uses.put(use.getName(), use);

        use = new UseDescriptor();
        use.setUse(TypeDescriptor.TEXT_OBJECT);
        use.setName("Text");
        uses.put(use.getName(), use);

        use = new UseDescriptor();
        use.setUse(TypeDescriptor.BOOLEAN_OBJECT);
        use.setName("Boolean");
        uses.put(use.getName(), use);

    }

    /**
     * Add UseDescriptors to the class. When ever "use" is found
     * that use is added to the class in which it was defined.
     * @param v
     */
    public void addUsesDescriptors(Vector<QualifiedNameDescriptor> v) {
        for (int i = 0; i < v.size(); i++) {
            UseDescriptor d = new UseDescriptor();
            d.setUse(v.get(i).getName());
            d.setLineBegin(v.get(i).getLineBegin());
            uses.put(d.toString(), d);
        }
    }

    /**
     * Get a UseDescriptor from this class given the static key for that use.
     * @param key
     * @return a UseDescriptor
     */
    public UseDescriptor getUse(String key) {
        return uses.get(key);
    }

    /**
     *
     * @return an iterator over the Uses in this class
     */
    public Iterator<UseDescriptor> getUses() {
        return uses.values().iterator();
    }

    /**
     * @return the file
     */
    public FileDescriptor getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    /**
     * This function returns the fully qualified name
     * for this class. For example, getName() might return "Support,"
     * but this might return "test.Support".
     * @return
     */
    @Override
    public String getStaticKey() {
        String myName = getContainer().getContainer() + "." + getName();
        return myName;
    }

    /**
     * Returns a type descriptor for this class.
     *
     * @return
     */
    public TypeDescriptor getType() {
        return TypeDescriptor.getClassType(this);
    }

    /**
     * Returns an iterator of all member (class) variables in the
     * class being referenced.
     * @return
     */
    public Iterator<VariableDescriptor> getClassVariables() {
        return variables.values().iterator();
    }

    /**
     * @return the location
     */
    public ClassLocation getLocation() {
        return location;
    }

    /**
     * Returns a list of all parents in the parent hierarchy of this class.
     * @return the flatListOfParents
     */
    public Iterator<ClassDescriptor> getFlattenedListOfParents() {
        return flatListOfParents.iterator();
    }

    /**
     * Reverse the order of the flattened list of parents
     */
    void reverseFlattenedListOfParentes() {
        //reverse order
        ArrayList<ClassDescriptor> reverse = new ArrayList<ClassDescriptor>();
        reverse = (ArrayList<ClassDescriptor>) flatListOfParents.clone();
        flatListOfParents.clear();
        for (int i = reverse.size() - 1; i >= 0; i--) {
            flatListOfParents.add(reverse.get(i));
        }
    }

    /**
     * Returns the number of parents a class has (no duplicates of classes).
     * flatListOfParents is a flat list of the parents of the class. This means
     * a classes parents only include a parent once in the hierarchy, ordered by level
     * in the inheritance tree.
     * 
     * @return
     */
    public int getNumberFlatParents() {
        return flatListOfParents.size();
    }

    @Override
    public String toString() {
        return this.getStaticKey();
    }

    private void setImplementable(boolean b) {
        implementable = b;
    }

    /**
     * A flag that tells the compiler if the class can be instantiated. In order
     * to instantiate an object all of the methods (belonging to that class
     * or a parent class) must be implemented.
     * @return
     */
    public boolean isInstantiatable() {
        return implementable;
    }

    /**
     * Returns a method from a list of methods which have had their types (templated
     * types) determined. This is similar to the virtual method has only this is
     * a copy so that type erasure does not eliminate a methods templated type
     * (usually Libraries.Language.Object).
     * 
     * @return the resolvedTypeVirtualMethods
     */
    public MethodDescriptor getResolvedTypeVirtualMethod(String key) {
        return resolvedTypeVirtualMethods.get(key);
    }

    /**
     * @return the constructor
     */
    public MethodDescriptor getConstructor() {
        return constructor;
    }

    /**
     * Does this class already have a constructor(Only one constructor without
     * parameters is permitted per class).
     *
     * @param newConstructor constructor being added to the class.
     * @return true if the class already has a constructor and false if it does
     * not already have a constructor.
     */
    private CompilerError hasConstructor(MethodDescriptor newConstructor){
        if(constructor != null){
            CompilerError error = getCompilerErrorInfoFromDescriptor(newConstructor);
            error.setError(newConstructor.getName() + " has already been defined.");
            error.setErrorType(ErrorType.METHOD_DUPLICATE);
            return error;
        }
        return null;
    }

    /**
     * This method looks through the list of methods to determine if the line number is
     * referencing one of them.
     * This method is currently rather inefficient. Speed this up later.
     * 
     * 
     * @param lineNumber
     * @return 
     */
    public MethodDescriptor getMethodAtLine(int lineNumber) {
        Iterator<MethodDescriptor> mIt = this.methods.values().iterator();
        
        
        //this code contains a special case for the instance where a method
        //is requested at a particular line, but the requested method
        //is written in a quorum file where neither methods nor classes are
        //explicitly declared. In this case, the beginning line number
        //will be 1, but the ending line number will be zero.
        while(mIt.hasNext()) {
            MethodDescriptor next = mIt.next();
            if(isLineWithinMethod(next, lineNumber)
               || (next.getLineBegin() == 1 && next.getLineEnd() == 0)) {
                return next;
            }
        }
        
        Iterator<BlueprintDescriptor> bIt = this.getBlueprints();
        while(bIt.hasNext()) {
            MethodDescriptor next = bIt.next();
            if(isLineWithinMethod(next, lineNumber)) {
                return next;
            }
        }
        
        Iterator<SystemActionDescriptor> aIt = this.getSystemActions();
        while(mIt.hasNext()) {
            MethodDescriptor next = mIt.next();
            if(isLineWithinMethod(next, lineNumber)) {
                return next;
            }
        }
        return null;
    }
    
    private boolean isLineWithinMethod(MethodDescriptor method, int line) {
        if(method.getLineInformation().getStartLine() <= line &&
           method.getLineInformation().getEndLine() >= line) {
            return true;
        }
        return false;
    }
}
