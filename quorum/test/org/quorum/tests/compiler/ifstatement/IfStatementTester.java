/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.ifstatement;

import org.quorum.execution.RunResult;
import org.quorum.execution.ExpressionValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for if statements are to be added here.
 * 
 * @author Melissa Stefik
 */
public class IfStatementTester {
    private QuorumVirtualMachine vm;
    public IfStatementTester() {
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
    public void test_if_else_if_else_multiple_conditions_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElseMultipleConditions.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("c");
        int c = variableValue.getResult().integer;
        if(c != 5) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_else_multiple_conditions_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElseMultipleConditions.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_if_else_if_else_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElse_Const.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 7) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_else_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElse_Const.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_if_else_if_else_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElse_Var.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 7) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_else_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfElse_Var.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_if_else_if_statement_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement_Const.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 20) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_statement_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement_Const.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("20"));
    }
    
    @Test
    public void test_if_else_if_statement_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement_Var.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 10) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_statement_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement_Var.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
    }
    
    @Test
    public void test_if_nested_in_else_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInElse.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 7) {
            fail();
        }

    }
    
    @Test
    public void test_if_nested_in_else_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInElse.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_if_nested_in_else_if_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInElseIf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 7) {
            fail();
        }

    }
    
    @Test
    public void test_if_nested_in_else_if_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInElseIf.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_if_nested_in_if_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInIf.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 7) {
            fail();
        }

    }
    
    @Test
    public void test_if_nested_in_if_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfNestedInIf.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_if_statement_one_condition_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Const.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        boolean a = variableValue.getResult().boolean_value;
        if(a) {
            fail();
        }

    }
    
    @Test
    public void test_if_statement_one_condition_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Const.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_if_one_condition_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Equal.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Equal.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_if_one_condition_gt_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_GT.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_gt_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_GT.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_if_one_condition_gte_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_GTE.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_gte_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_GTE.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_if_one_condition_lt_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_LT.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_lt_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_LT.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    
    @Test
    public void test_if_one_condition_lte_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_LTE.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_lte_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_LTE.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    
    @Test
    public void test_if_one_condition_notequal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_NotEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean x = variableValue.getResult().boolean_value;
        if(!x) {
            fail();
        }

    }
    
    @Test
    public void test_if_one_condition_notequal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_NotEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_if_statement_one_condition_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Var.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        boolean a = variableValue.getResult().boolean_value;
        if(a) {
            fail();
        }

    }
    
    @Test
    public void test_if_statement_one_condition_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition_Const.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_if_with_else_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfWithElse_Const.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 6) {
            fail();
        }

    }
    
    @Test
    public void test_if_with_else_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfWithElse_Const.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("6"));
    }
    
    @Test
    public void test_if_with_else_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfWithElse_Var.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 6) {
            fail();
        }

    }
    
    @Test
    public void test_if_with_else_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfWithElse_Var.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("6"));
    }
}
