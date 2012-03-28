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
public class FileRandomAccessTester {
    private QuorumVirtualMachine vm;
    public FileRandomAccessTester() {
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
    public void testOpenForRandomAccess_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "OpenForRandomAccess.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
    
    @Test
    public void testClose_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Close.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();     
        
        // should just succeed--no output, no errors.
    }
    
    @Test
    public void testRead_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this is a single line"));
        assert(r.getLine(1).equals("this is a single line"));
        assert(r.getLine(2).equals("line 1"));
        assert(r.getLine(3).equals("line 2"));
        assert(r.getLine(4).equals("line 3"));
        assert(r.getLine(5).equals("line 4"));
        assert(r.getLine(6).equals("..."));
        assert(r.getLine(7).equals("line n"));
    }
    
    @Test
    public void testReadAmount_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadAmount.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this"));
        assert(r.getLine(1).equals(" is"));
        assert(r.getLine(2).equals("this"));
        assert(r.getLine(3).equals("lin"));
        assert(r.getLine(4).equals("e 1"));
    }
    
    @Test
    public void testReadLine_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("this is a single line"));
        assert(r.getLine(1).equals("this is a single line"));
        assert(r.getLine(2).equals("line 1"));
        assert(r.getLine(3).equals("line 2"));
    }
    
    @Test
    public void testReadLines_bytecode() {
        // NOTE: BROKEN due to problem in Error.quorum - analyze later.
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadLines.quorum"));
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
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "IsAtEndOfFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
        assert(r.getLine(2).equals("false"));
    }
    
    @Test
    public void testWrite_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Write.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void testWriteLine_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "WriteLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
    
    @Test
    public void testSetPosition_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "SetPosition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2.0"));
    }
    
    @Test
    public void testGetPosition_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "GetPosition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("21.0"));
        assert(r.getLine(1).equals("2.0"));
    }
    
    @Test
    public void testGoToBeginning_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "GoToBeginning.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.0"));
    }
    
    @Test
    public void testReadThirdChar_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadThirdChar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("e"));
    }
    
    @Test
    public void testOpenForRandomAccess_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "OpenForRandomAccess.quorum"));
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
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Close.quorum"));
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
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String anotherSingleLine = vm.getDataEnvironment().getVariableValue("anotherSingleLine").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            
            assert(singleLine.equals("this is a single line"));
            assert(anotherSingleLine.equals("this is a single line"));
            assert(multiLine.equals("line 1\nline 2\nline 3\nline 4\n...\nline n"));
        }
    }
    
    @Test
    public void testReadAmount_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadAmount.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String singleLine2 = vm.getDataEnvironment().getVariableValue("singleLine2").getResult().text;
            String singleLine3 = vm.getDataEnvironment().getVariableValue("singleLine3").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            String multiLine2 = vm.getDataEnvironment().getVariableValue("multiLine2").getResult().text;
            assert(singleLine.equals("this"));
            assert(singleLine2.equals(" is"));
            assert(singleLine3.equals("this"));
            assert(multiLine.equals("lin"));
            assert(multiLine2.equals("e 1"));
        }
    }
    
    @Test
    public void testReadLine_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String singleLine = vm.getDataEnvironment().getVariableValue("singleLine").getResult().text;
            String singleLineAgain = vm.getDataEnvironment().getVariableValue("singleLineAgain").getResult().text;
            String multiLine = vm.getDataEnvironment().getVariableValue("multiLine").getResult().text;
            String multiLine2 = vm.getDataEnvironment().getVariableValue("multiLine2").getResult().text;
            assert(singleLine.equals("this is a single line"));
            assert(singleLineAgain.equals("this is a single line"));
            assert(multiLine.equals("line 1"));
            assert(multiLine2.equals("line 2"));
        }
    }
    
    @Test
    public void testReadLines_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadLines.quorum"));
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
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "IsAtEndOfFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean atEOF = vm.getDataEnvironment().getVariableValue("atEOF").getResult().boolean_value;
            boolean atEOFAfterSeek = vm.getDataEnvironment().getVariableValue("atEOFAfterSeek").getResult().boolean_value;
            boolean EOFAtBeginning = vm.getDataEnvironment().getVariableValue("EOFAtBeginning").getResult().boolean_value;
            
            assert(atEOF);
            assert(atEOFAfterSeek);
            assert(!EOFAtBeginning);
        }
    }
    
    @Test
    public void testWrite_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "Write.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean success = vm.getDataEnvironment().getVariableValue("success").getResult().boolean_value;
            assert(success);
        }
    }
    
    @Test
    public void testWriteLine_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "WriteLine.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean success = vm.getDataEnvironment().getVariableValue("success").getResult().boolean_value;
            assert(success);
        }
    }
    
    @Test
    public void testSetPosition_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "SetPosition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            double position = vm.getDataEnvironment().getVariableValue("position").getResult().number;
            assert(position == 2);
        }
    }
    
    @Test
    public void testGetPosition_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "GetPosition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            double position = vm.getDataEnvironment().getVariableValue("position").getResult().number;
            double newPosition = vm.getDataEnvironment().getVariableValue("newPosition").getResult().number;
            assert(position == 21);
            assert(newPosition == 2);
        }
    }
    
    @Test
    public void testGoToBeginning_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "GoToBeginning.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            double position = vm.getDataEnvironment().getVariableValue("position").getResult().number;
            assert(position == 0);
        }
    }
    
    @Test
    public void testReadThirdChar_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILERANDOMACCESS + CompilerTestSuite.PASS + "ReadThirdChar.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String thirdChar = vm.getDataEnvironment().getVariableValue("thirdChar").getResult().text;
            assert(thirdChar.equals("e"));
        }
    }
}