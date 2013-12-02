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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
        {
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
        {
//            System.out.println("Quorum: " + r.getLine(count));
//            System.out.println("Java:   " + result.get(count));
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
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
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
}
