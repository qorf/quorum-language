/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.ArrayList;
import java.util.Collection;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author stefika
 */
public class QuorumLanguageHierarchy extends LanguageHierarchy<QuorumTokenId> {

    @Override
    protected Collection<QuorumTokenId> createTokenIds() {
        ArrayList<QuorumTokenId> list = new ArrayList<QuorumTokenId>();
        String[] values = plugins.quorum.Libraries.Language.Compile.QuorumLexer.tokenNames;

        QuorumTokenId quorumToken;
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.OUTPUT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.OUTPUT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ON], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ON);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.CREATE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.CREATE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.CONSTANT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.CONSTANT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE_IF);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ME], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ME);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.UNTIL], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.UNTIL);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PUBLIC], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PUBLIC);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PRIVATE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PRIVATE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALERT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALERT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.DETECT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ALWAYS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.CHECK);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PARENT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PARENT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.BLUEPRINT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.BLUEPRINT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NATIVE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NATIVE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.INHERITS], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.INHERITS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.CAST], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.CAST);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.INPUT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.INPUT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.SAY], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.SAY);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOW], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOW);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.WHILE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.WHILE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PACKAGE_NAME], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PACKAGE_NAME);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.TIMES], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.TIMES);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.REPEAT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ELSE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.RETURNS], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.RETURNS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.RETURN], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.RETURN);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.AND], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.AND);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.OR], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.OR);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NULL], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NULL);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ACTION);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.COLON], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.COLON);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.INTEGER_KEYWORD], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.INTEGER_KEYWORD);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NUMBER_KEYWORD], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NUMBER_KEYWORD);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.TEXT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.TEXT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.BOOLEAN_KEYWORD], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.BOOLEAN_KEYWORD);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.USE], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.USE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOT], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOTEQUALS], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NOTEQUALS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PERIOD], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PERIOD);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.COMMA], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.COMMA);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.EQUALITY], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.EQUALITY);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.GREATER], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.GREATER);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.GREATER_EQUAL], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.GREATER_EQUAL);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.LESS], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.LESS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.LESS_EQUAL], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.LESS_EQUAL);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.PLUS], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.PLUS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.MINUS], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.MINUS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.MULTIPLY], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.MULTIPLY);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.DIVIDE], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.DIVIDE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.MODULO], "operator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.MODULO);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_SQR_BRACE], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_SQR_BRACE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_SQR_BRACE], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_SQR_BRACE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.LEFT_PAREN);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN], "separator", plugins.quorum.Libraries.Language.Compile.QuorumLexer.RIGHT_PAREN);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.DOUBLE_QUOTE], "whitespace", plugins.quorum.Libraries.Language.Compile.QuorumLexer.DOUBLE_QUOTE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.IF);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.END], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.END);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS], "keyword", plugins.quorum.Libraries.Language.Compile.QuorumLexer.CLASS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.BOOLEAN], "number", plugins.quorum.Libraries.Language.Compile.QuorumLexer.BOOLEAN);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.INT], "number", plugins.quorum.Libraries.Language.Compile.QuorumLexer.INT);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.DECIMAL], "number", plugins.quorum.Libraries.Language.Compile.QuorumLexer.DECIMAL);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.ID], "identifier", plugins.quorum.Libraries.Language.Compile.QuorumLexer.ID);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.STRING], "string", plugins.quorum.Libraries.Language.Compile.QuorumLexer.STRING);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.NEWLINE], "whitespace", plugins.quorum.Libraries.Language.Compile.QuorumLexer.NEWLINE);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.WS], "whitespace", plugins.quorum.Libraries.Language.Compile.QuorumLexer.WS);
        list.add(quorumToken);
        quorumToken = new QuorumTokenId(values[plugins.quorum.Libraries.Language.Compile.QuorumLexer.COMMENTS], "comment", plugins.quorum.Libraries.Language.Compile.QuorumLexer.COMMENTS);
        list.add(quorumToken);

        return list;
    }

    @Override
    protected Lexer<QuorumTokenId> createLexer(LexerRestartInfo<QuorumTokenId> info) {
        return new QuorumLexer(info);
    }

    @Override
    protected String mimeType() {
        return "text/x-quorum";
    }

}
