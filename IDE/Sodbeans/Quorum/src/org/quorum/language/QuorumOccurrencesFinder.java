/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.netbeans.modules.csl.api.ColoringAttributes;
import org.netbeans.modules.csl.api.OccurrencesFinder;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.Symbol.ActionCallResolution_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Class_;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable_;
import quorum.Libraries.Language.Compile.Symbol.Type_;
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
        String path = lookup.getPath();
        java.io.File ioFile = new java.io.File(path);
        Class_ clazz = table.GetClassInFile(ioFile.getAbsolutePath());
        if(clazz == null) {
            highlighting = null;
            return;
        }
        
        
        Iterator_ iterator = clazz.GetVariables();
        if(iterator == null) {
            return;
        }
        
        boolean done = checkVariables(iterator, table, clazz.GetFile());
        
        if(!done) {
            Iterator_ actions = clazz.GetActions();
            while(actions != null && actions.HasNext() && !done) {
                Action_ next = (Action_) actions.Next();
                if(caretPosition >= next.GetIndex() && caretPosition <= next.GetIndexEnd() + 1) {
                    Iterator_ locals = next.GetAllLocalVariables();
                    done = checkVariables(locals, table, clazz.GetFile());
                    
                    //not a variable, check if I'm clicked on the action name
                    if(!done) {
                        Location_ nameLocation = next.GetNameLocation();
                        if(nameLocation != null && caretPosition >= nameLocation.GetIndex() && caretPosition <= nameLocation.GetIndexEnd() + 1) {
                            OffsetRange callRange = new OffsetRange(nameLocation.GetIndex(), nameLocation.GetIndexEnd() + 1);
                            addRange(callRange, ColoringAttributes.MARK_OCCURRENCES);
                            
                            //now grab all the calls in this file and add them.
                            Iterator_ callLocations = next.GetCallLocationIterator(clazz.GetFile());
                            while(callLocations != null && callLocations.HasNext()) {
                                Location_ callLoc = (Location_) callLocations.Next();
                                File_ clazzFile = clazz.GetFile();
                                File_ locFile = callLoc.GetFile();
                                if(clazzFile.GetAbsolutePath().compareTo(locFile.GetAbsolutePath()) == 0) {
                                    OffsetRange callRange2 = new OffsetRange(callLoc.GetIndex(), callLoc.GetIndexEnd() + 1);
                                    addRange(callRange2, ColoringAttributes.MARK_OCCURRENCES);
                                }
                            }
                            done = true;
                        }
                    }
                    
                    //check if I'm in an action call
                    if(!done) {
                        Iterator_ calls = next.GetActionCalls();
                        while(calls.HasNext() && !done) {
                            ActionCallResolution_ call = (ActionCallResolution_) calls.Next();
                            Location_ loc = call.Get_Libraries_Language_Compile_Symbol_ActionCallResolution__location_();
                            if(caretPosition >= loc.GetIndex() && caretPosition <= loc.GetIndexEnd() + 1) {
                                Action_ resolved = call.Get_Libraries_Language_Compile_Symbol_ActionCallResolution__resolvedAction_();
                                Iterator_ callLocations = resolved.GetCallLocationIterator(clazz.GetFile());
                                while(callLocations != null && callLocations.HasNext()) {
                                    Location_ callLoc = (Location_) callLocations.Next();
                                    OffsetRange callRange = new OffsetRange(callLoc.GetIndex(), callLoc.GetIndexEnd() + 1);
                                    addRange(callRange, ColoringAttributes.MARK_OCCURRENCES);
                                }
                                
                                //if the action is in this file, highlight its name
                                Location_ nameLocation = resolved.GetNameLocation();
                                if(nameLocation.GetFile().GetAbsolutePath().compareTo(clazz.GetFile().GetAbsolutePath())==0) {
                                    OffsetRange callRange = new OffsetRange(nameLocation.GetIndex(), nameLocation.GetIndexEnd() + 1);
                                    addRange(callRange, ColoringAttributes.MARK_OCCURRENCES);
                                }
                                done = true;
                            }
                        }
                    }
                    
                    
                }
            }
        }
        
        //in this case, no cases were found to highlight.
        //as such, we should return an empty map to indicate this.
        if(!done) {
            highlighting = new HashMap<>();
        }
    }
    
    private void addRange(OffsetRange range, ColoringAttributes color) {
        if(!highlighting.containsKey(range)) {
            highlighting.put(range, ColoringAttributes.MARK_OCCURRENCES);
        }
    }
    
    public boolean checkVariables(Iterator_ iterator, SymbolTable_ table, File_ file) {
        boolean done = false;
        while(iterator.HasNext() && !done) {
            Variable_ next = (Variable_) iterator.Next();
            int index = next.GetIndex();
            int end = next.GetIndexEnd();
            boolean isIn = false;
            if(caretPosition >= index && caretPosition <= end + 1) {
                isIn = true;
            }
            
            Location_ typeLocation = next.GetTypeLocation();
            if(typeLocation == null) {
                int a = 54;
            }
            if(typeLocation != null && caretPosition >= typeLocation.GetIndex() && caretPosition <= typeLocation.GetIndexEnd() + 1) {
                Type_ type = next.GetType();
                String key = type.GetStaticKey();
                Class_ clazz = table.GetClass(key);
                if(clazz != null) {
                    Iterator_ uses = clazz.GetUseLocationIterator(file);
                    if(uses != null) {
                        while(uses.HasNext()) {
                            Location_ use = (Location_) uses.Next();
                            OffsetRange useRange = new OffsetRange(use.GetIndex(), use.GetIndexEnd() + 1);
                            addRange(useRange, ColoringAttributes.MARK_OCCURRENCES);
                        }
                    }
                }
                return true;
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
                    addRange(useRange, ColoringAttributes.MARK_OCCURRENCES);
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
