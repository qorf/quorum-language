/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.ExtensibleMarkup;

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
import org.xml.sax.SAXException;

/**
 *
 * @author Nicole
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
    public void test_DoctypeDefinition_pass_bytecode(){
        File[] files = new File[2];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "RootElement.quorum");
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
            //saxParser.parse(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXTENSIBLE_MARKUP + CompilerTestSuite.PASS + "simple_element.xml"), handler);
            saxParser.parse("<?xml version=\"1.0\"?><root>test</root>", handler);
            result = handler.GetResult();
         } catch (ParserConfigurationException  e) {
           e.printStackTrace();
         } catch (SAXException  e) {
           e.printStackTrace();
         }catch (IOException e) {
           e.printStackTrace();
         }
        
        int numLines = r.getNumberOfLines();
        for (int count = 0; count < (numLines - 1) && count < (result.size() - 1); count++)
        {
            //assert(r.getLine(count).equals(result.get(count)));
        }
        
    }
}
