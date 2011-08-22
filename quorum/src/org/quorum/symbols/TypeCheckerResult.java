/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import org.quorum.execution.ExecutionStep;



/** This class stores the result of a type check
 *
 * @author Aaron Willows and Andreas Stefik
 */
public class TypeCheckerResult {
    private TypeDescriptor result;
    private TypeConversionResults conversionResult;
    private String errorMessage;
    private TypeCheckerStepFactory opcodeGenerator;
    private TypeConversionEnum conversionScore;

    public TypeCheckerResult() {
        errorMessage = "";
        conversionScore = TypeConversionEnum.EXACT_MATCH;
    }

    /**
     * @param conversionResult the conversionResult to set
     */
    public void setConversionResult(TypeConversionResults conversionResult) {
        this.conversionResult = conversionResult;
    }

    /**
     *
     * @return the type of conversion preformed on this type
     */
    public TypeConversionResults getConversionResult(){
        return conversionResult;
    }

    /**
     * @param result the result to set
     */
    public void setResult(TypeDescriptor result) {
        this.result = result;
    }
    /**
     * 
     * @return the TypeDescriptor that represents the final type of the check. If the check fails, null is returned.
     */
    public TypeDescriptor getResult(){
        return result;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    /**
     *
     * @return the message that describes the type checks error, if no error occurs it returns the empty string
     */
    public String getErrorMessage(){

        return errorMessage;

    }

    /**
     * @return the opcodeGenerator
     */
    public TypeCheckerStepFactory getOpcodeGenerator() {
        return opcodeGenerator;
    }

    /**
     * @param opcodeGenerator the opcodeGenerator to set
     */
    public void setOpcodeGenerator(TypeCheckerStepFactory opcodeGenerator) {
        this.opcodeGenerator = opcodeGenerator;
    }


    /**
     * Generates an appropriate opcode for this operation. If not opcode
     * exists for this type of operation, null is returned.
     * 
     * @return
     */
    public ExecutionStep generateOpcode() {
        return opcodeGenerator.generateStep();
    }

    /**
     * @return the conversionScore
     */
    public TypeConversionEnum getConversionScore() {
        return conversionScore;
    }

    /**
     * @param conversionScore the conversionScore to set
     */
    public void setConversionScore(TypeConversionEnum conversionScore) {
        this.conversionScore = conversionScore;
    }
}
