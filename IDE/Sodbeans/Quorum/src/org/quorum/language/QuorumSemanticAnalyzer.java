/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.netbeans.modules.csl.api.ColoringAttributes;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.api.SemanticAnalyzer;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Class_;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable_;
import quorum.Libraries.Language.Compile.Symbol.Variable_;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumSemanticAnalyzer extends SemanticAnalyzer<QuorumParserResult> {
    boolean cancel = false;
    Map<OffsetRange, Set<ColoringAttributes>> highlighting = new HashMap<>();
    
    @Override
    public Map<OffsetRange, Set<ColoringAttributes>> getHighlights() {
        return highlighting;
    }

    @Override
    public void run(QuorumParserResult parserResult, SchedulerEvent arg1) {
        resume();

        if (isCancelled()) {
            return;
        }
        
        CompilerResult_ quorumResult = parserResult.getRecentResult();
        if(quorumResult == null) {
            return;
        }
        //ProjectInformation info = parserResult.getInfo();
        String source = quorumResult.Get_Libraries_Language_Compile_CompilerResult__source_();//info.Get_Libraries_Language_Compile_ProjectInformation__source_();
        File_ loc = quorumResult.Get_Libraries_Language_Compile_CompilerResult__sourceLocation_();//info.Get_Libraries_Language_Compile_ProjectInformation__sourceLocation_();
        if(quorumResult == null) {
            return;
        }
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        Class_ clazz = table.GetClassInFile(loc.GetAbsolutePath());
        if(clazz == null) {
            return;
        }
        
        Location_ classNameLocation = clazz.GetNameLocation();
        highlighting.clear();
        if(classNameLocation != null) {
            int index = classNameLocation.GetIndex();
            int end = classNameLocation.GetIndexEnd();
            OffsetRange useRange = new OffsetRange(index, end + 1);
            highlighting.put(useRange, ColoringAttributes.CLASS_SET);
        }
        
        Iterator_ actions = clazz.GetActions();
        while(actions != null && actions.HasNext()) {
            Action_ next = (Action_) actions.Next();
            Location_ name = next.GetNameLocation();
            if(name != null) {
                int index = name.GetIndex();
                int end = name.GetIndexEnd();
                if(index > -1 && end > -1) {
                    OffsetRange useRange = new OffsetRange(index, end + 1);
                    //Set<ColoringAttributes> useColors = new HashSet<>();
                    //useColors.add(ColoringAttributes.METHOD);
                    highlighting.put(useRange, ColoringAttributes.METHOD_SET);
                }
            }
            
            Iterator_ locals = next.GetAllLocalVariables();
            while(locals.HasNext()) {
                Variable_ var = (Variable_) locals.Next();
                if(var.IsVisibleToDebugger()) {
                    int index = var.GetIndex();
                    int end = var.GetIndexEnd();
                    if(index != -1 && end != -1) {
                        OffsetRange range = new OffsetRange(index, end + 1);

                        Set<ColoringAttributes> colors = new HashSet<>();
                        if(var.IsParameter()) {
                            colors.add(ColoringAttributes.PARAMETER);
                        } else {
                            colors.add(ColoringAttributes.LOCAL_VARIABLE);
                        }
                        if(!var.IsUsed()) {
                            colors.add(ColoringAttributes.UNUSED);
                        }
                        highlighting.put(range, colors);
                    }
                }
            }
        } 
        
        Iterator_ iterator = clazz.GetVariables();
        if(iterator == null) {
            return;
        }
        
        while(iterator.HasNext()) {
            Variable_ next = (Variable_) iterator.Next();
            int index = next.GetIndex();
            int end = next.GetIndexEnd();
            if(index != -1 && end != -1) {
                OffsetRange range = new OffsetRange(index, end + 1);
                Set<ColoringAttributes> colors = new HashSet<>();
                colors.add(ColoringAttributes.FIELD);
                if(!next.IsUsed()) {
                    colors.add(ColoringAttributes.UNUSED);
                }
                highlighting.put(range, colors);
            }
            
            //now create ranges for all of its uses
            Iterator_ uses = next.GetUseLocations();
            while(uses.HasNext()) {
                Location_ use = (Location_) uses.Next();
                if(use.GetIndex() != -1 && use.GetIndexEnd() != -1) {
                    OffsetRange useRange = new OffsetRange(use.GetIndex(), use.GetIndexEnd() + 1);
                    Set<ColoringAttributes> useColors = new HashSet<>();
                    useColors.add(ColoringAttributes.FIELD);
                    highlighting.put(useRange, useColors);
                }
            }
        }
    }

    @Override
    public int getPriority() {
        return 1;
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
