/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.text.Document;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.api.StructureItem;
import org.netbeans.modules.csl.api.StructureScanner;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Source;
import org.quorum.language.structure.QuorumClassStructureItem;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.ProjectInformation;
import quorum.Libraries.Language.Compile.QualifiedName_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Class_;
import quorum.Libraries.Language.Compile.Symbol.Documentation_;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable_;
import quorum.Libraries.Language.Compile.Symbol.Variable_;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumStructureScanner implements StructureScanner{

    @Override
    public List<? extends StructureItem> scan(ParserResult result) {
        QuorumParserResult parserResult = (QuorumParserResult) result;
        CompilerResult_ quorumResult = parserResult.getRecentResult();
        ProjectInformation info = parserResult.getInfo();
        String source = info.Get_Libraries_Language_Compile_ProjectInformation__source_();
        File_ loc = info.Get_Libraries_Language_Compile_ProjectInformation__sourceLocation_();
        
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        Class_ clazz = table.GetClassInFile(loc.GetAbsolutePath());
        if(clazz == null) {
            return Collections.EMPTY_LIST;
        }
        QuorumClassStructureItem c = new QuorumClassStructureItem();
        c.setClazz(clazz);
        
        List<StructureItem> items = new LinkedList<>();
        items.add(c);
        return items;
    }

    @Override
    public Map<String, List<OffsetRange>> folds(ParserResult result) {
        QuorumParserResult parserResult = (QuorumParserResult) result;
        CompilerResult_ quorumResult = parserResult.getRecentResult();
        ProjectInformation info = parserResult.getInfo();
        String source = info.Get_Libraries_Language_Compile_ProjectInformation__source_();
        File_ loc = info.Get_Libraries_Language_Compile_ProjectInformation__sourceLocation_();
        
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        Class_ clazz = table.GetClassInFile(loc.GetAbsolutePath());
        if(clazz == null) {
            return Collections.EMPTY_MAP;
        }
        List<OffsetRange> classRanges = new LinkedList<>();
        List<OffsetRange> actionRanges = new LinkedList<>();
        List<OffsetRange> documentationRanges = new LinkedList<>();
        
        //add class
        int classStart = clazz.GetIndex();
        String classValue = "class " + clazz.GetName();
        classStart = classStart + classValue.length();
        
        int classEnd = clazz.GetIndexEnd();
        OffsetRange classRange = new OffsetRange(classStart, classEnd + 1);
        classRanges.add(classRange);
        
        //get all of the unresolved use statements and check their locations
        Iterator_ unresolved = clazz.GetUnresolvedUseStatements();
        int useStart = Integer.MAX_VALUE;
        int useEnd = 0;
        boolean hasUnresolved = false;
        while(unresolved.HasNext()) {
            hasUnresolved = true;
            QualifiedName_ next = (QualifiedName_) unresolved.Next();
            if(next.GetIndex() < useStart) {
                useStart = next.GetIndex();
            }
            if(next.GetIndexEnd() > useEnd) {
                useEnd = next.GetIndexEnd();
            }
        }
        
        if(hasUnresolved) {
            OffsetRange useRange = new OffsetRange(useStart + 3, useEnd + 1);
            classRanges.add(useRange);
        }
        
        Documentation_ classDoc = clazz.GetDocumentation();
        if(classDoc != null) {
            int index = classDoc.GetIndex();
            int indexEnd = classDoc.GetIndexEnd();
            OffsetRange docRange = new OffsetRange(index, indexEnd + 1);
            documentationRanges.add(docRange);
        }
        //create the final map
        Map<String, List<OffsetRange>> map = new HashMap<>();
        map.put(QuorumFoldTypeProvider.CLASS.code(), classRanges);
        
        
        //add in all of the actions.
        Iterator_ actions = clazz.GetActions();
        while(actions.HasNext()) {
            Action_ action = (Action_) actions.Next();
            int actionStart = action.GetHeaderLocation().GetIndexEnd() + 1;
            
            int actionEnd = action.GetIndexEnd();
                        
            OffsetRange actionRange = new OffsetRange(actionStart, actionEnd + 1);
            actionRanges.add(actionRange);
            Documentation_ actionDoc = action.GetDocumentation();
            if(actionDoc != null) {
                int index = actionDoc.GetIndex();
                int indexEnd = actionDoc.GetIndexEnd();
                OffsetRange docRange = new OffsetRange(index, indexEnd + 1);
                documentationRanges.add(docRange);
            }
        }
        if(!actionRanges.isEmpty()) {
            map.put(QuorumFoldTypeProvider.ACTION.code(), actionRanges);
        }
        
        if(!documentationRanges.isEmpty()) {
            map.put(QuorumFoldTypeProvider.DOCUMENTATION.code(), documentationRanges);
        }
        
        //finally update the document itself with the code folds
        Source snapshotSource = result.getSnapshot().getSource();
        if(source == null) {
            return map;
        }
        Document document = snapshotSource.getDocument(false);
        
        if (document != null) {
            document.putProperty("LAST_CORRECT_FOLDING_PROPERY", map);
        }
        
        //give back the map to the system
        return map;
    }
    
    @Override
    public Configuration getConfiguration() {
        return new Configuration(true, true);
    }
}
