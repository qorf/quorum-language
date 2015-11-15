/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.BLUEPRINT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.END;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.NATIVE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.NEWLINE;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN;
import static plugins.quorum.Libraries.Language.Compile.QuorumLexer.WS;

/**
 *
 * @author stefika
 */
public class QuorumBracesMatcher implements BracesMatcher {

    private MatcherContext context;
    private HashMap<Integer, Integer> matches = new HashMap<>();
    private HashMap<Integer, Integer> backwardMatches = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, Integer>> endMatches = new HashMap<>();
    private ArrayList<Integer> endMatchers = new ArrayList<>();
    private ArrayList<Integer> leftParenMatchers = new ArrayList<>();
    private ArrayList<Integer> rightParenMatchers = new ArrayList<>();

    QuorumBracesMatcher(MatcherContext mc) {
        context = mc;
        endMatchers.add(CLASS);
        endMatchers.add(ACTION);
        endMatchers.add(REPEAT);
        endMatchers.add(IF);
        endMatchers.add(CHECK);

        matches.put(CLASS, END);
        matches.put(ACTION, END);
        matches.put(REPEAT, END);
        matches.put(IF, END);
        matches.put(CHECK, END);
        matches.put(NATIVE, ACTION);
        matches.put(BLUEPRINT, ACTION);

        backwardMatches.put(CLASS, END);
        backwardMatches.put(ACTION, END);
        backwardMatches.put(REPEAT, END);
        backwardMatches.put(IF, END);
        backwardMatches.put(CHECK, END);

        HashMap<Integer, Integer> classEndMatchers = new HashMap<>();
        classEndMatchers.put(CLASS, END);
        classEndMatchers.put(ACTION, END);
        classEndMatchers.put(REPEAT, END);
        classEndMatchers.put(IF, END);
        classEndMatchers.put(ELSE_IF, END);
        classEndMatchers.put(ELSE, END);
        classEndMatchers.put(CHECK, END);
        classEndMatchers.put(DETECT, END);
        classEndMatchers.put(ALWAYS, END);

        HashMap<Integer, Integer> nativeEndMatchers = new HashMap<>();
        nativeEndMatchers.put(NATIVE, ACTION);
        nativeEndMatchers.put(BLUEPRINT, ACTION);
        endMatches.put(END, classEndMatchers);
        endMatches.put(ACTION, nativeEndMatchers);

        leftParenMatchers.add(LEFT_PAREN);
        rightParenMatchers.add(RIGHT_PAREN);
    }

    @Override
    public int[] findOrigin() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();
            int offset = context.getSearchOffset();

            TokenHierarchy<BaseDocument> th = TokenHierarchy.get(doc);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(offset, true);
            if (sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(offset, false);
            }

            if (sequence.isEmpty()) {
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
            if (id.ordinal() == CLASS) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == NATIVE) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == BLUEPRINT) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == ACTION) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == REPEAT) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == IF) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == ELSE_IF) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == ELSE) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == CHECK) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == DETECT) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == ALWAYS) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == LEFT_PAREN) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == RIGHT_PAREN) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            } else if (id.ordinal() == END) {
                return new int[]{tokens.offset(), tokens.offset() + token.length()};
            }
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }

    @Override
    public int[] findMatches() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();
            int offset = context.getSearchOffset();

            TokenHierarchy<BaseDocument> th = TokenHierarchy.get(doc);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(offset, true);
            if (sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(offset, false);
            }

            if (sequence.isEmpty()) {
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
            if (id.ordinal() == CLASS) {
                return findMatch(tokens, CLASS);
            } else if (id.ordinal() == BLUEPRINT) {
                return findMatch(tokens, BLUEPRINT);
            } else if (id.ordinal() == NATIVE) {
                return findMatch(tokens, NATIVE);
            } else if (id.ordinal() == ACTION) {
                int[] previous = isPreviousTokenBlueprintOrNative(tokens);
                if (previous == null) {
                    //reset the stream to the correct position
                    tokens.move(offset);
                    if (!tokens.moveNext()) {
                        return null;
                    }
                    token = (Token<QuorumTokenId>) tokens.token();
                    if (token == null) {
                        return null;
                    }
                    return findMatch(tokens, endMatchers, END);
                } else {
                    return previous;
                }
            } else if (id.ordinal() == REPEAT) {
                return findMatch(tokens, REPEAT);
            } else if (id.ordinal() == IF) {
                return findMatch(tokens, IF);
            } else if (id.ordinal() == ELSE_IF) {
                return findMatch(tokens, ELSE_IF);
            } else if (id.ordinal() == ELSE) {
                return findMatch(tokens, ELSE);
            } else if (id.ordinal() == CHECK) {
                return findMatch(tokens, CHECK);
            } else if (id.ordinal() == DETECT) {
                return findMatch(tokens, DETECT);
            } else if (id.ordinal() == ALWAYS) {
                return findMatch(tokens, ALWAYS);
            } else if (id.ordinal() == LEFT_PAREN) {
                return findMatch(tokens, leftParenMatchers, RIGHT_PAREN);
            } else if (id.ordinal() == RIGHT_PAREN) {
                tokens.moveNext();
                return findMatchBackward(tokens, LEFT_PAREN, rightParenMatchers);
            } else if (id.ordinal() == END) {
                return findMatchBackward(tokens, END);
            }
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }

    /**
     * This method determines whether the previous non-whitepsace token is
     * either a native or blueprint token. If it is, offsets are returned.
     * Otherwise, it is null.
     *
     * @param tokens
     * @return
     */
    private int[] isPreviousTokenBlueprintOrNative(TokenSequence<?> tokens) {
        while (tokens.movePrevious()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            if (ordinal != NEWLINE && ordinal != WS) {
                if (ordinal == BLUEPRINT || ordinal == NATIVE) {
                    return new int[]{tokens.offset(), tokens.offset() + token.length()};
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    private Token<QuorumTokenId> getPreviousNonwhitespaceToken(TokenSequence<?> tokens) {
        while (tokens.movePrevious()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            if (ordinal != NEWLINE && ordinal != WS) {
                return token;
            }
        }
        return null;
    }

    private int[] findMatch(TokenSequence<?> tokens, int ordinal) {
        Stack<Integer> stack = new Stack<>();
        stack.push(ordinal);

        while (tokens.moveNext()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int type = token.id().ordinal();
            if (matches.containsKey(type)) {
                Integer peek = stack.peek();
                if (peek != NATIVE && peek != BLUEPRINT) {
                    stack.push(type);
                }
            }

            if (endMatches.containsKey(type)) {
                int pop = stack.peek();
                HashMap<Integer, Integer> get = endMatches.get(type);
                if (get.containsKey(pop)) {
                    //pop the stack
                    stack.pop();
                    //if it's empty, we've found our value. If not, keep going.
                    if (stack.isEmpty()) {
                        return new int[]{tokens.offset(), tokens.offset() + token.length()};
                    }
                }
            }
        }
        return null;
    }

    private int[] findMatchBackward(TokenSequence<?> tokens, int ordinal) {
        Stack<Integer> stack = new Stack<>();
        stack.push(ordinal);

        while (tokens.movePrevious()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int type = token.id().ordinal();
            if (endMatches.containsKey(type)) {
                stack.push(type);
            }

            if (backwardMatches.containsKey(type)) {
                int pop = stack.peek();
                HashMap<Integer, Integer> get = endMatches.get(type);
                //this is one of the possible matching conditions
                if (pop == ACTION) {
                    //now check the previous token to see if it's a native
                    //or a blueprint
                    Token<QuorumTokenId> previous = getPreviousNonwhitespaceToken(tokens);
                    if (previous.id().ordinal() == BLUEPRINT || previous.id().ordinal() == NATIVE) {
                        stack.pop(); //pop off the action, it was a blueprint or system
                    } else {
                        //pop the action from the stack
                        stack.pop();
                        //pop the end from the stack
                        stack.pop();

                        //move the offset back
                        boolean done = false;
                        while (tokens.moveNext() && !done) {
                            Token<QuorumTokenId> tokenNow = (Token<QuorumTokenId>) tokens.token();
                            int ordinalNow = tokenNow.id().ordinal();
                            if (ordinalNow != NEWLINE && ordinalNow != WS) {
                                tokens.movePrevious();
                                done = true;
                            }
                        }

                        //if it's empty, we've found our value. If not, keep going.
                        if (stack.isEmpty()) {
                            return new int[]{tokens.offset(), tokens.offset() + token.length()};
                        }
                    }
                } else {
                    //pop the stack
                    stack.pop();
                    //if it's empty, we've found our value. If not, keep going.
                    if (stack.isEmpty()) {
                        return new int[]{tokens.offset(), tokens.offset() + token.length()};
                    }
                }
            }
        }
        return null;
    }

    private int[] findMatch(TokenSequence<?> tokens, ArrayList<Integer> upTokens, int downToken) {
        int balance = 0;
        while (tokens.moveNext()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            int i = 0;
            while (i < upTokens.size()) {
                if (ordinal == upTokens.get(i)) {
                    balance++;
                }
                i++;
            }

            if (ordinal == downToken) {
                if (balance == 0) {
                    return new int[]{tokens.offset(), tokens.offset() + token.length()};
                }
                balance--;
            }
        }
        return null;
    }

    private int[] findMatchBackward(TokenSequence<?> tokens, int upToken, ArrayList<Integer> downTokens) {
        int balance = 0;
        while (tokens.movePrevious()) {
            Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
            int ordinal = token.id().ordinal();
            if (ordinal == upToken) {
                balance--;
            }

            int i = 0;
            while (i < downTokens.size()) {
                if (ordinal == downTokens.get(i)) {
                    balance++;
                }
                if (balance == 0) {
                    return new int[]{tokens.offset(), tokens.offset() + token.length()};
                }
                i++;
            }
        }
        return null;
    }
}
