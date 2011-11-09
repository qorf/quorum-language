/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.Expressions;

import org.quorum.execution.RunResult;
import java.io.File;
import org.quorum.execution.ExpressionValue;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit tests for expressions.
 *
 * @author Kim Slattery
 */
public class ExpressionsTester {

    private QuorumVirtualMachine vm;
    
    public ExpressionsTester() {
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
    public void test_pass_AddBooleanText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddBooleanText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true text"));
        assert(r.getLine(1).equals("false text"));
    }
    
    @Test
    public void test_pass_AddDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7.1"));
    }
    
    @Test
    public void test_pass_AddDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7.2"));
        assert(r.getLine(1).equals("7.2"));
    }
    
    @Test
    public void test_pass_AddDoubleText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddDoubleText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("3.2 is a number."));
        assert(r.getLine(1).equals("3.2 is a number."));
    }
    
    @Test
    public void test_pass_AddIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("6"));
    }
    
    @Test
    public void test_pass_AddIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("13.5"));
        assert(r.getLine(1).equals("13.5"));
    }
    
    @Test
    public void test_pass_AddIntText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddIntText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1 is an integer"));
        assert(r.getLine(1).equals("2 is an integer"));
    }
    
    @Test
    public void test_pass_AddTextBoolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddTextBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("boolean is true"));
        assert(r.getLine(1).equals("boolean is false"));
    }
    
    @Test
    public void test_pass_AddTextDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddTextDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("number is 1.0"));
        assert(r.getLine(1).equals("number is 2.0"));
    }
    
    @Test
    public void test_pass_AddTextInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddTextInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("int is 1"));
        assert(r.getLine(1).equals("int is 2"));
    }
    
    @Test
    public void test_pass_AddTextText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AddTextText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("hello world"));
        assert(r.getLine(1).equals("world hello"));
    }
    
    @Test
    public void test_pass_AndBooleanBoolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "AndBooleanBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
        assert(r.getLine(2).equals("false"));
        assert(r.getLine(3).equals("false"));
    }
    
    @Test
    public void test_pass_OrBooleanBoolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "OrBooleanBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
        assert(r.getLine(2).equals("true"));
        assert(r.getLine(3).equals("false"));
    }
    
    @Test
    public void test_pass_SubDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "SubDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.0"));
        assert(r.getLine(1).equals("0.0"));
    }
    
    @Test
    public void test_pass_SubDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "SubDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.0"));
        assert(r.getLine(1).equals("0.0"));
    }
    
    @Test
    public void test_pass_SubIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "SubIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0.0"));
        assert(r.getLine(1).equals("0.0"));
    }
    
    @Test
    public void test_pass_SubIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "SubIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("0"));
        assert(r.getLine(1).equals("0"));
    }
    
    @Test
    public void test_pass_MultDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "MultDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4.0"));
        assert(r.getLine(1).equals("4.0"));
    }
    
    @Test
    public void test_pass_MultDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "MultDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4.0"));
        assert(r.getLine(1).equals("4.0"));
    }
    
    @Test
    public void test_pass_MultIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "MultIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4.0"));
        assert(r.getLine(1).equals("4.0"));
    }
    
    @Test
    public void test_pass_MultIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "MultIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
        assert(r.getLine(1).equals("4"));
    }
    
    @Test
    public void test_pass_DivDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "DivDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2.0"));
        assert(r.getLine(1).equals("2.0"));
    }
    
    @Test
    public void test_pass_DivDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "DivDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2.0"));
        assert(r.getLine(1).equals("2.0"));
    }
    
    @Test
    public void test_pass_DivIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "DivIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2.0"));
        assert(r.getLine(1).equals("2.0"));
    }
    
    @Test
    public void test_pass_DivIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "DivIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
        assert(r.getLine(1).equals("2"));
    }
    
    @Test
    public void test_pass_ModDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "ModDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
        assert(r.getLine(1).equals("1.0"));
    }
    
    @Test
    public void test_pass_ModDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "ModDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
        assert(r.getLine(1).equals("1.0"));
    }
    
    @Test
    public void test_pass_ModIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "ModIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1.0"));
        assert(r.getLine(1).equals("1.0"));
    }
    
    @Test
    public void test_pass_ModIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "ModIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("1"));
    }

    @Test
    public void test_pass_EqualBooleanBoolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualBooleanBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualCustomCustom_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualCustomCustom.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualTextText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualTextText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail("Run unsuccessful");
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_EqualIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "EqualIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualBooleanBoolean_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualBooleanBoolean.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualCustomCustom_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualCustomCustom.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("false"));
    }
    
    @Test
    public void test_pass_NotEqualTextText_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualTextText.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_NotEqualIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "NotEqualIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
        @Test
    public void test_pass_LTEDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTEDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTEDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTEDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTEIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTEIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_LTEIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "LTEIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTEDoubleDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTEDoubleDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTEDoubleInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTEDoubleInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTEIntDouble_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTEIntDouble.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_GTEIntInt_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "GTEIntInt.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("false"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_IsA_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXPRESSIONS + CompilerTestSuite.PASS + "IsA.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
    }
}
