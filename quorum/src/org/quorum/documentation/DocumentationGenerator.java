/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.documentation;

import java.io.File;
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
     * If this returns true, every call to generate will not only generate
     * documentation for that particular class, but will store an index file
     * that can be accessed through getIndex().
     * @return 
     */
    public boolean isIndexed();
    
    /**
     * Tells the generator whether or not it should index files.
     * 
     * @param isIndexed 
     */
    public void setIndexed(boolean isIndexed);
    
    /**
     * Returns the generated index file. If isIndexed() is false, this returns
     * null.
     * 
     * @return 
     */
    public String getIndex();
    
    /**
     * Clears any index values.
     * 
     */
    public void clearIndex();
    
    /**
     * Computes any final details with an index that may have been generated
     * as this documentation generator was used. DocumentationGenerator objects
     * are not required to store an index, and as such, this call may do nothing\
     * on a particular subclass.
     */
    public void finishIndex();
    
    /**
     * This method does any final processing when documentation is completely
     * generated. For example, the HTML generator copies over a css file. Other
     * generators may do nothing.
     * 
     * @param standardLibrary The folder where the standard library resides.
     * @param documentationRoot The folder where the documentation was generated.
     */
    public void finish(File standardLibrary, File documentationRoot);
    
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
