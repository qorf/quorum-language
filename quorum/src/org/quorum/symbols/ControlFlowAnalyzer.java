/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.LineInformation;

/**
 * This class analyzes the control flow of a particular function and ensures
 * that any return type requirements are met. If the requirements are not
 * met, this class issues a CompilerError.
 *
 * @author Andreas Stefik
 */
public class ControlFlowAnalyzer {
    private MethodDescriptor method;
    private boolean returnSatisfied = false;
    private FileDescriptor parentFile;
    private HashMap<String, LineInformation> returnStatements;
    private ControlFlowNode root;
    private ControlFlowNode currentNode;
    private int ifCounter = 0;
    private int repeatCounter = 0;
    private int checkCounter = 0;
    
    public ControlFlowAnalyzer() {
    }
    
    /**
     * Sets the method to be analyzed by the control flow analyzer.
     * @param m
     */
    public void setMethod(MethodDescriptor m) {
        method = m;
        returnStatements = new HashMap<String, LineInformation>();

        root = new ControlFlowNode();
        if(method != null) {
            root.key = method.getScopeString();
            root.type = ControlFlowType.METHOD;
        }
        currentNode = root;
        ifCounter = 0;
    }

    /**
     * Adds a return statement to the control flow analyzer and checks to see
     * if one has already been defined in the current scope. If so, this method
     * issues a compiler error.
     * 
     * @param info
     * @return
     */
    public CompilerError addReturnStatement(LineInformation info) {
        LineInformation exists = returnStatements.get(currentNode.key);
        CompilerError error = null;
        if(exists == null) {
            returnStatements.put(currentNode.key, info);
        }
        else {
            error = new CompilerError();
            error.setLineNumber(info.getStartLine());
            error.setFile(parentFile.getStaticKey());
            error.setError("Unreachable, as a previous return statement " +
                "was already defined on line " + exists.getStartLine()
                + ".");
        }
        return error;
    }

    /**
     * Checks to see if adding a statement at this point in the parse is possible,
     * or whether a return statement has already been issued, effectively
     * making it impossible for control flow to reach this point.
     *
     * @param info
     * @return
     */
    public CompilerError addStatement(LineInformation info) {
        if(this.method == null) { //It's an instance variable
            return null;
        }
        LineInformation exists = returnStatements.get(currentNode.key);
        CompilerError error = null;
        if(exists != null) {
            error = new CompilerError();
            error.setLineNumber(info.getStartLine());
            error.setFile(parentFile.getStaticKey());
            error.setError("Unreachable, as a return statement " +
                "was defined on line " + exists.getStartLine()
                + ".");
        }
        return error;
    }

    /**
     * Informs the analyzer that an if statement has started in this method.
     */
    public void ifStart() {
        ifCounter++;
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":if" + ifCounter;
        node.parent = currentNode;
        node.type = ControlFlowType.IF;
        
        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that an if statement has ended.
     */
    public void ifEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * Informs the analyzer that an if else, attached to a previous if, has
     * started.
     */
    public void ifElseStart() {
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":if_else" + ifCounter + ":" + currentNode.getNumberChildren();
        node.parent = currentNode;
        node.type = ControlFlowType.ELSE_IF;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that the current if else has ended.
     */
    public void ifElseEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * Informs the analyzer that an else statement has begun.
     */
    public void elseStart() {
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":else" + ifCounter;
        node.parent = currentNode;
        node.type = ControlFlowType.ELSE;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that an else has ended.
     */
    public void elseEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * Informs the analyzer that a repeat statement has started. Repeat
     * statements are largely ignored, as they do not guarantee return values
     * are correct.
     */
    public void repeatStart() {
        repeatCounter++;
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":repeat" + repeatCounter;
        node.parent = currentNode;
        node.type = ControlFlowType.REPEAT;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that a repeat statement has ended.
     */
    public void repeatEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * This method checks to see if all of the constraints on return flow
     * are satisfied. If the constraints are satisfied,
     * this method returns null, otherwise, it issues a compiler error.
     *
     * @return
     */
    public CompilerError endMethod() {
        if(method == null) {
            return null;
        }
        if(doesNodeIncludeReturnStatement(root)) {
            returnSatisfied = true;
        }
        else {//fine if this is a void method, is it?
            if(method.getReturnType().getName().compareTo(TypeDescriptor.getVoidType().getName())==0) {
                returnSatisfied = false;
            }
            else {
                CompilerError error = new CompilerError();
                error.setLineNumber(method.getLineInformation().getEndLine());
                error.setFile(parentFile.getStaticKey());
                error.setError("Missing return statement in action " + 
                        method.getName() + ".");
                this.method = null;
                return error;
            }
            
        }
        this.method = null;
        return null;
    }

    /**
     * This method analyzes the input stream and determines whether or not
     * the compiler can guarantee that a particular method will return an
     * appropriate value. If the compiler can make this guarantee, this
     * method will return true, otherwise it will return false.
     * 
     * @param node
     * @return
     */
    private boolean doesNodeIncludeReturnStatement(ControlFlowNode node) {
        if(returnStatements.containsKey(node.key)) {
            return true;
        }
        else if(node.getNumberChildren() != 0) {
            Iterator<ControlFlowNode> children = node.getChildren();
            ControlFlowType state = null;
            while(children.hasNext()) {
                ControlFlowNode child = children.next();

                //first handle the case where it is an if
                if(child.type == ControlFlowType.IF) {
                    //if this node has a return, it's possible
                    boolean passed = doesNodeIncludeReturnStatement(child);
                    if(passed && children.hasNext()) {//grab if-elses and elses
                        state = ControlFlowType.IF;
                    }
                    else {
                        state = null;
                    }
                }
                else if(child.type == ControlFlowType.ELSE_IF) {
                    if(state != null) {//check to ensure the if passed
                        boolean passed = doesNodeIncludeReturnStatement(child);
                        if(!passed) {
                            state = null;
                        }
                    }
                }
                else if(child.type == ControlFlowType.ELSE) {
                    if(state != null) { //the if/else ifs passed
                        boolean passed = doesNodeIncludeReturnStatement(child);
                        if(passed) {
                            return true;
                        }
                        else {
                            state = null;
                        }
                    }
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * This method determines whether all constraints are satisfied for
     * return statements in the current method.
     * @return
     */
    public boolean needsReturnStep() {
        return !returnSatisfied;
    }

    /**
     * @return the parentFile
     */
    public FileDescriptor getParentFile() {
        return parentFile;
    }

    /**
     * @param parentFile the parentFile to set
     */
    public void setParentFile(FileDescriptor parentFile) {
        this.parentFile = parentFile;
    }

    /**
     * Informs the analyzer that a check(try) statement has started in this method.
     */
    public void checkStart() {
        checkCounter++;
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":check" + checkCounter;
        node.parent = currentNode;
        node.type = ControlFlowType.CHECK;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that the check has ended.
     */
    public void checkEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * Informs the analyzer that a detect(catch) statement has started in this method.
     */
    public void detectStart() {
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":detect" + checkCounter + ":" + currentNode.getNumberChildren();
        node.parent = currentNode;
        node.type = ControlFlowType.DETECT;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that a always(finally) statement has started in this method.
     */
    public void alwaysStart() {
        ControlFlowNode node = new ControlFlowNode();
        node.key = this.currentNode.key + ":always" + checkCounter;
        node.parent = currentNode;
        node.type = ControlFlowType.ALWAYS;

        currentNode.addChild(node);
        currentNode = node;
    }

    /**
     * Informs the analyzer that the always has ended.
     */
    public void alwaysEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * Informs the analyzer that the detect has ended.
     */
    public void detectEnd() {
        currentNode = currentNode.parent;
    }

    /**
     * A helper class for handling control flow analysis.
     */
    private class ControlFlowNode {
        String key = "";
        ControlFlowType type;
        Vector<ControlFlowNode> children;
        ControlFlowNode parent;
        
        public ControlFlowNode() {
            children = new Vector<ControlFlowNode>();
        }

        public void addChild(ControlFlowNode node) {
            children.add(node);
        }

        public Iterator<ControlFlowNode> getChildren() {
            return children.iterator();
        }

        public int getNumberChildren() {
            return children.size();
        }
    }

    private enum ControlFlowType {
        METHOD, IF, ELSE_IF, ELSE, REPEAT, CHECK, DETECT, ALWAYS;
    }
}
