/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.CodeCompletionHandler;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.netbeans.modules.parsing.spi.Parser;

/**
 *
 * @author stefika
 */
@LanguageRegistration(mimeType = "text/x-quorum")
public class QuorumLanguageConfig extends DefaultLanguageConfig{
    private static final String LINE_COMMENT_PREFIX = "//";
    QuorumParser parser = new QuorumParser();
    QuorumLanguageHierarchy language = new QuorumLanguageHierarchy();
    org.netbeans.api.lexer.Language lexerLanguage = language.language();
    QuorumCodeCompletionHandler completion = new QuorumCodeCompletionHandler();
    
    @Override
    public org.netbeans.api.lexer.Language getLexerLanguage() {
        return lexerLanguage;
    }
    
    @Override
    public Parser getParser() {
        return parser;
    }

//    @Override
//    public CodeCompletionHandler getCompletionHandler() {
//        return completion;
//    }
    
    @Override
    public String getLineCommentPrefix() {
        return LINE_COMMENT_PREFIX;
    }

    @Override
    public String getDisplayName() {
        return "Quorum";
    }
    
    @Override
    public boolean isIdentifierChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }   
}
