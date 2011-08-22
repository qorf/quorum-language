/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.Comparator;
import java.util.HashMap;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.LineInformation;

/**
 *
 * @author Andreas Stefik
 */
public abstract class Descriptor {
    private String name;
    private int lineBegin = 0;
    private int lineEnd = 0;
    private int columnBegin = 0;
    private int columnEnd = 0;
    private AbstractVirtualMachine machine;
    private Documentation documentation = new Documentation();

    /**
     * Returns an object representing the line and column information for this
     * object. TODO: Deprecate old line and column information in place
     * of new objects representing such information.
     * 
     * @return
     */
    public LineInformation getLineInformation() {
        LineInformation info = new LineInformation();
        info.setStartLine(lineBegin);
        info.setStartColumn(columnBegin);
        info.setEndLine(lineEnd);
        info.setEndColumn(columnEnd);
        return info;
    }

    /**
     * Determines if a descriptor has already been defined. If it has
     * return a compiler error and if not return null.
     * @param d
     * @param m
     * @return
     */
    public CompilerError isDefined(Descriptor d, HashMap m) {
        if(m.containsKey(d.getStaticKey())) { //already defined
            CompilerError error = getCompilerErrorInfoFromDescriptor(d);
            error.setError(d.getName() + " has already been defined.");
            return error;
        }
        else {
            return null;
        }
    }

    /** A convenience method for setting information into a compiler error
     * message.
     * @param d
     * @return
     */
    protected CompilerError getCompilerErrorInfoFromDescriptor(Descriptor d) {
        CompilerError error = new CompilerError();
        error.setLineNumber(d.getLineBegin());
        error.setColumn(d.getColumnBegin());
        return error;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lineBegin
     */
    public int getLineBegin() {
        return lineBegin;
    }

    /**
     * @param lineBegin the lineBegin to set
     */
    public void setLineBegin(int lineBegin) {
        this.lineBegin = lineBegin;
    }

    /**
     * @return the lineEnd
     */
    public int getLineEnd() {
        return lineEnd;
    }

    /**
     * @param lineEnd the lineEnd to set
     */
    public void setLineEnd(int lineEnd) {
        this.lineEnd = lineEnd;
    }

    /**
     * @return the columnBegin
     */
    public int getColumnBegin() {
        return columnBegin;
    }

    /**
     * @param columnBegin the columnBegin to set
     */
    public void setColumnBegin(int columnBegin) {
        this.columnBegin = columnBegin;
    }

    /**
     * @return the columnEnd
     */
    public int getColumnEnd() {
        return columnEnd;
    }

    /**
     * @param columnEnd the columnEnd to set
     */
    public void setColumnEnd(int columnEnd) {
        this.columnEnd = columnEnd;
    }

    /** This is a string key that represents the item uniquely on the system.
     * This key only represents the static representation of an object uniquely.
     * In other words, this key might represent the class for an object, but
     * not the instantiation of a particular object (uniquely).
     * @return
     */
    public String getStaticKey() {
        return name;
    }

    /**
     * @return the machine
     */
    protected AbstractVirtualMachine getVirtualMachine() {
        return machine;
    }

    /**
     * @param machine the machine to set
     */
    protected void setVirtualMachine(AbstractVirtualMachine machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return this.getStaticKey();
    }

    /**
     * @return the documentation
     */
    public Documentation getDocumentation() {
        return documentation;
    }

    /**
     * @param documentation the documentation to set
     */
    public void setDocumentation(Documentation documentation) {
        if(documentation != null) {
            this.documentation = documentation;
        }
    }
}
