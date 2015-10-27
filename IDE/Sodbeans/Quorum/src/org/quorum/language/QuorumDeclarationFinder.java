/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import javax.swing.text.Document;
import org.netbeans.modules.csl.api.DeclarationFinder;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.spi.ParserResult;

/**
 *
 * @author stefika
 */
public class QuorumDeclarationFinder implements DeclarationFinder{

    @Override
    public DeclarationLocation findDeclaration(ParserResult pr, int i) {
        return null;
    }

    @Override
    public OffsetRange getReferenceSpan(Document dcmnt, int i) {
        return null;
    }
    
}
