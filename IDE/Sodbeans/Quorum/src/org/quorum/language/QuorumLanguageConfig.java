/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.CodeCompletionHandler;
import org.netbeans.modules.csl.api.DeclarationFinder;
import org.netbeans.modules.csl.api.HintsProvider;
import org.netbeans.modules.csl.api.IndexSearcher;
import org.netbeans.modules.csl.api.SemanticAnalyzer;
import org.netbeans.modules.csl.api.StructureScanner;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexerFactory;

/**
 *
 * @author stefika
 */
@LanguageRegistration(mimeType = "text/x-quorum")
public class QuorumLanguageConfig extends DefaultLanguageConfig{
    private static final String LINE_COMMENT_PREFIX = "//";
    QuorumParser parser = new QuorumParser();
    QuorumHintsProvider hints = new QuorumHintsProvider();
    QuorumLanguageHierarchy language = new QuorumLanguageHierarchy();
    org.netbeans.api.lexer.Language lexerLanguage = language.language();
    QuorumCodeCompletionHandler completion = new QuorumCodeCompletionHandler();
    QuorumIndexerFactory factory = new QuorumIndexerFactory();
    QuorumIndexSearcher indexSearcher = new QuorumIndexSearcher();
    QuorumSemanticAnalyzer semanticAnalyzer = new QuorumSemanticAnalyzer();
    QuorumStructureScanner structureScanner = new QuorumStructureScanner();
    
    public QuorumLanguageConfig() {
        
    }
    @Override
    public org.netbeans.api.lexer.Language getLexerLanguage() {
        return lexerLanguage;
    }
    
    @Override
    public Parser getParser() {
        return parser;
    }

    @Override
    public CodeCompletionHandler getCompletionHandler() {
        return completion;
    }

    @Override
    public HintsProvider getHintsProvider() {
        return hints;
    }

    @Override
    public boolean hasHintsProvider() {
        return true;
    }
    
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

    @Override
    public SemanticAnalyzer getSemanticAnalyzer() {
        return semanticAnalyzer; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasStructureScanner() {
        return true;
    }

    @Override
    public DeclarationFinder getDeclarationFinder() {
        return super.getDeclarationFinder(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StructureScanner getStructureScanner() {
        return structureScanner;
    } 

    @Override
    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    @Override
    public EmbeddingIndexerFactory getIndexerFactory() {
        return factory;
    }
}
