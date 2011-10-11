/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.File;

import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

    @Test
    public void test_CreateNew(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.FILE + CompilerTestSuite.PASS + "CreateNew.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
//        vm.blockRun();
//
//        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("booleanTotal");
//        String a = variableValue.getResult().text;
//        if(a.equals("truetruefalsefalsefalsetruetruefalse") == false){
//            fail();
//        }
//
//        ExpressionValue variableValue1 = vm.getDataEnvironment().getVariableValue("integerTotal");
//        String a1 = variableValue1.getResult().text;
//        if(a1.equals("12345678") == false){
//            fail();
//        }
//
//        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("numberTotal");
//        String a2 = variableValue2.getResult().text;
//        if(a2.equals("1.02.03.04.05.06.07.08.0") == false){
//            fail();
//        }
//
//        ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("textTotal");
//        String a3 = variableValue3.getResult().text;
//        if(a3.equals("tttttttt") == false)
//        {
//            fail();
//        }
    }
}
