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
    public void test_if_statement_one_condition(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean a = variableValue.getResult().boolean_value;
        if(a == false) {
            fail();
        }

    }
    
    @Test
    public void test_if_statement_one_condition_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_if_else_if(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int x = variableValue.getResult().integer;
        if(x != 40) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_statement_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfStatement.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("40"));
    }
    
    @Test
    public void test_if_else_if_multiple_conditions(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfMultipleConditions.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("c");
        int c = variableValue.getResult().integer;
        if(c != 3) {
            fail();
        }

    }
    
    @Test
    public void test_if_else_if_multiple_conditions_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfElseIfMultipleConditions.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3"));
    }
}
