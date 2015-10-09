/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.Collections;
import java.util.Set;
import org.netbeans.api.project.Project;
import org.netbeans.modules.csl.api.IndexSearcher;
import org.netbeans.modules.parsing.spi.indexing.support.QuerySupport;

/**
 *
 * @author stefika
 */
public class QuorumIndexSearcher implements IndexSearcher{

    @Override
    public Set<? extends Descriptor> getTypes(Project prjct, String string, QuerySupport.Kind kind, Helper helper) {
        return Collections.EMPTY_SET;
    }

    @Override
    public Set<? extends Descriptor> getSymbols(Project prjct, String string, QuerySupport.Kind kind, Helper helper) {
        return Collections.EMPTY_SET;
    }
    
}
