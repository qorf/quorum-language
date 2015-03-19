/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.project.FileOwnerQuery;
import org.openide.util.Lookup;
import org.netbeans.api.project.Project;
import org.netbeans.modules.csl.api.CodeCompletionContext;
import org.netbeans.modules.csl.api.CodeCompletionHandler2;
import org.netbeans.modules.csl.api.CodeCompletionResult;
import org.netbeans.modules.csl.api.CompletionProposal;
import org.netbeans.modules.csl.api.Documentation;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ParameterInfo;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Source;

/**
 *
 * @author stefika
 */
public class QuorumCodeCompletionHandler implements CodeCompletionHandler2{

    @Override
    public Documentation documentElement(ParserResult pr, ElementHandle eh, Callable<Boolean> clbl) {
        return Documentation.create("Hi");
    }

    @Override
    public CodeCompletionResult complete(CodeCompletionContext context) {
        QuorumCodeCompletionResult result = new QuorumCodeCompletionResult();
        
        ParserResult parserResult = context.getParserResult();
        Source source = parserResult.getSnapshot().getSource();
        Project project = FileOwnerQuery.getOwner(source.getFileObject());
        if(project != null) {
                Lookup lookup = project.getLookup();
                quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);

                String string = parserResult.getSnapshot().getText().toString();
                
        }
        return result;
    }

    @Override
    public String document(ParserResult pr, ElementHandle eh) {
        return "";
    }

    @Override
    public ElementHandle resolveLink(String string, ElementHandle eh) {
        return null;
    }

    @Override
    public String getPrefix(ParserResult pr, int i, boolean bln) {
        return "";
    }

    @Override
    public QueryType getAutoQuery(JTextComponent jtc, String string) {
        if(string.length() > 0) {
            if(string.startsWith(":") || string.startsWith(".")) {
                return QueryType.COMPLETION;
            }
        }
        return QueryType.NONE;
    }

    @Override
    public String resolveTemplateVariable(String string, ParserResult pr, int i, String string1, Map map) {
        return "";
    }

    @Override
    public Set<String> getApplicableTemplates(Document dcmnt, int i, int i1) {
        return Collections.emptySet();
    }

    @Override
    public ParameterInfo parameters(ParserResult pr, int i, CompletionProposal cp) {
        return ParameterInfo.NONE;
    }
}
