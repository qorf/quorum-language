/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.refactoring.spi.ui.ActionsImplementationProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author stefika
 */
@ServiceProvider(service = ActionsImplementationProvider.class, position=100)
public class QuorumRefactoringProvider extends ActionsImplementationProvider {
    
    @Override
    public boolean canFindUsages(Lookup lookup) {
        return false;
    }

    @Override
    public boolean canRename(Lookup lookup) {
        return false;
    }
    
    @Override
    public boolean canCopy(Lookup lookup) {
        return false;
    }

    @Override
    public boolean canDelete(Lookup lookup) {
        return false;
    }

    @Override
    public boolean canMove(Lookup lookup) {
        return false;
    }
    
    @Override
    public void doFindUsages(Lookup lookup) {
    }
    
    @Override
    public void doRename(Lookup lookup) {
        
    }

    @Override
    public void doCopy(Lookup lookup) {
        super.doCopy(lookup); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doDelete(Lookup lookup) {
        super.doDelete(lookup); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void doMove(Lookup lookup) {
        
    }
}
