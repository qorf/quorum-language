/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.ExtensibleMarkup;

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
public class ExtensibleMarkupIBMTester {
    private QuorumVirtualMachine vm;
    public ExtensibleMarkupIBMTester() {
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
    public void test_ibm01v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm01v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm01v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
//        System.out.println("Quorum count: " + r.getNumberOfLines() + "\tJava count: " + result.size());
        
        assert(numLines == result.size());
        for (int count = 0; count < numLines; count++)
        {
//            System.out.println("Quorum: " + r.getLine(count));
//            System.out.println("Java:   " + result.get(count));
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm02v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm02v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm02v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm03v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm03v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm03v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }    
    
    @Test
    public void test_ibm09v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm09v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm09v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm09v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm09v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm09v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm09v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm09v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm09v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm09v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm09v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm09v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm09v05_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm09v05.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm09v05.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
        @Test
    public void test_ibm10v05_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v05.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v05.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v06_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v06.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v06.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v07_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v07.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v07.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm10v08_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm10v08.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm10v08.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm14v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm14v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm14v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm14v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm14v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm14v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm14v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm14v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm14v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm15v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm15v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm15v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm15v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm15v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm15v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm15v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm15v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm15v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm15v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm15v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm15v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm16v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm16v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm16v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm16v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm16v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm16v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm16v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm16v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm16v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm17v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm17v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm17v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }@Test
    public void test_ibm18v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm18v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm18v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm19v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm19v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm19v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    ///////everything above this line works!
    
    @Test
    public void test_ibm20v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm20v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm20v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm20v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm20v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm20v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm21v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm21v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm21v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }    
    
    @Test
    public void test_ibm22v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v05_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v05.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v05.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());        
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v06_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v06.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v06.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm22v07_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm22v07.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm22v07.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm23v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
        @Test
    public void test_ibm23v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm23v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm23v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm23v05_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v05.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v05.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm23v06_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm23v06.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm23v06.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm24v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm24v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm24v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm24v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm24v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm24v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm25v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm25v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm25v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm25v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm25v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm25v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm25v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm25v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm25v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm25v04_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm25v04.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm25v04.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm26v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm26v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm26v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm27v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm27v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm27v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm27v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm27v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm27v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm27v03_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm27v03.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm27v03.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }@Test
    public void test_ibm28v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm28v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm28v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm29v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm29v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm29v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm29v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm29v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm29v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm30v01_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm30v01.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm30v01.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_ibm30v02_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "IBMTests\\ibm30v02.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "AnalyticsMarkupListener.quorum");
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "IBMTests\\ibm30v02.xml"), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
            fail();
         } catch (SAXException  e) {
            fail();
         } catch (IOException e) {
            fail();
         }
        
        int numLines = r.getNumberOfLines();
        assert(numLines == result.size());         
        for (int count = 0; count < numLines; count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
}
