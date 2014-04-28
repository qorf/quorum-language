/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Containers.Array;
import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.System.File;
import quorum.Libraries.System.File$Interface;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult$Interface;
import quorum.Libraries.Language.Types.Text;
import quorum.Libraries.Language.Types.Text$Interface;

/**
 *
 * @author stefika
 */
public class CompilerTestSuite {
    public java.lang.Object $me = null;
    
    public CompilerTestResult$Interface RunClassFile(File$Interface file) {
        String name = file.GetFileName();
        CompilerTestResult$Interface result = new CompilerTestResult();
        
        ProcessBuilder pb = new ProcessBuilder("java", "-DQuorumUnitTest=1", "-classpath", "build/classes/build", "quorum." + name.split("\\.")[0]);
        System.out.println(file.GetWorkingDirectory());
        pb.directory(new java.io.File(file.GetWorkingDirectory()));
        Process proc = null;
        
        try {
            proc = pb.start();
        } catch (IOException ex) {
            System.out.println("No No");
            ex.printStackTrace();
            Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            int returnCode = proc.waitFor();
            result.Set$Libraries$Language$Compile$Test$CompilerTestResult$returnCode(returnCode);
            result.Set$Libraries$Language$Compile$Test$CompilerTestResult$passed(returnCode == 0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        do {
            try {
                line = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (line != null) {
                Text text = new Text();
                text.SetValue(line);
                result.Get$Libraries$Language$Compile$Test$CompilerTestResult$lines().Add(text);
            }
        } while (line != null);
        return result;
    }
    
    public static void main(String[] args) {
        File file = new File();
        file.SetWorkingDirectory("/Users/stefika/Repositories/quorum-language/Quorum3");
        
        plugins.quorum.Libraries.Language.Compile.Test.CompilerTestSuite suite = new plugins.quorum.Libraries.Language.Compile.Test.CompilerTestSuite();
        suite.RunClassFile(file);
    }
}
