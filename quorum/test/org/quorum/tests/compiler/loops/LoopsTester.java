/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.loops;

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
}
