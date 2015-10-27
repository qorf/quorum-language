/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexer;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexerFactory;
import org.netbeans.modules.parsing.spi.indexing.Indexable;

/**
 *
 * @author stefika
 */
public class QuorumIndexerFactory extends EmbeddingIndexerFactory{

    @Override
    public EmbeddingIndexer createIndexer(Indexable indexable, Snapshot snapshot) {
        
        
        
        return new QuorumIndexer();
        
    }

    @Override
    public boolean scanStarted(Context context) {
        return false;
    }

    @Override
    public void scanFinished(Context context) {
    }
    
    @Override
    public void filesDeleted(Iterable<? extends Indexable> arg0, Context arg1) {
        int a = 5;
    }

    @Override
    public void filesDirty(Iterable<? extends Indexable> arg0, Context arg1) {
        int a = 5;
    }

    @Override
    public String getIndexerName() {
        return "Quorum Indexer";
    }

    @Override
    public int getIndexVersion() {
        return 1;
    }
    
}
