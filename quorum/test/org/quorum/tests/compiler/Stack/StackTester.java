package org.quorum.tests.compiler.Stack;

import org.quorum.execution.RunResult;
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
 * Unit test for arrays are to be added here.
 *
 * @author Elliot Motl
 */
public class StackTester {
    private QuorumVirtualMachine vm;
    public StackTester() {
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
//    public void test_Add_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS + "Add.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a = variableValue.getResult().text;
//        if(!a.equals("FiveFourThreeTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Add_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveFourThreeTwoOne"));
    }

//    @Test
//    public void test_Push_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Push.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a = variableValue.getResult().text;
//        if(!a.equals("FiveFourThreeTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Push_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Push.quorum"));
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveFourThreeTwoOne"));
    }
    
//    @Test
//    public void test_Clear_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Empty.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a = variableValue.getResult().text;
//        if(!a.equals("FiveFourThreeTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Clear_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveFourThreeTwoOne"));
    }
    
//    @Test
//    public void test_Contains_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Contains.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
//        boolean a1 = variableValue.getResult().boolean_value;
//        variableValue = vm.getDataEnvironment().getVariableValue("bool2");
//        boolean a2 = variableValue.getResult().boolean_value;
//        variableValue = vm.getDataEnvironment().getVariableValue("bool3");
//        boolean a3 = variableValue.getResult().boolean_value;
//        variableValue = vm.getDataEnvironment().getVariableValue("bool4");
//        boolean a4 = variableValue.getResult().boolean_value;
//        if(a1 != true){
//            fail();
//        }
//        if(a2 != false){
//            fail();
//        }
//        if(a3 != true){
//            fail();
//        }
//        if(a4 != false){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Contains_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Contains.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
        assert(r.getLine(2).equals("true"));
        assert(r.getLine(3).equals("false"));
    }
    
//    @Test
//    public void test_Copy_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Copy.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a = variableValue.getResult().text;
//        if(!a.equals("FiveFourThreeTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Copy_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveFourThreeTwoOne"));
    }
    
//    @Test
//    public void test_IsEmpty_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"IsEmpty.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool1");
//        boolean bool1 = variableValue.getResult().boolean_value;
//        variableValue = vm.getDataEnvironment().getVariableValue("bool2");
//        boolean bool2 = variableValue.getResult().boolean_value;
//        if(bool1 != false){
//            fail();
//        }
//        if(bool2 != true){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_IsEmpty_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
//    @Test
//    public void test_Iterator_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Iterator.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a1 = variableValue.getResult().text;
//        if(!a1.equals("SevenSixFiveFourThreeTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Iterator_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Iterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("SevenSixFiveFourThreeTwoOne"));
    }
    
//    @Test
//    public void test_Peek_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Peek.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a1 = variableValue.getResult().text;
//        if(!a1.equals("Five")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Peek_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Peek.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Five"));
    }
    
//    @Test
//    public void test_RemoveAll_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"RemoveAll.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a1 = variableValue.getResult().text;
//        if(!a1.equals("SixFiveFourTwo")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_RemoveAll_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("SixFiveFourTwo"));
    }
    
//    @Test
//    public void test_Pop_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Pop.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a1 = variableValue.getResult().text;
//        if(!a1.equals("Six")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Pop_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Pop.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("Six"));
    }
    
//    @Test
//    public void test_RemoveValue_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"RemoveValue.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        String a1 = variableValue.getResult().text;
//        if(!a1.equals("FiveFourTwoOne")){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_RemoveValue_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"RemoveValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("FiveFourTwoOne"));
    }
    
//    @Test
//    public void test_Size_execute(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Size.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
//        int a = variableValue.getResult().integer;
//        if(a != 5){
//            fail();
//        }
//        vm.stop();
//    }
    
    @Test
    public void test_Size_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.STACK + CompilerTestSuite.PASS +"Size.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
}