/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * Scores used to match the most specific methods to a method that is called.
 * The lower the score the closer the match of the method and it's parameters.
 *
 * @author Melissa Stefik
 */
public enum TypeConversionEnum {
    EXACT_MATCH(0),
    PARENT(1),
    EXACT_AUTO_BOX(2),
    LOSSLESS_CONVERT(3),
    PARENT_AUTO_BOX(4);

    int conversionType;
    TypeConversionEnum(int st) {
        conversionType = st;
    }

    public int getScore() {
        return conversionType;
    }
}
