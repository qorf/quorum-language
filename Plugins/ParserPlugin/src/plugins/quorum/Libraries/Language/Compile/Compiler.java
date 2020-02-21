/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile;

import java.io.IOException;
import quorum.Libraries.System.File_;
import quorum.Libraries.Language.Compile.QuorumSourceListener_;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import quorum.Libraries.Language.Compile.CompilerError;
import quorum.Libraries.Language.Compile.CompilerErrorType;
import quorum.Libraries.Language.Compile.QuorumBytecodeListener;
import quorum.Libraries.Language.Compile.Lexer;

/**
 *
 * @author stefika
 */
public class Compiler {
    public java.lang.Object me_ = null;
    private boolean disposeAfterNextCompile = false;
    
    public void ConnectToAntlr() {
        
    }
    
    public void Dispose() {
        disposeAfterNextCompile = true;
    }
    
    public void ParseNative(File_ file, QuorumSourceListener_ listener) {
        try {
            JavaToQuorumListener javaToQuorumListener = new JavaToQuorumListener();
            QuorumErrorListener javaToQuorumErrorListener = new QuorumErrorListener();
            javaToQuorumListener.setFile(file);
            javaToQuorumListener.setListener(listener);
            javaToQuorumErrorListener.setFile(file);
            javaToQuorumErrorListener.setListener(listener);
            
            ANTLRFileStream stream = new ANTLRFileStream(file.GetAbsolutePath());
            QuorumLexer lexer = new QuorumLexer(stream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(javaToQuorumErrorListener);
            
            
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QuorumParser parser = new QuorumParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(javaToQuorumErrorListener);
            
            ParseTree tree = parser.start();
            ParseTreeWalker walker = new ParseTreeWalker();
            javaToQuorumListener.setTokens(tokens);
            walker.walk(javaToQuorumListener, tree);
            Lexer quorumLexer = new Lexer();
            quorumLexer.plugin_.setLexer(lexer);
            listener.SetLexer(quorumLexer);
            
            if(disposeAfterNextCompile) {
                parser.getInterpreter().clearDFA();
                disposeAfterNextCompile = false;
            }
        } catch (IOException ex) {
            CompilerError error = new CompilerError();
            error.SetFile(file);
            error.SetLineNumber(1);
            error.SetColumnNumber(1);
            CompilerErrorType type = new CompilerErrorType();
            type.SetCurrentType(type.MISSING_FILE);
            error.SetCompilerErrorType(type);
            if(file == null) {
                error.SetErrorMessage("I cannot find this file.");
            } else {
                error.SetErrorMessage("I cannot find this file.");
            }
            listener.SyntaxError(error);
        }
    }
    
    public void ParseNative(String source, QuorumSourceListener_ listener) {
        JavaToQuorumListener javaToQuorumListener = new JavaToQuorumListener();
        QuorumErrorListener javaToQuorumErrorListener = new QuorumErrorListener();
        javaToQuorumListener.setListener(listener);
        javaToQuorumListener.setFile(listener.GetFile());
        javaToQuorumErrorListener.setFile(listener.GetFile());
        javaToQuorumErrorListener.setListener(listener);
            
        ANTLRInputStream stream = new ANTLRInputStream(source);
        QuorumLexer lexer = new QuorumLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(javaToQuorumErrorListener);
            
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QuorumParser parser = new QuorumParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(javaToQuorumErrorListener);
        
        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        javaToQuorumListener.setTokens(tokens);
        walker.walk(javaToQuorumListener, tree);
        Lexer quorumLexer = new Lexer();
        quorumLexer.plugin_.setLexer(lexer);
        listener.SetLexer(quorumLexer);
        
        if(disposeAfterNextCompile) {
            parser.getInterpreter().clearDFA();
            disposeAfterNextCompile = false;
        }
    }
    
    public static void main(String[] args) {
        plugins.quorum.Libraries.Language.Compile.Compiler compiler = new plugins.quorum.Libraries.Language.Compile.Compiler();
        quorum.Libraries.System.File file = new quorum.Libraries.System.File();
        file.SetWorkingDirectory("/Users/stefika/Repositories/quorum-language/Quorum3/Test/");
        //QuorumSourceListener listener = new quorum.Libraries.Language.Compile.QuorumSourceListener();
        QuorumBytecodeListener listener = new QuorumBytecodeListener();
        listener.SetSymbolTable(new quorum.Libraries.Language.Compile.Symbol.SymbolTable());
        
        
        file.SetPath("Expression.quorum");
        compiler.ParseNative(file, listener);
    }
}
