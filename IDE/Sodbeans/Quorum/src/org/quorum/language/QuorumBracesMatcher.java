/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;

/**
 *
 * @author stefika
 */
public class QuorumBracesMatcher implements BracesMatcher {
    private MatcherContext context;
    private ArrayList<Integer> endMatchers = new ArrayList<>();
    private ArrayList<Integer> leftParenMatchers = new ArrayList<>();
    private ArrayList<Integer> rightParenMatchers = new ArrayList<>();
    QuorumBracesMatcher(MatcherContext mc) {
        context = mc;
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT);
        endMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS);
        
        leftParenMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN);
        rightParenMatchers.add(plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN);
    }

    @Override
    public int [] findOrigin() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();
            int offset = context.getSearchOffset();

            TokenHierarchy<BaseDocument> th = TokenHierarchy.get(doc);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(offset, true);
            if(sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(offset, false);
            }
            
            if(sequence.isEmpty()) {
                return null;
            }
            TokenSequence<?> tokens = sequence.get(0);
            tokens.move(offset);
            if (!tokens.moveNext()) {
                return null;
            }
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            if (token == null) {
                return null;
            }
            
            QuorumTokenId id = token.id();
            if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.END) {
                return new int [] {tokens.offset(), tokens.offset() + token.length()};
            }
            
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }

    @Override
    public int [] findMatches() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();
            int offset = context.getSearchOffset();

            TokenHierarchy<BaseDocument> th = TokenHierarchy.get(doc);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(offset, true);
            if(sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(offset, false);
            }
            
            if(sequence.isEmpty()) {
                return null;
            }
            TokenSequence<?> tokens = sequence.get(0);
            tokens.move(offset);
            if (!tokens.moveNext()) {
                return null;
            }
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            if (token == null) {
                return null;
            }
            
            QuorumTokenId id = token.id();
            if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS) {
                return findMatch(tokens, endMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN) {
                return findMatch(tokens, leftParenMatchers, plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN) {
                tokens.moveNext();
                return findMatchBackward(tokens, plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN, rightParenMatchers);
            } else if(id.ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.END) {
                tokens.moveNext();
                return findMatchBackward(tokens, plugins.quorum.Libraries.Language.Compile.QuorumLexer.END, endMatchers);
            }
            
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }
    
    private int[] findMatch(TokenSequence<?> tokens, ArrayList<Integer> upTokens, int downToken) {
        int balance = 0;
        while(tokens.moveNext()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            int i = 0;
            while(i < upTokens.size()) {
                if(ordinal == upTokens.get(i)) {
                    balance++;
                }
                i++;
            }
            
            if(ordinal == downToken) {
                if(balance == 0) {
                    return new int [] {tokens.offset(), tokens.offset() + token.length()};
                }
                balance--;
            }
        }
        return null;
    }
    
    private int[] findMatchBackward(TokenSequence<?> tokens, int upToken, ArrayList<Integer> downTokens) {
        int balance = 0;
        while(tokens.movePrevious()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            
            if(ordinal == upToken) {
                
                balance--;
            }
            
            int i = 0;
            while(i < downTokens.size()) {
                if(ordinal == downTokens.get(i)) {
                    balance++;
                }
                if(balance == 0) {
                    return new int [] {tokens.offset(), tokens.offset() + token.length()};
                }
                i++;
            }
        }
        return null;
    }
}
