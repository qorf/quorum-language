/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Andreas Stefik
 */
public class CodeCompletionResult {
    private ArrayList<CodeCompletionItem> results = new ArrayList<CodeCompletionItem>();
    private String filter = "";
    
    public void add(CodeCompletionItem item) {
        results.add(item);
    }
    
    public void clear() {
        results.clear();
    }
    
    public Collection<CodeCompletionItem> getCompletionItems() {
        return results;
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
