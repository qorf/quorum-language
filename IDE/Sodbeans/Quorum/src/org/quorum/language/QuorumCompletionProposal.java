/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.api.HtmlFormatter;
import org.netbeans.modules.csl.spi.DefaultCompletionProposal;
import quorum.Libraries.Language.Compile.CodeCompletionItem_;

/**
 *
 * @author stefika
 */
public class QuorumCompletionProposal extends DefaultCompletionProposal{
    private CodeCompletionItem_ completionItem = null;
    private String prefix = "";
    
    public QuorumCompletionProposal() {
    }
    
    @Override
    public ElementHandle getElement() {
        String value = completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__displayText_();
        return new ElementHandle.UrlHandle(value);
    }

    @Override
    public String getLhsHtml(HtmlFormatter formatter) {
        String value = completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__displayText_();
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        ElementKind kind = getKind();
        formatter.name(kind, true);
        if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__isBaseClassAction_()) {
            formatter.appendHtml("<strong>" + value + "</strong>");
        } else {
            formatter.appendHtml(value);
        }
        
        formatter.name(kind, false);
        value = formatter.getText();
        return value;
    }

    @Override
    public String getRhsHtml(HtmlFormatter formatter) {
        String value = completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__rightDisplayText_();
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        ElementKind kind = getKind();
        formatter.name(kind, true);
        if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__isBaseClassAction_()) {
            formatter.appendHtml("<strong>" + value + "</strong>");
        } else {
            formatter.appendHtml(value);
        }
        
        formatter.name(kind, false);
        value = formatter.getText();
        return value;
    }
    
    @Override
    public String getName() {
        String value = completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__displayText_();
        return value;
    }

    @Override
    public String getInsertPrefix() {
        String value = completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__completionText_();
        if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__isActionWithoutParameters_()) {
            value = value + "()";
        }
        return value;
    }

    @Override
    public int getAnchorOffset() {
        return completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__dotOffset_();
    }
    
    /**
     * @return the completionItem
     */
    public CodeCompletionItem_ getCompletionItem() {
        return completionItem;
    }

    /**
     * @param completionItem the completionItem to set
     */
    public void setCompletionItem(CodeCompletionItem_ completionItem) {
        this.completionItem = completionItem;
        if(completionItem != null) {
            int type = completionItem.GetType();
            if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__CLASS_() == type) {
                elementKind = ElementKind.CLASS;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__LOCAL_VARIABLE_()== type) {
                elementKind = ElementKind.VARIABLE;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PACKAGE_()== type) {
                elementKind = ElementKind.PACKAGE;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PARAMETER_()== type) {
                elementKind = ElementKind.VARIABLE;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PRIMITIVE_()== type) {
                elementKind = ElementKind.CONSTANT;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PRIVATE_ACTION_()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PRIVATE_BLUEPRINT_ACTION_()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PRIVATE_SYSTEM_ACTION_()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PUBLIC_ACTION_()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PUBLIC_BLUEPRINT_ACTION_() == type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PUBLIC_SYSTEM_ACTION_()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PRIVATE_FIELD_VARIABLE_()== type) {
                elementKind = ElementKind.FIELD;
            } else if(completionItem.Get_Libraries_Language_Compile_CodeCompletionItem__PUBLIC_FIELD_VARIABLE_()== type) {
                elementKind = ElementKind.FIELD;
            } else {
                elementKind = ElementKind.VARIABLE;
            }
        }
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
