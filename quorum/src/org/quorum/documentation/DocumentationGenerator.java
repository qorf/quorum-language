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
    public String generate(ClassDescriptor clazz);
    public DocumentationStyle getDocumentationStyle();
}
