/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author stefika
 */
public class Documentation{
    private String documentationString = "";
    private String description = "";
    private String author = "";
    private String example = "";
    private String returns = "";
    private HashMap<String, String> parameters = new HashMap<String, String>();

    private final String AUTHOR = "Author";
    private final String PARAMETER = "Parameter";
    private final String RETURNS = "Returns";
    private final String EXAMPLE = "Example";
    
    public void parseDocumentationString(String value) {
        if(value != null) {
            String[] split = value.split("Attribute:");
            int length = split.length;
            
            if(length > 0) {
                description = split[0].substring(2);
                description = getDescription().trim();
            }

            int current = 1;
            while(current < length) {
                String attribute = split[current];
                attribute = attribute.trim();

                if(current == split.length - 1) {
                    attribute = attribute.substring(0, attribute.length() - 2).trim();
                }

                if(attribute.startsWith(AUTHOR)) {
                    String result = attribute.substring(AUTHOR.length(), attribute.length());
                    author = result.trim();
                }
                else if(attribute.startsWith(PARAMETER)) {
                    String result = attribute.substring(PARAMETER.length(), attribute.length()).trim();

                    for(int i = 0; i < result.length(); i++) {
                        char c = result.charAt(i);
                        if(Character.isWhitespace(c)) { //stopping point
                            String name = result.substring(0, i).trim();
                            String paramDescription = result.substring(i, result.length()).trim();
                            parameters.put(name, paramDescription);
                            i = result.length();
                        }
                    }
                }
                else if(attribute.startsWith(RETURNS)) {
                    String result = attribute.substring(RETURNS.length(), attribute.length());
                    returns = result.trim();
                }
                else if(attribute.startsWith(EXAMPLE)) {
                    String result = attribute.substring(EXAMPLE.length(), attribute.length());
                    example = result.trim();
                }

                current = current + 1;
            }

            documentationString = value;

        }
    }
    
    public String toString() {
        return documentationString;
    }


    public String getParameter(String name) {
        String result = "";
        boolean has = parameters.containsKey(name);
        if(has) {
            return parameters.get(name);
        }
        return "";
    }

    public int getNumberDocumentedParameters() {
        return parameters.size();
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the example
     */
    public String getExample() {
        return example;
    }

    /**
     * @return the returns
     */
    public String getReturns() {
        return returns;
    }
    
    /**
     * This method takes an input documentation string and post-processes
     * it to have reasonable 
     * @param string
     * @return 
     */
    public static String breakStringIntoParagraphs(String string) {
        String result = "";
        String patternString = "(?<=(\r\n|\r|\n))([ \\t]*$)+";
        String[] paragraphs = Pattern.compile(patternString, Pattern.MULTILINE).split(string);
        for (int i = 0; i < paragraphs.length; i++) {
            paragraphs[i] = paragraphs[i].replaceAll("\\s+", " ");
            result += paragraphs[i];
        }
        return result;
    }
    
    public static String[] breakStringIntoParagraphArray(String string) {
        String patternString = "(?<=(\r\n|\r|\n))([ \\t]*$)+";
        String[] paragraphs = Pattern.compile(patternString, Pattern.MULTILINE).split(string);
        for (int i = 0; i < paragraphs.length; i++) {
            paragraphs[i] = paragraphs[i].replaceAll("\\s+", " ");
            paragraphs[i] = paragraphs[i].trim();
        }
        return paragraphs;
    }

}
