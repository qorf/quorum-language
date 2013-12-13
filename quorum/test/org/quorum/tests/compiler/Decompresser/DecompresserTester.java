/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.Decompresser;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.execution.RunResult;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;

/**
 *
 * @author Nicole Blumhorst
 */
public class DecompresserTester {
    private QuorumVirtualMachine vm;
    public DecompresserTester() {
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
    public void test_DecompressGZip_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.DECOMPRESSER + CompilerTestSuite.PASS + "DecompressGZip.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();

        if (!r.isSuccessful())
            fail();
        
        //add check to see if file was made
        File output = CompilerTestSuite.getQuorumFile(CompilerTestSuite.DECOMPRESSER + CompilerTestSuite.RESOURCES + "gzipfile.txt");
        assert(output.exists());
    }
    
    @Test
    public void test_DecompressZip_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.DECOMPRESSER + CompilerTestSuite.PASS + "DecompressZip.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        
        if (!r.isSuccessful())
            fail();
        
        //add check to see if file was made
        File output = CompilerTestSuite.getQuorumFile(CompilerTestSuite.DECOMPRESSER + CompilerTestSuite.RESOURCES + "zipfile.txt");
        assert(output.exists());
    }
}
