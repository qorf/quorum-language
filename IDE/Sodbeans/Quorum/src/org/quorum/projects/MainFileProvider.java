/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
//import quorum.Libraries.Language.Compile.Compiler;
import org.quorum.support.Utility;

/**
 *
 * @author stefika
 */
public class MainFileProvider {
    private final QuorumProject project;
    private FileObject mainFile = null;
    private boolean checked = false;
    protected static final String SOURCE_CODE_DIRECTORY = "SourceCode";
    protected static final String PROJET_PROPERTIES_DIRECTORY = "Project";
    protected static final String BUILD_DIRECTORY = "Build";
    protected static final String DISTRIBUTION_DIRECTORY = "Run";
    protected static final String DOCUMENTS_DIRECTORY = "Documentation";

    MainFileProvider(QuorumProject proj) {
        this.project = proj;
    }
    
    public FileObject getBuildDirectory() {
        FileObject dir = project.getProjectDirectory();
        FileObject build = dir.getFileObject(BUILD_DIRECTORY);
        if(build == null || !build.isFolder()) { 
            try {
                //create it if it doesn't exist
                build = dir.createFolder(BUILD_DIRECTORY);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return build;
    }
    
    public FileObject getDistributionDirectory() {
        FileObject dir = project.getProjectDirectory();
        FileObject run = dir.getFileObject(DISTRIBUTION_DIRECTORY);
        if(run == null || !run.isFolder()) { 
            try {
                //create it if it doesn't exist
                run = dir.createFolder(DISTRIBUTION_DIRECTORY);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return run;
    }
    
    public FileObject getDocumentsDirectory() {
        FileObject dir = project.getProjectDirectory();
        FileObject run = dir.getFileObject(DOCUMENTS_DIRECTORY);
        if(run == null || !run.isFolder()) { 
            try {
                //create it if it doesn't exist
                run = dir.createFolder(DOCUMENTS_DIRECTORY);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return run;
    }

    public FileObject getMainFile() {
        //Try to look up the main file in the project properties
        //the first time this is called; no need to look it up
        //every time, either it's there or it's not and when the
        //user sets it we'll save it when the project is closed
        if (mainFile == null && !checked) {
            checked = true;
            Properties props = project.getLookup().lookup(Properties.class);

            String path = props.getProperty(QuorumProject.KEY_MAINFILE);
            if (path != null) {
                FileObject projectDir = project.getProjectDirectory();
                mainFile = projectDir.getFileObject(path);
                //Since a main file was found, we can actually compile.
                //get the rest of the files here and cache them.
                project.SetMain(Utility.toQuorumFile(mainFile));
            }
        }
        if (mainFile != null && !mainFile.isValid()) {
            return null;
        }
        return mainFile;
    }
    
    public boolean isMainFile (FileObject obj) {
        return obj.equals(getMainFile());
    }

    public FileObject[] getSourceFiles() {
        FileObject[] files = project.getProjectDirectory().getChildren();

        for(int i = 0; i < files.length; i++) {
            if(files[i].getName().compareToIgnoreCase(SOURCE_CODE_DIRECTORY) == 0) {
                //we've found the src directory, rip files from it.
                FileObject src = files[i];
                FileObject[] srcFiles = src.getChildren();
                return srcFiles;
            }
        }
        return null;
    }

    public void setMainFile(FileObject file) {
        String projPath = project.getProjectDirectory().getPath();
        
        
        assert file == null ||
                file.getPath().startsWith(projPath) :
                "Main file not under project";

        boolean change = ((mainFile == null) != (file == null)) ||
                (mainFile != null && !mainFile.equals(file));

        if (change) {
            mainFile = file;
            //Get the project properties (loaded from
            //$PROJECT/pvproject/project.properties)
            Properties props = project.getLookup().lookup(
                    Properties.class);

            //Store the relative path from the project root as the main file
            String relPath = file.getPath().substring(projPath.length());
            props.put(QuorumProject.KEY_MAINFILE, relPath);
            project.SetMain(Utility.toQuorumFile(file));
//            Compiler compiler = project.getCompiler();
//            if(compiler != null) {
//                File toFile = FileUtil.toFile(file);
//                compiler.SetMain(Utility.toQuorumFile(toFile));
//            }
        }
    }
}
