/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.documentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.quorum.symbols.*;

/**
 *
 * @author stefika
 */
public class TracWikiDocumentationGenerator implements DocumentationGenerator {

    
    public String generate() {
        return "";
    }

    public String generate(ClassDescriptor clazz) {
        String result = "";
        String key = clazz.getStaticKey();
        result += headingSurround(pascalCasePackageChecker(key), 1) + "\n";


        //get the class's name in wiki format
        String className = clazz.getName();

        int numTemplateVariables = 0;
        String templateString = "";
        Iterator<GenericDescriptor> templateVariables = clazz.getTemplateVariables();
        while(templateVariables.hasNext()) {
            GenericDescriptor generic = templateVariables.next();
            String genericName = generic.getName();
            if(numTemplateVariables == 0) {
                templateString += genericName;
            }
            else {
                templateString += "," + genericName;
            }

            numTemplateVariables++;
        }

        if(numTemplateVariables > 0) {
            //handle generics
            templateString = "<" + templateString + ">";
            className += templateString;
        }

        className = "Class " + this.pascalCaseChecker(className);
        result += headingSurround(className, 2) + "\n";
        //get the class's description in wiki format
        Documentation documentation = clazz.getDocumentation();
        String description = "";

        if(documentation != null) {
            description = Documentation.breakStringIntoParagraphs(documentation.getDescription());
        }
        description = "''Description'':\n" + description;
        result += description + "\n";

        //create the parent initialization wiki
        String parentsString = "''Inherits from'':\n";
        Iterator<ClassDescriptor> unsortedParents = clazz.getFlattenedListOfParents();
        ArrayList<ClassDescriptor> sortedParents = new ArrayList<ClassDescriptor>();
        while(unsortedParents.hasNext()) {
            sortedParents.add(unsortedParents.next());
        }
        DescriptorComparator compare = new DescriptorComparator();
        Collections.sort(sortedParents, compare);
        Iterator<ClassDescriptor> parents = sortedParents.iterator();
        
        while(parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            String pStr = " * [wiki:documentation/QuorumStandardLibrary/" + 
                    parent.getStaticKey().replace('.', '/') + " " + parent.getStaticKey() + "]\n";
            parentsString += pStr;
        }

        result += "\n''Example Code'':\n";
        result += "{{{\n"
                + "" + documentation.getExample()
                + "\n}}}\n\n";
        
        result += parentsString;
        //add work for any public variables
        
        Iterator<VariableDescriptor> unsortedVariables = clazz.getVariables();
        ArrayList<VariableDescriptor> variables = new ArrayList<VariableDescriptor>();
        while(unsortedVariables.hasNext()) {
            VariableDescriptor next = unsortedVariables.next();
            if(next.getAccessModifier() == AccessModifierEnum.PUBLIC) {
                variables.add(next); //ignore private variables
            }
        }
        if(variables.size() > 0) {
            Collections.sort(variables, compare);
            Iterator<VariableDescriptor> sortedVariables = variables.iterator();
            result += "\n " + headingSurround("Variables",2) + "\n";
            while(sortedVariables.hasNext()) {
                VariableDescriptor next = sortedVariables.next();
                result += getVariableDocumentation(next) + "\n";
            }
        }
        
        //add work for any public actions
        result += "\n " + headingSurround("Actions",2) + "\n";
        
        ArrayList<MethodDescriptor> methods = new ArrayList<MethodDescriptor>();
        Iterator<MethodDescriptor> unsortedMethods = clazz.getMethods();
        while(unsortedMethods.hasNext()) {
            MethodDescriptor method = unsortedMethods.next();
            methods.add(method);
        }
        Iterator<BlueprintDescriptor> blueprints = clazz.getBlueprints();
        while(blueprints.hasNext()) {
            BlueprintDescriptor method = blueprints.next();
            methods.add(method);
        }
        Iterator<SystemActionDescriptor> systemsActions = clazz.getSystemActions();
        while(systemsActions.hasNext()) {
            SystemActionDescriptor method = systemsActions.next();
            methods.add(method);
        }
        
        //sort the methods into alphabetical order
        
        Collections.sort(methods, compare);
        
        Iterator<MethodDescriptor> sortedMethods = methods.iterator();
        while(sortedMethods.hasNext()) {
            MethodDescriptor method = sortedMethods.next();
            if(method.getAccessModifier() == AccessModifierEnum.PUBLIC) {
                String methodDocumentation = getMethodDocumentation(method);
                result += methodDocumentation;
                result += "\n----\n";
            }
        }
        //now generate any information relevant from the parents.
        parents = sortedParents.iterator();
        String pString = "== Inherited Actions ==\n\n";
        int totalParentMethodsNotImplemented = 0;
        while(parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            ParentResult more = getParentMethodsNotImplemented(parent, clazz);
            if(more.numParents != 0) {
                pString += more.documentation;
                totalParentMethodsNotImplemented += more.numParents;
            }
        }
        if(totalParentMethodsNotImplemented > 0) {
            result += pString;
        }
        return result;
    }
    
    private String getVariableDocumentation(VariableDescriptor variable) {
        String result = "";
        TypeDescriptor type = variable.getType();
        String name = variable.getName();
        
        
        if(type.isTemplated()) {
            result = pascalCaseChecker(type.getTemplateName());
        }
        else {
            result = pascalCasePackageChecker(type.getStaticKey());
        }
        
        if(type.hasSubTypes()) {
            result += "<";
            Iterator<GenericDescriptor> subTypes = type.getSubTypes();
            while(subTypes.hasNext()) {
                GenericDescriptor next = subTypes.next();
                
                String templateName = next.getType().getTemplateName();
                result += pascalCaseChecker(templateName);
                result += ",";
            }
            result = result.substring(0, result.length() - 1);
            result += ">";
        }
        result = " * " + result + " " + pascalCaseChecker(name);
        return result;
    }
    
    private String getClassStringAsPath(ClassDescriptor clazz) {
        return clazz.getStaticKey().replace('.', '/') + " " + clazz.getStaticKey();
    }
    
    private boolean isMethodInClassOrPrivate(MethodDescriptor method, ClassDescriptor clazz) {
        String staticKey = method.getStaticKey();
        if(method.getAccessModifier() == AccessModifierEnum.PRIVATE) {
            return true;
        }
        if(clazz.getMethod(staticKey) == null && clazz.getBlueprint(staticKey) == null && clazz.getSystemAction(staticKey) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    private ParentResult getParentMethodsNotImplemented(ClassDescriptor parent, ClassDescriptor clazz) {
        String result = "";
        
        ArrayList<MethodDescriptor> documentThese = new ArrayList<MethodDescriptor>();
        
        Iterator<MethodDescriptor> methods = parent.getMethods();
        while(methods.hasNext()) {
            MethodDescriptor method = methods.next();
            if(!isMethodInClassOrPrivate(method, clazz)) {
                //we need to document this method
                documentThese.add(method);
                
            }
        }
        
        Iterator<BlueprintDescriptor> blueprints = parent.getBlueprints();
        while(blueprints.hasNext()) {
            MethodDescriptor method = blueprints.next();
            if(!isMethodInClassOrPrivate(method, clazz)) {
                //we need to document this method
                documentThese.add(method);
                
            }
        }
        
        Iterator<SystemActionDescriptor> sysActions = parent.getSystemActions();
        while(sysActions.hasNext()) {
            MethodDescriptor method = sysActions.next();
            if(!isMethodInClassOrPrivate(method, clazz)) {
                //we need to document this method
                documentThese.add(method);
                
            }
        }
        
        DescriptorComparator compare = new DescriptorComparator();
        Collections.sort(documentThese, compare);
        Iterator<MethodDescriptor> iterator = documentThese.iterator();
        while(iterator.hasNext()) {
            MethodDescriptor method = iterator.next();
            result += getParentMethodDocumentation(parent, method);
        }
        
        ParentResult res = new ParentResult();
        res.documentation = "'''From [wiki:Hop/HopStandardLibrary/" + getClassStringAsPath(parent) + "]'''\n\n";
        res.documentation += result;
        res.numParents = documentThese.size();
        return res;
    }
    
    private class ParentResult {
        public int numParents = 0;
        public String documentation = "";
    }
    
    private String getParentMethodDocumentation(ClassDescriptor parent, MethodDescriptor method) {
        String result = "";
        result += " * " + getMethodSignature(method) + "";
        return result;
    } 
    
    /**
     * This method assumes a string of the form Libraries.Containers.OtherStuff. It 
     * checks each dot separated
     * @param string
     * @return 
     */
    private String pascalCasePackageChecker(String string) {
        String result = "";
        String[] split = string.replace('.', '/').split("/");
        for(int i = 0; i < split.length; i++) {
            result += pascalCaseChecker(split[i]) + ".";
        }
        if(result.endsWith(".")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
    
    private String pascalCaseChecker(String string) {
        if(string.length() == 0) {
            return "";
        }
        boolean hasMoreThanTwoUppers = false;
        int numUpper = 0;
        for(int i = 0; i < string.length(); i++) {
            boolean upper = Character.isUpperCase(string.charAt(i));
            if(upper) {
                numUpper++;
            }
        }
        
        if(numUpper >= 2) {
            hasMoreThanTwoUppers = true;
        }
        boolean first = Character.isUpperCase(string.charAt(0));
        String result = string;
        if(hasMoreThanTwoUppers && first) {
            result = "!" + result;
        }
        return result;
    }
    
    private String getMethodSignature(MethodDescriptor method) {
        String result = "";
        String name = pascalCaseChecker(method.getName());
        String modifier = method.getAccessModifier().toString();

        result = "";

        String methodType = "";

        if(method instanceof BlueprintDescriptor) {
            methodType = " blueprint";
        }
        else if(method instanceof SystemActionDescriptor) {
            methodType = " system";
        }

        Parameters parameters = method.getParameters();
        String params = "";
        for (int i = 0; i < parameters.size(); i++) {
            if( i != 0) {
                params += ",";
            }
            ParameterDescriptor descriptor = parameters.get(i);
            TypeDescriptor type = descriptor.getType();
            if(type != null) {
                boolean templated = parameters.get(i).getType().isTemplated();
                String currentParam = "";
                if(templated) {
                    currentParam = pascalCaseChecker(parameters.get(i).getType().getTemplateName()) + " "
                        + parameters.get(i).getName();
                    
                }else {
                    currentParam = pascalCasePackageChecker(parameters.get(i).getType().getStaticKey()) + " "
                        + parameters.get(i).getName();
                }
                params += currentParam;
            }
        }
        result += "" + modifier + methodType + " action " + name +
                "(" + params + ")";
        
        String returnType = "";
        if(method.getReturnType().isVoid()) {
            returnType = "";
        }
        else {
            boolean templated = method.getReturnType().isTemplated();
            if(templated) {
                returnType = pascalCaseChecker(method.getReturnType().getTemplateName());
            }
            else {
                returnType = pascalCasePackageChecker(method.getReturnType().getStaticKey());
            }
        }
        
        if(!method.getReturnType().isVoid()) {
            result += " returns " + returnType;
        }
        result += "\n\n";

        return result;
    }

    public String getMethodDocumentation(MethodDescriptor method) {
        String result = "";
        String name = pascalCaseChecker(method.getName());
        String modifier = method.getAccessModifier().toString();

        result = "";

        String methodType = "";

        if(method instanceof BlueprintDescriptor) {
            methodType = " blueprint";
        }
        else if(method instanceof SystemActionDescriptor) {
            methodType = " system";
        }

        Parameters parameters = method.getParameters();
        String params = "";
        String paramList = "\n''Parameters'':\n\n";
        for (int i = 0; i < parameters.size(); i++) {
            if( i != 0) {
                params += ",";
            }
            ParameterDescriptor descriptor = parameters.get(i);
            TypeDescriptor type = descriptor.getType();
            if(type != null) {
                boolean templated = parameters.get(i).getType().isTemplated();
                String currentParam = "";
                if(templated) {
                    currentParam = pascalCaseChecker(parameters.get(i).getType().getTemplateName()) + " "
                        + parameters.get(i).getName();
                    
                }else {
                    currentParam = pascalCasePackageChecker(parameters.get(i).getType().getStaticKey()) + " "
                        + parameters.get(i).getName();
                }
                params += currentParam;
                paramList +=  " '''" + currentParam + "''': ";
                paramList += Documentation.breakStringIntoParagraphs(method.getDocumentation().getParameter(parameters.get(i).getName()));
                paramList += "\n\n";
            }
        }
        result += "'''" + modifier + methodType + " action " + name +
                "(" + params + ")";
        
        String returnType = "";
        if(method.getReturnType().isVoid()) {
            returnType = "none";
        }
        else {
            boolean templated = method.getReturnType().isTemplated();
            if(templated) {
                returnType = pascalCaseChecker(method.getReturnType().getTemplateName());
            }
            else {
                returnType = pascalCasePackageChecker(method.getReturnType().getStaticKey());
            }
        }
        
        if(!method.getReturnType().isVoid()) {
            result += " returns " + returnType;
        }
        
        result += "'''\n\n";

        Documentation documentation = method.getDocumentation();
        if(documentation != null) {
            result += Documentation.breakStringIntoParagraphs(documentation.getDescription()) + "\n";
        }
        
        if(parameters.isEmpty()) {
            paramList += " '''none'''";
        }
        
        result += paramList + "\n";
        result += "''Returns'':\n\n";
        
        
        
        
        result += " '''" + returnType + "''': " + 
                Documentation.breakStringIntoParagraphs(documentation.getReturns()) + " ";
        if(!(method instanceof BlueprintDescriptor)) {
            result += "\n\n''Example Code'':\n";
            result += "\n{{{"
                    + "\n" + documentation.getExample()
                    + "\n}}}\n";
        }

        return result;
    }

    public String headingSurround(String input, int level) {
        String result = "";

        String wiki = "";
        for(int i = 0; i < level; i++) {
            wiki += "=";
        }

        result = wiki + " " + input + " " + wiki;


        return result;
    }
    public DocumentationStyle getDocumentationStyle() {
        return DocumentationStyle.TRAC_WIKI;
    }


}
