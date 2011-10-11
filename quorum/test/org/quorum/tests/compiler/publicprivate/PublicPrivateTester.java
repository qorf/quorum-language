/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.publicprivate;

import java.io.File;
import org.quorum.execution.ExpressionValue;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for public and private variables and methods are to be
 * added here.
 * 
 * @author Melissa Stefik
 */
public class PublicPrivateTester {
    private QuorumVirtualMachine vm;
    public PublicPrivateTester() {
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
    public void test_simple_public_action(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimplePublicAction.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 555) {
            fail();
        }
    }

    @Test
    public void test_set_public_variable_from_child_execute(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableFromChild.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=2) {
            fail();
        }
    }

    @Test
    public void test_set_private_variable_from_child_execute(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APrivateVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPrivateVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateVariableFromChild.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=2) {
            fail();
        }
    }

    @Test
    public void test_set_private_variable_Internally_execute(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APrivateVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateVariableInternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=2) {
            fail();
        }
    }

    @Test
    public void test_set_public_variable_externally_execute(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableExternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=2) {
            fail();
        }
    }

    @Test
    public void test_set_public_variable_internally(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableInternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=2) {
            fail();
        }
    }

    /***************************************************************************
     * Fail tests
     **************************************************************************/
    @Test
    public void test_set_private_variable_externally_execute(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.FAIL + "APrivateVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.FAIL + "SetPrivateVariableExternally.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
}
