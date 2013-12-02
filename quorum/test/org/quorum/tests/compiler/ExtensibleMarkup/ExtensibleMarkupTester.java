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
public class ExtensibleMarkupTester {
    private QuorumVirtualMachine vm;
    public ExtensibleMarkupTester() {
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
    public void test_SimpleElement_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SimpleElement.quorum");
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
            String xml = "<?xml version=\"1.0\"?><root>test</root>";
            saxParser.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))), handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
           fail();
         } catch (SAXException  e) {
           fail();
         }catch (IOException e) {
           fail();
         }
        
        int numLines = r.getNumberOfLines();
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
        {
            assert(r.getLine(count).trim().equals(result.get(count).trim()));
        }
    }
    
    @Test
    public void test_SimpleElementFile_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SimpleElementFile.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "SimpleElementFile.xml"), handler);
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
    public void test_SimpleAttribute_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SimpleAttribute.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "SimpleAttribute.xml"), handler);
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
    public void test_NestedElements_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "NestedElements.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "NestedElements.xml"), handler);
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
    public void test_ElementsAndAttributes_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "ElementsAndAttributes.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "ElementsAndAttributes.xml"), handler);
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
    public void test_SelfClosingElements_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SelfClosingElements.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "SelfClosingElements.xml"), handler);
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
    public void test_Schema_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "Schema.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "Schema.xml"), handler);
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
    public void test_SplitValues_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SplitValues.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "SplitValues.xml"), handler);
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
    public void test_SpecialCharacters_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "SpecialCharacters.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "SpecialCharacters.xml"), handler);
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
    public void test_DoctypeDefinition_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "DoctypeDefinition.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "DoctypeDefinition.xml"), handler);
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
    public void test_DoctypeWithExternalDefinition_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "DoctypeWithExternalDefinition.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "DoctypeWithExternalDefinition.xml"), handler);
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
    public void test_DoctypeWithEntity_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "DoctypeWithEntity.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "DoctypeWithEntity.xml"), handler);
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
    public void test_DoctypeWithExternalEntity_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "DoctypeWithExternalEntity.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "DoctypeWithExternalEntity.xml"), handler);
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
    public void test_DoctypeWithExternalDefinitionAndEntity_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "DoctypeWithExternalDefinitionAndEntity.quorum");
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
            saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.RESOURCES + "DoctypeWithExternalDefinitionAndEntity.xml"), handler);
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
