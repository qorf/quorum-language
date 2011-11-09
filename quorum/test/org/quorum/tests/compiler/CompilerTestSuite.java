/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler;

import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.quorum.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.quorum.execution.RunResult;
import org.quorum.plugins.DefaultPluginLoader;
import org.quorum.tests.compiler.Array.ArrayTester;
import org.quorum.tests.compiler.File.FileTester;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import org.quorum.vm.implementation.QuorumVirtualMachine;

// Test imports.
import org.quorum.tests.compiler.types.TypeCheckTester;
import org.quorum.tests.compiler.List.ListTester;
import org.quorum.tests.compiler.Math.MathTester;
import org.quorum.tests.compiler.Queue.QueueTester;
import org.quorum.tests.compiler.Random.RandomTester;
import org.quorum.tests.compiler.Stack.StackTester;
import org.quorum.tests.compiler.Table.TableTester;
import org.quorum.tests.compiler.actions.ActionsTester;
import org.quorum.tests.compiler.curriculum.CurriculumTester;
import org.quorum.tests.compiler.exceptions.ExceptionsTester;
import org.quorum.tests.compiler.ifstatement.IfStatementTester;
import org.quorum.tests.compiler.inheritance.InheritanceTester;
import org.quorum.tests.compiler.loops.LoopsTester;
import org.quorum.tests.compiler.nativefunctions.NativeFunctionsTester;
import org.quorum.tests.compiler.Expressions.ExpressionsTester;
import org.quorum.tests.compiler.other.OtherTester;
import org.quorum.tests.compiler.publicprivate.PublicPrivateTester;
import org.quorum.tests.compiler.templating.TemplateTester;
import org.quorum.tests.compiler.use.UseTester;

/**
 *
 * @author astefik
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value={TypeCheckTester.class, ListTester.class, RandomTester.class, StackTester.class,
    CurriculumTester.class, ActionsTester.class/*, ExceptionsTester.class*/, ArrayTester.class, TableTester.class,
    MathTester.class, QueueTester.class, IfStatementTester.class, InheritanceTester.class, LoopsTester.class,
    NativeFunctionsTester.class, OtherTester.class, PublicPrivateTester.class, /*TemplateTester.class*/
    UseTester.class, FileTester.class, ExpressionsTester.class})


/*@Suite.SuiteClasses(value={QuorumVirtualMachineTest.class, TypeCheckTester.class, TemplateTester.class, ActionsTester.class, ArraysTester.class,
    CurriculumTester.class, IfStatementTester.class, InheritanceTester.class, LoopsTester.class, NativeFunctionsTester.class, OtherTester.class,
    PublicPrivateTester.class, UseTester.class, ExceptionsTester.class, ListTester.class, MathTester.class, FileTester.class, 
    DateTimeTester.class, StackTester.class, PriorityQueueTester.class, QueueTester.class, TableTester.class})*/

public class CompilerTestSuite {
    public static final String PASS = "Pass" + File.separatorChar;
    public static final String FAIL = "Fail" + File.separatorChar;
    public static final String TYPE_CHECKER = "TypeChecker" + File.separatorChar;
    public static final String TEMPLATING = "templating" + File.separatorChar;
    public static final String CURRICULUM = "curriculum" + File.separatorChar;
    public static final String EXPRESSIONS = "expressions" + File.separatorChar;
    public static final String LOOPS = "loops" + File.separatorChar;
    public static final String IF_STATEMENT = "ifstatement" + File.separatorChar;
    public static final String ACTIONS = "actions" + File.separatorChar;
    public static final String NATIVE_FUNCIONS = "nativefunctions" + File.separatorChar;
    public static final String INHERITANCE = "inheritance" + File.separatorChar;
    public static final String USE = "use" + File.separatorChar;
    public static final String ARRAYS = "Array" + File.separatorChar;
    public static final String PUBLIC_PRIVATE = "publicprivate" + File.separatorChar;
    public static final String EXCEPTIONS = "exceptions" + File.separatorChar;
    public static final String OTHER = "other" + File.separatorChar;
    public static final String LIST = "List" + File.separatorChar;
    public static final String VECTOR = "Vector" + File.separatorChar;
    public static final String FILE = "File" + File.separatorChar;
    public static final String MATH = "Math" + File.separatorChar;
    public static final String TREESET = "TreeSet" + File.separatorChar;
    public static final String TREEMAP = "TreeMap" + File.separatorChar;
    public static final String RANDOM = "Random" + File.separatorChar;
    public static final String DATE_TIME = "DateTime" + File.separatorChar;
    public static final String QUEUE = "Queue" + File.separatorChar;
    public static final String STACK = "Stack" + File.separatorChar;
    public static final String PRIORITYQUEUE = "PriorityQueue"+File.separatorChar;
    public static final String TABLE = "Table"+File.separatorChar;
    private static QuorumVirtualMachine vm;
    private static File systemRoot;
    private static boolean setup = false;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        forceSetup();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        forceSetup();
    }

    public static void forceSetup() {
        if(!setup) {
            URL url = CompilerTestSuite.class.getResource("");
            systemRoot = new File(url.getPath());
            for(int i = 0; i < 7; i++) {
                systemRoot = systemRoot.getParentFile();
            }
            
            // Fix for new test suite: Update to the standard library path.
            File index = new File(systemRoot.getAbsolutePath() +
                    "/libraries/indexes/quorum.index");
            File root = new File(systemRoot.getAbsolutePath() +
                    "/libraries/quorum/");
            QuorumStandardLibrary.overrideStandardLibraryPath(root, index);
            setup = true;
        }
        
        //
        vm = new QuorumVirtualMachine();
        //add the default plugins
        DefaultPluginLoader loader = new DefaultPluginLoader();
        loader.loadIntoVirtualMachine(vm);
        loader.checkConsistency(vm);
    }
    

    @After
    public void tearDown() throws Exception {        

    }

    public static void build(File file) {
        File[] files = new File[1];
        files[0] = file;
        build(files);
    }

    /**
     * This method builds files appropriately. It is a little quirky. First,
     * the file in slot 0 of the array must have a main method, as this is
     * set to that field in the virtual machine. Second, the size of the
     * array is used to determine the number of files. So, for example, if
     * there are two files, but the size of the array is 3, then this compile
     * will throw a null pointer exception and will fail automatically,
     * regardless of the unit test's intent.
     * 
     * @param files
     */
    public static void build(File[] files) {
        vm.setMain(files[0].getAbsolutePath());
        vm.testBuild(files);
    }

    public static QuorumVirtualMachine getVirtualMachine() {
        return vm;
    }

    public static File getQuorumFile(String name) {
        File file = new File(systemRoot + "/test/tests/" + name);
        return file;
    }
    
    /**
     * Run the given build result with java. This method makes the assumption
     * that the outputted file name is "Main.class".
     * @return 
     */
    public static RunResult runQuorumFile() {
        File[] files = {new File(vm.getCurrentFileBeingExecuted())};
        return runQuorumFile(new File("Main.class"), files);
    }
    
    public static RunResult runQuorumFiles(File[] files){
        return runQuorumFile(new File("Main.class"), files);
    }
    
    /**
     * Run the given build result with java, and return the output as an array
     * of strings.
     * 
     * @param result
     * @return 
     */
    public static synchronized RunResult runQuorumFile(File file, File[] files) {
        RunResult runResult = new RunResult();
        File dir = new File(systemRoot + "/build/classes/build/");
        
        //setup the VM
        vm.setGenerateCode(true);
        vm.getCodeGenerator().setBuildFolder(dir);
        vm.setMain(files[0].getAbsolutePath());
        //build
        vm.build(files);
        
        ProcessBuilder pb = new ProcessBuilder("java", "quorum." + file.getName().split("\\.")[0]);
        pb.directory(dir);
        Process proc = null;
        
        try {
            proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            runResult.setSuccessful(proc.waitFor() == 0);
        } catch (InterruptedException ex) {
            Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Parse the output.
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        do {
            try {
                line = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(CompilerTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (line != null)
                runResult.addLine(line);
        } while (line != null);
        
        
        return runResult;
    }
}
