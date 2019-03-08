/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile;

import java.util.List;
import quorum.Libraries.Language.Compile.Token;
import quorum.Libraries.Language.Compile.Token_;

/**
 *
 * @author stefika
 */
public class Lexer {
    public java.lang.Object me_ = null;
    private QuorumLexer lexer = null;
    
    public Token_ NextToken() {
        org.antlr.v4.runtime.Token toke = lexer.nextToken();
        quorum.Libraries.Language.Compile.Token quorumToken = new quorum.Libraries.Language.Compile.Token();
        quorumToken.SetTokenCategory(toke.getType());
        int startIndex = toke.getStartIndex();
        int stopIndex = toke.getStopIndex();
        
        int chanel = toke.getChannel();
        String text = toke.getText();
        quorumToken.SetStartIndex(startIndex);
        quorumToken.SetStopIndex(stopIndex);
        quorumToken.SetText(text);
        
        return quorumToken;
    }
    
    public void Reset() {
        lexer.reset();
    }
    
    /**
     * @return the lexer
     */
    public QuorumLexer getLexer() {
        return lexer;
    }

    /**
     * @param lexer the lexer to set
     */
    public void setLexer(QuorumLexer lexer) {
        this.lexer = lexer;
    }
}
