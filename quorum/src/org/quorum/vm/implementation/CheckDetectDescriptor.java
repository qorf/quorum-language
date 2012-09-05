/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.ArrayList;
import java.util.Stack;
import org.objectweb.asm.Label;

/**
 * The check-detect descriptor contains information related specifically to
 * bytecode generation of these constructs. Primarily,  it contains:
 * 
 * 1. The start and end label of all detect statements.
 * 2. The start and end label of the check statement.
 * 3. The start and end label of the always statement.
 * 4. The physical Quorum Bytecode position of the start of the always block.
 * 
 * @author jeff
 */
public class CheckDetectDescriptor {
    private Label checkStart = new Label();
    private Label checkEnd = new Label();
    private Label alwaysStart = new Label();
    private Label alwaysEnd = new Label();
    private Label constructEnd = new Label();
    private int startingVariableNumber = 0;
    private int numberOfDetects = 0;
    private int alwaysStartPosition = -1;
    private int lastDetectVariableNumber = -1;
    private Stack<Label> detectStarts = new Stack<Label>();
    private Stack<Label> detectEnds = new Stack<Label>();
    private boolean hasAlways = false;
    private String blockName = "";
    private boolean hadThrow = false;
    private boolean isInAlwaysScope = false;
    private boolean hasEmptyCheck = false;
    private int maxVariableSize = 0;
    private int storedDetectVariableNumber = -1;
    
    private ArrayList<Label>processedDetectStart = new ArrayList<Label>();
    private ArrayList<Label>processedDetectEnd = new ArrayList<Label>();
    
    public Label pushDetectStartLabel() {
        Label l = new Label();
        detectStarts.push(l);
        
        return l;
    }
    
    public Label pushDetectEndLabel() {
        Label l = new Label();
        detectEnds.push(l);
        
        return l;
    }
    
    public Label getNextDetectStartLabel() {
        if(detectStarts.isEmpty())
            return null;
        else
            return detectStarts.pop();
    }
    
    public Label peekDetectStartLabel(){
        if(detectStarts.isEmpty()){
            return null;
        }
        return detectStarts.peek();
    }
    
    public Label getNextDetectEndLabel() {
        if(detectEnds.isEmpty())
            return null;
        else
            return detectEnds.pop();
    }
        
    public Label peekDetectEndLabel(){
        if(detectEnds.isEmpty()){
            return null;
        }
        return detectEnds.peek();
    }
    
    /**
     * @return the checkStart
     */
    public Label getCheckStart() {
        return checkStart;
    }

    /**
     * @param checkStart the checkStart to set
     */
    public void setCheckStart(Label checkStart) {
        this.checkStart = checkStart;
    }

    /**
     * @return the checkEnd
     */
    public Label getCheckEnd() {
        return checkEnd;
    }

    /**
     * @param checkEnd the checkEnd to set
     */
    public void setCheckEnd(Label checkEnd) {
        this.checkEnd = checkEnd;
    }

    /**
     * @return the alwaysStart
     */
    public Label getAlwaysStart() {
        return alwaysStart;
    }

    /**
     * @param alwaysStart the alwaysStart to set
     */
    public void setAlwaysStart(Label alwaysStart) {
        this.alwaysStart = alwaysStart;
    }

    /**
     * @return the alwaysEnd
     */
    public Label getAlwaysEnd() {
        return alwaysEnd;
    }

    /**
     * @param alwaysEnd the alwaysEnd to set
     */
    public void setAlwaysEnd(Label alwaysEnd) {
        this.alwaysEnd = alwaysEnd;
    }

    /**
     * @return the hasAlways
     */
    public boolean isHasAlways() {
        return hasAlways;
    }

    /**
     * @param hasAlways the hasAlways to set
     */
    public void setHasAlways(boolean hasAlways) {
        this.hasAlways = hasAlways;
    }

    /**
     * @return the constructEnd
     */
    public Label getConstructEnd() {
        return this.constructEnd;
    }

    /**
     * @param constructEnd the constructEnd to set
     */
    public void setConstructEnd(Label constructEnd) {
        this.constructEnd = constructEnd;
    }

    /**
     * @return the alwaysStartPosition
     */
    public int getAlwaysStartPosition() {
        return alwaysStartPosition;
    }

    /**
     * @param alwaysStartPosition the alwaysStartPosition to set
     */
    public void setAlwaysStartPosition(int alwaysStartPosition) {
        this.alwaysStartPosition = alwaysStartPosition;
    }

    /**
     * @return the startingVariableNumber
     */
    public int getStartingVariableNumber() {
        return startingVariableNumber;
    }

    /**
     * @param startingVariableNumber the startingVariableNumber to set
     */
    public void setStartingVariableNumber(int startingVariableNumber) {
        this.startingVariableNumber = startingVariableNumber;
    }

    /**
     * @return the numberOfDetects
     */
    public int getNumberOfDetects() {
        return numberOfDetects;
    }

    /**
     * @param numberOfDetects the numberOfDetects to set
     */
    public void setNumberOfDetects(int numberOfDetects) {
        this.numberOfDetects = numberOfDetects;
    }

    /**
     * @return the lastDetectVariableNumber
     */
    public int getLastDetectVariableNumber() {
        return lastDetectVariableNumber;
    }

    /**
     * @param lastDetectVariableNumber the lastDetectVariableNumber to set
     */
    public void setLastDetectVariableNumber(int lastDetectVariableNumber) {
        this.lastDetectVariableNumber = lastDetectVariableNumber;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    public String getBlockName(){
        return this.blockName;
    }

    public void flagThrow() {
        hadThrow = true;
    }
    
    public void unflagThrow(){
        hadThrow = false;
    }
    
    public boolean isAfterThrow(){
        return hadThrow;
    }
    public boolean isLastDetect(){
        if(this.detectEnds.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void addProcessedDetectStart(Label startLabel) {
        processedDetectStart.add(startLabel);
    }
    
    public void addProcessedDetectEnd(Label startLabel) {
        processedDetectEnd.add(startLabel);
    }

    public Label popProcessedDetectEnd() {
        if(processedDetectEnd.isEmpty()){
            return null;
        }else{
            return processedDetectEnd.remove(0);
        }
    }
    
    public Label popProcessedDetectStart() {
        if(processedDetectStart.isEmpty()){
            return null;
        }else{
            return processedDetectStart.remove(0);
        }
    }
    
    public boolean hasProcessedDetect() {
        if(!processedDetectStart.isEmpty() && !processedDetectEnd.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the isInAlwaysScope
     */
    public boolean isInAlwaysScope() {
        return isInAlwaysScope;
    }

    /**
     * @param isInAlwaysScope the isInAlwaysScope to set
     */
    public void flagAlwaysScope(boolean isInAlwaysScope) {
        this.isInAlwaysScope = isInAlwaysScope;
    }

    /**
     * @return the maxVariableSize
     */
    public int getMaxVariableSize() {
        return maxVariableSize;
    }

    /**
     * @param maxVariableSize the maxVariableSize to set
     */
    public void setMaxVariableSize(int maxVariableSize) {
        this.maxVariableSize = maxVariableSize;
    }

    /**
     * @return the storedDetectVariableNumber
     */
    public int getStoredDetectVariableNumber() {
        return storedDetectVariableNumber;
    }

    /**
     * @param storedDetectVariableNumber the storedDetectVariableNumber to set
     */
    public void setStoredDetectVariableNumber(int storedDetectVariableNumber) {
        this.storedDetectVariableNumber = storedDetectVariableNumber;
    }

    public void pushDetectEndLabel(Label endLabel) {
        this.detectEnds.push(endLabel);
    }

    public void setHasEmptyCheck(boolean b) {
        hasEmptyCheck = b;
    }
    
    public boolean hasEmptyCheck(){
        return hasEmptyCheck;
    }
}
