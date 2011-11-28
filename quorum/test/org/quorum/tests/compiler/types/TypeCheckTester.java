/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.types;

import org.quorum.execution.RunResult;
import org.quorum.execution.ExpressionValue;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author astefik
 */
public class TypeCheckTester {
    private QuorumVirtualMachine vm;
    public TypeCheckTester() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    // Tests for things that should pass start here

    @Test
    public void test_pass_CastFieldIntObjDest_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldIntObjDest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_CastFieldIntObjSource_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjSource.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldIntObjSource_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjSource.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_CastFieldIntObjSourceDest_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjSourceDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldIntObjSourceDest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldIntObjSourceDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_CastFieldObjIntDest_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        boolean t = variableValue.getResult().boolean_value;
        if(t!=true) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldObjIntDest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_pass_CastFieldObjIntSource_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntSource.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        boolean t = variableValue.getResult().boolean_value;
        if(t!=true) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldObjIntSource_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntSource.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_pass_CastFieldObjIntSourceDest_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntSourceDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        boolean t = variableValue.getResult().boolean_value;
        if(t!=true) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastFieldObjIntSourceDest_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastFieldObjIntSourceDest.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_pass_CastLocalVarIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastLocalVarIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastLocalVarIntObj_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastLocalVarIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_CastParamIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastParamIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastParamIntObj_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastParamIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_CastParamObjInt_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastParamObjInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        int t = variableValue.getResult().integer;
        if(t!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_CastParamObjInt_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "CastParamObjInt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_ImpAssignBoolObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBoolObjBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBoolObjBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBoolObjBoolObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBooleanBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("b");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBooleanBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBoolObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("b");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBooleanBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBooleanBoolean_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBoolean.quorum");
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile(files[0], files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_pass_ImpAssignBooleanBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBooleanWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignBooleanBooleanWrapped_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignBooleanBooleanWrapped.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_pass_ImpAssignIntObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntObjIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntObjIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntObjIntObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntegerIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntegerIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerIntObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntegerInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntegerInteger_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerInteger.quorum");
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerInteger.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile(new File("ImpAssignIntegerInteger.class"), files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("5"));
    }
    
    @Test
    public void test_pass_ImpAssignIntegerIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerIntegerWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignIntegerIntegerWrapped_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignIntegerIntegerWrapped.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("5"));
    }
    
    @Test
    public void test_pass_ImpAssignNumObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumObjNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumObjNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumObjNumObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberIntObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberInteger_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberInteger.quorum");
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberInteger.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile(new File("ImpAssignNumberInteger.class"), files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("5.0"));
    }
    
    @Test
    public void test_pass_ImpAssignNumberIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberIntegerWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberIntegerWrapped_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberIntegerWrapped.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("5.0"));
    }
    
    @Test
    public void test_pass_ImpAssignNumberNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
                vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberNumber_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumber.quorum");
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumber.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile(new File("ImpAssignNumberNumber.class"), files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7.5"));
    }
    
    @Test
    public void test_pass_ImpAssignNumberNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumberWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a!=7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignNumberNumberWrapped_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignNumberNumberWrapped.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7.5"));
    }
    
    @Test
    public void test_pass_ImpAssignTextObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextObjTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignTextObjTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextObjTextObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignTextText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignTextText_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextText.quorum");
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile(new File("ImpAssignTextText.class"), files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("t"));
    }
    
    @Test
    public void test_pass_ImpAssignTextTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextTextWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignTextTextWrapped_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextTextWrapped.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("t"));
    }
    
    @Test
    public void test_pass_ImpAssignTextTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("txt");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ImpAssignTextTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ImpAssignTextTextObjWrapped.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("txt");
        String a = variableValue.getResult().text;
        if(a.compareTo("t")!=0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBoolObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBoolObjBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignBooleanTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignBooleanTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean a = variableValue.getResult().boolean_value;
        if(a!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntObjIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=1) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=1) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=7) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=7) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignIntegerTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignIntegerTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumObjNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 1.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 1.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 5.0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignNumberTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignNumberTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextBoolObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("true") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextBoolean.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("true") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextIntObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("5") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextInteger.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("5") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextNumObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("7.5") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("7.5") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextText.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("t");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ExpAssignTextObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ExpAssignTextObjTextObj.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxBoolean_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxBoolean.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        boolean a = variableValue.getResult().boolean_value;
        if(a != false) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxBooleanSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxBooleanSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        boolean a = variableValue.getResult().boolean_value;
        if(a != false) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxInteger_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxInteger.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a != 5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxIntegerSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxIntegerSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a != 5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxNumber_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxNumber.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxNumberSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxNumberSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxText_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxText.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxTextSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTextSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxBoolObj_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxBoolObj.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        boolean a = variableValue.getResult().boolean_value;
        if(a != true) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxBoolObjSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxBoolObjSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        boolean a = variableValue.getResult().boolean_value;
        if(a != true) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxIntObj_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxIntObj.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a != 5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxIntObjSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxIntObjSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        int a = variableValue.getResult().integer;
        if(a != 5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxNumObj_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxNumObj.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxNumObjSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxNumObjSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        double a = variableValue.getResult().number;
        if(a != 7.5) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxTextObj_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTextObj.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoboxTextObjSemiWrapped_execute(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTextObjSemiWrapped.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoboxTempClass.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("x");
        String a = variableValue.getResult().text;
        if(a.compareTo("text") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_ReturningIntegerInPlaceOfNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "ReturningIntegerInPlaceOfNumber.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("n");
        double a = variableValue.getResult().number;
        if(a != 4) {
            fail();
        }
    }

    @Test
    public void test_pass_AutoBoxingAndMethodOverloading_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "AutoBoxingAndMethodOverloading.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
    }

    @Test
    public void test_pass_InstanceTypeChildOfChild_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "InstanceTypeChildOfChild.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "B.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "C.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "D.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_InstanceTypeDirectChild_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "InstanceTypeDirectChild.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "C.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "B.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "A.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "D.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=false) {
            fail();
        }
    }

    @Test
    public void test_pass_InstanceTypeCurrent_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "InstanceTypeCurrent.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "C.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "B.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "A.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "D.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_InstanceTypeDirectParent_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "InstanceTypeDirectParent.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "C.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "B.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "A.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "D.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_InstanceTypeParentOfParent_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "InstanceTypeParentOfParent.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "C.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "B.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "A.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "D.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_BooleanCompare_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "BooleanCompare.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("final");
        boolean a = variableValue.getResult().boolean_value;
        if(!a) {
            fail();
        }
    }

    @Test
    public void test_pass_IntegerCompare_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "IntegerCompare.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("final");
        boolean a = variableValue.getResult().boolean_value;
        if(!a) {
            fail();
        }
    }

    @Test
    public void test_pass_NumberCompare_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "NumberCompare.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("final");
        boolean a = variableValue.getResult().boolean_value;
        if(!a) {
            fail();
        }
    }

    @Test
    public void test_pass_TextCompare_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.PASS + "TextCompare.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("final");
        boolean a = variableValue.getResult().boolean_value;
        if(!a) {
            fail();
        }
    }

/*
 *
 *
 *          Tests of things that should fail start here
 *
 *
 *
 */

    
    @Test
    public void test_fail_ImpAssignBoolObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjIntObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBoolObjTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBoolObjTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanIntObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignBooleanTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignBooleanTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntObjTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntObjTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignIntegerTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignIntegerTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjIntObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumObjTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumObjTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberTextObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberTextObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignNumberTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignNumberTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextIntObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjBoolObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjBoolObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjBooleanWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjBooleanWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObIntObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjIntObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjIntegerWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjIntegerWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjNumObjWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjNumObjWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjNumberWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjNumberWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ImpAssignTextObjTextWrapped_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ImpAssignTextObjTextWrapped.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }


    @Test
    public void test_fail_ExpAssignBoolObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignBoolObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignBoolObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignIntObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignIntObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignNumObjTextObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignNumObjTextObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjBoolObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjBoolObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjBoolean_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjBoolean.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjIntObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjIntObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjInteger_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjInteger.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjNumObj_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjNumObj.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjNumber_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjNumber.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ExpAssignTextObjText_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "ExpAssignTextObjText.quorum"));
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_InstanceTypeUnrelatedTypes_execute(){
        File[] files = new File[3];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "InstanceTypeUnrelatedTypes.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.TYPE_CHECKER + CompilerTestSuite.FAIL + "D.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

}