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
    public void test_repeat_from_to_const_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToConstConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int x = variableValue.getResult().integer;
        if(x != 11) {
            fail();
        }
    }
    

    @Test
    public void test_repeat_from_to_const_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToConstConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("11"));
    }

    @Test
    public void test_repeat_from_to_const_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToConstVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int x = variableValue.getResult().integer;
        if(x != 11) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_const_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToConstVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("11"));
    }

    @Test
    public void test_repeat_from_to_nested_const_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedConstConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int toPrint = variableValue.getResult().integer;
        if(toPrint != 101) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_nested_const_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedConstConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("101"));
    }
    
    @Test
    public void test_repeat_from_to_nested_const_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedConstVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int toPrint = variableValue.getResult().integer;
        if(toPrint != 11) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_nested_const_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedConstVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("11"));
    }
    
    @Test
    public void test_repeat_from_to_nested_var_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedVarConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int toPrint = variableValue.getResult().integer;
        if(toPrint != 101) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_nested_var_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedVarConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("101"));
    }
    
    @Test
    public void test_repeat_from_to_nested_var_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedVarVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int toPrint = variableValue.getResult().integer;
        if(toPrint != 56) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_nested_var_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToNestedVarVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("56"));
    }
    
    @Test
    public void test_repeat_from_to_var_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToVarConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int x = variableValue.getResult().integer;
        if(x != 11) {
            fail();
        }
    }
  
    @Test
    public void test_repeat_from_to_var_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToVarConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("11"));
    }
    
    @Test
    public void test_repeat_from_to_var_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToVarVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int x = variableValue.getResult().integer;
        if(x != 11) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_from_to_var_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatFromToVarVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("11"));
    }
    
    @Test
    public void test_repeat_nested_from_times_until_while_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedFromTimesUntilWhile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int i = variableValue.getResult().integer;
        if(i != 82) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_nested_from_times_until_while_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedFromTimesUntilWhile.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("82"));
    }
    
    @Test
    public void test_repeat_nested_times_from_until_while_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedTimesFromUntilWhile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int i = variableValue.getResult().integer;
        if(i != 82) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_nested_times_from_until_while_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedTimesFromUntilWhile.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("82"));
    }
    
    @Test
    public void test_repeat_nested_until_while_times_from_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedUntilWhileTimesFrom.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int i = variableValue.getResult().integer;
        if(i != 82) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_nested_until_while_times_from_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedUntilWhileTimesFrom.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("82"));
    }
    
    @Test
    public void test_repeat_nested_while_until_times_from_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedWhileUntilTimesFrom.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("toPrint");
        int i = variableValue.getResult().integer;
        if(i != 82) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_nested_while_until_times_from_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatNestedWhileUntilTimesFrom.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("82"));
    }
    
    @Test
    public void test_repeat_times_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesConst.quorum"));
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
    public void test_repeat_times_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }

    @Test
    public void test_repeat_times_nested_const_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedConstConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 25) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_times_nested_const_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedConstConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("25"));
    }
    
    @Test
    public void test_repeat_times_nested_const_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedConstVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 25) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_times_nested_const_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedConstVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("25"));
    }
    
    @Test
    public void test_repeat_times_nested_var_const_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedVarConst.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 25) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_times_nested_var_const_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedVarConst.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("25"));
    }
    
    @Test
    public void test_repeat_times_nested_var_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedVarVar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 25) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_times_nested_var_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesNestedVarVar.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("25"));
    }
    
    @Test
    public void test_repeat_times_var_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesVar.quorum"));
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
    public void test_repeat_times_var_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatTimesVar.quorum"));

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
    public void test_repeat_while_and_until_with_paran_not_execute()
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
        
        if(x != 101 || y != 101 || b || !c || startString.compareTo("done.") != 0) {
            fail();
        }
    }
    
    @Test
    public void test_repeat_while_and_until_with_paran_not_bytecode()
    {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "RepeatWhileAndUntilWithParenNot.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();

        assert(r.getLine(0).equals("101"));
        assert(r.getLine(1).equals("101"));
        assert(r.getLine(2).equals("false"));
        assert(r.getLine(3).equals("true"));
        assert(r.getLine(4).equals("done."));
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
    
    @Test
    public void test_simple_repeat_while_execute(){
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
    public void test_simple_repeat_while_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.LOOPS + CompilerTestSuite.PASS + "SimpleRepeatWhile.quorum"));
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
}
