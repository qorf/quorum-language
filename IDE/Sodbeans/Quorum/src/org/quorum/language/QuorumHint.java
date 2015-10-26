/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.List;
import org.netbeans.modules.csl.api.Hint;
import org.netbeans.modules.csl.api.HintFix;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.api.Rule;
import org.openide.filesystems.FileObject;

/**
 *
 * @author stefika
 */
public class QuorumHint extends Hint{

    public QuorumHint(Rule rule, String string, FileObject fo, OffsetRange or, List<HintFix> list, int i) {
        super(rule, string, fo, or, list, i);
    }
    
}
