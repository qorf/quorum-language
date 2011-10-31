/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.use;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for 'use' are to be added here.
 *
 * @author Melissa Stefik
 */
public class UseTester {
    private QuorumVirtualMachine vm;
    public UseTester() {
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
    public void test_if_statement_one_condition(){
//        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.IF_STATEMENT + CompilerTestSuite.PASS + "IfStatementOneCondition.quorum"));
//        if (!vm.getCompilerErrors().isCompilationErrorFree()){
//            fail();
//        }
    }

}
