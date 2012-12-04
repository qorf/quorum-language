/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.tests.compiler.HashTable;

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
public class HashTableTester {
    private QuorumVirtualMachine vm;
    public HashTableTester() {
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
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Put.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            int r1 = variableValue.getResult().integer;
            variableValue = vm.getDataEnvironment().getVariableValue("height");
            int r2 = variableValue.getResult().integer;

            if( r1 != 23 || r2 != 65 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Add_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Put.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("23"));
        assert(r.getLine(1).equals("65"));
    } 
    
    @Test
    public void test_GetKey_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            String r1 = variableValue.getResult().text;
            variableValue = vm.getDataEnvironment().getVariableValue("height");
            String r2 = variableValue.getResult().text;

            if( !r1.equalsIgnoreCase("age") || !r2.equalsIgnoreCase("height") )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetKey_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("age"));
        assert(r.getLine(1).equals("height"));
    }
    
    @Test
    public void test_GetKeyIterator_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetKeyIterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("height"));
        assert(r.getLine(1).equals("age"));
        
    }
    
    @Test
    public void test_GetValueIterator_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetValueIterator.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("65"));
        assert(r.getLine(1).equals("23"));
    }
    
    @Test
    public void test_GetSize_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("size");
            int r1 = variableValue.getResult().integer;

            if( r1 != 2)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_GetSize_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "GetSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_Empty_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("isEmpty");
            boolean r1 = variableValue.getResult().boolean_value;

            if( !r1 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Empty_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Empty.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_HasValue_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "HasValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("isEmpty");
            boolean r1 = variableValue.getResult().boolean_value;

            if( !r1 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_HasValue_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "HasValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_HasKey_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "HasKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("isEmpty");
            boolean r1 = variableValue.getResult().boolean_value;

            if( !r1 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_HasKey_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "HasKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_RemoveKey_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            int r1 = variableValue.getResult().integer;

            if( r1 != 23 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveKey_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("23"));
    }
    
    @Test
    public void test_RemoveValue_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            String r1 = variableValue.getResult().text;

            if( !r1.equalsIgnoreCase("age") )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveValue_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("age"));
    }
    
    @Test
    public void test_RemoveAllKey_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveAllKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            boolean r1 = variableValue.getResult().boolean_value;

            if( !r1 )
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveAllKey_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveAllKey.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void test_RemoveAllValue_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveAllValue.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("age");
            boolean r1 = variableValue.getResult().boolean_value;
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("isEmpty");
            boolean r2 = variableValue2.getResult().boolean_value;

            if( !r1  || r2)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_RemoveAllValue_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "RemoveAllValue.quorum"));
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
    public void test_Copy_pass_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        
        if(vm.getExceptions().hasExceptions()){
            fail();
        }else{
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("size");
            int r1 = variableValue.getResult().integer;
            ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("three");
            int r2 = variableValue2.getResult().integer;

            if( r1 != 10  || r2 != 3)
            {
                fail();
            }
        }
        vm.stop();
    }
    
    @Test
    public void test_Copy_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.HASHTABLE + CompilerTestSuite.PASS + "Copy.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
        assert(r.getLine(1).equals("3"));
    }
}
