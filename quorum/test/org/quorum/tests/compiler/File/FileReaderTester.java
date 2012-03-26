/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.File;

import java.io.File;
import org.quorum.execution.RunResult;
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
 * @author Jeff Wilson
 */
public class FileReaderTester {
    private QuorumVirtualMachine vm;
    public FileReaderTester() {
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
    public void testOpenForRead_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "OpenForRead.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
    
    @Test
    public void testClose_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "Close.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();        
    }
    
    @Test
    public void testRead_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this is a single line"));
        assert(r.getLine(1).equals("line 1"));
        assert(r.getLine(2).equals("line 2"));
        assert(r.getLine(3).equals("line 3"));
        assert(r.getLine(4).equals("line 4"));
        assert(r.getLine(5).equals("..."));
        assert(r.getLine(6).equals("line n"));
    }
    
    @Test
    public void testReadAmount_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadAmount.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this"));
        assert(r.getLine(1).equals(" is"));
        assert(r.getLine(2).equals("lin"));
        assert(r.getLine(3).equals("e 1"));
    }
    
    @Test
    public void testReadLine_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this is a single line"));
        assert(r.getLine(1).equals("line 1"));
        assert(r.getLine(2).equals("line 2"));
    }
    
    @Test
    public void testReadLines_bytecode() {
        // NOTE: BROKEN due to problem in Error.quorum - analyze later.
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadLines.quorum"));
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
    public void testIsAtEndOfFile_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "IsAtEndOfFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
        assert(r.getLine(2).equals("false"));
        assert(r.getLine(3).equals("true"));
        assert(r.getLine(4).equals("true"));
    }
}