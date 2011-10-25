/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.actions;

import org.quorum.execution.RunResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.execution.ExpressionValue;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for actions are to be added here.
 * 
 * @author Melissa Stefik
 */
public class ActionsTester {
    private QuorumVirtualMachine vm;
    public ActionsTester() {
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
    public void test_pass_factorial_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Factorial.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("b");
        int b = variableValue.getResult().integer;
        if(b != 120) {
            fail();
        }
    }

    @Test
    public void test_pass_factorial_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Factorial.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("120"));
    }
  
    @Test
    public void test_pass_gcd_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "GCD.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        int a = variableValue.getResult().integer;
        if(a != 5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_gcd_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "GCD.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_simple_nested_actions_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleNestedActions.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        int a = variableValue.getResult().integer;
        if(a != 125) {
            fail();
        }
    }

    @Test
    public void test_simple_nested_actions_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleNestedActions.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("125"));
    }
    
    @Test
    public void test_simple_one_action_no_params_no_return_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsNoReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 555) {
            fail();
        }
    }

    @Test
    public void test_simple_one_action_no_params_no_return_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsNoReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("555"));
    }

    @Test
    public void test_simple_one_action_no_params_return_int_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 5) {
            fail();
        }
    }

    @Test
    public void test_simple_one_action_no_params_return_int_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("i"));
    }

    @Test
    public void test_simple_one_action_no_params_return_number_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("d");
        double d = variableValue.getResult().number;
        if(d != 5.0) {
            fail();
        }
    }

    @Test
    public void test_simple_one_action_no_params_return_number_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5.0"));
    }
    
    @Test
    public void test_simple_one_action_two_params_return_int_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionTwoParamsReturnInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("addedNumbers");
        int addedNumbers = variableValue.getResult().integer;
        if(addedNumbers != 15) {
            fail();
        }
    }

    @Test
    public void test_simple_one_action_two_params_return_int_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionTwoParamsReturnInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("15"));
    }
     /*
    @Test
    public void test_two_actions_different_parameters_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "TwoActionsDifferentParameters.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("b");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("c");
        boolean b = variableValue1.getResult().boolean_value;
        boolean c = variableValue2.getResult().boolean_value;
        if(!b || c) {
            fail();
        }
    }

    @Test
    public void test_two_actions_different_parameters_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "TwoActionsDifferentParameters.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }
    */
    @Test
    public void test_pass_VTableObjectTest_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "VTableObjectTest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("index");
        int b = variableValue.getResult().integer;
        if(b!=0) {
            fail();
        }

        variableValue = vm.getDataEnvironment().getVariableValue("index2");
        b = variableValue.getResult().integer;
        if(b!=1) {
            fail();
        }
    }
    
    @Test
    public void test_pass_VTableObjectTest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "VTableObjectTest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        /*
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("i"));
         */
    }
}
