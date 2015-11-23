/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.CodeCompletionHandler;
import org.netbeans.modules.csl.api.DeclarationFinder;
import org.netbeans.modules.csl.api.HintsProvider;
import org.netbeans.modules.csl.api.InstantRenamer;
import org.netbeans.modules.csl.api.OccurrencesFinder;
import org.netbeans.modules.csl.api.SemanticAnalyzer;
import org.netbeans.modules.csl.api.StructureScanner;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.netbeans.modules.parsing.spi.Parser;
import static org.quorum.language.QuorumLanguageConfig.QUORUM_MIME_TYPE;

/**
 *
 * @author stefika
 */
@LanguageRegistration(mimeType = QUORUM_MIME_TYPE)
public class QuorumLanguageConfig extends DefaultLanguageConfig{
    private static final String LINE_COMMENT_PREFIX = "//";
    public static final String QUORUM_MIME_TYPE = "text/x-quorum";
    QuorumParser parser = new QuorumParser();
    QuorumHintsProvider hints = new QuorumHintsProvider();
    QuorumLanguageHierarchy language = new QuorumLanguageHierarchy();
    org.netbeans.api.lexer.Language lexerLanguage = language.language();
    QuorumCodeCompletionHandler completion = new QuorumCodeCompletionHandler();
    QuorumIndexerFactory factory = new QuorumIndexerFactory();
    QuorumIndexSearcher indexSearcher = new QuorumIndexSearcher();
    QuorumSemanticAnalyzer semanticAnalyzer = new QuorumSemanticAnalyzer();
    QuorumStructureScanner structureScanner = new QuorumStructureScanner();
    QuorumOccurrencesFinder occurrencesFinder = new QuorumOccurrencesFinder();
    QuorumDeclarationFinder finder = new QuorumDeclarationFinder();
    QuorumInstantRenamer renamer = new QuorumInstantRenamer();
    
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
        return semanticAnalyzer;
    }
    
    @Override
    public boolean hasStructureScanner() {
        return true;
    }

    @Override
    public boolean hasOccurrencesFinder() {
        return true;
    }

    @Override
    public OccurrencesFinder getOccurrencesFinder() {
        return occurrencesFinder;
    }

    @Override
    public DeclarationFinder getDeclarationFinder() {
        return finder;
    }

    @Override
    public StructureScanner getStructureScanner() {
        return structureScanner;
    } 

    @Override
    public InstantRenamer getInstantRenamer() {
        return renamer;
    }
}
