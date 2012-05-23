/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.documentation;

import org.quorum.symbols.ClassDescriptor;

/**
 *
 * @author stefika
 */
public interface DocumentationGenerator {
    /**
     * This takes a Quorum class and generates documentation for it,
     * in a given style.
     * 
     * @param clazz
     * @return 
     */
    public String generate(ClassDescriptor clazz);
    
    /**
     * This helper function returns an enumerated type indicating the style
     * of documentation.
     * 
     * @return 
     */
    public DocumentationStyle getDocumentationStyle();
    
    /**
     * This returns a file extension for the particular type of documentation.
     * 
     * @return 
     */
    public String getFileExtension();
}
