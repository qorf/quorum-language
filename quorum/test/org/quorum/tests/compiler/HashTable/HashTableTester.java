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
}
