/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.implementation;

import java.io.File;
import java.io.FileInputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.stringtemplate.*;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.quorum.parser.*;
import org.quorum.parser.QuorumParser.start_return;

/**
 *  This class represents information regarding files that is relevant for
 *  parsing and building files using ANTLR.
 *
 * @author Andreas Stefik
 */
public class QuorumFile {
    private FileInputStream fileInputStream;
    private ANTLRInputStream input;
    private QuorumLexer lexer;
    private CommonTokenStream tokens;
    private QuorumParser parser;
    private start_return startNode;
    private CommonTree syntaxTree;
    private File file;
    private QuorumTreeWalker.start_return tree;
    
    /**
     * @return the fileInputStream
     */
    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    /**
     * @param fileInputStream the fileInputStream to set
     */
    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    /**
     * @return the input
     */
    public ANTLRInputStream getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(ANTLRInputStream input) {
        this.input = input;
    }

    /**
     * @return the lexer
     */
    public QuorumLexer getLexer() {
        return lexer;
    }

    /**
     * @param lexer the lexer to set
     */
    public void setLexer(QuorumLexer lexer) {
        this.lexer = lexer;
    }

    /**
     * @return the tokens
     */
    public CommonTokenStream getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(CommonTokenStream tokens) {
        this.tokens = tokens;
    }

    /**
     * @return the parser
     */
    public QuorumParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(QuorumParser parser) {
        this.parser = parser;
    }

    /**
     * @return the startNode
     */
    public start_return getStartNode() {
        return startNode;
    }

    /**
     * @param startNode the startNode to set
     */
    public void setStartNode(start_return startNode) {
        this.startNode = startNode;
    }

    /**
     * @return the syntaxTree
     */
    public CommonTree getSyntaxTree() {
        return syntaxTree;
    }

    /**
     * @param syntaxTree the syntaxTree to set
     */
    public void setSyntaxTree(CommonTree syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the tree
     */
    public QuorumTreeWalker.start_return getTree() {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(QuorumTreeWalker.start_return tree) {
        this.tree = tree;
    }
}
