/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.vm.interfaces.LineInformation;
import org.quorum.execution.ExpressionValue;

/**
 * A helper class for making native calls down to the system.
 *
 * @author Andreas Stefik
 */
public class NativeCallInformation {
    public int register;
    public LineInformation location;
    public int libraryRegister = -1;
    public int actionRegister = -1;
    public int parameterRegister = -1;
    public int objectParameterRegister = -1;
    public ExpressionValue libraryValue;
    public ExpressionValue actionValue;
    public ExpressionValue parameterValue;
    public ExpressionValue objectParameterValue;
}
