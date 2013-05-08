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
    
//    @Test
//    public void test_pass_average_of_five_numbers_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "AverageOfFiveNumbers.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        double a = variableValue.getResult().number;
//        if(a != 337.546) {
//            fail();
//        }
//    }

    @Test
    public void test_pass_average_of_five_numbers_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "AverageOfFiveNumbers.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("337.546"));
    }

//    @Test
//    public void test_pass_call_object_action_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CallObjectAction.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
//        String toPrint = variableValue.getResult().text;
//        if(toPrint.compareTo("I went") != 0) {
//            fail();
//        }
//    }

    @Test
    public void test_pass_call_object_action_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CallObjectAction.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("I went"));
    }
    
//    @Test
//    public void test_pass_factorial_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Factorial.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("b");
//        int b = variableValue.getResult().integer;
//        if(b != 120) {
//            fail();
//        }
//    }

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

//    @Test
//    public void test_pass_fibonacci_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Fibonacci.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 55) {
//            fail();
//        }
//    }

    @Test
    public void test_pass_fibonacci_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Fibonacci.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("55"));
    }
    
//    @Test
//    public void test_pass_gcd_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "GCD.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 5) {
//            fail();
//        }
//    }
    
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
    
//    @Test
//    public void test_pass_multiple_nested_function_calls_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCalls.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 65536) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_multiple_nested_function_calls_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCalls.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65536"));
    }
    
//    @Test
//    public void test_pass_multiple_nested_function_calls_double_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsDouble.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        double a = variableValue.getResult().number;
//        if(a != 65536.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_multiple_nested_function_calls_double_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsDouble.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65536.0"));
    }
    
//    @Test
//    public void test_pass_multiple_nested_function_calls_in_solo_call_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsInSoloCall.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
//        int a = variableValue.getResult().integer;
//        if(a != 65536) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_multiple_nested_function_calls_in_solo_call_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsInSoloCall.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65536"));
    }
    
//    @Test
//    public void test_pass_multiple_nested_function_calls_with_assignments_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsWithAssignments.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 65536) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_multiple_nested_function_calls_with_assignments_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsWithAssignments.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65536"));
    }
    
//    @Test
//    public void test_pass_multiple_nested_function_calls_with_assignments_double_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsWithAssignmentsDouble.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        double a = variableValue.getResult().number;
//        if(a != 65536.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_multiple_nested_function_calls_with_assignments_double_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "MultipleNestedFunctionCallsWithAssignmentsDouble.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65536.0"));
    }
    
//    @Test
//    public void test_pass_nested_function_call_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "NestedFunctionCall.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 300) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_nested_function_call_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "NestedFunctionCall.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("300"));
    }
    
//    @Test
//    public void test_pass_nested_function_call_double_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "NestedFunctionCallDouble.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        double a = variableValue.getResult().number;
//        if(a != 300.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_nested_function_call_double_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "NestedFunctionCallDouble.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("300.0"));
    }
    
//    @Test
//    public void test_simple_nested_actions_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleNestedActions.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 125) {
//            fail();
//        }
//    }

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

//    @Test
//    public void test_simple_one_action_change_parameter_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionChangeParameter.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        int a = variableValue.getResult().integer;
//        if(a != 5) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_change_parameter_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionChangeParameter.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }

//    @Test
//    public void test_simple_one_action_concatenate_text_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionConcatenateText.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
//        String a = variableValue.getResult().text;
//        if(a.compareTo("Hello World!") != 0) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_concatenate_text_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionConcatenateText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Hello World!"));
    }
    
//    @Test
//    public void test_simple_one_action_in_control_expression_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionInControlExpression.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        boolean result = variableValue.getResult().boolean_value;
//        if(result) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_in_control_expression_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionInControlExpression.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_simple_one_action_no_params_no_return_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsNoReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
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
        
        assert(r.getLine(0).equals("5"));
    }

//    @Test
//    public void test_simple_one_action_no_params_return_boolean_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnBoolean.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("d");
//        boolean d = variableValue.getResult().boolean_value;
//        if(d) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_no_params_return_boolean_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
//    @Test
//    public void test_simple_one_action_no_params_return_int_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnInt.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
//        int i = variableValue.getResult().integer;
//        if(i != 5) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_no_params_return_int_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }

//    @Test
//    public void test_simple_one_action_no_params_return_number_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnNumber.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("d");
//        double d = variableValue.getResult().number;
//        if(d != 5.0) {
//            fail();
//        }
//    }

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
        
//    @Test
//    public void test_simple_one_action_no_params_return_number_concatenate_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnNumberConcatenate.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double d = variableValue.getResult().number;
//        if(d != 24.0) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_no_params_return_number_concatenate_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnNumberConcatenate.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("36.0"));
    }
    
//    @Test
//    public void test_simple_one_action_no_params_return_text_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnText.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("d");
//        String d = variableValue.getResult().text;
//        if(d.compareTo("5.0") != 0) {
//            fail();
//        }
//    }

    @Test
    public void test_simple_one_action_no_params_return_text_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsReturnText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5.0"));
    }
    
//    @Test
//    public void test_simple_one_action_two_params_return_int_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionTwoParamsReturnInt.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("addedNumbers");
//        int addedNumbers = variableValue.getResult().integer;
//        if(addedNumbers != 15) {
//            fail();
//        }
//    }

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
    
//    @Test
//    public void test_two_actions_different_parameters_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "TwoActionsDifferentParameters.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("b");
//        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("c");
//        boolean b = variableValue1.getResult().boolean_value;
//        boolean c = variableValue2.getResult().boolean_value;
//        if(!b || c) {
//            fail();
//        }
//    }

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
 
//    @Test
//    public void test_pass_VTableObjectTest_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "VTableObjectTest.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("index");
//        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("index2");
//        int index = variableValue.getResult().integer;
//        int index2 = variableValue2.getResult().integer;
//        if(index !=0 || index2 !=1) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_VTableObjectTest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "VTableObjectTest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
        assert(r.getLine(1).equals("1"));
        
    }
    
//    @Test
//    public void test_pass_simple_action_pass_object_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleActionPassObject.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("message");
//        String message = variableValue.getResult().text;
//        if(!message.equals("hello world")) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_simple_action_pass_object_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleActionPassObject.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("hello world"));        
    }
    
//    @Test
//    public void test_pass_simple_cast_argument_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleCastArgument.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
//        double k = variableValue.getResult().number;
//        if(k != 1.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_simple_cast_argument_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleCastArgument.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));   
    }
    
//    @Test
//    public void test_pass_cast_middle_argument_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastMiddleArgument.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
//        double k = variableValue.getResult().number;
//        if(k != 1.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_cast_middle_argument_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastMiddleArgument.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));   
    }
    
//    @Test
//    public void test_pass_cast_last_argument_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastLastArgument.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
//        double k = variableValue.getResult().number;
//        if(k != 1.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_cast_last_argument_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastLastArgument.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));   
    }
    
//    @Test
//    public void test_pass_cast_return_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastReturn.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
//        double k = variableValue.getResult().number;
//        if(k != 1.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_cast_return_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));   
    }
    
//    @Test
//    public void test_pass_cast_return_value_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastReturnValue.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
//        double k = variableValue.getResult().number;
//        if(k != 4.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_pass_cast_return_value_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "CastReturnValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4.0"));   
    }
    
    @Test
    public void test_pass_field_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "Field.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));   
    }
        
    @Test
    public void test_pass_field_cast_parameter_value_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "FieldCastParameter.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));   
    }
        
    @Test
    public void test_pass_field_cast_return_value_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "FieldCastReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4.0"));   
    }
}
