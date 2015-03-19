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

/**
 *
 * @author stefika
 */
public class QuorumCodeCompletionResult extends CodeCompletionResult{
    List<CompletionProposal> list = new LinkedList<CompletionProposal>();
    
    @Override
    public List<CompletionProposal> getItems() {
        QuorumCompletionProposal proposal = new QuorumCompletionProposal();
        list.add(proposal);
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
    
}
