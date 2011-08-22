/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 *  This class represents a reference from one class to another or a
 *  regular expression representing a series of classes. This is functionally
 *  similar to Java's import or C#'s using.
 * @author Andreas Stefik
 */
public class UseDescriptor extends Descriptor{
    private String use = "";
    private String[] useNames;
    public static final String DELIMITER = "\\.";
    /** In many languages this would be .*;*/
    private static final String ALL_REFERENCE = "all";
    private static final String ALL_REFERENCE_CAP = "All";
    private String useNoLastName = "";

    /**
     * Takes a string of a package, parses it, and sets up this object
     * to easily work with this use.
     * @param string
     */
    public void setUse(String string) {
        use = string;
        useNoLastName = string;
        useNames = string.split(DELIMITER);
        

        String removeLast = "";
        for(int i = 0; i < useNames.length - 1; i++) {
            removeLast += useNames[i] + ".";
        }
        if(removeLast.length() == 0) {
            useNoLastName = removeLast;
        }
        else {
            useNoLastName = removeLast.substring(0, removeLast.length() - 1);
        }
    }

    /**
     * Returns the last value in this particular use statement.
     * @return
     */
    public String getLastName() {
        return useNames[useNames.length - 1];
    }

    /**
     * Returns whether or not this use statement refers to all items
     * in a particular package.
     * @return true if it refers to all items in a package.
     */
    public boolean isLastNamePackageReference() {
        String last = getLastName();
        if(last.compareTo(ALL_REFERENCE) == 0 ||
           last.compareTo(ALL_REFERENCE_CAP) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * If this use is a refererence to "all" items in a particular package
     * then this method returns the proper package name with the all removed.
     * @return
     */
    public String getNameWithoutLast() {
        return useNoLastName;
    }

    @Override
    public String toString() {
        return use;
    }
}
