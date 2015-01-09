/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.api.lexer.PartType;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author stefika
 */
public class QuorumLexer implements Lexer<QuorumTokenId> {

    private LexerRestartInfo<QuorumTokenId> info;

    QuorumLexer(LexerRestartInfo<QuorumTokenId> info) {
        this.info = info;
        LexerInput input = info.input();
        
    }
    @Override
    public Token<QuorumTokenId> nextToken() {
        QuorumTokenId id = new QuorumTokenId("ID", "keyword", 0);
        org.netbeans.api.lexer.Token<QuorumTokenId> token = info.tokenFactory().createToken(id);
        return token;
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }
    
}
