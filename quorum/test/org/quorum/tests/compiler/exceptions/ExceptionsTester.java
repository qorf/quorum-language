/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.exceptions;

import java.io.File;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 *  Unit tests for exceptions are to be added here.
 *
 * @author Melissa Stefik
 */
public class ExceptionsTester {
    private QuorumVirtualMachine vm;
    public ExceptionsTester() {
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
    public void test_cast_text_to_integer_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CastTextToIntegerException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasErrors()){
            fail();
        }else if(!vm.getExceptions().exceptionStackPeek().getStaticKey().equals(ErrorTypeDescriptor.CAST_ERROR)){
            fail();
        }else if(vm.getExceptions().hasErrors() && vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_cast_text_to_number_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CastTextToNumberException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasErrors()){
            fail();
        }else if(!vm.getExceptions().exceptionStackPeek().getStaticKey().equals(ErrorTypeDescriptor.CAST_ERROR)){
            fail();
        }else if(vm.getExceptions().hasErrors() && vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_catch_cast_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchCastException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_catch_With_Always_cast_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchWithAlwaysCastException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_generic_catch_cast_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "GenericCatchCastException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_nested_catch_cast_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedCatchCastException.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_1.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_1.quorum");
        
        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_cast_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "UncaughtCastException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasErrors() || vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_nested_cast_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_1.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedUncaughtCastException.quorum");

        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }


    @Test
    public void test_catch_cast_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchCastAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_catch_With_Always_cast_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchWithAlwaysCastAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_generic_catch_cast_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "GenericCatchCastAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_nested_catch_cast_alert_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedCatchCastAlertException.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_2.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_2.quorum");

        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_cast_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "UncaughtCastAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasAlerts() || vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_nested_cast_alert_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_2.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_2.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedUncaughtCastAlertException.quorum");

        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_catch_simple_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchCastSimpleAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 2) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_catch_With_Always_simple_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "CatchWithAlwaysCastSimpleAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_nested_catch_simple_alert_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedCatchSimpleAlertException.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_3.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_3.quorum");

        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(vm.getExceptions().hasErrors() && !vm.hasCaughtException()){
            fail();
        }

        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a != 3) {
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_simple_alert_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "UncaughtSimpleAlertException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasErrors() || vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }

    @Test
    public void test_uncaught_nested_simple_alert_exception(){
        String directory = CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_3.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_3.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "NestedUncaughtSimpleAlertException.quorum");

        CompilerTestSuite.build(build);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        if(!vm.getExceptions().hasErrors() || vm.hasCaughtException()){
            fail();
        }
        vm.stop();
    }
    
    @Test
    public void test_nested_always_with_exception(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.EXCEPTIONS + CompilerTestSuite.PASS + "NestedAlwaysWithException.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue variableXText = vm.getDataEnvironment().getVariableValue("XText");
        ExpressionValue variableYText = vm.getDataEnvironment().getVariableValue("YText");
        ExpressionValue variableHiText = vm.getDataEnvironment().getVariableValue("hiText");
        ExpressionValue variableHiOutText = vm.getDataEnvironment().getVariableValue("hiOutText");
        
        String XText = variableXText.getResult().text;
        String YText = variableYText.getResult().text;
        String hiText = variableHiText.getResult().text;
        String hiOutText = variableHiOutText.getResult().text;
        
        if (!XText.equals("X") || !YText.equals("Y") || !hiText.equals("hi") || !hiOutText.equals("hi2")) {
            fail();
        }
        
        vm.stop();
    }
}
