/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.List;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.csl.api.Hint;
import org.netbeans.modules.csl.api.HintsProvider;
import org.netbeans.modules.csl.api.Rule;
import org.netbeans.modules.csl.api.RuleContext;
import org.netbeans.modules.csl.spi.ParserResult;

/**
 *
 * @author stefika
 */
public class QuorumHintsProvider implements HintsProvider{

    @Override
    public void computeHints(HintsManager hm, RuleContext rc, List<Hint> list) {
        int a = 5;
    }

    @Override
    public void computeSuggestions(HintsManager hm, RuleContext rc, List<Hint> list, int i) {
        int a = 5;
    }

    @Override
    public void computeSelectionHints(HintsManager hm, RuleContext rc, List<Hint> list, int i, int i1) {
        int a = 5;
    }

    @Override
    public void computeErrors(HintsManager hm, RuleContext rc, List<Hint> hints, List<Error> unhandled) {
        QuorumParserResult result = (QuorumParserResult) rc.parserResult;
        if (result == null) {
            return;
        }
        
        List<? extends Error> errors = result.getDiagnostics();
        if(errors == null || errors.isEmpty()) {
            return;
        }
        unhandled.addAll(errors);
    }

    @Override
    public void cancel() {
        int a = 5;
    }

    @Override
    public List<Rule> getBuiltinRules() {
        return null;
    }

    @Override
    public RuleContext createRuleContext() {
        return new QuorumRuleContext();
    }
    
}
