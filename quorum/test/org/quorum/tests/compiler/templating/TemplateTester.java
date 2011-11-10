/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.templating;

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
 *
 * @author astefik
 */
public class TemplateTester {
    private QuorumVirtualMachine vm;
    public TemplateTester() {
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

    /*
     *
     *
     * Test cases that should pass start here
     *
     *
     */

    @Test
    public void test_pass_ArraySetAndGet_0_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;

        try {
            CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(directory + "ArraySetAndGet_0.quorum"));
        }
        catch(Exception e) {
            fail();
        }
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x!=34) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("j");
        x = variableValue.getResult().integer;
        if(x!=16) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("k");
        x = variableValue.getResult().integer;
        if(x!=-62) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("l");
        x = variableValue.getResult().integer;
        if(x!=3) {
            fail();
        }
    }
    
    @Test
    public void test_pass_ArraySetAndGet_0_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;

        try {
            CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(directory + "ArraySetAndGet_0.quorum"));
        }
        catch(Exception e) {
            fail();
        }
        
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("34"));
        assert(r.getLine(1).equals("16"));
        assert(r.getLine(2).equals("-62"));
        assert(r.getLine(3).equals("3"));
    }

    @Test
    public void test_pass_TemplateUsingInteger_1_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingInteger_1.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("int");
        int x = variableValue.getResult().integer;
        if(x!=5) {
            fail();
        }
    }

    @Test
    public void test_pass_TemplateUsingInteger_1_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingInteger_1.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_pass_ProgramUsingEntryWithThreeTypes_2_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "SimpleEntryUsingThreeTypes_2.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "ProgramUsingEntryWithThreeTypes_2.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_ProgramUsingEntryWithThreeTypes_2_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "SimpleEntryUsingThreeTypes_2.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "ProgramUsingEntryWithThreeTypes_2.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething1"));
        assert(r.getLine(1).equals("doSomething2"));
        assert(r.getLine(2).equals("doSomething3"));
    }
    
    @Test
    public void test_pass_TemplateUsingTwoIntegers_3_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry2_3.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingTwoIntegers_3.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x!=10) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("j");
        x = variableValue.getResult().integer;
        if(x!=5) {
            fail();
        }
    }
    
    @Test
    public void test_pass_TemplateUsingTwoIntegers_3_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry2_3.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingTwoIntegers_3.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
        assert(r.getLine(1).equals("15"));
    }

    @Test
    public void test_pass_PassIntegerTakesInteger_4_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesInteger_4.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }
    
    @Test
    public void test_pass_PassIntegerTakesInteger_4_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesInteger_4.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }

    @Test
    public void test_pass_PassBooleanTakesBoolean_5_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesBoolean_5.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_PassBooleanTakesBoolean_5_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesBoolean_5.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }
    
    @Test
    public void test_pass_PassNumberTakesNumber_6_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesNumber_6.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }
    
    @Test
    public void test_pass_PassNumberTakesNumber_6_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesNumber_6.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }

    @Test
    public void test_pass_PassTextTakesText_7_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassTextTakesText_7.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_PassTextTakesText_7_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassTextTakesText_7.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }
    
    @Test
    public void test_pass_TempClassHasATempClass_8_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "LinkedList_8.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TempClassHasATempClass_8.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x!=5) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("j");
        x = variableValue.getResult().integer;
        if(x!=35) {
            fail();
        }
    }
    
    @Test
    public void test_pass_TempClassHasATempClass_8_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "LinkedList_8.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TempClassHasATempClass_8.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
        assert(r.getLine(1).equals("35"));
    }

    @Test
    public void test_pass_ReturnNullWithTemplatedReturnType_9_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "ReturnNullWithTemplatedReturnType_9.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_ReturnNullWithTemplatedReturnType_9_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "ReturnNullWithTemplatedReturnType_9.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
    }
    
    @Test
    public void test_pass_TempClassHasTempClassThatInheritsFromTempClass_10_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[4];
        build[3] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "DoubleNode_10.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "DoubleNodeList_10.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TempClassHasTempClassThatInheritsFromTempClass_10.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int x = variableValue.getResult().integer;
        if(x!=1) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("t");
        String str = variableValue.getResult().text;
        if(str.compareTo("Slattery") != 0) {
            fail();
        }
        variableValue = vm.getDataEnvironment().getVariableValue("s");
        str = variableValue.getResult().text;
        if(str.compareTo("Stefik") != 0) {
            fail();
        }
    }

    @Test
    public void test_pass_TempClassHasTempClassThatInheritsFromTempClass_10_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[4];
        build[3] = CompilerTestSuite.getQuorumFile(directory + "Node_8.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "DoubleNode_10.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "DoubleNodeList_10.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TempClassHasTempClassThatInheritsFromTempClass_10.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("1"));
        assert(r.getLine(1).equals("Slattery"));
        assert(r.getLine(2).equals("Stefik"));
    }
    
    @Test
    public void test_pass_PassIntegerTakesNumber_11_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesNumber_11.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }
    
    @Test
    public void test_pass_PassIntegerTakesNumber_11_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesNumber_11.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }

    @Test
    public void test_pass_PassIntegerTakesText_12_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesText_12.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_PassIntegerTakesText_12_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesText_12.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }
    
    @Test
    public void test_pass_PassNumberTakesText_13_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesText_13.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_pass_PassNumberTakesText_13_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesText_13.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }
    
    @Test
    public void test_pass_PassBooleanTakesText_14_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesText_14.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }
    
    @Test
    public void test_pass_PassBooleanTakesText_14_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_4.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesText_14.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
        assert(r.getLine(0).equals("doSomething"));
    }

    @Test
    public void test_pass_TemplateUsingTemplatedClass_15_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "Container_15.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingTemplatedClass_15.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }
    
    @Test
    public void test_pass_TemplateUsingTemplatedClass_15_bytecode(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "Container_15.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Entry_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TemplateUsingTemplatedClass_15.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(build);
        if (!r.isSuccessful())
            fail();
    }


    /*
     * 
     * 
     * Test cases that should fail start here
     * 
     * 
     */

    @Test
    public void test_fail_PassBooleanTakesInteger_1_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesInteger_1.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassNumberTakesInteger_2_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesInteger_2.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassTextTakesInteger_3_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassTextTakesInteger_3.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassIntegerTakesBoolean_4_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassIntegerTakesBoolean_4.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassNumberTakesBoolean_5_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassNumberTakesBoolean_5.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassTextTakesBoolean_6_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassTextTakesBoolean_6.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassBooleanTakesNumber_7_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassBooleanTakesNumber_7.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_PassTextTakesNumber_8_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[2];
        build[1] = CompilerTestSuite.getQuorumFile(directory + "Test_1.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "PassTextTakesNumber_8.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_DiamondProblemWithTwoTypes_9_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[5];
        build[4] = CompilerTestSuite.getQuorumFile(directory + "D_9.quorum");
        build[3] = CompilerTestSuite.getQuorumFile(directory + "B_9.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "C_9.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "A_9.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "DiamondProblemWithTwoTypes_9.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_DefneTypeOfUntemplatedClass_10_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[4];
        build[3] = CompilerTestSuite.getQuorumFile(directory + "A_10.quorum");
        build[2] = CompilerTestSuite.getQuorumFile(directory + "B_10.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "C_10.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "main_10.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_TypeCheckUntemplatedChildOfTempClass_11_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_11.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_11.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TypeCheckUntemplatedChildOfTempClass_11.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_TypeCheckTemplatedChildOfTempClass_12_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A_11.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B_12.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "TypeCheckTemplatedChildOfTempClass_12.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_fail_ArrayFilledWithWrongType_execute(){
        String directory = CompilerTestSuite.TEMPLATING + CompilerTestSuite.FAIL;
        File[] build = new File[1];
        build[0] = CompilerTestSuite.getQuorumFile(directory + "ArrayFilledWithWrongType.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }
}