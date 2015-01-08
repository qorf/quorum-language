/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author stefika
 */
public class QuorumTokenId implements TokenId {

    private final String name;
    private final String primaryCategory;
    private final int ordinal;
    
    public QuorumTokenId(
            String name,
            String primaryCategory,
            int id) {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.ordinal = id;
    }
    
    @Override
    public String name() {
        return name;
    }

    @Override
    public int ordinal() {
        return ordinal;
    }

    @Override
    public String primaryCategory() {
        return primaryCategory;
    }
    
}
