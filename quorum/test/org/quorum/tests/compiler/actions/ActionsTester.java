/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.actions;

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
    public void test_simple_one_action_no_params_no_return(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "SimpleOneActionNoParamsNoReturn.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 555) {
            fail();
        }
    }

    @Test
    public void test_pass_factorial_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "factorial.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("pass");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_gcd_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.ACTIONS + CompilerTestSuite.PASS + "gcd.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("pass");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

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
}
