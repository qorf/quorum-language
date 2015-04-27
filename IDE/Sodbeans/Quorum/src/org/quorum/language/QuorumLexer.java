/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.antlr.v4.runtime.ANTLRInputStream;
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
    private plugins.quorum.Libraries.Language.Compile.QuorumLexer lexer;
    QuorumLexer(LexerRestartInfo<QuorumTokenId> info) {
        this.info = info;
        LexerInput input = info.input();
        
        //String value = input.readText().toString();
       // ANTLRInputStream stream = new ANTLRInputStream(value);
        AntlrCharStream stream = new AntlrCharStream(input, "QuorumEditor");
        
        lexer = new plugins.quorum.Libraries.Language.Compile.QuorumLexer(stream);
        
        
        int a = 5;
    }
    @Override
    public Token<QuorumTokenId> nextToken() {
        org.antlr.v4.runtime.Token token = lexer.nextToken();
        if (info.input().readLength() < 1) {
            return null;
        } else if (token.getType() == -1) {
            QuorumTokenId id = new QuorumTokenId(plugins.quorum.Libraries.Language.Compile.QuorumLexer.tokenNames[1], "", 1);
            org.netbeans.api.lexer.Token<QuorumTokenId> toke = info.tokenFactory().createToken(id);
            return toke;
        }
        
        
        QuorumTokenId id = new QuorumTokenId(plugins.quorum.Libraries.Language.Compile.QuorumLexer.tokenNames[token.getType()], "", token.getType());
        org.netbeans.api.lexer.Token<QuorumTokenId> toke = info.tokenFactory().createToken(id);
        return toke;
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
        int a = 5;
    }
    
}
