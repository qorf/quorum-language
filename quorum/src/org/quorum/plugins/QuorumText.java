/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

/**
 * This class represents an instance of Text in Quorum.
 * 
 * @author jeff
 */
class QuorumText {
    private String value = "";

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
   
    /**
     * 
     * @return the length of the value
     */
    public int getLength() {
        return value.length();
    }
    
    /**
     * Gets character at specified index.
     * @param index
     * @return character (as string)
     */
    public String getCharacter(int index) {
        return Character.toString(value.charAt(index));
    }
    
    /**
     * Gets the substring of the value.
     * @param start start index
     * @param end end index
     * @return 
     */
    public String getSubstring(int start, int end) {
        return value.substring(start, end);
    }
}
