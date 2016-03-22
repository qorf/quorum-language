/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.modules.csl.api.Formatter;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.modules.csl.api.EditList;
import org.netbeans.modules.editor.indent.spi.Context;
import org.openide.util.Exceptions;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.BLUEPRINT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.END;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.NATIVE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.NEWLINE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ON;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.USE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.WS;

/**
 *
 * @author stefika
 */
public class QuorumFormatter implements Formatter{

    @Override
    public void reformat(Context context, ParserResult result) {
        BaseDocument document = (BaseDocument) context.document();
        
        
        try {
            document.readLock();
            
            TokenHierarchy<BaseDocument> th = TokenHierarchy.get(document);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(0, true);
            if (sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(0, false);
            }

            if (sequence.isEmpty()) {
                return;
            }
            TokenSequence<?> tokens = sequence.get(0);
            tokens.move(0);
            format(context, result, document, tokens, th);
        } finally {
            document.readUnlock();
        }
    }

    private void format(Context context, ParserResult result, BaseDocument document, TokenSequence<?> tokens, TokenHierarchy<BaseDocument> hierarchy) {
        int start = context.startOffset();
        int end = context.endOffset();
        
        final EditList edits = new EditList(document);
        HashMap<Integer, Integer> indents = new HashMap<>();
        
        Token<QuorumTokenId> previous = null;
        int previousOffset = -1;
        Token<QuorumTokenId> current = null;
        int currentOffset = -1;
        Token<QuorumTokenId> next = null;
        int nextOffset = -1;
        
        int indent = 0;
        while (tokens.moveNext()) {
            previous = current;
            previousOffset = currentOffset;
            currentOffset = tokens.offset();
            current = (Token<QuorumTokenId>) tokens.token();
            
            if(tokens.moveNext()) {
                nextOffset = tokens.offset();
                next = (Token<QuorumTokenId>) tokens.token();
                tokens.movePrevious();
            }
            
            if (current != null) {
                try {
                    int indentAmount = indent * this.indentSize();
                    QuorumTokenId id = current.id();
                    int ordinal = id.ordinal();
                    switch(ordinal) {
                        case CLASS:
                            //indents.put(offset, indentAmount);
                            if(currentOffset != Utilities.getRowFirstNonWhite(document, currentOffset)) {
                                edits.replace(previousOffset, previous.length(), "\n", false, 0);
                            }
                            indent = indent + 1;
                            break;
                        case USE:
                            
                            break;
                        case ACTION:
                            
                            
                            indent = indent + 1;
                            break;
                        case END:
                            
                            
                            indent = indent - 1;
                            break;
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        
        edits.apply();
    }
    
    @Override
    public void reindent(org.netbeans.modules.editor.indent.spi.Context cntxt) {
        int a = 5;
    }

    @Override
    public boolean needsParserResult() {
        return true;
    }

    @Override
    public int indentSize() {
        return 4;
    }

    @Override
    public int hangingIndentSize() {
        return 4;
    }
    
}
