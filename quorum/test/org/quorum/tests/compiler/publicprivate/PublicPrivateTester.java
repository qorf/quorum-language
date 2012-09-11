/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.publicprivate;

import org.quorum.execution.RunResult;
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
    public void test_assign_local_private_variables_and_action_calls_multiple_objects_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivVarAndActionCallsMultipleObjects.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_assign_local_private_variables_and_action_calls_multiple_objects_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivVarAndActionCallsMultipleObjects.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
    
    @Test
    public void test_assign_local_private_variables_and_action_calls_nested_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivateVarAndActionCallsNested.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_assign_local_private_variables_and_action_calls_nested_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivateVarAndActionCallsNested.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
    
    @Test
    public void test_assign_local_private_variables_and_action_calls_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivateVariablesAndActionCalls.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
    }

    @Test
    public void test_assign_local_private_variables_and_action_calls_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignLocalPrivateVariablesAndActionCalls.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }

    @Test
    public void test_simple_public_action_execute(){
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
    public void test_simple_public_action_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimplePublicAction.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("555"));
    }
    
    @Test
    public void test_simple_private_action_execute(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimplePrivateAction.quorum"));
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
    public void test_simple_private_action_bytecode(){
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimplePrivateAction.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("555"));
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
    public void test_set_public_variable_from_child_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableFromChild.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
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
    public void test_set_private_variable_from_child_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APrivateVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPrivateVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateVariableFromChild.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
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
    public void test_set_private_variable_Internally_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APrivateVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateVariableInternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
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
    public void test_set_public_variable_externally_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableExternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_set_public_variable_internally_execute(){
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
    
    @Test
    public void test_set_public_variable_internally_bytecode(){
        File[] files = new File[2];
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicVariableInternally.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }

    @Test
    public void test_set_public_variable_inherited_execute(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicInheritedHelper.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AWithPublicOnly.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicInherited.quorum");

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
    public void test_set_public_variable_inherited_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicInheritedHelper.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AWithPublicOnly.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPublicInherited.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_set_private_variable_inherited_execute(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateInheritedHelper.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BWithPrivateOnly.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateInherited.quorum");

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
    public void test_set_private_variable_inherited_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateInheritedHelper.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BWithPrivateOnly.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SetPrivateInherited.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_inheritance_access_to_variables_execute(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "InstanceAccessToVariables.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("value");
        int b = variableValue.getResult().integer;
        if(b!=7) {
            fail();
        }
    }
    
    @Test
    public void test_inheritance_access_to_variables_bytecode(){
        File[] files = new File[3];
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "APublicVariable.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "BPublicVariable.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "InstanceAccessToVariables.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("7"));
    }
    
    @Test
    public void test_simple_set_field_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimpleSetField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int k = variableValue.getResult().integer;
        if(k!=2) {
            fail();
        }        
    }
    
    @Test
    public void test_simple_set_field_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimpleSetField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_simple_set_field_public_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimpleSetFieldPublic.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int k = variableValue.getResult().integer;
        if(k!=2) {
            fail();
        }        
    }
    
    @Test
    public void test_simple_set_field_public_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "SimpleSetFieldPublic.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_assign_field_private_action_result_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignFieldPrivateActionResult.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int k = variableValue.getResult().integer;
        if(k!=2) {
            fail();
        }        
    }
    
    @Test
    public void test_assign_field_private_action_result_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignFieldPrivateActionResult.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_assign_field_public_action_result_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignFieldPublicActionResult.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("k");
        int k = variableValue.getResult().integer;
        if(k!=2) {
            fail();
        }        
    }
    
    @Test
    public void test_assign_field_public_action_result_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "AssignFieldPublicActionResult.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
    }
    
    @Test
    public void test_mix_fields_and_locals_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "MixFieldsAndLocals.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("field");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("local");
        int field = variableValue.getResult().integer;
        int local = variableValue2.getResult().integer;
        if(field != 10 || local != 100) {
            fail();
        }        
    }
    
    @Test
    public void test_mix_fields_and_locals_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "MixFieldsAndLocals.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10"));
        assert(r.getLine(1).equals("100"));
    }
    
    @Test
    public void test_multiple_fields_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "MultipleFields.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("t");
        int a = variableValue.getResult().integer;
        String t = variableValue2.getResult().text;
        if(a != 2 || !t.equals("in blah")) {
            fail();
        }        
    }
    
    @Test
    public void test_multiple_fields_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "MultipleFields.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
        assert(r.getLine(1).equals("in blah"));
    }
    
    @Test
    public void test_public_constant_field_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "PublicConstantField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 4 ) {
            fail();
        }        
    }
    
    @Test
    public void test_public_constant_field_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "PublicConstantField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
    
    @Test
    public void test_private_constant_field_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "PrivateConstantField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 4 ) {
            fail();
        }        
    }
    
    @Test
    public void test_private_constant_field_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "PrivateConstantField.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
    
    @Test
    public void test_constant_local_variable_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantLocalVariable.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue.getResult().integer;
        if(i != 4 ) {
            fail();
        }        
    }
    
    @Test
    public void test_constant_local_variable_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantLocalVariable.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("4"));
    }
        
    @Test
    public void test_constant_field_assigned_constant_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantFieldAssignedConstant.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        int a = variableValue.getResult().integer;
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue2.getResult().integer;
        if(i != 2 || a != 6 ) {
            fail();
        }        
    }
    
    @Test
    public void test_constant_field_assigned_constant_bytecode() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantFieldAssignedConstant.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("2"));
        assert(r.getLine(1).equals("6"));
    }
    
    @Test
    public void test_constant_field_not_assigned_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantFieldAssignedNonConstant.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }        
    }
    
    @Test
    public void test_constant_field_assigned_another_value_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantFieldAssignedAnotherValue.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }        
    }
    
    @Test
    public void test_constant_local_variable_assigned_another_value_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantLocalVariableAssignedAnotherValue.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }        
    }
    
    @Test
    public void test_constant_object_variable_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantObjectVariable.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        int a = variableValue.getResult().integer;
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("i");
        int i = variableValue2.getResult().integer;
        if(a != 4 || i != 6 ) {
            fail();
        }        
    }
    
    @Test
    public void test_constant_object_variable_assigned_another_value_execute() {
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.PUBLIC_PRIVATE + CompilerTestSuite.PASS + "ConstantObjectVariableAssignedAnotherValue.quorum");

        CompilerTestSuite.build(files);
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }        
    }
    /***************************************************************************
     * Fail tests
     * 
     * Unless the test actually executes (and doesn't raise a compiler error),
     * there is no need to create bytecode tests for these.
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
