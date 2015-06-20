/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.LinkedList;
import java.util.List;
import org.netbeans.modules.csl.api.CodeCompletionResult;
import org.netbeans.modules.csl.api.CompletionProposal;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.CodeCompletionResult_;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.Language.Compile.CodeCompletionItem_;

/**
 *
 * @author stefika
 */
public class QuorumCodeCompletionResult extends CodeCompletionResult{
    
    private CodeCompletionResult_ result = null;
    
    @Override
    public List<CompletionProposal> getItems() {
        List<CompletionProposal> list = new LinkedList<CompletionProposal>();
        if(result != null) {
            Iterator_ it = result.GetIterator();
            if(it != null) {
                while(it.HasNext()) {
                    CodeCompletionItem_ next = (CodeCompletionItem_) it.Next();
                    QuorumCompletionProposal prop = new QuorumCompletionProposal();
                    prop.setCompletionItem(next);
                    prop.setPrefix(result.Get_Libraries_Language_Compile_CodeCompletionResult__prefix_());
                    list.add(prop);
                }
            }
        }
        return list;
    }

    @Override
    public boolean isTruncated() {
        return false;
    }

    @Override
    public boolean isFilterable() {
        return true;
    }

    /**
     * @return the result
     */
    public CodeCompletionResult_ getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(CodeCompletionResult_ result) {
        this.result = result;
    }
    
}
