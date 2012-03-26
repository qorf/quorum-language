/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.File;

import org.quorum.execution.ExpressionValue;
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
    
    @Test
    public void testOpenForRead_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "OpenForRead.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        }
    }
    
    @Test
    public void testClose_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "Close.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        }   
    }
    
    @Test
    public void testRead_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            
            assert(singleLine.equals("this is a single line"));
            assert(multiLine.equals("line 1\nline 2\nline 3\nline 4\n...\nline n"));
        }
    }
    
    @Test
    public void testReadAmount_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadAmount.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String singleLine2 = vm.getDataEnvironment().getVariableValue("singleLine2").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            String multiLine2 = vm.getDataEnvironment().getVariableValue("multiLine2").getResult().text;
            
            assert(singleLine.equals("this"));
            assert(singleLine2.equals(" is"));
            assert(multiLine.equals("lin"));
            assert(multiLine2.equals("e 1"));
        }
    }
    
    @Test
    public void testReadLine_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            String multiLine2 = vm.getDataEnvironment().getVariableValue("multiLine2").getResult().text;
            
            assert(singleLine.equals("this is a single line"));
            assert(multiLine.equals("line 1"));
            assert(multiLine2.equals("line 2"));
        }
    }
    
    @Test
    public void testReadLines_execute() {
        // NOTE: BROKEN due to problem in Error.quorum - analyze later.
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "ReadLines.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean firstReadOK = vm.getDataEnvironment().getVariableValue("firstReadOK").getResult().boolean_value;
            boolean secondReadOK = vm.getDataEnvironment().getVariableValue("secondReadOK").getResult().boolean_value;

            assert(firstReadOK);
            assert(secondReadOK);
        }
    }
    
    @Test
    public void testIsAtEndOfFile_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILEREADER + CompilerTestSuite.PASS + "IsAtEndOfFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean emptyAtEOF = vm.getDataEnvironment().getVariableValue("emptyAtEOF").getResult().boolean_value;
            boolean singleStartAtEOF = vm.getDataEnvironment().getVariableValue("singleStartAtEOF").getResult().boolean_value;
            boolean singleLastAtEOF = vm.getDataEnvironment().getVariableValue("singleLastAtEOF").getResult().boolean_value;
            boolean singleAfterReadLineEOF = vm.getDataEnvironment().getVariableValue("singleAfterReadLineEOF").getResult().boolean_value;
            
            assert(emptyAtEOF);
            assert(singleStartAtEOF);
            assert(singleLastAtEOF);
            assert(singleAfterReadLineEOF);
        }
    }
}