/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.nativefunctions;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for native functions are to be add here.
 * 
 * @author Melissa Stefik
 */
public class NativeFunctionsTester {
    private QuorumVirtualMachine vm;
    public NativeFunctionsTester() {
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


    //TODO
    //placeholder test please replace with a Native Function test!!
    @Test
    public void test_if_statement_one_condition(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

}
