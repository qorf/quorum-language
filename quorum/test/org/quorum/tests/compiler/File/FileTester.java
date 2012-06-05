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
public class FileTester {
    private QuorumVirtualMachine vm;
    public FileTester() {
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


    /**
     * Test of GetLastModifiedNative method, of class File.
     */
    @Test
    public void testGetLastModifiedDate_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetLastModifiedDate.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
    }

 
    /**
     * Test of GetDirectoryListingNative method, of class File.
     */
    @Test
    public void testGetDirectoryListing_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetDirectoryListing.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }

    /**
     * Test of GetParentDirectoryNative method, of class File.
     */
    @Test
    public void testGetParentDirectory_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetParentDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of GetPathNative method, of class File.
     */
    @Test
    public void testGetPath_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetPath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals(""));
        assert(r.getLine(1).equals("jeff"));
    }

    /**
     * Test of GetPathNative method, of class File.
     */
    @Test
    public void testGetAbsolutePath_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetAbsolutePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(!r.getLine(0).isEmpty());
    }
    
    /**
     * Test of GetWorkingDirectoryNative method, of class File.
     */
    @Test
    public void testGetWorkingDirectory_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetWorkingDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of Exists method, of class File.
     */
    @Test
    public void testExists_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Exists.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }

    /**
     * Test of IsFile method, of class File.
     */
    @Test
    public void testIsFile_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "IsFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }

    /**
     * Test of IsDirectory method, of class File.
     */
    @Test
    public void testIsDirectory_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "IsDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }

    /**
     * Test of IsHidden method, of class File.
     */
    @Test
    public void testIsHidden_bytecode() {
        // This is highly operating system specific... TODO: Test later?
    }

    /**
     * Test of GetFileName method, of class File.
     */
    @Test
    public void testGetFileName_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileName.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("."));
        assert(r.getLine(1).equals("file.exe"));
        assert(r.getLine(2).equals("Program"));
    }

    /**
     * Test of GetFileExtension method, of class File.
     */
    @Test
    public void testGetFileExtension_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileExtension.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals(""));
        assert(r.getLine(1).equals(""));
        assert(r.getLine(2).equals(""));
        assert(r.getLine(3).equals("ext"));
    }

    /**
     * Test of GetFreeDiskSpace method, of class File.
     */
    @Test
    public void testGetFreeDiskSpace_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFreeDiskSpace.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of GetTotalDiskSpace method, of class File.
     */
    @Test
    public void testGetTotalDiskSpace_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetTotalDiskSpace.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of GetFileSize method, of class File.
     */
    @Test
    public void testGetFileSize_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
    }

    /**
     * Test of Delete method, of class File.
     */
    @Test
    public void testDelete_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Delete.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of CreateDirectory method, of class File.
     */
    @Test
    public void testCreateDirectory_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "CreateDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    /**
     * Test of Move method, of class File.
     */
    @Test
    public void testMove_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Move.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }

    @Test
    public void testOpenForRead_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForRead.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("abcdef"));
    }
    
    @Test
    public void testOpenForWrite_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForWrite.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("hello world"));
    }
    
    @Test
    public void testOpenForWriteAppend_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForWriteAppend.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("hello worldzzz"));
    }
    
    @Test
    public void testOpenForRandomAccess_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForRandomAccess.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("abcdef"));
    }
    
    @Test
    public void testClose_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Close.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        // no output to check for
    }
    
    @Test
    public void testRead_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("abcdef"));
    }
    
    @Test
    public void testWrite_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Write.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("test text"));
    }
    
    /**
     * Test of GetLastModifiedNative method, of class File.
     */
    @Test
    public void testGetLastModifiedDate_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetLastModifiedDate.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean gotDateTime = vm.getDataEnvironment().getVariableValue("gotDateTime").getResult().boolean_value;
            boolean isValidDateTime = vm.getDataEnvironment().getVariableValue("isValidDateTime").getResult().boolean_value;
            
            assert(gotDateTime && isValidDateTime);
        }
    }

    /**
     * Test of GetDirectoryListingNative method, of class File.
     */
    @Test
    public void testGetDirectoryListing_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetDirectoryListing.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        }
    }

    /**
     * Test of GetParentDirectoryNative method, of class File.
     */
    @Test
    public void testGetParentDirectory_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetParentDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean hasParent = vm.getDataEnvironment().getVariableValue("hasParent").getResult().boolean_value;
            
            assert(hasParent);
        }
    }

    /**
     * Test of GetPathNative method, of class File.
     */
    @Test
    public void testGetPath_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetPath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String relativePath = vm.getDataEnvironment().getVariableValue("relativePath").getResult().text;
            String absolutePath = vm.getDataEnvironment().getVariableValue("absolutePath").getResult().text;
            
            assert(relativePath.equals(""));
            assert(absolutePath.equals("jeff"));
        }
    }

    /**
     * Test of GetPathNative method, of class File.
     */
    @Test
    public void testGetAbsolutePath_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetAbsolutePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String exactPath = vm.getDataEnvironment().getVariableValue("exactPath").getResult().text;
            
            assert(!exactPath.isEmpty());
        }
    }
    
    /**
     * Test of GetWorkingDirectoryNative method, of class File.
     */
    @Test
    public void testGetWorkingDirectory_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetWorkingDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean validWorkingDir = vm.getDataEnvironment().getVariableValue("validWorkingDir").getResult().boolean_value;
            assert(validWorkingDir);
        }
    }

    /**
     * Test of Exists method, of class File.
     */
    @Test
    public void testExists_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Exists.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean workDirExists = vm.getDataEnvironment().getVariableValue("workDirExists").getResult().boolean_value;
            boolean randomFileExists = vm.getDataEnvironment().getVariableValue("randomFileExists").getResult().boolean_value;
            
            assert(workDirExists && !randomFileExists);
        }
    }

    /**
     * Test of IsFile method, of class File.
     */
    @Test
    public void testIsFile_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "IsFile.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean workingDirIsFile = vm.getDataEnvironment().getVariableValue("workingDirIsFile").getResult().boolean_value;
            boolean runResultIsFile = vm.getDataEnvironment().getVariableValue("runResultIsFile").getResult().boolean_value;
            
            assert(!workingDirIsFile && runResultIsFile);
        }
    }

    /**
     * Test of IsDirectory method, of class File.
     */
    @Test
    public void testIsDirectory_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "IsDirectory.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            boolean workingDirIsDir = vm.getDataEnvironment().getVariableValue("workingDirIsDir").getResult().boolean_value;
            boolean runResultIsDir = vm.getDataEnvironment().getVariableValue("runResultIsDir").getResult().boolean_value;
            
            assert(workingDirIsDir && !runResultIsDir);
        }
    }

    /**
     * Test of IsHidden method, of class File.
     */
    @Test
    public void testIsHidden_execute() {
        // This is highly operating system specific... TODO: Test later?
    }

    /**
     * Test of GetFileName method, of class File.
     */
    @Test
    public void testGetFileName_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileName.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String dotFileName = vm.getDataEnvironment().getVariableValue("dotFileName").getResult().text;
            String otherName = vm.getDataEnvironment().getVariableValue("otherName").getResult().text;
            String other2Name = vm.getDataEnvironment().getVariableValue("other2Name").getResult().text;
            
            assert(dotFileName.equals("."));
            assert(otherName.equals("file.exe"));
            assert(other2Name.equals("Program"));
        }
    }

    /**
     * Test of GetFileExtension method, of class File.
     */
    @Test
    public void testGetFileExtension_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileExtension.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String dotExtension = vm.getDataEnvironment().getVariableValue("dotExtension").getResult().text;
            String relDirExtension = vm.getDataEnvironment().getVariableValue("relDirExtension").getResult().text;
            String noExtExtension = vm.getDataEnvironment().getVariableValue("noExtExtension").getResult().text;
            String hasExtExtension = vm.getDataEnvironment().getVariableValue("hasExtExtension").getResult().text;
        
            assert(dotExtension.equals(""));
            assert(relDirExtension.equals(""));
            assert(noExtExtension.equals(""));
            assert(hasExtExtension.equals("ext"));
        }
    }

    /**
     * Test of GetFreeDiskSpace method, of class File.
     */
    @Test
    public void testGetFreeDiskSpace_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFreeDiskSpace.quorum"));
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

    /**
     * Test of GetTotalDiskSpace method, of class File.
     */
    @Test
    public void testGetTotalDiskSpace_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetTotalDiskSpace.quorum"));
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

    /**
     * Test of GetFileSize method, of class File.
     */
    @Test
    public void testGetFileSize_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "GetFileSize.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            double size = vm.getDataEnvironment().getVariableValue("size").getResult().number;
            assert(size == 1);
        }
    }

    /**
     * Test of Delete method, of class File.
     */
    @Test
    public void testDelete_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Delete.quorum"));
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

    /**
     * Test of CreateDirectory method, of class File.
     */
    @Test
    public void testCreateDirectory_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "CreateDirectory.quorum"));
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

    /**
     * Test of Move method, of class File.
     */
    @Test
    public void testMove_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Move.quorum"));
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
    public void testOpenForRead_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForRead.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("abcdef"));
        }
    }
    
    @Test
    public void testOpenForWrite_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForWrite.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("hello world"));
        }
    }
    
    @Test
    public void testOpenForWriteAppend_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForWriteAppend.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("hello worldzzz"));
        }
    }
    
    @Test
    public void testOpenForRandomAccess_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "OpenForRandomAccess.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("abcdef"));
        }
    }
    
    @Test
    public void testClose_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Close.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        // no output to check for
    }
    
    @Test
    public void testRead_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Read.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("abcdef"));
        }
    }
    
    @Test
    public void testWrite_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Write.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        vm.blockRun();

        if (vm.getExceptions().hasExceptions()) {
            fail();
        } else {
            String fileContent = vm.getDataEnvironment().getVariableValue("fileContent").getResult().text;
            
            assert(fileContent.equals("test text"));
        }
    }
}
