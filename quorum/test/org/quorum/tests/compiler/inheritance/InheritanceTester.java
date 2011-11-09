/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.inheritance;


import org.quorum.execution.RunResult;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.execution.ExpressionValue;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import static org.junit.Assert.*;

/**
 * Unit tests for inheritance are to be added here.
 * 
 * @author Melissa Stefik
 */
public class InheritanceTester {
    private QuorumVirtualMachine vm;
    public InheritanceTester() {
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
    public void test_single_inheritance_simple_one_layer(){
        String directory = CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS;
        File[] build = new File[3];
        build[2] = CompilerTestSuite.getQuorumFile(directory + "A.quorum");
        build[1] = CompilerTestSuite.getQuorumFile(directory + "B.quorum");
        build[0] = CompilerTestSuite.getQuorumFile(directory + "SingleInheritanceSimpleOneLayer.quorum");

        try {
            CompilerTestSuite.build(build);
        }
        catch(Exception e) {
            fail();
        }
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_pass_inheritance_(){
        CompilerTestSuite.build(
                CompilerTestSuite.getQuorumFile(
                CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "SystemActionInherit.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        int a = variableValue.getResult().integer;
        if(a!=1) {
            fail();
        }
    }
    
    @Test
    public void test_pass_fully_qualified(){
        CompilerTestSuite.build(
                CompilerTestSuite.getQuorumFile(
                CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "ParentFullyQualified.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("hash");
        int a = variableValue.getResult().integer;
        if(a!=1) {
            fail();
        }
    }
    
    @Test
    public void test_pass_action_call(){
        CompilerTestSuite.build(
                CompilerTestSuite.getQuorumFile(
                CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "ParentActionCall.quorum"));
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("hash");
        int a = variableValue.getResult().integer;
        if(a!=1) {
            fail();
        }
    }

    @Test
    public void test_pass_DogTest_execute(){
        File[] files = new File[3];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "DogMain.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Dog.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Mammal.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("furry");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }

        variableValue = vm.getDataEnvironment().getVariableValue("alive");
        b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }
    }

    @Test
    public void test_pass_DogTest_bytecode(){
        File[] files = new File[3];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "DogMain.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Dog.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Mammal.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_pass_inherit_templated_methods_execute(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "InheritTemplatedMethods.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "ATemplated.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "BTemplated.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CTemplated.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "DTemplated.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("bool");
        boolean b = variableValue.getResult().boolean_value;
        if(b!=true) {
            fail();
        }

        variableValue = vm.getDataEnvironment().getVariableValue("tResult");
        String a = variableValue.getResult().text;
        if(a.compareTo("true") != 0) {
            fail();
        }

        variableValue = vm.getDataEnvironment().getVariableValue("nResult");
        double c = variableValue.getResult().number;
        if(c!=1.2) {
            fail();
        }

        variableValue = vm.getDataEnvironment().getVariableValue("intResult");
        int d = variableValue.getResult().integer;
        if(d!=10) {
            fail();
        }
    }
    
    @Test
    public void test_pass_inherit_templated_methods_bytecode(){
        File[] files = new File[5];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "InheritTemplatedMethods.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "ATemplated.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "BTemplated.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CTemplated.quorum");
        files[4] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "DTemplated.quorum");

        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
        assert(r.getLine(2).equals("1.2"));
        assert(r.getLine(3).equals("10"));
    }
    
    @Test
    public void test_pass_TruckTest_execute(){
        File[] files = new File[4];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "TruckMain.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Truck.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "GasPoweredVehicle.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "LandVehicle.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("a");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("b");
        int a = variableValue.getResult().integer;
        int b = variableValue2.getResult().integer;
        if(a != 4 || b != 20) {
            fail();
        }
    }
    
    @Test
    public void test_pass_TruckTest_bytecode(){
        File[] files = new File[4];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "TruckMain.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "Truck.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "GasPoweredVehicle.quorum");
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "LandVehicle.quorum");
    
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        
        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("Number of wheels: 4"));
        assert(r.getLine(1).equals("Size of gas tank (gallons): 20"));
    }

    @Test
    public void test_call_inherited_action_execute(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CallInheritedAction.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("resultA");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("resultB");
        ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("resultC");
        String resultA = variableValue.getResult().text;
        String resultB = variableValue2.getResult().text;
        String resultC = variableValue3.getResult().text;

        if(!resultA.equals("implemented by A") || !resultB.equals("implemented by B") || !resultC.equals("implemented by B")) {
            fail();
        }
    }
    
    @Test
    public void test_call_inherited_action_bytecode(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CallInheritedAction.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("implemented by A"));
        assert(r.getLine(1).equals("implemented by B"));
        assert(r.getLine(2).equals("implemented by B"));
    }
    
    @Test
    public void test_call_method_with_child_type_execute(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CallMethodWithChildType.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("resultA");
        ExpressionValue variableValue2 = vm.getDataEnvironment().getVariableValue("resultB");
        ExpressionValue variableValue3 = vm.getDataEnvironment().getVariableValue("resultC");
        String resultA = variableValue.getResult().text;
        String resultB = variableValue2.getResult().text;
        String resultC = variableValue3.getResult().text;

        if(!resultA.equals("implemented by A") || !resultB.equals("implemented by B") || !resultC.equals("implemented by B")) {
            fail();
        }
    }
    
    @Test
    public void test_call_method_with_child_type_bytecode(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "CallMethodWithChildType.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("implemented by A"));
        assert(r.getLine(1).equals("implemented by B"));
        assert(r.getLine(2).equals("implemented by B"));
    }
    
    @Test
    public void test_simple_cast_execute(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "SimpleCast.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        
        String result = variableValue.getResult().text;
        if(!result.equals("implemented by B")) {
            fail();
        }
    }
    
    @Test
    public void test_simple_cast_bytecode(){
        File[] files = new File[4];
        files[3] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "A.quorum");
        files[2] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "B.quorum");
        files[1] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "C.quorum");
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.INHERITANCE + CompilerTestSuite.PASS + "SimpleCast.quorum");
        
        CompilerTestSuite.build(files);
        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFiles(files);
        if (!r.isSuccessful())
            fail();
    
        assert(r.getLine(0).equals("implemented by B"));
    }
}
