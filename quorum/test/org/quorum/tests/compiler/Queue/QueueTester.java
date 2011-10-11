/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler.Queue;

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
 *
 * @author Melissa Stefik
 */
public class QueueTester {
    private QuorumVirtualMachine vm;
    public QueueTester() {
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
    public void test_Add(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a = variableValue.getResult().text;
        if(!a.equals("One")){
            fail();
        }
    }

    @Test
    public void test_Push(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Push.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a = variableValue.getResult().text;
        if(!a.equals("OneTwoThreeFourFive")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Clear(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a = variableValue.getResult().text;
        if(!a.equals("OneTwoThreeFourFive")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Contains(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Contains.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
        boolean a1 = variableValue.getResult().boolean_value;
        variableValue = vm.getDataEnvironment().getVariableValue("bool2");
        boolean a2 = variableValue.getResult().boolean_value;
        variableValue = vm.getDataEnvironment().getVariableValue("bool3");
        boolean a3 = variableValue.getResult().boolean_value;
        variableValue = vm.getDataEnvironment().getVariableValue("bool4");
        boolean a4 = variableValue.getResult().boolean_value;
        if(a1 != true){
            fail();
        }
        if(a2 != false){
            fail();
        }
        if(a3 != true){
            fail();
        }
        if(a4 != false){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Copy(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a = variableValue.getResult().text;
        if(!a.equals("OneTwoThreeFourFive")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_IsEmpty(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
        boolean bool1 = variableValue.getResult().boolean_value;
        variableValue = vm.getDataEnvironment().getVariableValue("bool2");
        boolean bool2 = variableValue.getResult().boolean_value;
        if(bool1 != false){
            fail();
        }
        if(bool2 != true){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Iterator(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Iterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a1 = variableValue.getResult().text;
        if(!a1.equals("OneTwoThreeFourFiveSixSeven")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Peek(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Peek.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a1 = variableValue.getResult().text;
        if(!a1.equals("One")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_RemoveAll(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a1 = variableValue.getResult().text;
        if(!a1.equals("TwoFourFiveSix")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Pop(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Pop.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a1 = variableValue.getResult().text;
        if(!a1.equals("One")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_RemoveValue(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"RemoveValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        String a1 = variableValue.getResult().text;
        if(!a1.equals("OneTwoFourFive")){
            fail();
        }
        vm.stop();
    }
    @Test
    public void test_Size(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.QUEUE + CompilerTestSuite.PASS +"Size.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a != 5){
            fail();
        }
        vm.stop();
    }

}