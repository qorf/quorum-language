package plugins.quorum.Libraries.Language.Compile.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.System.File$Interface;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult$Interface;
import quorum.Libraries.Language.Types.Text;

/**
 *
 * @author stefika
 */
public class CompilerTestSuite {
    public java.lang.Object $me = null;
    
    public CompilerTestResult$Interface RunClassFile(File$Interface file) {
        String name = file.GetFileName();
        CompilerTestResult$Interface result = new CompilerTestResult();
        ProcessBuilder pb = new ProcessBuilder("java", "-DQuorumUnitTest=1", "-jar", "Default.jar");
        java.io.File parentFile = new java.io.File(file.GetAbsolutePath()).getParentFile();
        pb.directory(parentFile);
        Process proc = null;
        
        try {
            proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            int returnCode = proc.waitFor();
            result.Set$Libraries$Language$Compile$Test$CompilerTestResult$returnCode(returnCode);
            result.Set$Libraries$Language$Compile$Test$CompilerTestResult$ranWithoutError(returnCode == 0);
        } catch (InterruptedException ex) {
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
}
