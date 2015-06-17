/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.csl.api.Severity;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.quorum.projects.QuorumProject;
import org.quorum.support.Utility;
import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Containers.Blueprints.Iterator$Interface;
import quorum.Libraries.Language.Compile.CompilerErrorManager$Interface;
import quorum.Libraries.Language.Compile.CompilerError$Interface;
import quorum.Libraries.Language.Compile.CompilerResult$Interface;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable$Interface;
/**
 *
 * @author stefika
 */
public class QuorumParser extends Parser{
    Snapshot snapshot;
    Task task;
    SourceModificationEvent sme;
    private ArrayList<QuorumError> fileErrors = new ArrayList<QuorumError>();
    quorum.Libraries.Language.Compile.ProjectInformation info = new quorum.Libraries.Language.Compile.ProjectInformation();
    private static final Logger logger = Logger.getLogger(QuorumParser.class.getName());
    
    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        this.snapshot = snapshot;
        this.task = task;
        this.sme = sme;
        
        if(sme.sourceChanged()) {
            FileObject fo = snapshot.getSource().getFileObject();
            String extension = fo.getExt();
            
            if(extension.compareTo("quorum") != 0) {
                return;
            }
            
            Project project = FileOwnerQuery.getOwner(fo);
            
            if(project != null) {
                Lookup lookup = project.getLookup();
                quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);

                String string = snapshot.getText().toString();

                try {
                    File fileToParse = FileUtil.toFile(fo);
                    String absp = fileToParse.getAbsolutePath();
                    quorum.Libraries.System.File quorumFile = new quorum.Libraries.System.File();
                    File working = FileUtil.toFile(project.getProjectDirectory());
                    String workingPath = working.getAbsolutePath();
                    String finalPath = absp.substring(workingPath.length());
                    quorumFile.SetWorkingDirectory(workingPath);
                    quorumFile.SetPath(finalPath);
                    
                    FileObject projectDirectory = project.getProjectDirectory();
                    File directory = FileUtil.toFile(projectDirectory);
                    File file = new File(directory.getAbsolutePath() + "/" + QuorumProject.SOURCES_DIR);
                    quorum.Libraries.System.File sourceFolder = Utility.toQuorumFile(file);
                    Array$Interface listing = sourceFolder.GetDirectoryListing();
                    
                    info.Set$Libraries$Language$Compile$ProjectInformation$source(string);
                    info.Set$Libraries$Language$Compile$ProjectInformation$sourceLocation(quorumFile);
                    info.Set$Libraries$Language$Compile$ProjectInformation$projectFiles(listing);
                    CompilerResult$Interface result = compiler.ParseRepeat(info);                 
                    
                    CompilerErrorManager$Interface errors = result.Get$Libraries$Language$Compile$CompilerResult$compilerErrorManager();
                    if(errors.IsCompilationErrorFree()) {
                        fileErrors.clear();
                        if(result != null && project instanceof QuorumProject) {
                            QuorumProject qp = (QuorumProject) project;
                            qp.setSandboxCompilerResult(result);
                        }
                        
                    } else {
                        fileErrors.clear();
                        Iterator$Interface it = errors.GetIterator();
                        while(it.HasNext()) {
                            CompilerError$Interface next = (CompilerError$Interface) it.Next();
                            String displayName = next.GetDisplayName();
                            String description = next.GetErrorMessage();
                            String key = next.GetDisplayName();
                            String path = next.GetAbsolutePath();
                            File file2 = new File(path);
                            FileObject fo2 = FileUtil.toFileObject(file2);
                            int start = next.GetIndex();
                            int end = next.GetIndexEnd();
                            Severity severity = Severity.ERROR;
                            QuorumError error = new QuorumError(displayName, description, key, fo2, start, end, severity);
                            fileErrors.add(error);
                        }
                    }
                } catch (RuntimeException ex) {
                    logger.log(java.util.logging.Level.SEVERE, string, ex);
                }
            }
        }
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        return new QuorumParserResult(snapshot, this);
    }

    @Override
    public void addChangeListener(ChangeListener cl) {
    }

    @Override
    public void removeChangeListener(ChangeListener cl) {
    }

    /**
     * @return the fileErrors
     */
    public ArrayList<QuorumError> getFileErrors() {
        return fileErrors;
    }
    
}
