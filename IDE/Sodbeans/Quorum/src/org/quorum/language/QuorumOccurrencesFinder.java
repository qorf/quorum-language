/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.HashMap;
import java.util.Map;
import org.netbeans.modules.csl.api.ColoringAttributes;
import org.netbeans.modules.csl.api.OccurrencesFinder;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.ProjectInformation;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Class_;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable_;
import quorum.Libraries.Language.Compile.Symbol.Variable_;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumOccurrencesFinder extends OccurrencesFinder<QuorumParserResult>{
    private int caretPosition = 0;
    boolean cancel = false;
    Map<OffsetRange, ColoringAttributes> highlighting = new HashMap<>();
    FileObject file = null;
    
    @Override
    public void setCaretPosition(int i) {
        caretPosition = i;
    }

    @Override
    public Map<OffsetRange, ColoringAttributes> getOccurrences() {
        return highlighting;
    }

    @Override
    public void run(QuorumParserResult parserResult, SchedulerEvent arg1) {
        resume();

        if (isCancelled()) {
            return;
        }
        
        //check to see if we are parsing a different file. We don't want to 
        //colorize and return something that we are not currently editing.
        Lookup global = Utilities.actionsGlobalContext();
        if(global == null) {
            return;
        }
        FileObject lookup = global.lookup(FileObject.class);
        if(lookup == null) {
            return;
        }
        
        highlighting = new HashMap<>();
        CompilerResult_ quorumResult = parserResult.getRecentResult();
        if(quorumResult == null) {
            highlighting = null;
            return;
        }
        
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        Class_ clazz = table.GetClassInFile(lookup.getPath());
        if(clazz == null) {
            highlighting = null;
            return;
        }
        
        
        Iterator_ iterator = clazz.GetVariables();
        if(iterator == null) {
            return;
        }
        
        boolean done = checkVariables(iterator);
        
        if(!done) {
            Iterator_ actions = clazz.GetActions();
            while(actions != null && actions.HasNext() && !done) {
                Action_ next = (Action_) actions.Next();
                if(caretPosition >= next.GetIndex() && caretPosition <= next.GetIndexEnd() + 1) {
                    Iterator_ locals = next.GetAllLocalVariables();
                    done = checkVariables(locals);
                }
            }
        }
        
        if(!done) {
            highlighting = null;
        }
    }
    
    public boolean checkVariables(Iterator_ iterator) {
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
                OffsetRange range = new OffsetRange(index, end + 1);
                highlighting.put(range, ColoringAttributes.MARK_OCCURRENCES);
                
                //now create ranges for all of its uses
                Iterator_ uses = next.GetUseLocations();
                while(uses.HasNext()) {
                    Location_ use = (Location_) uses.Next();
                    OffsetRange useRange = new OffsetRange(use.GetIndex(), use.GetIndexEnd() + 1);
                    highlighting.put(useRange, ColoringAttributes.MARK_OCCURRENCES);
                }
            }
        }
        return done;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public synchronized void cancel() {
        cancel = true;
    }
    
    protected synchronized boolean isCancelled() {
        return cancel;
    }

    protected synchronized void resume() {
        cancel = false;
    }
}
