/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.netbeans.modules.csl.api.InstantRenamer;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.spi.ParserResult;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Class_;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable_;
import quorum.Libraries.Language.Compile.Symbol.Variable_;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumInstantRenamer implements InstantRenamer{

    @Override
    public boolean isRenameAllowed(ParserResult parserResult, int i, String[] strings) {
        return true;
    }

    @Override
    public Set<OffsetRange> getRenameRegions(ParserResult parserResult, int caretPosition) {
        Lookup global = Utilities.actionsGlobalContext();
        if(global == null) {
            return Collections.EMPTY_SET;
        }
        FileObject lookup = global.lookup(FileObject.class);
        if(lookup == null) {
            return Collections.EMPTY_SET;
        }
        
        CompilerResult_ quorumResult = ((QuorumParserResult) parserResult).getRecentResult();
        if(quorumResult == null) {
            return Collections.EMPTY_SET;
        }
        
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        String path = lookup.getPath();
        java.io.File ioFile = new java.io.File(path);
        Class_ clazz = table.GetClassInFile(ioFile.getAbsolutePath());
        if(clazz == null) {
            return Collections.EMPTY_SET;
        }
        
        
        Iterator_ iterator = clazz.GetVariables();
        if(iterator == null) {
            return Collections.EMPTY_SET;
        }
        
        Set<OffsetRange> ranges = null;
        
        Iterator_ actions = clazz.GetActions();
        while(actions != null && actions.HasNext()) {
            Action_ next = (Action_) actions.Next();
            if(caretPosition >= next.GetIndex() && caretPosition <= next.GetIndexEnd() + 1) {
                Iterator_ locals = next.GetAllLocalVariables();
                ranges = checkVariables(locals, table, clazz.GetFile(), caretPosition);
            }
        }
        return ranges;
    }
    
    public Set<OffsetRange> checkVariables(Iterator_ iterator, SymbolTable_ table, File_ file, int caretPosition) {
        Set<OffsetRange> ranges = new HashSet<>();
        boolean done = false;
        while(iterator.HasNext() && !done) {
            Variable_ next = (Variable_) iterator.Next();
            int index = next.GetIndex();
            int end = next.GetIndexEnd();
            boolean isIn = false;
            if(caretPosition >= index && caretPosition <= end + 1) {
                isIn = true;
            }
            
            if(!isIn) {
                Iterator_ uses = next.GetUseLocations();
                while(uses.HasNext() && !isIn) {
                    Location_ use = (Location_) uses.Next();
                    int indexUse = use.GetIndex();
                    int endUse = use.GetIndexEnd();
                    if(caretPosition >= indexUse && caretPosition <= endUse + 1) {
                        isIn = true;
                    }
                }
            }
            
            if(isIn) {
                done = true;
                //now create ranges for all of its uses
                Iterator_ uses = next.GetUseLocations();
                while(uses.HasNext()) {
                    Location_ use = (Location_) uses.Next();
                    OffsetRange useRange = new OffsetRange(use.GetIndex(), use.GetIndexEnd() + 1);
                    ranges.add(useRange);
                }
            }
        }
        return ranges;
    }
}
