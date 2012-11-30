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
    private boolean documentationExists = false;
    
    public void parseDocumentationString(String value) {
        if(value != null) {
            //first chop out the /* and the */
            value = value.substring(2, value.length() - 2).trim();
            String[] split = value.split("Attribute:");
            int length = split.length;
            
            description = split[0].trim();

            int current = 1;
            while(current < length) {
                String attribute = split[current];
                attribute = attribute.trim();

                if(current == split.length - 1) {
                    attribute = attribute.trim();
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
                    //break the example by line, trim it, and recombine it
                    result = formatExample(result);
                    example = result.trim();
                }

                current = current + 1;
            }

            documentationString = value;
            documentationExists = true;
        }
    }
    
    private String formatExample(String example) {
        
        if(example == null || example.length() == 0) {
            return "";
        }
        int indent = 0;
        String[] split = example.split("\\r?\\n");
        String result = "";
        for(int i = 0; i < split.length; i++) {
            if(split[i].length() > 0) {
                String trimmed = split[i].trim();
                isInString = false;
                indent += indentCheck(trimmed);
                result += computeIndent(indent) + trimmed + "\n";
                isInString = false;
                indent += postIndentCheck(trimmed);
            }
        }
        return result;
    }
    
    private String computeIndent(int level) {
        String result = "";
        final int CHARS_IN_INDENT = 3;
        for(int i = 0; i < level; i++) {
            for(int j = 0; j < CHARS_IN_INDENT; j++) {
                result += " ";
            }
        }
        return result;
    }
    
    public int indentCheck(String string) {
        String[] split = string.split("\\s+");
        int indent = 0;
        
        boolean check = true;
        for(int i = 0; i < split.length; i++) {
            if(isCommentStart(split[i])) {
                check = false;
            }
            
            if(isCommentEnd(split[i])) {
                check = true;
            }
            
            if(check) {
                indent += isIncreaseIndentWord(split[i]);
            }
        }
        return indent;
    }
    
    public boolean isCommentStart(String string) {
        if(string.contains("//")) {
            return true;
        } else if(string.contains("/*")) {
            return true;
        }
        
        return false;
    }
    
    public boolean isCommentEnd(String string) {
        if(string.contains("*/")) {
            return true;
        }
        
        return false;
    }
    
    private void areQuotationsInString(String string) {
        for(int i = 0; i < string.length(); i++) {
            char val = string.charAt(i);
            if(val == '\"') {
                isInString = !isInString;
            }
        }
    }
    
    boolean isInString = false;
    public int isIncreaseIndentWord(String string) {
        areQuotationsInString(string);
        
        if(isInString) {
            return 0;
        }
        
        if("class".equals(string)) {
            return 0;
        } else if ("if".equals(string)) {
            return 0;
        } else if ("check".equals(string)) {
            return 0;
        } else if ("detect".equals(string)) {
            return -1;
        } else if ("always".equals(string)) {
            return 0;
        } else if ("repeat".equals(string)) {
            return 0;
        } else if ("action".equals(string)) {
            return 0;
        } else if ("end".equals(string)) {
            return -1;
        }
        
        return 0;
    }
    
    public int postIndentCheck(String string) {
        String[] split = string.split("\\s+");
        int indent = 0;
        
        boolean check = true;
        for(int i = 0; i < split.length; i++) {
            if(isCommentStart(split[i])) {
                check = false;
            }
            
            if(isCommentEnd(split[i])) {
                check = true;
            }
            
            if(check) {
                indent += isPostIndent(split[i]);
            }
        }
        return indent;
    }
    
    public int isPostIndent(String string) {
        areQuotationsInString(string);
        
        if(isInString) {
            return 0;
        }
        
        if("class".equals(string)) {
            return 1;
        } else if ("if".equals(string)) {
            return 1;
        } else if ("check".equals(string)) {
            return 1;
        } else if ("detect".equals(string)) {
            return 1;
        } else if ("always".equals(string)) {
            return 1;
        } else if ("repeat".equals(string)) {
            return 1;
        } else if ("action".equals(string)) {
            return 1;
        } else if ("end".equals(string)) {
            return 0;
        }
        
        return 0;
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

    /**
     * @return the documentationExists
     */
    public boolean doesDocumentationExist() {
        return documentationExists;
    }
}
