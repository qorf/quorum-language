/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.loops;

import org.quorum.execution.RunResult;
import org.quorum.execution.ExpressionValue;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for loops are to be added here.
 * 
 * @author Melissa Stefik
 */
public class LoopsTester {
    private QuorumVirtualMachine vm;
    public LoopsTester() {
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
    public void test_simple_repeat_while(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "SimpleRepeatWhile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_while_and_until_with_paran_not()
    {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileAndUntilWithParenNot.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableX = vm.getDataEnvironment().getVariableValue("x");
        ExpressionValue variableY = vm.getDataEnvironment().getVariableValue("y");
        ExpressionValue variableB = vm.getDataEnvironment().getVariableValue("b");
        ExpressionValue variableC = vm.getDataEnvironment().getVariableValue("c");
        ExpressionValue variableText = vm.getDataEnvironment().getVariableValue("startString");
        int x = variableX.getResult().integer;
        int y = variableY.getResult().integer;
        boolean b = variableB.getResult().boolean_value;
        boolean c = variableC.getResult().boolean_value;
        String startString = variableText.getResult().text;
        
        if(x != 101 || y != 101 || b || !c) {
            fail();
        }
    }
    @Test
    public void test_repeat_while_boolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_boolean_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_repeat_while_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_repeat_while_greater_than_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileGreaterThan.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_greater_than_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileGreaterThan.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_repeat_while_greater_than_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileGreaterThanEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 4) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_greater_than_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileGreaterThanEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
    
    @Test
    public void test_repeat_while_less_than_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileLessThan.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_less_than_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileLessThan.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_repeat_while_less_than_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileLessThanEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 6) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_less_than_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileLessThanEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("6"));
    }
    
    @Test
    public void test_repeat_while_not_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileNotEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_while_not_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileNotEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
        @Test
    public void test_repeat_until_boolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_boolean_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_repeat_until_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_repeat_until_greater_than_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilGreaterThan.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 6) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_greater_than_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilGreaterThan.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("6"));
    }
    
    @Test
    public void test_repeat_until_greater_than_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilGreaterThanEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_greater_than_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilGreaterThanEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_repeat_until_less_than_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilLessThan.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_less_than_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilLessThan.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_repeat_until_less_than_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilLessThanEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_less_than_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilLessThanEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_repeat_until_not_equal_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilNotEqual.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_until_not_equal_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatUntilNotEqual.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
    
    @Test
    public void test_repeat_times_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimes.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 5) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_times_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimes.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    @Test
    public void test_repeat_zero_times_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatZeroTimes.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x != 0) {
            fail();
        }

    }
    
    @Test
    public void test_repeat_zero_times_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatZeroTimes.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
    }
}
