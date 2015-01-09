/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.Collections;
import java.util.List;
import org.netbeans.modules.csl.api.Error;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;

/**
 *
 * @author stefika
 */
public class QuorumParserResult extends ParserResult {
    private boolean valid = true;
    private QuorumParser parser;
    
    QuorumParserResult(Snapshot snapshot, QuorumParser p) {
        super(snapshot);
        parser = p;
    }
    
    @Override
    public List<? extends Error> getDiagnostics() {
        return parser.getFileErrors();
    }

    @Override
    protected void invalidate() {
        valid = false;
    }
    
}
