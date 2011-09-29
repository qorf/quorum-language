/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.quorum.Main;

import java.io.File;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.quorum.plugins.DefaultPluginLoader;
import org.quorum.tests.compiler.Array.ArrayTester;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import org.quorum.vm.implementation.QuorumVirtualMachine;

// Test imports.
//import org.quorum.tests.compiler.File.FileTester; // <-- old senior project team code
//import org.quorum.tests.compiler.DateTime.DateTimeTester; // < -- old senior project team code
//import org.sodbeans.tests.compiler.PriorityQueue.PriorityQueueTester; // <-- old senior project code ?
import org.quorum.tests.compiler.types.TypeCheckTester;
import org.quorum.tests.compiler.List.ListTester;
import org.quorum.tests.compiler.Random.RandomTester;
import org.quorum.tests.compiler.Stack.StackTester;
import org.quorum.tests.compiler.actions.ActionsTester;
import org.quorum.tests.compiler.curriculum.CurriculumTester;
import org.quorum.tests.compiler.exceptions.ExceptionsTester;

//import org.sodbeans.tests.compiler.actions.ActionsTester;
//import org.sodbeans.tests.compiler.arrays.ArraysTester;
//import org.sodbeans.tests.compiler.curriculum.CurriculumTester;
//import org.sodbeans.tests.compiler.exceptions.ExceptionsTester;
//import org.sodbeans.tests.compiler.ifstatement.IfStatementTester;
//import org.sodbeans.tests.compiler.inheritance.InheritanceTester;
//import org.sodbeans.tests.compiler.loops.LoopsTester;
//import org.sodbeans.tests.compiler.nativefunctions.NativeFunctionsTester;
//import org.sodbeans.tests.compiler.other.OtherTester;
//import org.sodbeans.tests.compiler.publicprivate.PublicPrivateTester;
//import org.sodbeans.tests.compiler.templating.TemplateTester;
//import org.sodbeans.tests.compiler.use.UseTester;
//import org.sodbeans.tests.compiler.math.MathTester;
//import org.sodbeans.tests.compiler.Stack.StackTester;
//import org.sonify.vm.quorum.parser.QuorumVirtualMachineTest;
//import org.sodbeans.tests.compiler.queue.QueueTester;
//import org.sodbeans.tests.compiler.table.TableTester;



/**
 *
 * @author astefik
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value={TypeCheckTester.class, ListTester.class, RandomTester.class, StackTester.class,
    CurriculumTester.class, ActionsTester.class, ExceptionsTester.class, ArrayTester.class})

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
            vm = new QuorumVirtualMachine();
            //add the default plugins
            DefaultPluginLoader loader = new DefaultPluginLoader();
            loader.loadIntoVirtualMachine(vm);
            loader.checkConsistency(vm);

            setup = true;
        }
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

}
