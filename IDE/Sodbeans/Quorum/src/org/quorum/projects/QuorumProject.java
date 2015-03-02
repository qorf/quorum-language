/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.debugger.Debugger;
import org.debugger.jdi.JDIDebugger;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.quorum.actions.Build;
import org.quorum.actions.Clean;
import org.quorum.actions.CleanBuild;
import org.quorum.actions.Debug;
import org.quorum.actions.Document;
import org.quorum.actions.Run;
import org.quorum.actions.Stop;
import quorum.Libraries.System.File$Interface;

/**
 *
 * @author stefika
 */
public class QuorumProject implements Project {

    public static final String SOURCES_DIR = "SourceCode";
    public static final String QUORUM_PROJECT_KEY = "Quorum_Project_Keycode";
    public static final String KEY_MAINFILE = "main.file";
    public static final String QUORUM_PROJECT_TYPE = "Quorum_Project_Type";
    public static final String QUORUM_TOMCAT_LOCATION = "Quorum_Tomcat_Location";

    public static final String QUORUM_CONSOLE_PROJECT = "Quorum_Console_Project";
    public static final String QUORUM_WEB_PROJECT = "Quorum_Web_Project";
    public static final String QUORUM_COMPILED_WEB_PROJECT = "Quorum_Compiled_Web_Project";

    public static final String QUORUM_PROJECT_ICON = "org/quorum/resources/project.png";
    public static final String QUORUM_FILE_ICON = "org/quorum/resources/file.png";

    public static final String SOURCE_CODE_DIRECTORY = "SourceCode";
    public static final String PROJET_PROPERTIES_DIRECTORY = "Project";
    public static final String BUILD_DIRECTORY = "Build";
    public static final String DISTRIBUTION_DIRECTORY = "Run";
    public static final String DOCUMENTS_DIRECTORY = "Documentation";

    public static final String MIME_TYPE = "text/x-quorum";
    private final FileObject projectDir;
    private LogicalViewProvider logicalView = new QuorumLogicalView(this);
    private final ProjectState state;
    private Lookup lookup;
    
    private final Debugger debugger = new JDIDebugger();
    private final Document document = new Document(this);
    private final Debug debug = new Debug(this);
    private final Build build = new Build(this);
    private final Clean clean = new Clean(this);
    private final CleanBuild cleanBuild = new CleanBuild(this);
    private final Run run = new Run(this);
    private final quorum.Libraries.Language.Compile.Compiler compiler = 
            new quorum.Libraries.Language.Compile.Compiler();
    private MainFileProvider mainFileProvider = new MainFileProvider(this);
    
    public QuorumProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
        File projectDirectory = FileUtil.toFile(projectDir);
        projectDirectory.getAbsolutePath();
        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File standardInNB = locator.locate("modules/Library", "org.quorum", false);
        
        quorum.Libraries.System.File standardLibrary = new quorum.Libraries.System.File();
        standardLibrary.SetWorkingDirectory(standardInNB.getAbsolutePath());
        standardLibrary.SetPath("Standard");
        
        quorum.Libraries.System.File outputFolder = new quorum.Libraries.System.File();
        outputFolder.SetWorkingDirectory(projectDirectory.getParent());
        outputFolder.SetPath(projectDirectory.getName());
        
        compiler.SetStandardLibraryFolder(standardLibrary);
        compiler.SetOutputFolder(outputFolder);
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    public FileObject getSources(boolean create) {
        FileObject result
                = projectDir.getFileObject(SOURCES_DIR);

        if (result == null && create) {
            try {
                result = projectDir.createFolder(SOURCES_DIR);
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }
        return result;
    }

    @Override
    public Lookup getLookup() {
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[]{
                this, //Project spec requires a project be in its own lookup
                state, //Allow outside code to mark the project as needing saving
                new ActionProviderImpl(), //Provides standard actions like Build and Clean
                new QuorumDeleteOperation(),
                new QuorumCopyOperation(this),
                loadProperties(), //The project properties
                new Info(), //Project information implementation
                logicalView, //Logical view of project implementation
                mainFileProvider, 
                new QuorumPrivilegedTemplates(),
                new QuorumCustomizer(this), getCompiler(),
                debugger
            });
        }
        return lookup;
    }

    public String getExecutableLocation() {
        File$Interface output = getCompiler().GetRunFolder();
        String path = output.GetAbsolutePath();
        
        return path + "/" + getCompiler().GetName() + getCompiler().GetFileExtension();
    }
    
    private Properties loadProperties() {
        FileObject fob = projectDir.getFileObject(QuorumProjectFactory.PROJECT_DIR
                + "/" + QuorumProjectFactory.PROJECT_PROPFILE);
        Properties properties = new NotifyProperties(state);
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (Exception e) {
                Exceptions.printStackTrace(e);
            }
        }
        
        String key = properties.getProperty(KEY_MAINFILE);
        if(key != null) {
            FileObject directory = this.getProjectDirectory();
            FileObject mainFile = directory.getFileObject(key);
            mainFileProvider.setMainFile(mainFile);
        }
        return properties;
    }

    /**
     * @return the debug
     */
    public Debug getDebug() {
        return debug;
    }

    /**
     * @return the build
     */
    public Build getBuild() {
        return build;
    }

    /**
     * @return the clean
     */
    public Clean getClean() {
        return clean;
    }

    /**
     * @return the cleanBuild
     */
    public CleanBuild getCleanBuild() {
        return cleanBuild;
    }

    /**
     * @return the run
     */
    public Run getRun() {
        return run;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @return the compiler
     */
    public quorum.Libraries.Language.Compile.Compiler getCompiler() {
        return compiler;
    }

    private static class NotifyProperties extends Properties {
        private final ProjectState state;

        NotifyProperties(ProjectState state) {
            this.state = state;
        }

        @Override
        public Object put(Object key, Object val) {
            Object result = super.put(key, val);
            if (((result == null) != (val == null)) || (result != null
                    && val != null && !val.equals(result))) {
                state.markModified();
            }
            return result;
        }
    }

    private final class ActionProviderImpl implements ActionProvider {
        private final String[] supported = new String[]{
            ActionProvider.COMMAND_DELETE,
            ActionProvider.COMMAND_COPY,
            ActionProvider.COMMAND_MOVE,
            ActionProvider.COMMAND_BUILD,
            ActionProvider.COMMAND_CLEAN,
            ActionProvider.COMMAND_REBUILD,
            ActionProvider.COMMAND_RUN,
            ActionProvider.COMMAND_DEBUG
        };

        @Override
        public String[] getSupportedActions() {
            return supported;
        }

        @Override
        public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_DELETE)) {
                DefaultProjectOperations.performDefaultDeleteOperation(QuorumProject.this);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_COPY)) {
                DefaultProjectOperations.performDefaultCopyOperation(QuorumProject.this);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_MOVE)) {
                DefaultProjectOperations.performDefaultMoveOperation(QuorumProject.this);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_DEBUG)) {
                getDebug().actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_BUILD)) {
                getBuild().actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_CLEAN)) {
                getClean().actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_REBUILD)) {
                getCleanBuild().actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_RUN)) {
                getRun().actionPerformed(null);
            }
        }

        @Override
        public boolean isActionEnabled(String command, Lookup lookup) throws IllegalArgumentException {
            if ((command.equals(ActionProvider.COMMAND_DELETE))) {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_COPY))) {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_MOVE))) {
                return true;
            } else {
                return true;
            }
        }
    }

    private final class QuorumDeleteOperation implements DeleteOperationImplementation {

        @Override
        public void notifyDeleting() throws IOException {
        }

        @Override
        public void notifyDeleted() throws IOException {
        }

        @Override
        public List<FileObject> getMetadataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }

        @Override
        public List<FileObject> getDataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }
    }

    private final class QuorumCopyOperation implements CopyOperationImplementation {

        private final QuorumProject project;
        private final FileObject projectDir;

        public QuorumCopyOperation(QuorumProject project) {
            this.project = project;
            this.projectDir = project.getProjectDirectory();
        }

        @Override
        public List<FileObject> getMetadataFiles() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public List<FileObject> getDataFiles() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public void notifyCopying() throws IOException {
        }

        @Override
        public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
        }
    }

    /**
     * Implementation of project system's ProjectInformation class
     */
    private final class Info implements ProjectInformation {

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(
                    QUORUM_PROJECT_ICON));
        }

        @Override
        public String getName() {
            return QUORUM_PROJECT_KEY;
        }

        @Override
        public String getDisplayName() {
            return getProjectDirectory().getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return QuorumProject.this;
        }
    }
}
