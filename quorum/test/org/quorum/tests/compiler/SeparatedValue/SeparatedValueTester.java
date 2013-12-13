/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.SeparatedValue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.execution.RunResult;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Nicole Blumhorst
 */
public class SeparatedValueTester {
    private QuorumVirtualMachine vm;
    public SeparatedValueTester() {
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
    public void test_Simple_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.SEPARATED_VALUE + CompilerTestSuite.PASS + "Simple.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();

        if (!r.isSuccessful())
            fail();
        
        int numLines = r.getNumberOfLines();
        assert(numLines == 4);      
        
        assert(r.getLine(0).equals("Rows: 26"));
        assert(r.getLine(1).equals("Columns: 50"));
        assert(r.getLine(2).equals("IsConsistent: true"));
        assert(r.getLine(3).equals("Separator: ,"));
        
//        for (int count = 0; count < numLines; count++)
//        {
//            System.out.println(r.getLine(count));
//        }
    }
    
    @Test
    public void test_BarSeparator_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.SEPARATED_VALUE + CompilerTestSuite.PASS + "BarSeparator.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();

        if (!r.isSuccessful())
            fail();
        
        int numLines = r.getNumberOfLines();
        assert(numLines == 4);      
        
        assert(r.getLine(0).equals("Rows: 240"));
        assert(r.getLine(1).equals("Columns: 50"));
        assert(r.getLine(2).equals("IsConsistent: true"));
        assert(r.getLine(3).equals("Separator: |"));
        
    }
    
    @Test
    public void test_Inconsistent_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.SEPARATED_VALUE + CompilerTestSuite.PASS + "Inconsistent.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();

        if (!r.isSuccessful())
            fail();
        
        int numLines = r.getNumberOfLines();
        assert(numLines == 4);      
        
        assert(r.getLine(0).equals("Rows: 26"));
        assert(r.getLine(1).equals("Columns: 50"));
        assert(r.getLine(2).equals("IsConsistent: false"));
        assert(r.getLine(3).equals("Separator: ,"));
        
    }
    
    @Test
    public void test_LargeFile_pass_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.SEPARATED_VALUE + CompilerTestSuite.PASS + "LargeFile.quorum"));        
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();

        if (!r.isSuccessful())
            fail();
        
        int numLines = r.getNumberOfLines();
        assert(numLines == 4);      
        
        assert(r.getLine(0).equals("Rows: 45482"));
        assert(r.getLine(1).equals("Columns: 18"));
        assert(r.getLine(2).equals("IsConsistent: true"));
        assert(r.getLine(3).equals("Separator: ,"));
        
    }
}
