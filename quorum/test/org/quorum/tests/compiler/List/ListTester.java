package org.quorum.tests.compiler.List;

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
    public void test_Add() {
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
    public void test_Append() {
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
    public void test_Clear() {
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
    public void test_Contains() {
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
    public void test_Copy() {
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
    public void test_Get() {
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
    public void test_GetFirstIndexOf() {
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
    public void test_GetLastIndexOf() {
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
    public void test_IsEmpty() {
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
    public void test_Set() {
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
    public void test_Size() {
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
    public void test_Add2() {
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
    public void test_RemoveLast() {
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
    public void test_RemoveFirst() {
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
    public void test_RemoveAt() {
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
    public void test_RemoveAll() {
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
    public void test_Remove() {
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
    public void test_Iterator() {
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
    public void test_GetFirst() {
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
    public void test_GetLast() {
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
}