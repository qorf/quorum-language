/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.Math;

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
public class MathTester {
    private QuorumVirtualMachine vm;
    public MathTester() {
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
    public void test_AbsoluteValue_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "AbsoluteValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if(result != 1.0)
        {
            fail();
        }
    }

    // author James Daniel

     @Test
    public void test_Cosine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Cosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.87758)
        {
            fail();
        }
    }

    @Test
    public void test_HyperbolicCosine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 1.12763)
        {
            fail();
        }
    }

    @Test
    public void test_HyperbolicSine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.5211)
        {
            fail();
        }
    }

    @Test
    public void test_HyperbolicTangent_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.46212)
        {
            fail();
        }
    }

    @Test
    public void test_InverseCosine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 1.0472)
        {
            fail();
        }
    }

    @Test
    public void test_InverseHyperbolicCosine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 1.31696)
        {
            fail();
        }
    }

    @Test
    public void test_InverseHyperbolicSine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.88137)
        {
            fail();
        }
    }

    @Test
    public void test_InverseHyperbolicTangent_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.54931)
        {
            fail();
        }
    }

    @Test
    public void test_InverseSine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.5236)
        {
            fail();
        }
    }

    @Test
    public void test_InverseTangent_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.46365)
        {
            fail();
        }
    }

    @Test
    public void test_Logarithm_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Logarithm.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.69897)
        {
            fail();
        }
    }

    @Test
    public void test_NaturalLogarithm_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "NaturalLogarithm.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 1.60944)
        {
            fail();
        }
    }

    @Test
    public void test_Sine_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Sine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.47943)
        {
            fail();
        }
    }

    @Test
    public void test_SquareRoot_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "SquareRoot.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 3.0)
        {
            fail();
        }
    }

    @Test
    public void test_Tangent_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Tangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 0.5463)
        {
            fail();
        }
    }

    @Test
    public void test_Power_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RaiseToPower.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if( result != 9.0)
        {
            fail();
        }
    }

    @Test
    public void test_Round_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUp.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if(result != 3.0)
        {
            fail();
        }
    }

   @Test
    public void test_roundToDecimal_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUpToDecimal.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if(result != 3.0)
        {
            fail();
        }
    }

    @Test
    public void test_roundDown_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDown.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if(result != 3.0)
        {
            fail();
        }
    }   
   
    @Test
    public void test_roundDownToDecimal_pass(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDownToDecimal.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        double result = variableValue.getResult().number;
        if(result != 3.5)
        {
            fail();
        }
    }
}
