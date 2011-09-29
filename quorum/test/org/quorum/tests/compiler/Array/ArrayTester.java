/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler.Array;

import org.quorum.execution.ExpressionValue;
import java.io.File;
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
 * @author Melissa Stefik
 */
public class ArrayTester {

    private QuorumVirtualMachine vm;

    public ArrayTester() {
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
    public void test_pass_Array_Of_Type_Integer_Action_Set_Get_Integer_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeIntegerActionSetGetInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            for (int i = 0; i < 10; i++) {
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("elementAt" + i);
                int a = variableValue.getResult().integer;
                if (a != 1) {
                    fail();
                }
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Number_Action_Set_Get_Number_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeNumberActionSetGetNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            for (int i = 0; i < 10; i++) {
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("elementAt" + i);
                double a = variableValue.getResult().number;
                if (a != 1.5) {
                    fail();
                }
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Integer_Object_Action_Set_Get_Integer_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeIntegerObjectActionSetGetIntegerObject.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("elementAt0");
            int a = variableValue1.getObjectHash();
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("eAt0");
            int b = variableValue2.getObjectHash();
            if (a != b) {
                fail();
            }

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("value0");
            int val = variableValue3.getResult().integer;
            if (val != 1) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Number_Object_Action_Set_Get_Number_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeNumberObjectActionSetGetNumberObject.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("elementAt0");
            int a = variableValue1.getObjectHash();
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("eAt0");
            int b = variableValue2.getObjectHash();
            if (a != b) {
                fail();
            }

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("value0");
            double val = variableValue3.getResult().number;
            if (val != 1.5) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Text_Action_Set_Get_Text_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeTextActionSetGetText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            for (int i = 0; i < 10; i++) {
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("elementAt" + i);
                String a = variableValue.getResult().text;
                if (a.compareTo("t") != 0) {
                    fail();
                }
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Text_Object_Action_Set_Get_Text_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeTextObjectActionSetGetTextObject.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("elementAt0");
            int a = variableValue1.getObjectHash();
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("eAt0");
            int b = variableValue2.getObjectHash();
            if (a != b) {
                fail();
            }

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("value0");
            String val = variableValue3.getResult().text;
            if (val.compareTo("t") != 0) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Boolean_Action_Set_Get_Boolean_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeBooleanActionSetGetBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            for (int i = 0; i < 10; i++) {
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("elementAt" + i);
                boolean a = variableValue.getResult().boolean_value;
                if (a != true) {
                    fail();
                }
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Of_Type_Boolean_Object_Action_Set_Get_Boolean_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayOfTypeBooleanObjectActionSetGetBooleanObject.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("elementAt0");
            int a = variableValue1.getObjectHash();
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("eAt0");
            int b = variableValue2.getObjectHash();
            if (a != b) {
                fail();
            }

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("value0");
            boolean val = variableValue3.getResult().boolean_value;
            if (val != true) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_Array_Add() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayAdd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 12 || val3 != 12) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_add_at() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayAddAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_add_to_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayAddToFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_add_to_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayAddToEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_remove_at() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayRemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 14 || val3 != 2) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_remove() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayRemove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 13 || val3 != 2) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_remove_all() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayRemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val3 != 1) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_set_max_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArraySetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 13 || val3 != 20) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_get_max_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayGetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val3 != 10) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_is_empty() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayIsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result1");
            boolean val3 = variableValue3.getResult().boolean_value;

            ExpressionValue variableValue4 = vm.getDataEnvironment().getVariableValue("result2");
            boolean val4 = variableValue4.getResult().boolean_value;
            if (val3 != true || val4 != false) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_empty() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result1");
            boolean val3 = variableValue3.getResult().boolean_value;

            ExpressionValue variableValue4 = vm.getDataEnvironment().getVariableValue("result2");
            boolean val4 = variableValue4.getResult().boolean_value;
            if (val3 != true || val4 != true) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_copy() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayCopy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_get_from_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayGetFromFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_get_from_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayGetFromEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 14 || val2 != 12 || val3 != 13) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_remove_from_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayRemoveFromFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_remove_from_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayRemoveFromEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 14 || val2 != 13 || val3 != 12) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_set_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArraySetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;
            if (val1 != 6) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_array_get_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "ArrayGetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;
            if (val1 != 0) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_add() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorAdd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 12 || val3 != 12) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_add_at() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorAddAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_add_to_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorAddToFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_add_to_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorAddToEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_remove_at() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorRemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 14 || val3 != 2) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_remove() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorRemove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 13 || val3 != 2) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_remove_all() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorRemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val3 != 1) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_set_max_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorSetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 12 || val2 != 13 || val3 != 20) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_get_max_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorGetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("size");
            int val3 = variableValue3.getResult().integer;
            if (val3 != 10) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_is_empty() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorIsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result1");
            boolean val3 = variableValue3.getResult().boolean_value;

            ExpressionValue variableValue4 = vm.getDataEnvironment().getVariableValue("result2");
            boolean val4 = variableValue4.getResult().boolean_value;
            if (val3 != true || val4 != false) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_empty() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result1");
            boolean val3 = variableValue3.getResult().boolean_value;

            ExpressionValue variableValue4 = vm.getDataEnvironment().getVariableValue("result2");
            boolean val4 = variableValue4.getResult().boolean_value;
            if (val3 != true || val4 != true) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_copy() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorCopy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_get_from_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorGetFromFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_get_from_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorGetFromEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 14 || val2 != 12 || val3 != 13) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_remove_from_front() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorRemoveFromFront.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 13 || val2 != 12 || val3 != 14) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_remove_from_end() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorRemoveFromEnd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;

            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("result2");
            int val2 = variableValue2.getResult().integer;

            ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("result3");
            int val3 = variableValue3.getResult().integer;
            if (val1 != 14 || val2 != 13 || val3 != 12) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_set_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorSetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;
            if (val1 != 6) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_get_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorGetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("result1");
            int val1 = variableValue1.getResult().integer;
            if (val1 != 0) {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_pass_vector_over_max_size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.PASS + "VectorOverMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        }
        vm.stop();
    }

    /********************************************************************************/
    @Test
    public void test_fail_Array_Of_Multiple_Template_Types() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOfMultipleTemplateTypes.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
    }

    @Test
    public void test_fail_Array_Of_Type_Integer_Action_Set_Get_Integer_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOfTypeIntegerActionSetGetIntegerObject.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
    }

    @Test
    public void test_fail_Array_Of_Type_Boolean_Action_Set_Get_Boolean_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOfTypeBooleanActionSetGetBooleanObject.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
    }

    @Test
    public void test_fail_Array_Of_Type_Text_Action_Set_Get_Text_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOfTypeTextActionSetGetTextObject.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
    }

    @Test
    public void test_fail_Array_Of_Type_Number_Action_Set_Get_Number_Object_Template() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOfTypeNumberActionSetGetNumberObject.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
    }

    @Test
    public void test_fail_Array_Over_Max_Size() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ARRAYS + CompilerTestSuite.FAIL + "ArrayOverMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()) {
            fail();
        }
        vm.blockRun();

        if (!vm.getExceptions().hasExceptions()) {
            fail();
        }
        vm.stop();
    }
}
