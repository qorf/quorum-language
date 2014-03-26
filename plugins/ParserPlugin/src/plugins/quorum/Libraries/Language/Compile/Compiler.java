/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.System.File$Interface;
import quorum.Libraries.Language.Compile.QuorumSourceListener$Interface;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


/**
 *
 * @author stefika
 */
public class Compiler {
    public java.lang.Object $me = null;
    private JavaToQuorumListener javaToQuorumListener = new JavaToQuorumListener();
    
    public void ConnectToAntlr() {
        
    }
    
    public void ParseNative(File$Interface file, QuorumSourceListener$Interface listener) {
        try {
            ANTLRFileStream stream = new ANTLRFileStream(file.GetAbsolutePath());
            QuorumLexer lexer = new QuorumLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QuorumParser parser = new QuorumParser(tokens);
            ParseTree tree = parser.start();
            javaToQuorumListener.setListener(listener);
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(javaToQuorumListener, tree);
        } catch (IOException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
