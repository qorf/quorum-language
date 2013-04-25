/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.documentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class PHPDocumentationGenerator implements DocumentationGenerator{

    
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
    private ClassDescriptor currentClass = null;
    
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
        indexPage += generateHeader();
        indexPage += "\t\t<div class=\"hero-unit\">\n";
        indexPage += "\t\t\t<div class=\"hero-unit-container\">\n";
        indexPage += "\t\t\t\t<h1>The Quorum Standard Library</h1>\n";
        indexPage += "\t\t\t\t<p>Quorum includes library classes like: 1) "
                + "text-to-speech and audio playback, 2) container classes "
                + "(e.g., arrays, lists, hash tables), 3) system classes, "
                + "4) classes for iCreate robotics,"
                + " and 5) mathematics and random numbers. "
                + "The standard library is expanded in each release.</p>\n";
        indexPage += "\t\t</div>\n";
//        indexPage += "\t<div class=\"search-box-large\">\n";
//        indexPage += "\t\t<form class=\"search\" name=\"search\" action=\"/search.php\" method=\"post\">\n";
//        indexPage += "\t\t\t<div class=\"input-append\">\n";
//        indexPage += "\t\t\t\t<input class=\"search-query\" name=\"search-query\" type=\"text\" autocomplete=\"off\" placeholder=\"Example: Array, Object, Music\">\n";
//        indexPage += "\t\t\t\t<input class=\"btn search-submit\" name=\"submit\" type=\"submit\" value=\"Search!\">\n";
//        indexPage += "\t\t\t</div>\n";
//        indexPage += "\t\t</form>\n";
//        indexPage += "\t</div>\n";
        indexPage += "</div>\n";        
        indexPage += "<ul class=\"index-grid\">\n";
        
        Iterator<IndexWrapperClass> iterator = getContainersCollection().iterator();
        while(iterator.hasNext()) {
            IndexWrapperClass next = iterator.next();
            
            indexPage += "<li class=\"grid-item grid-item-" + next.containerName.toLowerCase().replace(".","-") + "\">";
            String listHeader = (next.containerName.isEmpty() ? "Default Package" : next.containerName).replace(".", ".<br />");
            indexPage += "<h2 class=\"index_package_title\"><a href=\"#" + next.containerName.toLowerCase().replace(".","-") + "\">" + listHeader + "</a></h2>\n<i></i>\n";
            
            Iterator<String> subs = next.subcontainers.values().iterator();
            String subList = "";
            while(subs.hasNext()) {
                subList += "<li class=\"sublist-item\">" + (subs.next()) + "</li>";
            }
            if (!subList.equals("")) {
                indexPage += "<ul class=\"grid-sublist\">" + subList + "</ul>";
            }
        }
            
        indexPage += "</ul>\n";
        
        createClassTables();
        indexPage += "<?php include('static/templates/pagefooter.template.php'); ?>";
    }
    
    private String generateHeader() {
        return "<?php include('static/templates/pageheader.template.php'); ?>\n";
    }
    
    private void startNewIndex() {
        classes.clear();
        currentClass = null;
    }
    
    
    private void addToIndex(ClassDescriptor clazz) {
        classes.add(clazz);        
    }
    
    
    /**
    * Get all the containers is the compiler in a sorted and subcontainer-nested collection.
    * 
    * @param
    * @return
    */
    private Collection getContainersCollection() {
        sortClasses();
        
        HashMap<String, IndexWrapperClass> containers = new HashMap<String, IndexWrapperClass>();
        for(int i = 0; i < classes.size(); i++) {
            ClassDescriptor clazz = classes.get(i);
            String newContainer = clazz.getContainer().getContainer();
            
            //is this a new container?
            boolean isTopLevelContainer = newContainer.split("\\.").length == 2;
            if(isTopLevelContainer) {
                if(!containers.containsKey(newContainer)) {
                    HashMap<String, String> subContainers = new HashMap<String, String>();
                    IndexWrapperClass wrapped = new IndexWrapperClass();
                    wrapped.containerName = newContainer;
                    containers.put(newContainer, wrapped);
                }
            }
            
            boolean isSubContainer = newContainer.split("\\.").length >= 3;
            if(isSubContainer) {
                String[] split = newContainer.split("\\.");
                String name = split[0] + "." + split[1];
                
                IndexWrapperClass wrapped = containers.get(name);
                if(!wrapped.subcontainers.containsKey(newContainer)) {
                    wrapped.subcontainers.put(newContainer, newContainer);
                }
            }
        }
        
        List<IndexWrapperClass> containersCollection = new ArrayList<IndexWrapperClass>(containers.values());
        Comparator comparator = new Comparator() {
                                        @Override
                                        public int compare(Object a, Object b) {
                                            IndexWrapperClass left = (IndexWrapperClass) a;
                                            IndexWrapperClass right = (IndexWrapperClass) b;
                                            return (left.containerName.compareTo(right.containerName));
                                        };
                               };
        
        Collections.sort(containersCollection, comparator);
        
        return containersCollection;
    }
    
    
    /**
    * Generate the tables appear underneath the Libraries grid.
    * 
    * @param
    * @return
    */
    private void createClassTables() {
        indexPage += "\n<table class=\"table\">";
        String previousContainer = "!!!!----invalid----!!!!";
        boolean firstPackage = true;
        boolean standardListItem = true;
        for(int i = 0; i < classes.size(); i++) {
            ClassDescriptor clazz = classes.get(i);
            String newContainer = clazz.getContainer().getContainer();
            if(previousContainer.compareTo(newContainer)!=0) { // if it's a new container
                previousContainer = newContainer;
                if(!firstPackage) {
                    indexPage += "\t</table>\n";
                }
                
                indexPage += "<a id=\"" + newContainer.toLowerCase().replace(".","-") + "\"></a><table id=\"table-" + newContainer.toLowerCase().replace(".","-") + "\" class=\"table index-package\">\n";
                indexPage += "<tr><th><h3 class=\"index_package_title\">" + (newContainer.isEmpty() ? "Default Package" : newContainer) + "</h3></th></tr>\n";
                indexPage += "\t<tr class=\"packages\">\n";
                standardListItem = true;
            }
            
            indexPage += "\t\t<tr><td class=\"" + (standardListItem ? "primary" : "alternative") + "\">" + linkForClassFromRoot(clazz);
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
            indexPage += "</td></tr>\n";
            firstPackage = false;
        }
        indexPage += "\n</table>";
    }
    
    
    /**
    * Generate the left-side navigation on the documentation pages.
    * 
    * @param
    * @return
    */
    private void generateSidebar() {
        String html = "\n<ul class=\"index\">";
        String previousContainer = "!!!!----invalid----!!!!";
        boolean firstPackage = true;
        boolean standardListItem = true;
        boolean firstSublistItem = true;
        boolean beginSublist = false;
        
        for(int i = 0; i < classes.size(); i++) {
            ClassDescriptor clazz = classes.get(i);
            String newContainer = clazz.getContainer().getContainer();
            if(previousContainer.compareTo(newContainer)!=0) { // if it's a new container
                previousContainer = newContainer;
                if(!firstPackage) { html += "\t</li></ul>\n"; }
                
                html += "<li>";
                html += "\t<span class=\"collapsable\"><a href=\"#\">" + (newContainer.isEmpty() ? "Default Package" : newContainer) + "</a></span>\n";
                standardListItem = true;
                beginSublist = true;
            }
            
            if (firstSublistItem) {
                html += "</ul>";
                firstSublistItem = false;
            }
            if (beginSublist) {
                html += "<ul class=\"child\">";
            }
            
            html += "<li><span class=\"collapsable\">" + linkForClassFromRoot(clazz) + "</span></li>";
            
            firstPackage = false;
            beginSublist = false;
        }
        
        html += "\n</li></ul>";
        
    }
    
    
    /**
    * Sort all of the classes in the compiler - first by container name, then by
    * class name.
    * 
    * @param
    * @return
    */
    private void sortClasses() {
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
    }
    
    /**
    * Given a class, get the link to its page.
    * 
    * @param clazz
    * @return String
    */
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
    }
    
    /**
     * This method computes a relative path root. This root is relative to
     * the package structure in Quorum.
     * 
     * @param clazz 
     */
    private String computeSuperkey(ClassDescriptor clazz) {
        ContainerDescriptor con = clazz.getContainer();
        String container = con.getContainer();
        return container.replace('.', '|');
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
        currentClass = clazz;
        computeRelativeRoot(clazz);
        addToIndex(clazz);
          
        String key = clazz.getStaticKey();
        String result = "<?php include('" + root + "static/templates/pageheader.template.php'); ?>";
        result += "<?php include('" + root + "static/templates/classheader.template.php'); ?>";
        
        result += headingSurround(key, 1, "page_title") + "\n";
        result += "<input type=\"hidden\" id=\"classkey\" value=\"" + clazz.getStaticKey() + "\" />";

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

        className = controllableComponent("Class " + className, "class-name");
        result += headingSurround(className, 2) + "\n";
        //get the class's description in wiki format
        Documentation documentation = clazz.getDocumentation();
        String description = "";

        if(documentation != null) {
            description = Documentation.breakStringIntoParagraphs(documentation.getDescription());
        }
        description = controllableComponent(italics("Description") + ":\n" + description, "class-description");
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
            result += "\n" + controllableComponent(italics("Example Code") + ":", "class-example") + "\n";
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
                result += "<li " + listClass + " >" + getVariableDocumentation(next) + "</li>\n";
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
        
        
        result += "<?php include('" + root + "static/templates/classfooter.template.php'); ?>";
        result += "<?php include('" + root + "static/templates/pagefooter.template.php'); ?>";
        return result;
    }
    
    private String paragraph(String string) {
        return "<p>" + string + "</p>";
    }
    
    private String italics(String string) {
        return "<em>" + string + "</em>";
    }
    
    private String bold(String string) {
        return "<strong>" + string + "</strong>";
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
    
    private String controllableComponent(String string, String componentType) {
        return "<span class=\"controllable\" data-componentType=\"" + componentType + "\">" + string + "</span>";  
    }
    
    private String controllableComponent(String string, String componentType, String staticKey) {
        String dataName = "";
        
        if (componentType.equals("class-name") || componentType.equals("class-example") || componentType.equals("class-description")) {
            dataName = "data-classkey"; 
        }
        if (componentType.equals("action-name") || componentType.equals("action-example") || componentType.equals("action-description")) {
            dataName = "data-actionkey"; 
        }
        else if (componentType.equals("parameter-name") || componentType == "parameter-description") {
            dataName = "data-parameterkey";
        }
        
        return "<span class=\"controllable\" data-componentType=\"" + componentType + "\" " + dataName + "=\"" + staticKey + "\">" + string + "</span>";  
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
        return "php";
    }

    @Override
    public String getIndexName() {
        return "libraries";
    }
    
    @Override
    public void finish(File standardLibrary, File documentation) {
        File css = new File(standardLibrary.getParentFile().getAbsolutePath() + "/style.css");
        File destination = new File(documentation.getAbsolutePath() + "/style.css");
        try {
            QuorumJarGenerator gen = new QuorumJarGenerator();
            gen.copyFile(css, destination);
            
            File intro = new File(standardLibrary.getParentFile().getAbsolutePath() + "/index.php");
            destination = new File(documentation.getAbsolutePath() + "/index.php");
            gen.copyFile(intro, destination);
            
            File codeRunner = new File(standardLibrary.getParentFile().getAbsolutePath() + "/ide.html");
            destination = new File(documentation.getAbsolutePath() + "/ide.html");
            gen.copyFile(codeRunner, destination);
            
            File syntax = new File(standardLibrary.getParentFile().getAbsolutePath() + "/syntax.php");
            destination = new File(documentation.getAbsolutePath() + "/syntax.php");
            gen.copyFile(syntax, destination);
            
            //File libraries = new File(standardLibrary.getParentFile().getAbsolutePath() + "/libraries.php");
            //destination = new File(documentation.getAbsolutePath() + "/libraries.php");
            //gen.copyFile(libraries, destination);
            
            File search = new File(standardLibrary.getParentFile().getAbsolutePath() + "/search.php");
            destination = new File(documentation.getAbsolutePath() + "/search.php");
            gen.copyFile(search, destination);
            
            File curriculum = new File(standardLibrary.getParentFile().getAbsolutePath() + "/curriculum.php");
            destination = new File(documentation.getAbsolutePath() + "/curriculum.php");
            gen.copyFile(curriculum, destination);
            
            File downloads = new File(standardLibrary.getParentFile().getAbsolutePath() + "/download.php");
            destination = new File(documentation.getAbsolutePath() + "/download.php");
            gen.copyFile(downloads, destination);
            
            File models = new File(standardLibrary.getParentFile().getAbsolutePath() + "/models");
            destination = new File(documentation.getAbsolutePath() + "/models");
            gen.copyFile(models, destination);
            
            File documents = new File(standardLibrary.getParentFile().getAbsolutePath() + "/documents");
            destination = new File(documentation.getAbsolutePath() + "/documents");
            gen.copyFile(documents, destination);
            
            File statics = new File(standardLibrary.getParentFile().getAbsolutePath() + "/static");
            destination = new File(documentation.getAbsolutePath() + "/static");
            gen.copyFile(statics, destination);
            
            File assets = new File(standardLibrary.getParentFile().getAbsolutePath() + "/assets");
            destination = new File(documentation.getAbsolutePath() + "/assets");
            gen.copyFile(assets, destination);
            
            File controllers = new File(standardLibrary.getParentFile().getAbsolutePath() + "/controllers");
            destination = new File(documentation.getAbsolutePath() + "/controllers");
            gen.copyFile(controllers, destination);
            
        } catch (IOException ex) {
            Logger.getLogger(PHPDocumentationGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
//        if(string.length() == 0) {
//            return "";
//        }
//        boolean hasMoreThanTwoUppers = false;
//        int numUpper = 0;
//        for(int i = 0; i < string.length(); i++) {
//            boolean upper = Character.isUpperCase(string.charAt(i));
//            if(upper) {
//                numUpper++;
//            }
//        }
//        
//        if(numUpper >= 2) {
//            hasMoreThanTwoUppers = true;
//        }
//        boolean first = Character.isUpperCase(string.charAt(0));
//        String result = string;
//        if(hasMoreThanTwoUppers && first) {
//            result = "!" + result;
//        }
        return string;
    }
    
    private String getMethodSignature(MethodDescriptor method, boolean ratingsOn) {
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
        if(ratingsOn) {
            result += controllableComponent(modifier + methodType + " action " + name + "(" + params + ")", "action-name", method.getStaticKey());
        } else {
            result += modifier + methodType + " action " + name + "(" + params + ")";
        }
        
        String returnType = getReturnTypeString(method);
        return result;
    }
    
    
    private String getMethodSignature(MethodDescriptor method) {
        return getMethodSignature(method, false);
    }

    public String getMethodDocumentation(MethodDescriptor method) {
        Documentation documentation = method.getDocumentation();
        //check if there's documentation. If there isn't,
        //then check parents to see if it's available.
        if(!documentation.doesDocumentationExist() && currentClass != null) {
            //check all parents and inspect for conflicts
            Documentation parentDoc = null;
            MethodDescriptor parentMethod = null;
            for(int i = 0; i < currentClass.getNumFlatParents(); i++) {
                ClassDescriptor parent = currentClass.getFlatParent(i);
                MethodDescriptor currentMethod = parent.getMethod(method.getStaticKey());
                if(currentMethod == null) {
                    currentMethod = parent.getBlueprint(method.getStaticKey());
                }
                
                if(currentMethod == null) {
                    currentMethod = parent.getSystemAction(method.getStaticKey());
                }
                
                
                if(currentMethod != null && parentMethod == null) {
                    parentMethod = currentMethod;
                    if(currentMethod.getDocumentation().doesDocumentationExist()) {
                        parentDoc = parentMethod.getDocumentation();
                    }
                } else if(currentMethod != null && parentMethod != null) {
                    //if there is no multiple inheritance conflict, use the 
                    //parent's if one is not defined.
                    parentDoc = null;
                    parentMethod = null;
                    i = currentClass.getNumFlatParents() + 1;
                }
            }
            if(parentDoc != null) {
                documentation = parentDoc;
            }
        }
        
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
                    String parameterDocumentation = documentation.getParameter(parameters.get(i).getName());
                    currentParam = "<h5>" + controllableComponent(bold(currentParam) + " " + italics(parameters.get(i).getName()) + ":", "parameter-name", descriptor.getStaticKey()) + "</h5>";
                    if ("".equals(parameterDocumentation.trim())) {
                        currentParam += controllableComponent(parameterDocumentation, "parameter-description", descriptor.getStaticKey());
                    }
                    else {
                        currentParam += parameterDocumentation;
                    }
                    paramList += "<li>" + currentParam + "</li>\n\n";
                }
            }
            paramList += "</ul>";
        }
        String methodSignature = getMethodSignature(method, true);
        result += "\n\t" + headingSurround(methodSignature, 3, "action_title") + "\n";

        
        if(documentation != null) {
            String[] methodDescription = Documentation.breakStringIntoParagraphArray(documentation.getDescription());
            result += "\n\t" + breakDescriptionIntoParagraphs(methodDescription, method);
        }
        result += paramList + "\n";
        
        if(!method.getReturnType().isVoid()) {
            result += italics("Returns") + ":<ul class=\"parameters\">";
            result += "\n\t<li>" + bold(getReturnTypeString(method)) + ": " + documentation.getReturns() + "</li>\n</ul>";
        }
        if(!(method instanceof BlueprintDescriptor)) {
            if(!documentation.getExample().isEmpty()) {
                result += "\n\t" + controllableComponent(italics("Example Code:"), "action-example", method.getStaticKey());
                result += "\n" + code(documentation.getExample())
                        + "\n\n";
            }
        }

        return result;
    }
    
    private String breakDescriptionIntoParagraphs(String[] array, MethodDescriptor method) {
        String result = "";
        for(int i = 0; i < array.length; i++) {
            result += (i == array.length - 1) ? controllableComponent(paragraph(array[i]), "action-description", method.getStaticKey()) : paragraph(array[i]);
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
        return DocumentationStyle.PHP;
    }
    
}
