/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.HashMap;
import java.util.List;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.csl.api.ColoringAttributes;
import org.netbeans.modules.csl.api.DeclarationFinder;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.spi.ParserResult;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import quorum.Libraries.Containers.Blueprints.Iterator_;
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
public class QuorumDeclarationFinder implements DeclarationFinder{

    @Override
    public DeclarationLocation findDeclaration(ParserResult parserResult, int caretPosition) {
        //check to see if we are parsing a different file. We don't want to 
        //colorize and return something that we are not currently editing.
        Lookup global = Utilities.actionsGlobalContext();
        if(global == null) {
            return DeclarationLocation.NONE ;
        }
        FileObject lookup = global.lookup(FileObject.class);
        if(lookup == null) {
            return DeclarationLocation.NONE ;
        }
        
        CompilerResult_ quorumResult = ((QuorumParserResult) parserResult).getRecentResult();
        if(quorumResult == null) {
            return DeclarationLocation.NONE ;
        }
        
        SymbolTable_ table = quorumResult.Get_Libraries_Language_Compile_CompilerResult__symbolTable_();
        Class_ clazz = table.GetClassInFile(lookup.getPath());
        if(clazz == null) {
            return DeclarationLocation.NONE;
        }
        
        Iterator_ iterator = clazz.GetVariables();
        if(iterator == null) {
            return DeclarationLocation.NONE;
        }
        
        DeclarationLocation done = checkVariables(iterator, caretPosition);
        
        if(done == DeclarationLocation.NONE) {
            Iterator_ actions = clazz.GetActions();
            while(actions != null && actions.HasNext() && done == DeclarationLocation.NONE) {
                Action_ next = (Action_) actions.Next();
                if(caretPosition >= next.GetIndex() && caretPosition <= next.GetIndexEnd() + 1) {
                    Iterator_ locals = next.GetAllLocalVariables();
                    done = checkVariables(locals, caretPosition);
                }
            }
        }
        return done;
    }
    
    public DeclarationLocation checkVariables(Iterator_ iterator, int caretPosition) {
        DeclarationLocation done = DeclarationLocation.NONE;
        while(iterator.HasNext() && done == DeclarationLocation.NONE) {
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
                File_ file = next.GetFile();
                FileObject fo = org.quorum.support.Utility.toFileObject(file);
                DeclarationLocation location = new DeclarationLocation(fo, index);
                return location;
            }
        }
        return done;
    }

    @Override
    public OffsetRange getReferenceSpan(Document document, int offset) {
        final BaseDocument doc = (BaseDocument)document;
        doc.readLock();
        OffsetRange range = OffsetRange.NONE;
        try {
            TokenHierarchy<Document> th = TokenHierarchy.get(document);
            List<TokenSequence<?>> sequence = th.embeddedTokenSequences(offset, true);
            if(sequence.isEmpty()) {
                sequence = th.embeddedTokenSequences(offset, false);
            }
            if(!sequence.isEmpty()) {
                TokenSequence<?> tokens = sequence.get(0);
                tokens.move(offset);
                if (tokens.moveNext()) {
                    Token<QuorumTokenId> token = (Token<QuorumTokenId>) tokens.token();
                    if (token != null) {
                        if(token.id().ordinal() == plugins.quorum.Libraries.Language.Compile.QuorumLexer.ID) {
                            range = new OffsetRange(tokens.offset(), tokens.offset() + token.length());
                        }
                    }
                }
            }
        } finally {
            doc.readUnlock();
        }
        return range;
    }
    
}
