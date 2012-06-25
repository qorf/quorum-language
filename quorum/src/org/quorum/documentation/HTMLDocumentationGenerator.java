/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.documentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.symbols.AccessModifierEnum;
import org.quorum.symbols.BlueprintDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.ContainerDescriptor;
import org.quorum.symbols.DescriptorComparator;
import org.quorum.symbols.Documentation;
import org.quorum.symbols.GenericDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Parameters;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.vm.implementation.QuorumJarGenerator;

/**
 *
 * @author astefik
 */
public class HTMLDocumentationGenerator implements DocumentationGenerator{

    
    /**
     * This string represents the root directory, by which to compare all links.
     * For all links, it must be computed, given a class's package
     * location.
     * 
     */
    private String root = "";

    private boolean indexed = true;
    private String indexPage = "";
    private ArrayList<ClassDescriptor> classes = new ArrayList<ClassDescriptor>();
    
    @Override
    public boolean isIndexed() {
        return indexed;
    }

    @Override
    public void setIndexed(boolean isIndexed) {
        indexed = isIndexed;
    }

    @Override
    public String getIndex() {
        return indexPage;
    }

    @Override
    public void clearIndex() {
        startNewIndex();
    }
    
    @Override
    public void finishIndex() {
        Collections.sort(classes, new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                ClassDescriptor left = (ClassDescriptor) a;
                ClassDescriptor right = (ClassDescriptor) b;
                //first compare the keys of the containers
                String leftContainer = left.getContainer().getContainer();
                String rightContainer = right.getContainer().getContainer();
                
                int result = leftContainer.compareTo(rightContainer);
                if(result == 0) {
                    return left.getStaticKey().compareTo(right.getStaticKey());
                }
                else {
                    return result;
                }
                
            }
        });
        
        String previousContainer = "!!!!----invalid----!!!!";
        boolean firstPackage = true;
        boolean standardListItem = true;
        for(int i = 0; i < classes.size(); i++) {
            ClassDescriptor clazz = classes.get(i);
            String newContainer = clazz.getContainer().getContainer();
            if(previousContainer.compareTo(newContainer)!=0) { //if it's a new container
                previousContainer = newContainer;
                if(!firstPackage) {
                    indexPage += "\t</ul>\n</div>\n";
                }
                
                indexPage += "<div class=\"index_package\">\n";
                if(newContainer.isEmpty()) {
                    indexPage += "\t<h2 class=\"index_package_title\">" + "Default Package" + "</h2>\n";
                }
                else {
                    indexPage += "\t<h2 class=\"index_package_title\">" + newContainer + "</h2>\n";
                }
                indexPage += "\t<ul class=\"packages\">\n";
                standardListItem = true;
            }
            
            String listClass = "";
            if(standardListItem) {
                listClass = "class = \"package_standard\"";
            }
            else {
                listClass = "class = \"package_alternate\"";
            }
            standardListItem = !standardListItem;
            indexPage += "\t\t<li " + listClass + ">" + linkForClassFromRoot(clazz);
            String description = clazz.getDocumentation().getDescription();
            final int MAX_LENGTH = 180;
            int length = description.length();
            if(length > MAX_LENGTH) {
                length = MAX_LENGTH;
            } 
            indexPage += ": " + description.substring(0, length);
            
            if(description.length() > MAX_LENGTH) {
                indexPage += " ...";
            }
            indexPage += "</li>\n";
            firstPackage = false;
        }
        indexPage += "</ul>\n";
        indexPage += "\n</div>";
        indexPage += "\n</body>\n</html>";
    }
    
    private String generateHeader() {
        String result = "";
        result += "<div class=\"quorum_header\">";
        result += "<ul class=\"quorum_header\">";
        result += "<li class=\"quorum_header_start\">Quorum</li>";
        result += "<li class=\"quorum_header\"><a href=\"" + root + "index.html" 
                + "\" class=\"quorum_header\">Index</a></li>";
        result += "<li class=\"quorum_header\"><a href=\"https://sourceforge.net/apps/trac/quorum" 
                + "\" class=\"quorum_header\">Wiki</a></li>";
        result += "<li class=\"quorum_header\"><a href=\"https://sourceforge.net/apps/trac/quorum/wiki/documentation" 
                + "\" class=\"quorum_header\">Syntax</a></li>";
        result += "</ul>";
        result += "</div>";
        return result;
    }
    
    private void startNewIndex() {
        indexPage = "";
        classes.clear();
        String pageTitle = "Index of Quorum Libraries";
        String result = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
        result += "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n";
        result += "<head>";
        result += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
        result += "<title>"+pageTitle+"</title>\n";
        result += generateHeader();
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + root + "style.css\"></link>";
        result += "</head>\n";
        result += "<body>\n";
        result += "<h1 class=\"page_title\">"+pageTitle+"</h1>\n";
        indexPage = result;
    }
    
    
    private void addToIndex(ClassDescriptor clazz) {
        classes.add(clazz);        
    }
    
    private String linkForClassFromRoot(ClassDescriptor clazz) {
        return link(generatePathToClassFromRoot(clazz), clazz.getName());
    }
    
    /**
     * Given a class in a package X, this method computes a string relative
     * to the root.
     * 
     * @param clazz
     * @return 
     */
    private String generatePathToClassFromRoot(ClassDescriptor clazz) {
        ContainerDescriptor con = clazz.getContainer();
        String container = con.getContainer();
        if(container.startsWith(".")) {
            container = container.substring(1);
        }
        String[] split = container.replace('.', '/').split("/");
        String path = "";
        for(int i = 0; i < split.length; i++) {
            path += split[i] + "/";
        }
        if(path.compareTo("/")==0) {
            path = "";
        }
        return path + clazz.getName() + "." + this.getFileExtension();
    }
    
    /**
     * Given a particular class descriptor object, this method returns an
     * html link for that class.
     * 
     * @param clazz
     * @return 
     */
    private String linkForClass(ClassDescriptor clazz) {
        return link(generatePathToClass(clazz), clazz.getStaticKey());
    }
    
    /**
     * This method computes a relative path root. This root is relative to
     * the package structure in Quorum.
     * 
     * @param clazz 
     */
    private void computeRelativeRoot(ClassDescriptor clazz) {
        root = "";
        ContainerDescriptor con = clazz.getContainer();
        String container = con.getContainer();
        if(container.length() == 0) {
           root = ""; //the root is the current folder
        } else {
            String[] split = container.replace('.', '/').split("/");
            for(int i = 0; i < split.length; i++) {
                root += "../";
            }
        }
        int a = 5;
    }
    
    /**
     * Given a class in a package X, this method computes a string relative
     * to the root.
     * 
     * @param clazz
     * @return 
     */
    private String generatePathToClass(ClassDescriptor clazz) {
        ContainerDescriptor con = clazz.getContainer();
        String container = con.getContainer();
        if(container.startsWith(".")) {
            container = container.substring(1);
        }
        String[] split = container.replace('.', '/').split("/");
        String path = root;
        for(int i = 0; i < split.length; i++) {
            path += split[i] + "/";
        }
        return path + clazz.getName() + "." + this.getFileExtension();
    }
    
    private String formatCodeForHtml(String example) {
        String result = "";
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < example.length(); i++) {
            char val = example.charAt(i);
            String newVal = "";
            if(val == '<') {
                newVal = "&lt;";
            }
            else if(val == '>') {
                newVal = "&gt;";
            }
            else if(val == '&') {
                newVal = "&amp;";
            }
            else {
                newVal = "" + val;
            }
            builder.append(newVal);
        }
        result = builder.toString();
        return result;
    }
    
    @Override
    public String generate(ClassDescriptor clazz) {
        computeRelativeRoot(clazz);
        addToIndex(clazz);
        String result = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
        result += "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n";
        result += "<head>";
        result += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";        
        String key = clazz.getStaticKey();
        result += "<title>" + key + "</title>\n";
        result += generateHeader();
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + root + "style.css\"></link>";
        result += "</head>\n";
        result += "<body>\n";
        
        result += headingSurround(key, 1, "page_title") + "\n";

        
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
            templateString = getTemplateStartCharacter() + templateString + getTemplateEndCharacter();
            className += templateString;
        }

        className = "Class " + className;
        result += headingSurround(className, 2) + "\n";
        //get the class's description in wiki format
        Documentation documentation = clazz.getDocumentation();
        String description = "";

        if(documentation != null) {
            description = Documentation.breakStringIntoParagraphs(documentation.getDescription());
        }
        description = italics("Description") + ":\n" + description;
        result += paragraph(description) + "\n";

        //create the parent initialization wiki
        
        Iterator<ClassDescriptor> unsortedParents = clazz.getFlattenedListOfParents();
        ArrayList<ClassDescriptor> sortedParents = new ArrayList<ClassDescriptor>();
        while(unsortedParents.hasNext()) {
            sortedParents.add(unsortedParents.next());
        }
        DescriptorComparator compare = new DescriptorComparator();
        String parentsString = "";
        if(!sortedParents.isEmpty()) {
            parentsString = italics("Inherits from") + ":\n";
        }
        Collections.sort(sortedParents, compare);
        Iterator<ClassDescriptor> parents = sortedParents.iterator();
        
        String parentList = "";
        while(parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            String parentPath = generatePathToClass(parent);
            String parentLink = link(parentPath, parent.getStaticKey());
            String parentListItem = parentLink;
            if(parents.hasNext()) { //there's another
                parentListItem += ", ";
            }
            parentList += parentListItem;
        }
        
        parentsString += parentList;
                
        if(!documentation.getExample().isEmpty()) {
            result += "\n" + italics("Example Code") + ":\n";
            result += code(documentation.getExample()) + "\n\n";
        }
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
            result += "\n " + headingSurround("Variables",2, "action_or_variables_header") + "\n";
            result += "<ul class=\"variables\">";
            boolean standardListItem = true;
            while(sortedVariables.hasNext()) {
                VariableDescriptor next = sortedVariables.next();
                String listClass = "";
                if(standardListItem) {
                    listClass = "class = \"package_standard\"";
                }
                else {
                    listClass = "class = \"package_alternate\"";
                }
                standardListItem = !standardListItem;
                result += "<li " + listClass + ">" + getVariableDocumentation(next) + "</li>\n";
            }
            result += "</ul>";
        }
        
        //add work for any public actions
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
        
        if(!methods.isEmpty()) {
            result += "\n " + headingSurround("Actions",2) + "\n";
        }
        
        //sort the methods into alphabetical order
        Collections.sort(methods, compare);
        
        Iterator<MethodDescriptor> sortedMethods = methods.iterator();
        while(sortedMethods.hasNext()) {
            MethodDescriptor method = sortedMethods.next();
            if(method.getAccessModifier() == AccessModifierEnum.PUBLIC) {
                String methodDocumentation = getMethodDocumentation(method);
                result += "<div class=\"action\">";
                result += methodDocumentation;
                result += "</div>";
                result += "<p></p>";
            }
        }
        //now generate any information relevant from the parents.
        parents = sortedParents.iterator();
        String pString = headingSurround("Inherited Actions",2) + "\n\n";
        int totalParentMethodsNotImplemented = 0;
        pString += "<ul class=\"parent_variables\">\n";
        boolean standard = true;
        while(parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            ParentResult more = getParentMethodsNotImplemented(parent, clazz);
            if(more.numParents != 0) {
                if(standard) {
                    pString += "<li class=\"package_standard\">";
                }
                else {
                    pString += "<li class=\"package_alternate\">";
                }
                standard = !standard;
                pString += more.documentation + "</li>\n";
                totalParentMethodsNotImplemented += more.numParents;
            }
        }
        pString += "</ul>";
        if(totalParentMethodsNotImplemented > 0) {
            result += pString;
        }
        
        result += "</body>\n";
        result += "</html>";
        return result;
    }
    
    private String paragraph(String string) {
        return "<p>" + string + "</p>";
    }
    
    private String italics(String string) {
        return "<i>" + string + "</i>";
    }
    
    private String bold(String string) {
        return "<b>" + string + "</b>";
    }
    
    private String code(String string) {
        return "<pre class=\"code\">" + formatCodeForHtml(string) + "</pre>";
    }
    
    private String link(String link, String text) {
        return "<a href=" + "\"" + link + "\">" + text + "</a>";
    }
    
    private String unorderedList(String string) {
        return "<ul>" + string + "</ul>";
    }
    
    private String listItem(String string) {
        return "<li>" + string + "</li>";
    }
    
    private String getVariableDocumentation(VariableDescriptor variable) {
        String result = "";
        TypeDescriptor type = variable.getType();
        String name = italics(variable.getName());
        
        
        if(type.isTemplated()) {
            result = pascalCaseChecker(type.getTemplateName());
        }
        else {
            result = pascalCasePackageChecker(type.getStaticKey());
        }
        result = bold(result);
        
        if(type.hasSubTypes()) {
            result += getTemplateStartCharacter();
            Iterator<GenericDescriptor> subTypes = type.getSubTypes();
            while(subTypes.hasNext()) {
                GenericDescriptor next = subTypes.next();
                
                String templateName = next.getType().getTemplateName();
                result += pascalCaseChecker(templateName);
                result += ",";
            }
            result = result.substring(0, result.length() - 1);
            result += getTemplateEndCharacter();
        }
        result = result + " " + pascalCaseChecker(name);
        String description = variable.getDocumentation().getDescription();
        if(!description.isEmpty()) {
            result += ": " + description;
        }
        return result;
    }
    
    private String getTemplateStartCharacter() {
        return "&lt;";
    }
    
    private String getTemplateEndCharacter() {
        return "&gt;";
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
        String parentList = "";
        while(iterator.hasNext()) {
            MethodDescriptor method = iterator.next();
            parentList += getParentMethodDocumentation(parent, method);
            if(iterator.hasNext()) {
                parentList += ", ";
            }
        }
        
        result += parentList;
        
        ParentResult res = new ParentResult();
        String link = link(generatePathToClass(parent), parent.getStaticKey()) + " ";
        res.documentation = bold("From: ") + link;
        res.documentation += result;
        res.numParents = documentThese.size();
        return res;
    }

    @Override
    public String getFileExtension() {
        return "html";
    }

    @Override
    public void finish(File standardLibrary, File documentation) {
        File css = new File(standardLibrary.getParentFile().getAbsolutePath() + "/style.css");
        File destination = new File(documentation.getAbsolutePath() + "/style.css");
        try {
            QuorumJarGenerator gen = new QuorumJarGenerator();
            gen.copyFile(css, destination);
        } catch (IOException ex) {
            Logger.getLogger(HTMLDocumentationGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class ParentResult {
        public int numParents = 0;
        public String documentation = "";
    }
    
    private String getParentMethodDocumentation(ClassDescriptor parent, MethodDescriptor method) {
        String result = "";
        result = getMethodSignature(method) + "";
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
        String name = method.getName();
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
        
        String returnType = getReturnTypeString(method);
        return result;
    }

    public String getMethodDocumentation(MethodDescriptor method) {
        String result = "";
        Parameters parameters = method.getParameters();
        String params = "";
        String paramList = "";
        if(!parameters.isEmpty()) {
            paramList = italics("Parameters") + ":";
            paramList += "<ul class=\"parameters\">";
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
                        currentParam = pascalCaseChecker(parameters.get(i).getType().getTemplateName());

                    }else {
                        currentParam = pascalCasePackageChecker(parameters.get(i).getType().getStaticKey());
                    }
                    params += currentParam;
                    paramList += "<li>";
                    paramList +=  bold(currentParam) + " " + italics(parameters.get(i).getName()) + ": ";
                    String parameterDocumentation = method.getDocumentation().getParameter(parameters.get(i).getName());
                    paramList += parameterDocumentation;
                    paramList += "</li>\n\n";
                }
            }
            paramList += "</ul>";
        }
        String methodSignature = getMethodSignature(method);
        result += "\n\t" + headingSurround(methodSignature, 3, "action_title") + "\n";

        Documentation documentation = method.getDocumentation();
        if(documentation != null) {
            String[] array = Documentation.breakStringIntoParagraphArray(documentation.getDescription());
            result += "\n\t" + breakIntoParagraphs(array);
        }
        result += paramList + "\n";
        
        if(!method.getReturnType().isVoid()) {
            result += italics("Returns") + ":<ul class=\"parameters\">";
            result += "\n\t<li>" + bold(getReturnTypeString(method)) + ": " + documentation.getReturns() + "</li>\n</ul>";
        }
        if(!(method instanceof BlueprintDescriptor)) {
            if(!documentation.getExample().isEmpty()) {
                result += "\n\t" + paragraph(italics("Example Code") + ":");
                result += "\n" + code(documentation.getExample())
                        + "\n\n";
            }
        }

        return result;
    }
    
    private String breakIntoParagraphs(String[] array) {
        String result = "";
        for(int i = 0; i < array.length; i++) {
            result += paragraph(array[i]);
        }
        return result;
    }

    private String getReturnTypeString(MethodDescriptor method) {
        String returnType = "";
        if(method.getReturnType().isVoid()) {
            returnType = "none";
        }
        else {
            boolean templated = method.getReturnType().isTemplated();
            if(templated) {
                returnType = method.getReturnType().getTemplateName();
            }
            else {
                returnType = method.getReturnType().getStaticKey();
            }
        }
        return returnType;
    }
    
    /**
     * Creates text in the style of a heading at a particular level.
     * 
     * @param input
     * @param level
     * @return 
     */
    public String headingSurround(String input, int level) {
        String result = "";
        String wiki = "";
        for(int i = 0; i < level; i++) {
            wiki += "=";
        }
        result = "<h" + level + "> " + input + "</h" + level + "> ";
        return result;
    }
    
    /**
     * Creates text in the style of a heading at a particular level.
     * 
     * @param input
     * @param level
     * @param clazz
     * @return 
     */
    public String headingSurround(String input, int level, String clazz) {
        String result = "";
        String wiki = "";
        for(int i = 0; i < level; i++) {
            wiki += "=";
        }
        result = "<h" + level + " class=\"" + clazz + "\"> " + input + "</h" + level + ">";
        return result;
    }

    @Override
    public DocumentationStyle getDocumentationStyle() {
        return DocumentationStyle.HTML;
    }
    
}
