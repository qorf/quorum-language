/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.modules.csl.api.EditList;
import org.netbeans.modules.csl.api.HintFix;
import org.netbeans.modules.csl.api.RuleContext;
import quorum.Libraries.Language.Compile.Hints.Hint_;

/**
 *
 * @author stefika
 */
public class QuorumHintFix implements HintFix {
    private Hint_ hint;
    private RuleContext rc;
    
    @Override
    public String getDescription() {
        if(hint != null) {
            return hint.GetDisplayName();
        }
        return "";
    }

    @Override
    public void implement() throws Exception {
        BaseDocument doc = rc.doc;
        EditList edits = new EditList(doc);
        int offset = hint.GetEndOffset();
        if(offset != 0) {
            offset += 2;
        }
        int lineStart = Utilities.getRowStart(doc, offset);
        edits.replace(lineStart, hint.GetLinesToRemove(), hint.GetInsertionText(), false, 1);
        edits.apply();
    }

    @Override
    public boolean isSafe() {
        return true;
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    /**
     * @return the hint
     */
    public Hint_ getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(Hint_ hint) {
        this.hint = hint;
    }

    /**
     * @return the rc
     */
    public RuleContext getRuleContext() {
        return rc;
    }

    /**
     * @param rc the rc to set
     */
    public void setRuleContext(RuleContext rc) {
        this.rc = rc;
    }
    
}
