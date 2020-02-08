/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import quorum.Libraries.Language.Compile.CompilerError;
import quorum.Libraries.Language.Compile.CompilerErrorType;
import quorum.Libraries.Language.Compile.QuorumSourceListener_;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumErrorListener extends BaseErrorListener{
    private QuorumSourceListener_ listener;
    private File_ file;
    
    /**
     * @return the listener
     */
    public QuorumSourceListener_ getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(QuorumSourceListener_ listener) {
        this.listener = listener;
    }

    /**
     * @return the file
     */
    public File_ getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File_ file) {
        this.file = file;
    }
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String message, RecognitionException rec)
    {
        CompilerError error = new CompilerError();
        error.SetFile(file);
        error.SetLineNumber(line);
        error.SetColumnNumber(charPositionInLine);        
        CompilerErrorType type = new CompilerErrorType();
        
        //keep this in for now. We may do more with it later.
        if(rec != null) {
            Token offense = rec.getOffendingToken();
            if(offense != null) {
                error.SetIndex(offense.getStartIndex());
                error.SetIndexEnd(offense.getStopIndex());
            }
            
            if(rec instanceof FailedPredicateException) {
                type.SetCurrentType(type.FAILED_PREDICATE);
            } else if(rec instanceof InputMismatchException) {
                type.SetCurrentType(type.INPUT_MISMATCH);
            } else if(rec instanceof LexerNoViableAltException) {
                type.SetCurrentType(type.LEXER_NO_VIABLE_ALTERNATIVE);
                LexerNoViableAltException lex = (LexerNoViableAltException) rec;
                error.SetIndex(lex.getStartIndex());
                
                if(offendingSymbol != null && offendingSymbol instanceof String) {
                    String str = (String) offendingSymbol;
                    int length = str.length();
                    error.SetIndexEnd(lex.getStartIndex() + length);
                } else {
                    error.SetIndexEnd(lex.getStartIndex());
                }
            } else if(rec instanceof NoViableAltException) {
                type.SetCurrentType(type.PARSER_NO_VIABLE_ALTERNATIVE);
            } else {
                type.SetCurrentType(type.OTHER);
            }
        } else {
            type.SetCurrentType(type.OTHER);
            if(offendingSymbol != null && offendingSymbol instanceof Token) {
                Token offense = (Token) offendingSymbol;
                error.SetIndex(offense.getStartIndex());
                error.SetIndexEnd(offense.getStopIndex());
            }
        }
        error.SetCompilerErrorType(type);
        error.SetErrorMessage(message);
        listener.SyntaxError(error);
    }
}
