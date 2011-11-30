package org.quorum.tests.compiler.List;

import org.quorum.execution.RunResult;
import org.quorum.execution.ExpressionValue;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for arrays are to be added here.
 *
 * @author Elliot Motl
 */
public class ListTester {

    private QuorumVirtualMachine vm;

    public ListTester() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        CompilerTestSuite.forceSetup();
        vm = CompilerTestSuite.getVirtualMachine();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_Add_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FirstPurpleDinosaurChairWhiteCouchLego")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Add_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FirstPurpleDinosaurChairWhiteCouchLego"));
    }
    
    @Test
    public void test_Append_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Append.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("ThisIsATestIThinkMaybe")) {
                fail();
            }
        }
        vm.stop();
    }
    @Test
    public void test_Append_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Append.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("ThisIsATestIThinkMaybe"));
    }
    
    @Test
    public void test_Clear_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Clear.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("ThisIsATextLinkedList!")) {
                fail();
            }
        }
        vm.stop();
    }
    @Test
    public void test_Clear_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Clear.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("ThisIsATextLinkedList!"));
    }
    
    @Test
    public void test_Contains_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Contains.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
            boolean a1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool2");
            boolean a2 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool3");
            boolean a3 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool4");
            boolean a4 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool5");
            boolean a5 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool6");
            boolean a6 = variableValue.getResult().boolean_value;
            if (a1 != true) {
                fail();
            }
            if (a2 != false) {
                fail();
            }
            if (a3 != true) {
                fail();
            }
            if (a4 != false) {
                fail();
            }
            if (a5 != true) {
                fail();
            }
            if (a6 != false) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Contains_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Contains.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
        assert(r.getLine(2).equals("true"));
        assert(r.getLine(3).equals("false"));
        assert(r.getLine(4).equals("true"));
        assert(r.getLine(5).equals("false"));
    }
    
    @Test
    public void test_Copy_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal1");
            String a1 = variableValue.getResult().text;
            variableValue = vm.getDataEnvironment().getVariableValue("textTotal2");
            String a2 = variableValue.getResult().text;
            if (!a1.equals(a2)) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Copy_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_Get_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Get.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a1 = variableValue.getResult().text;
            if (!a1.equals("onefivenineoneeightthreeone")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Get_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Get.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("onefivenineoneeightthreeone"));
    }
    
    @Test
    public void test_GetFirstIndexOf_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetFirstIndexOf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textIndex");
            int a1 = variableValue.getResult().integer;
            if (a1 != 0) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_GetFirstIndexOf_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetFirstIndexOf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_GetLastIndexOf_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetLastIndexOf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textIndex");
            int a1 = variableValue.getResult().integer;
            if (a1 != 4) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_GetLastIndexOf_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetLastIndexOf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
    
    @Test
    public void test_IsEmpty_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
            boolean a1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("bool2");
            boolean a2 = variableValue.getResult().boolean_value;
            if (a1 != true) {
                fail();
            }
            if (a2 != false) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_IsEmpty_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }
    
    @Test
    public void test_Set_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Set.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a1 = variableValue.getResult().text;
            if (!a1.equals("eightthreenineone")) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Set_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Set.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("eightthreenineone"));
    }
    
    @Test
    public void test_Size_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Size.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textSize");
            int a1 = variableValue.getResult().integer;
            if (a1 != 28) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Size_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Size.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("28"));
    }
    
    @Test
    public void test_Add2_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Add2.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("SystemMonsterComputerClock")) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Add2_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Add2.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("SystemMonsterComputerClock"));
    }

    @Test
    public void test_RemoveLast_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveLast.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("OneFiveNineOneEightThree")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_RemoveLast_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveLast.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("OneFiveNineOneEightThree"));
    }
    
    @Test
    public void test_RemoveFirst_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveFirst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FiveNineOneEightThreeOne")) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveFirst_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveFirst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveNineOneEightThreeOne"));
    }

    @Test
    public void test_RemoveAt_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FiveNineOneThree")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_RemoveAt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveNineOneThree"));
    }
    
    @Test
    public void test_RemoveAll_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FiveNineEightThree")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_RemoveAll_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveNineOneThree"));
    }
    
    @Test
    public void test_Remove_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Remove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FiveNineOneEightThreeOne")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Remove_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Remove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveNineOneEightThreeOne"));
    }
    
    @Test
    public void test_Iterator_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Iterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("FirstPurpleDinosaurChairWhiteCouchLego")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Iterator_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "Iterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FirstPurpleDinosaurChairWhiteCouchLego"));
    }
    
    @Test
    public void test_GetFirst_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetFirst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("One")) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_GetFirst_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetFirst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("One"));
    }
    
    @Test
    public void test_GetLast_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetLast.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("textTotal");
            String a = variableValue.getResult().text;
            if (!a.equals("Five")) {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetLast_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LIST + CompilerTestSuite.PASS + "GetLast.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Five"));
    }
}