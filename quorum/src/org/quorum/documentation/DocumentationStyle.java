/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.documentation;

/**
 *
 * @author stefika
 */
public enum DocumentationStyle {
    TRAC_WIKI,
    JAVADOC,
    HTML;

    public static DocumentationGenerator getDocumentationGenerator(DocumentationStyle style) {
        if(style == TRAC_WIKI) {
            return new TracWikiDocumentationGenerator();
        }
        else if(style == JAVADOC) {
            return null;
        }
        else if(style == HTML) {
            return new HTMLDocumentationGenerator();
        }
        else { //return a default
            return new TracWikiDocumentationGenerator();
        }
    }
}
