/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.implementation;


import org.quorum.tests.compiler.CompilerTestSuite;
import java.net.URL;
import org.quorum.execution.ExpressionValue;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * A test class for the Quorum Virtual Machine.
 * 
 * @author Andreas Stefik
 */
public class QuorumVirtualMachineTest {
    private QuorumVirtualMachine vm;
    public QuorumVirtualMachineTest() {
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

//    @Test
//    public void test_pass_Basic(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Basic.quorum")), false);
//    }

    @Test
    public void test_pass_Basic_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Basic.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        int a = variableValue.getResult().integer;
        if(a != 4) {
            fail();
        }
    }

    @Test
    public void test_pass_Array_get_execute(){
        synchronized (this) {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "ArrayGet.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
            vm.blockRun();
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
            int a = variableValue.getResult().integer;
            if(a != 1) {
                fail();
            }
        }
    }
    //ImplicitCastAssignmentBooleanNumber.quorum
    

//    @Test
//    public void test_pass_objectToPrimitiveCasting_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "objectToPrimitiveCasting.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("iIsJ");
//        boolean b = variableValue.getResult().boolean_value;
//        if(b != true) {
//            fail();
//        }
//    }

//    @Test
//    public void test_pass_primitiveToObjectCasting_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "primitiveToObjectCasting.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("jIsI");
//        boolean b = variableValue.getResult().boolean_value;
//        if(b != true) {
//            fail();
//        }
//    }


//    @Test
//    public void test_pass_numberEqNumText_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "numberEqNumText.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//    }
//
//    @Test
//    public void test_pass_numberEqNumTextWrapped_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "numberEqNumTextWrapped.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//    }

//    @Test
//    public void test_pass_BooleanTest(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "BooleanTest.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_class(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "class.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_ClassNoName(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "ClassNoName.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_comboExpression(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "comboExpression.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_DefaultClass(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "DefaultClass.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_expressions(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "expressions.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_expressions2(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "expressions2.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_Factorial(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Factorial.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_FactorialFunction(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "FactorialFunction.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_functionParameters(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "functionParameters.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_gcd(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "gcd.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_if(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "if.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_if_else(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "if_else.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_IntegerTest(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "IntegerTest.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_loops(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "loops.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_methods(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "methods.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_NumberTest(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "NumberTest.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_operationOrder(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "operationOrder.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_recursion(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "recursion.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_reference(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "reference.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_Simple_If_Else_False(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Simple_If_Else_False.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_Simple_if_false(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Simple_if_false.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_Simple_if_true(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "Simple_if_true.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_TextTest(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "TextTest.quorum")), false);
//    }
//
//    @Test
//    public void test_pass_TypeChecker_Tester(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PASS + "TypeChecker_Tester.quorum")), false);
//    }
//
//    @Test
//    public void test_fail_class(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "class.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_comboExpression(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "comboExpression.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_DefaultClass(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "DefaultClass.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_expressions(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "expressions.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_functionParameter(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "functionParameter.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_gcd(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "gcd.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_if(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "if.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_ifelse(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "ifelse.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_loops(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "loops.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_methods(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "methods.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_operationOrder(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "operationOrder.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_recursion(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "recursion.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_reference(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "reference.quorum")), true);
//    }
//
//    @Test
//    public void test_fail_ScopeTest(){
//        test(CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FAIL + "ScopeTest.quorum")), true);
//    }

    



}
