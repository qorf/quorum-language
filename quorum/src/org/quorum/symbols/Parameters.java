/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.CompilerError;

/**
 * This is a general class to represent method parameters.
 * 
 * @author Andreas Stefik
 */
public class Parameters {
    private HashMap<String, ParameterDescriptor> parameters;
    private Vector<ParameterDescriptor> paramList;
    public Parameters() {
        parameters = new HashMap<String, ParameterDescriptor>();
        paramList = new Vector<ParameterDescriptor>();
    }

    /**
     * Adds a parameter to the list of parameters.
     * @param param
     * @return
     */
    public CompilerError add(ParameterDescriptor param) {
        if(parameters.containsKey(param.getName())) {
            CompilerError error = new CompilerError();
            error.setError("Two parameters cannot have the same name, " + param.getName() + ".");
            error.setLineNumber(param.getLineBegin());
            error.setColumn(param.getColumnBegin());
            return error;
        }
        else {
            parameters.put(param.getName(), param);
            paramList.add(param);
            return null;
        }
    }

    /**
     * Returns an iterator of all parameters in this list
     * @return
     */
    public Iterator<ParameterDescriptor> iterator() {
        return parameters.values().iterator();
    }

    /**
     * Returns the number of parameters.
     * @return
     */
    public int size() {
        return parameters.size();
    }

    public boolean isEmpty() {
        return parameters.size() == 0;
    }
    /**
     * Returns one parameter.
     * 
     * @param name
     * @return
     */
    public ParameterDescriptor get(String name) {
        return parameters.get(name);
    }

    /**
     * Returns an indexed parameter in the order
     * specified by the user of the programming language.
     * 
     * @param index
     * @return
     */
    public ParameterDescriptor get(int index) {
        return paramList.get(index);
    }
}
