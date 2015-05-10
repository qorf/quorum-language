/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading.text;

/**
 * This implementation processes characters and returns a literal name. For
 * example, * will be translated into the string star. This class does this
 * for a large variety, but not necessarily all, characters.
 * 
 * @author Andreas Stefik
 */
public class LiteralCharacterProcessor implements CharacterProcessor {
    public String processCharacter(char c) {
        String s = "";
        if (c == '@') {
            s += " at ";
        } else if (c == '/') {
            s += " slash ";
        } else if(c == '!'){
           s += " bang ";
        }else if(c == '"'){
            s += " double quote ";
        } else if(c == '\''){
            s += " quote ";
        } else if(c == ':'){
            s += " colon ";
        } else if(c == '?'){
            s += " question ";
        }  else if (c == '\\') {
            s += " back slash ";
        } else if (c == '*') {
            s += " star ";
        } else if(c == '.'){
            s += " dot ";
        } else if (c == '{') {
            s += " left brace ";
        } else if (c == '}') {
            s += " right brace ";
        } else if (c == '(') {
            s += " left paren ";
        } else if (c == ')') {
            s += " right paren ";
        } else if (c == '[') {
            s += " left bracket ";
        } else if (c == ']') {
            s += " right bracket ";
        } else if (c == '|') {
            s += " bar ";
        } else if (c == ',') {
            s += " comma ";
        } else if (c == ';') {
            s += " semicolon ";
        } else if (c == '=') {
            s += " equals ";
        }else if (c == '<') {
            s += " less than ";
        }else if (c == '>') {
            s += " greater than ";
        }else if (c == '=') {
            s += " equals ";
        }else if (c == '+') {
            s += " plus ";
        }else if (c == '-') {
            s += " minus ";
        }else if (c == '_') {
           s += " underline ";
        }else if (c == 'a') {
           s += " eh ";
        }else {
            s += c;
        }
        return s;
    }
}
