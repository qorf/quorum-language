/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.ArrayList;
import java.util.Collection;
import org.netbeans.api.editor.fold.FoldTemplate;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.fold.FoldTypeProvider;

/**
 *
 * @author stefika
 */
@MimeRegistration(mimeType = "text/x-quorum", service = FoldTypeProvider.class, position = 1000)
public class QuorumFoldTypeProvider implements FoldTypeProvider{
    private static final Collection<FoldType> VALID_FOLD_TYPES = new ArrayList<>(5);

    public static final FoldType CLASS = FoldType.NESTED.derive(
            "class", "Quorum_Class", FoldTemplate.DEFAULT_BLOCK);
    
    public static final FoldType ACTION = FoldType.NESTED.derive(
            "action", "Quorum_Action", FoldTemplate.DEFAULT_BLOCK);
    
    public static final FoldType DOCUMENTATION = FoldType.DOCUMENTATION.override(
            "Quorum_Documentation", new FoldTemplate(2, 2, "/*...*/"));

    static {
        VALID_FOLD_TYPES.add(CLASS);
        VALID_FOLD_TYPES.add(ACTION);
        VALID_FOLD_TYPES.add(DOCUMENTATION);
    }

    @Override
    public Collection getValues(Class type) {
        return type == FoldType.class ? VALID_FOLD_TYPES : null;
    }

    @Override
    public boolean inheritable() {
        return false;
    }
}
