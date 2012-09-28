/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

/**
 *
 * @author Andreas Stefik
 */
public class CodeCompletionItem {
    protected String documentation = "";
    protected String completion = "";
    protected String displayName = "";
    protected String displayType = "";
    private CodeCompletionType codeCompletionType = CodeCompletionType.LOCAL_VARIABLE;
    private boolean isBaseClassMethod = false;
    /**
     * @return the isBaseClassMethod
     */
    public boolean isBaseClassMethod() {
        return isBaseClassMethod;
    }

    /**
     * @param isBaseClassMethod the isBaseClassMethod to set
     */
    public void setIsBaseClassMethod(boolean isBaseClassMethod) {
        this.isBaseClassMethod = isBaseClassMethod;
    }
    
    /**
     * @return the documentation
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * @param documentation the documentation to set
     */
    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    /**
     * @return the completion
     */
    public String getCompletion() {
        return completion;
    }

    /**
     * @param completion the completion to set
     */
    public void setCompletion(String completion) {
        this.completion = completion;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * @return the displayType
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     * @param displayName the displayType to set
     */
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     * @return the codeCompletionType
     */
    public CodeCompletionType getCodeCompletionType() {
        return codeCompletionType;
    }

    /**
     * @param codeCompletionType the codeCompletionType to set
     */
    public void setCodeCompletionType(CodeCompletionType codeCompletionType) {
        this.codeCompletionType = codeCompletionType;
    }
}
