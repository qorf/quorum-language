/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.curriculum;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for the grant curriculum are to be added
 * here.
 * 
 * @author Melissa Stefik
 */
public class CurriculumTester {
    private QuorumVirtualMachine vm;
    public CurriculumTester() {
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
    public void test_pass_week15_pig(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.CURRICULUM + CompilerTestSuite.PASS + "Week15_Pig.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
}
