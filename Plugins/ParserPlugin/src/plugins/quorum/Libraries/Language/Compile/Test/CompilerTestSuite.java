package plugins.quorum.Libraries.Language.Compile.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import quorum.Libraries.System.File_;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult;
import quorum.Libraries.Language.Compile.Test.CompilerTestResult_;
import quorum.Libraries.Language.Types.Text;

/**
 *
 * @author stefika
 */
public class CompilerTestSuite {
    public java.lang.Object me_ = null;
    
    public CompilerTestResult_ RunClassFile(File_ file) {
        String name = file.GetFileName();
        CompilerTestResult_ result = new CompilerTestResult();
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
            result.Set_Libraries_Language_Compile_Test_CompilerTestResult__returnCode_(returnCode);
            result.Set_Libraries_Language_Compile_Test_CompilerTestResult__ranWithoutError_(returnCode == 0);
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
                result.Get_Libraries_Language_Compile_Test_CompilerTestResult__lines_().Add(text);
            }
        } while (line != null);
        return result;
    }
    
    public CompilerTestResult_ RunJavaScript(String script) {
        CompilerTestResult_ result = new CompilerTestResult();
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            engine.getContext().setWriter(pw);
            Object eval = engine.eval(script);
            
            String output = eval.toString();
            String lines[] = output.split("<br />");
            for(int i = 0; i < lines.length; i++) {
                Text text = new Text();
                text.SetValue(lines[i]);
                result.Get_Libraries_Language_Compile_Test_CompilerTestResult__lines_().Add(text);
            }
            result.Set_Libraries_Language_Compile_Test_CompilerTestResult__ranWithoutError_(true);
        } catch (Exception ex) {
            result.Set_Libraries_Language_Compile_Test_CompilerTestResult__passed_(false);
        }
        return result;
    }
}
