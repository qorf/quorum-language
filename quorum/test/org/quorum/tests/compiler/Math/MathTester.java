/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.Math;

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

//    @Test
//    public void test_AbsoluteValue_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "AbsoluteValue.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if(result != 1.0)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_AbsoluteValue_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "AbsoluteValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
    }


//    @Test
//    public void test_Cosine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Cosine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.87758)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_Cosine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Cosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.87758"));
    }
     
//    @Test
//    public void test_HyperbolicCosine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicCosine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 1.12763)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_HyperbolicCosine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.12763"));
    }
    
//    @Test
//    public void test_HyperbolicSine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicSine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.5211)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_HyperbolicSine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.5211"));
    }
    
//    @Test
//    public void test_HyperbolicTangent_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicTangent.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.46212)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_HyperbolicTangent_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "HyperbolicTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.46212"));
    }
    
//    @Test
//    public void test_InverseCosine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseCosine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 1.0472)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_InverseCosine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0472"));
    }
    
//    @Test
//    public void test_InverseHyperbolicCosine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicCosine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 1.31696)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_InverseHyperbolicCosine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicCosine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.31696"));
    }

//    @Test
//    public void test_InverseHyperbolicSine_pass_execute() {
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicSine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.88137)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_InverseHyperbolicSine_pass_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.88137"));
    }

//    @Test
//    public void test_InverseHyperbolicTangent_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicTangent.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.54931)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_InverseHyperbolicTangent_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseHyperbolicTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.54931"));
    }

//    @Test
//    public void test_InverseSine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseSine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.5236)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_InverseSine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseSine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.5236"));
    }

//    @Test
//    public void test_InverseTangent_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseTangent.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.46365)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_InverseTangent_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "InverseTangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.46365"));
    }

//    @Test
//    public void test_Logarithm_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Logarithm.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.69897)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_Logarithm_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Logarithm.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.69897"));
    }

//    @Test
//    public void test_NaturalLogarithm_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "NaturalLogarithm.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 1.60944)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_NaturalLogarithm_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "NaturalLogarithm.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.60944"));
    }

//    @Test
//    public void test_Sine_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Sine.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.47943)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_Sine_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Sine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.47943"));
    }

//    @Test
//    public void test_SquareRoot_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "SquareRoot.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 3.0)
//        {
//            fail();
//        }
//    }

    @Test
    public void test_SquareRoot_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "SquareRoot.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.0"));
    }
    
//    @Test
//    public void test_Tangent_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Tangent.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 0.5463)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_Tangent_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "Tangent.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.5463"));
    }

//    @Test
//    public void test_Power_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RaiseToPower.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if( result != 9.0)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_Power_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RaiseToPower.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("9.0"));
    }

//    @Test
//    public void test_Round_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUp.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if(result != 3.0)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_Round_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUp.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.0"));
    }

//    @Test
//    public void test_roundToDecimal_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUpToDecimal.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if(result != 3.0)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_roundToDecimal_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundUpToDecimal.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.0"));
    }

//    @Test
//    public void test_roundDown_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDown.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if(result != 3.0)
//        {
//            fail();
//        }
//    }   
   
    @Test
    public void test_roundDown_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDown.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.0"));
    }
    
//    @Test
//    public void test_roundDownToDecimal_pass_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDownToDecimal.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        double result = variableValue.getResult().number;
//        if(result != 3.5)
//        {
//            fail();
//        }
//    }
    
    @Test
    public void test_roundDownToDecimal_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.MATH + CompilerTestSuite.PASS + "RoundDownToDecimal.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.5"));
    }
}
