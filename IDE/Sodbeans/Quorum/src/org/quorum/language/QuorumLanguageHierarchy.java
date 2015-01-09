/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.ArrayList;
import java.util.Collection;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author stefika
 */
public class QuorumLanguageHierarchy extends LanguageHierarchy<QuorumTokenId>{

    
    @Override
    protected Collection<QuorumTokenId> createTokenIds() {
        ArrayList<QuorumTokenId> list = new ArrayList<QuorumTokenId>();
        return list;
    }

    @Override
    protected Lexer<QuorumTokenId> createLexer(LexerRestartInfo<QuorumTokenId> info) {
        return new QuorumLexer(info);
    }

    @Override
    protected String mimeType() {
         return "text/x-quorum";
    }
    
}
