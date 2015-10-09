/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.csl.api.Hint;
import org.netbeans.modules.csl.api.HintFix;
import org.netbeans.modules.csl.api.HintSeverity;
import org.netbeans.modules.csl.api.HintsProvider;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.api.Rule;
import org.netbeans.modules.csl.api.RuleContext;
import org.netbeans.modules.csl.spi.ParserResult;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.CompilerError_;
import quorum.Libraries.Language.Compile.Hints.Hint_;
import quorum.Libraries.Language.Object_;

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
        Iterator<? extends Error> iterator = errors.iterator();
        while(iterator.hasNext()) {
            QuorumError next = (QuorumError) iterator.next();
            CompilerError_ error = next.getError();
            boolean hasHints = error.HasHints();
            if(hasHints) {
                Hint hint = getHint(error, rc);
                hints.add(hint);
                unhandled.add(next);
            } else {
                unhandled.add(next);
            }
        }
        //unhandled.addAll(errors);
    }
    
    public Hint getHint(final CompilerError_ error, RuleContext rc) {
        Rule rule = new Rule() {
            @Override
            public boolean appliesTo(RuleContext rc) {
                return true;
            }

            @Override
            public String getDisplayName() {
                return error.GetDisplayName();
            }

            @Override
            public boolean showInTasklist() {
                return true;
            }

            @Override
            public HintSeverity getDefaultSeverity() {
                return HintSeverity.CURRENT_LINE_WARNING;
            }
        };
        File file = new File(error.GetAbsolutePath());
        FileObject fo = FileUtil.toFileObject(file);
        int start = error.GetIndex();
        int finish = error.GetIndexEnd();
        
        List<HintFix> list = new LinkedList<HintFix>();
        Iterator_ hints = error.GetHintIterator();
        while(hints.HasNext()) {
            Hint_ next = (Hint_) hints.Next();
            QuorumHintFix fix = new QuorumHintFix();
            fix.setRuleContext(rc);
            fix.setError(error);
            fix.setHint(next);
            list.add(fix);
        }
        OffsetRange or = new OffsetRange(start,finish);
        int severity = 1;
        QuorumHint hint = new QuorumHint(rule, error.GetDisplayName(), fo, or, list, severity);
        
        return hint;
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
