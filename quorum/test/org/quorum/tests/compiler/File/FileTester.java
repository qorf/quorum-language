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
 * @author Elliot
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
        
        assert(r.getLine(0).equals("true"));   
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
        assert(r.getLine(1).equals("false"));
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
        
        assert(r.getLine(0).equals("."));
        assert(r.getLine(1).equals("/Users/"));
    }

    /**
     * Test of SetPathNative method, of class File.
     */
    @Test
    public void testSetAbsolutePath_pass_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "SetAbsolutePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        String s = File.separator;
        assert(r.getLine(0).equals(s + "Users" + s + "jeff"));
        assert(r.getLine(1).equals("C:" + s + "Program Files" + s));
        assert(r.getLine(2).equals(s + "home" + s + "jeff" + s + "file.txt"));
    }
    
    /**
     * Test of SetPathNative method, of class File.
     */
    @Test
    public void testSetAbsolutePath_fail_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.FAIL + "SetAbsolutePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (r.isSuccessful())
            fail();
    }
    
    /**
     * Test of SetPathNative method, of class File.
     */
    @Test
    public void testSetRelativePath_pass_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "SetRelativePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        String s = File.separator;
        assert(r.getLine(0).equals("." + s + "Users" + s + "jeff"));
        assert(r.getLine(1).equals("Program Files" + s));
        assert(r.getLine(2).equals(".." + s + "home" + s + "jeff" + s + "file.txt"));
    }
    
    /**
     * Test of SetPathNative method, of class File.
     */
    @Test
    public void testSetRelativePath_fail_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.FAIL + "SetRelativePath.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (r.isSuccessful())
            fail();
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
        assert(r.getLine(1).equals("build"));
        assert(r.getLine(2).equals("file.exe"));
        assert(r.getLine(3).equals("Programs"));
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
        // TODO: Complete me
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
        // TODO: Complete me
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
        // TODO: Complete me
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
        // TODO: Complete me
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
        // TODO: Complete me
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
        // TODO: Complete me
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
        // TODO: Complete me
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "Write.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("test text"));
    }
}
