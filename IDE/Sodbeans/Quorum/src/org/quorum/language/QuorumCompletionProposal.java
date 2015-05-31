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
import quorum.Libraries.Language.Compile.CodeCompletionItem$Interface;

/**
 *
 * @author stefika
 */
public class QuorumCompletionProposal extends DefaultCompletionProposal{
    private CodeCompletionItem$Interface completionItem = null;
    private String prefix = "";
    
    public QuorumCompletionProposal() {
    }
    
    @Override
    public ElementHandle getElement() {
        String value = completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$displayText();
        return new ElementHandle.UrlHandle(value);
    }

    @Override
    public String getLhsHtml(HtmlFormatter formatter) {
        String value = completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$displayText();
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        ElementKind kind = getKind();
        formatter.name(kind, true);
        if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$isBaseClassAction()) {
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
        String value = completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$rightDisplayText();
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        ElementKind kind = getKind();
        formatter.name(kind, true);
        if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$isBaseClassAction()) {
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
        String value = completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$displayText();
        return value;
    }

    @Override
    public String getInsertPrefix() {
        String value = completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$completionText();
        if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$isActionWithoutParameters()) {
            value = value + "()";
        }
        return value;
    }

    @Override
    public int getAnchorOffset() {
        return completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$dotOffset();
    }
    
    /**
     * @return the completionItem
     */
    public CodeCompletionItem$Interface getCompletionItem() {
        return completionItem;
    }

    /**
     * @param completionItem the completionItem to set
     */
    public void setCompletionItem(CodeCompletionItem$Interface completionItem) {
        this.completionItem = completionItem;
        if(completionItem != null) {
            int type = completionItem.GetType();
            if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$CLASS() == type) {
                elementKind = ElementKind.CLASS;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$LOCAL_VARIABLE()== type) {
                elementKind = ElementKind.VARIABLE;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PACKAGE()== type) {
                elementKind = ElementKind.PACKAGE;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PARAMETER()== type) {
                elementKind = ElementKind.VARIABLE;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PRIMITIVE()== type) {
                elementKind = ElementKind.CONSTANT;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PRIVATE_ACTION()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PRIVATE_BLUEPRINT_ACTION()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PRIVATE_SYSTEM_ACTION()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PUBLIC_ACTION()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PUBLIC_BLUEPRINT_ACTION() == type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PUBLIC_SYSTEM_ACTION()== type) {
                elementKind = ElementKind.METHOD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PRIVATE_FIELD_VARIABLE()== type) {
                elementKind = ElementKind.FIELD;
            } else if(completionItem.Get$Libraries$Language$Compile$CodeCompletionItem$PUBLIC_FIELD_VARIABLE()== type) {
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
