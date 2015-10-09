/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexer;
import org.netbeans.modules.parsing.spi.indexing.Indexable;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * @author stefika
 */
public class QuorumIndexer extends EmbeddingIndexer{

    @Override
    protected void index(Indexable indexable, Parser.Result results, Context context) {
        FileObject fo = results.getSnapshot().getSource().getFileObject();
        String extension = fo.getExt();


        Project project = FileOwnerQuery.getOwner(fo);

        if(project != null) {
            Lookup lookup = project.getLookup();
            quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);
        }
    }
}
