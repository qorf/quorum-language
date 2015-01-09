/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import java.io.File;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.api.project.ui.ProjectGroup;
import org.netbeans.modules.csl.api.Severity;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import quorum.Libraries.Containers.Blueprints.Iterator$Interface;
import quorum.Libraries.Language.Compile.CompilerErrorManager$Interface;
import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.Language.Compile.CompilerError$Interface;
/**
 *
 * @author stefika
 */
public class QuorumParser extends Parser{
    Snapshot snapshot;
    Task task;
    SourceModificationEvent sme;
    private ArrayList<QuorumError> fileErrors = new ArrayList<QuorumError>();
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
                   
                    String totalPath = quorumFile.GetAbsolutePath();
                    compiler.ParseSingle(string, quorumFile);
                    fileErrors.clear();
                    
                    CompilerErrorManager$Interface errors = compiler.GetCompilerErrorManager();
                    Iterator$Interface it = errors.GetIterator();
                    while(it.HasNext()) {
                        CompilerError$Interface next = (CompilerError$Interface) it.Next();
                        String displayName = next.GetDisplayName();
                        String description = next.GetErrorMessage();
                        String key = next.GetDisplayName();
                        String path = next.GetAbsolutePath();
                        File file = new File(path);
                        FileObject fo2 = FileUtil.toFileObject(file);
                        int start = next.GetIndex();
                        int end = next.GetIndexEnd();
                        Severity severity = Severity.ERROR;
                        QuorumError error = new QuorumError(displayName, description, key, fo2, start, end, severity);
                        fileErrors.add(error);
                        
                    }
                } catch (RuntimeException exc) {
                    exc.printStackTrace();
                }
                
                int a = 5;
            }
            
            //if this object is not null, 
            
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
