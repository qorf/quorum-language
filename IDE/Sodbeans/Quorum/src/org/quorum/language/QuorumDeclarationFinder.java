/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.util.List;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.csl.api.DeclarationFinder;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.spi.ParserResult;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.QualifiedName_;
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
        String path = lookup.getPath();
        java.io.File ioFile = new java.io.File(path);
        Class_ clazz = table.GetClassInFile(ioFile.getAbsolutePath());
        if(clazz == null) {
            return DeclarationLocation.NONE;
        }
        
        DeclarationLocation done = null;
        Iterator_ iterator = clazz.GetVariables();
        if(iterator != null) {
            done = checkVariables(table, iterator, caretPosition);
        }
        
        Iterator_ parents = clazz.GetUnresolvedParents();
        while(parents.HasNext() && done == DeclarationLocation.NONE) {
            QualifiedName_ parent = (QualifiedName_) parents.Next();
            if(parent != null) {
                int returnIndex = parent.GetIndex();
                int returnIndexEnd = parent.GetIndexEnd();
                if(caretPosition >= returnIndex && caretPosition <= returnIndexEnd + 1) {
                    Class_ parentClazz = clazz.GetValidUseName(parent.GetName());
                    if(parentClazz != null) {
                        File_ file = parentClazz.GetFile();
                        FileObject fo = org.quorum.support.Utility.toFileObject(file);
                        DeclarationLocation decl = new DeclarationLocation(fo, parentClazz.GetIndex());
                        return decl;
                    }
                }
            }
        }
        
        if(done == DeclarationLocation.NONE) {
            Iterator_ actions = clazz.GetActions();
            while(actions != null && actions.HasNext() && done == DeclarationLocation.NONE) {
                Action_ next = (Action_) actions.Next();
                if(caretPosition >= next.GetIndex() && caretPosition <= next.GetIndexEnd() + 1) {
                    //first check if we've hit the return type
                    Type_ returnType = next.GetReturnType();
                    if(returnType != null && !returnType.IsVoid()) {
                        Location_ returnLocation = next.GetReturnLocation();
                        if(returnLocation != null) {
                            int returnIndex = returnLocation.GetIndex();
                            int returnIndexEnd = returnLocation.GetIndexEnd();
                            if(caretPosition >= returnIndex && caretPosition <= returnIndexEnd + 1) {
                                String key = returnType.GetStaticKey();
                                Class_ returnClazz = table.GetClass(key);
                                if(returnClazz != null) {
                                    File_ file = returnClazz.GetFile();
                                    FileObject fo = org.quorum.support.Utility.toFileObject(file);
                                    DeclarationLocation decl = new DeclarationLocation(fo, returnClazz.GetIndex());
                                    return decl;
                                }
                            }
                        }
                    }
                    
                    //it wasn't the return type, check the parameters
                    if(done == DeclarationLocation.NONE) {
                        Iterator_ parameters = next.GetParameterIterator();
                        done = checkVariables(table, parameters, caretPosition);
                    }
                    
                    //if we still haven't found anything, check the variables
                    if(done == DeclarationLocation.NONE) {
                        Iterator_ locals = next.GetAllLocalVariables();
                        done = checkVariables(table, locals, caretPosition);
                    }
                }
                
                //check if there are any declarations for this method in this file
                if(done == DeclarationLocation.NONE) {
                    Iterator_ calls = next.GetActionCalls();
                    while(calls.HasNext()) {
                        ActionCallResolution_  call = (ActionCallResolution_) calls.Next();
                        Location_ nextLocation = call.Get_Libraries_Language_Compile_Symbol_ActionCallResolution__location_();
                        int locationIndex = nextLocation.GetIndex();
                        int locationIndexEnd = nextLocation.GetIndexEnd();
                        if(caretPosition >= locationIndex && caretPosition <= locationIndexEnd + 1) {
                            Action_ resolved = call.Get_Libraries_Language_Compile_Symbol_ActionCallResolution__resolvedAction_();
                            Class_ parentClass = resolved.GetParentClass();
                            File_ file = parentClass.GetFile();
                            FileObject fo = org.quorum.support.Utility.toFileObject(file);
                            DeclarationLocation decl = new DeclarationLocation(fo, resolved.GetIndex());
                            return decl;
                        }
                    }
                }
            }
        }
        return done;
    }
    
    public DeclarationLocation checkVariables(SymbolTable_ table, Iterator_ iterator, int caretPosition) {
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
                Location_ location = next.GetTypeLocation();
                if(location != null) {
                    int typeIndex = location.GetIndex();
                    int typeIndexEnd = location.GetIndexEnd();
                    if(caretPosition >= typeIndex && caretPosition <= typeIndexEnd + 1) {
                        Type_ type = next.GetType();
                        String key = type.GetStaticKey();
                        Class_ clazz = table.GetClass(key);
                        if(clazz != null) {
                            File_ file = clazz.GetFile();
                            FileObject fo = org.quorum.support.Utility.toFileObject(file);
                            DeclarationLocation decl = new DeclarationLocation(fo, clazz.GetIndex());
                            return decl;
                        }
                    }
                }
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
