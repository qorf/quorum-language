/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler.Table;

import org.quorum.execution.RunResult;
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
 *
 * @author Melissa Stefik
 */
public class TableTester {
    private QuorumVirtualMachine vm;
    public TableTester() {
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
    public void test_Add_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 4 || r2 != 3 || r3 != 2 || r4 != 1)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Add_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Add.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
        assert(r.getLine(1).equals("3"));
        assert(r.getLine(2).equals("2"));
        assert(r.getLine(3).equals("1"));
    }
    
    @Test
    public void test_Set_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Set.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Set_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Set.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_Get_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Get.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }

    @Test
    public void test_Get_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Get.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }

    @Test
    public void test_GetMaxNumberOfColumns_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetMaxNumberOfColumns.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;

            if( r1 != 10)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetMaxNumberOfColumns_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetMaxNumberOfColumns.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
    }
    
    @Test
    public void test_GetMaxNumberOfRows_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetMaxNumberOfRows.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;

            if( r1 != 10)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetMaxNumberOfRows_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetMaxNumberOfRows.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
    }
    
    @Test
    public void test_SetAutoResize_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetAutoResize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;

            if(r1){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_SetAutoResize_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetAutoResize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
    }
    
    @Test
    public void test_GetAutoResize_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetAutoResize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;

            if(!r1){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetAutoResize_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetAutoResize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_GetSizeOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetSizeOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;

            if( r1 != 4){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetSizeOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetSizeOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
    
    @Test
    public void test_GetNumberOfRows_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetNumberOfRows.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;

            if( r1 != 1){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetNumberOfRows_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetNumberOfRows.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
    }
    
    @Test
    public void test_SetSize_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 3 || r2 != 5 || r3 != 5 || r4 != 5){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_SetSize_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3"));
        assert(r.getLine(1).equals("5"));
        assert(r.getLine(2).equals("5"));
        assert(r.getLine(3).equals("5"));
    }
    
    @Test
    public void test_SetMaxSize_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;

            if( r1 != 5 || r2 != 3){
                fail();
            }
        }
        vm.stop();
    } 
    
    @Test
    public void test_SetMaxSize_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "SetMaxSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("3"));
    } 
    
    @Test
    public void test_AddRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_AddRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_GetRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_AddToEndOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddToEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_AddToEndOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddToEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_AddToFrontOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddToFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_AddToFrontOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "AddToFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_IsEmpty_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            boolean r2 = variableValue.getResult().boolean_value;

            if( !r1 || r2){
                fail();
            }
        }
        vm.stop();
    } 
    
    @Test
    public void test_IsEmpty_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "IsEmpty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }     
    
    @Test
    public void test_Empty_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            boolean r2 = variableValue.getResult().boolean_value;

            if( !r1 || !r2){
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Empty_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
    }    
    
    @Test
    public void test_RemoveAt_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveAt_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveAt.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }    
    
    @Test
    public void test_RemoveFromFrontOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveFromFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveFromFrontOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveFromFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }    
    
    @Test
    public void test_RemoveFromEndOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveFromEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 4 || r2 != 3 || r3 != 2 || r4 != 1)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveFromEndOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveFromEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
        assert(r.getLine(1).equals("3"));
        assert(r.getLine(2).equals("2"));
        assert(r.getLine(3).equals("1"));
    }    
    
    @Test
    public void test_Remove_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Remove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( !r1 || r2 != 1 || r3 != 4 || r4 != 2)
            {
                fail();
            }
        }
        vm.stop();
    }
        
    @Test
    public void test_Remove_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Remove.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("1"));
        assert(r.getLine(2).equals("4"));
        assert(r.getLine(3).equals("2"));
    }    
    
    @Test
    public void test_RemoveAll_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;

            if( !r1 || r2 != 1 || r3 != 4)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveAll_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "RemoveAll.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("1"));
        assert(r.getLine(2).equals("4"));
    }    
    
    @Test
    public void test_Has_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Has.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            boolean r1 = variableValue.getResult().boolean_value;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            boolean r2 = variableValue.getResult().boolean_value;

            if( !r1 || r2 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Has_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Has.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }
    
    @Test
    public void test_GetRowIterator_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetRowIterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetRowIterator_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetRowIterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_GetFromFrontOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetFromFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 1 || r3 != 1 || r4 != 1 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetFromFrontOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetFromFrontOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("1"));
        assert(r.getLine(2).equals("1"));
        assert(r.getLine(3).equals("1"));
    }
    
    @Test
    public void test_GetFromEndOfRow_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetFromEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 4 || r2 != 4 || r3 != 4 || r4 != 4 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetFromEndOfRow_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "GetFromEndOfRow.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
        assert(r.getLine(1).equals("4"));
        assert(r.getLine(2).equals("4"));
        assert(r.getLine(3).equals("4"));
    }
    
    @Test
    public void test_Copy_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("r1");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r2");
            int r2 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r3");
            int r3 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("r4");
            int r4 = variableValue.getResult().integer;

            if( r1 != 1 || r2 != 2 || r3 != 3 || r4 != 4 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Copy_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.TABLE + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("2"));
        assert(r.getLine(2).equals("3"));
        assert(r.getLine(3).equals("4"));
    }
}