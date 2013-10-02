/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.other;

import org.quorum.execution.RunResult;
import java.io.File;
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
 * Unit tests for cases that cannot be classified or are difficult to
 * classify are defined here.
 *
 * @author Kim Slattery
 */
public class OtherTester {

    private QuorumVirtualMachine vm;
    
    public OtherTester() {
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
    public void test_fail_AssignmentToUndeclaredVariable_run(){
        String directory = CompilerTestSuite.OTHER + CompilerTestSuite.FAIL;

        try {
            CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(directory + "AssignmentToUndeclaredVariable.quorum"));
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

//    @Test
//    public void test_pass_ThisSet_execute(){
//        File[] files = new File[1];
//        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ThisSet.quorum");
//
//        CompilerTestSuite.build(files);
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        boolean a = variableValue.getResult().boolean_value;
//        if(a == true) {
//            fail();
//        }
//    }
    
//    @Test
//    public void test_not_not() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NotNot.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue trueAVal = vm.getDataEnvironment().getVariableValue("trueA");
//        ExpressionValue trueBVal = vm.getDataEnvironment().getVariableValue("trueB");
//        boolean trueA = trueAVal.getResult().boolean_value;
//        boolean trueB = trueBVal.getResult().boolean_value;
//        
//        if (!trueA && !trueB) {
//            fail();
//        }
//    }
    
    @Test
    public void test_not_not_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NotNot.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_frame_error_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "FrameError.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Worked."));
    }
    
//    @Test
//    public void test_parameters_with_same_names_as_ivars() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ParametersWithSameNameAsIVars.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue iVarVal = vm.getDataEnvironment().getVariableValue("i");
//        ExpressionValue vVarVal = vm.getDataEnvironment().getVariableValue("v");
//        ExpressionValue iValueVarVal = vm.getDataEnvironment().getVariableValue("i_value");
//        ExpressionValue iMeValueVarVal = vm.getDataEnvironment().getVariableValue("i_me_value");
//        int i = iVarVal.getResult().integer;
//        int v = vVarVal.getResult().integer;
//        int i_value = iValueVarVal.getResult().integer;
//        int i_me_value = iMeValueVarVal.getResult().integer;
//        
//        if (i != 0 || v != 0 || i_value != 0 || i_me_value != 0) {
//            fail();
//        }
//    }
    
//    @Test
//    public void test_pass_recursion() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Recursion.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue fibResultAVal = vm.getDataEnvironment().getVariableValue("fibResultA");
//        ExpressionValue fibResultBVal = vm.getDataEnvironment().getVariableValue("fibResultB");
//        int fibResultA = fibResultAVal.getResult().integer;
//        int fibResultB = fibResultBVal.getResult().integer;
//        
//        if (fibResultA != 8 || fibResultB != 8) {
//            fail();
//        }
//    }
    
    
    @Test
    public void test_pass_recursion_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Recursion.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("8"));
        assert(r.getLine(1).equals("8"));
    }
    
    @Test
    public void test_pass_print_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Print.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
        
    @Test
    public void test_pass_me_assignment_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "MeAssignmentAndPrint.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10.0"));
    }

//    @Test
//    public void test_cast_with_addition() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastWithAddition.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue noteVal = vm.getDataEnvironment().getVariableValue("note");
//        int note = noteVal.getResult().integer;
//        
//        if (note != 72) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_with_addition_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastWithAddition.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("72"));
    }
    
//    @Test
//    public void test_cast_function_result_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResult.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_function_result_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_cast_function_result_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResultAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_function_result_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResultAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_cast_nested_function_result_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastNestedFunctionResult.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_nested_function_result_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastNestedFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_cast_expression_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastExpression.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_expression_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastExpression.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_cast_triple_nested_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastTripleNested.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_triple_nested_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastTripleNested.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_cast_quad_nested_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastQuadNested.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 5) {
//            fail();
//        }
//    }
    
    @Test
    public void test_cast_quad_nested_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastQuadNested.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
//    @Test
//    public void test_reverse_autobox_text_number_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxTextNumber.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        double k = val.getResult().number;
//        
//        if (k != 62.9) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_text_number_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxTextNumber.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("62.9"));
    }
    
//    @Test
//    public void test_function_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "FunctionAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"IThinkItWorked.com".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_function_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "FunctionAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("IThinkItWorked.com"));
    }
    
//    @Test
//    public void test_nested_function_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedFunctionAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"EvenThisCaseWorked.com".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_nested_function_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedFunctionAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("EvenThisCaseWorked.com"));
    }
    
//    @Test
//    public void test_really_nested_function_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReallyNestedFunctionAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"EvenThisHorribleCaseWorked.com".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_really_nested_function_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReallyNestedFunctionAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("EvenThisHorribleCaseWorked.com"));
    }
    
//    @Test
//    public void test_two_param_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "TwoParamAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"Hello".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_two_param_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "TwoParamAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Hello"));
    }
    
//    @Test
//    public void test_two_param_nested_autobox_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "TwoParamNestedAutoBox.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"Hello".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_two_param_nested_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "TwoParamNestedAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Hello"));
    }
    
//    @Test
//    public void test_reverse_autobox_text_boolean_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxTextBoolean.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        boolean k = val.getResult().boolean_value;
//        
//        if (k != true) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_text_boolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxTextBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
//    @Test
//    public void test_reverse_autobox_number_integer_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberInteger.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 62) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_number_integer_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberInteger.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("62"));
    }
    
//    @Test
//    public void test_reverse_autobox_number_text_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberText.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"62.9".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_number_text_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("62.9"));
    }
    
//    @Test
//    public void test_reverse_autobox_number_boolean_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberBoolean.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        boolean k = val.getResult().boolean_value;
//        
//        if (k != true) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_number_boolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxNumberBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
//    @Test
//    public void test_reverse_autobox_integer_number_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerNumber.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        double k = val.getResult().number;
//        
//        if (k != 42.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_integer_number_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerNumber.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("42.0"));
    }
    
//    @Test
//    public void test_reverse_autobox_integer_text_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerText.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"42".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_integer_text_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("42"));
    }
    
//    @Test
//    public void test_reverse_autobox_integer_boolean_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerBoolean.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        boolean k = val.getResult().boolean_value;
//        
//        if (k != true) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_integer_boolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxIntegerBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
//    @Test
//    public void test_reverse_autobox_boolean_integer_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanInteger.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        int k = val.getResult().integer;
//        
//        if (k != 1) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_boolean_integer_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanInteger.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
    }
    
//    @Test
//    public void test_reverse_autobox_boolean_number_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanNumber.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        double k = val.getResult().number;
//        
//        if (k != 1.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_boolean_number_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanNumber.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
    }
    
//    @Test
//    public void test_reverse_autobox_boolean_text_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanText.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"true".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_reverse_autobox_boolean_text_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ReverseAutoBoxBooleanText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
//    @Test
//    public void test_nested_reverse_autobox_function_return_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedReverseAutoBoxFunctionReturn.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("result");
//        String k = val.getResult().text;
//        
//        if (!"e".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_nested_reverse_autobox_function_return_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedReverseAutoBoxFunctionReturn.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("e"));
    }
    
//    @Test
//    public void test_strange_registry_error_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "StrangeRegistryError.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("hotelPrice");
//        double k = val.getResult().number;
//        
//        if (k != 0.0) {
//            fail();
//        }
//    }
    
    @Test
    public void test_strange_registry_error_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "StrangeRegistryError.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult t = CompilerTestSuite.runQuorumFile();
        if (!t.isSuccessful())
            fail();
        
        assert(t.getLine(0).equals("0.0"));
    }
        
//    @Test
//    public void test_nested_reverse_autobox_multi_param_function_return_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedReverseAutoBoxMultiParamFunctionReturn.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("result");
//        String k = val.getResult().text;
//        
//        if (!"h".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_nested_reverse_autobox_multi_param_function_return_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedReverseAutoBoxMultiParamFunctionReturn.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("h"));
    }
    
//    @Test
//    public void test_nested_function_autobox_alternating_params_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedFunctionAutoBoxAlternatingParams.quorum"));
//
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
//        String k = val.getResult().text;
//        
//        if (!"Hello".equals(k)) {
//            fail();
//        }
//    }
    
    @Test
    public void test_nested_function_autobox_alternating_params_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NestedFunctionAutoBoxAlternatingParams.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Hello"));
    }
    
    @Test
    public void test_ChangeConstantThroughInheritance_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ClassWithConstants.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ChangeConstantThroughInheritance.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ConstantObjectsReassign_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ConstantObjectsReassign.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ConstantObjectsReassignInConstructor_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ConstantObjectsReassignInConstructor.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ConstantPrimitivesReassign_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ConstantPrimitivesReassign.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ConstantPrimitivesReassignInConstructor_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ConstantPrimitivesReassignInConstructor.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignClassConstantWithMethod_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignClassConstantWithMethod.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignConstantFromMethodCallingMethod_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantChanger.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ClassWithConstants.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantFromMethodCallingMethod.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignConstantFromOtherClass_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ClassWithConstants.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantFromOtherClass.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignConstantFromOtherClassThroughMethod_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ClassWithConstants.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantFromOtherClassThroughMethod.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignConstantTemplatedType_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantTemplatedType.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ReassignConstantUsingMe_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantUsingMe.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_UndefinedVariable_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "UndefinedVariable.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
        
    @Test
    public void test_ReassignConstantUsingMeInPrivateMethod_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.FAIL + "ReassignConstantUsingMeInPrivateMethod.quorum");
        
        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
    
    @Test
    public void test_ConstantObjects_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantObjects.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("9"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_ConstantObjectsInConstructor_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantObjectsInConstructor.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
        assert(r.getLine(1).equals("false"));
    }
    
    @Test
    public void test_ConstantPrimitives_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantPrimitives.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();

        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2.0"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("true"));
        assert(r.getLine(4).equals("4"));
        assert(r.getLine(5).equals("5.0"));
        assert(r.getLine(6).equals("6"));
        assert(r.getLine(7).equals("false"));
    }
    
    @Test
    public void test_ConstantPrimitivesInConstructor_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantPrimitivesInConstructor.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();

        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2.0"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("false"));
    }
    
    @Test
    public void test_ConstantTemplatedType_bytecode(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ConstantTemplatedTypes.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();

        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("1"));
        assert(r.getLine(4).equals("2"));
        assert(r.getLine(5).equals("3"));
        assert(r.getLine(6).equals("1.0"));
        assert(r.getLine(7).equals("2.0"));
        assert(r.getLine(8).equals("3.0"));
    }
}
