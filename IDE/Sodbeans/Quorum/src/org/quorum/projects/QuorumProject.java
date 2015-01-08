/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author stefika
 */
public class QuorumProject implements Project{
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
    
    private final FileObject projectDir;
    private LogicalViewProvider logicalView = new QuorumLogicalView(this);
    private final ProjectState state;
    private Lookup lookup;
    
    public QuorumProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
    }
    
    
    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    public FileObject getSources(boolean create) {
        FileObject result =
            projectDir.getFileObject(SOURCES_DIR);

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
            lookup = Lookups.fixed(new Object[] {
                this,           //Project spec requires a project be in its own lookup
                state,          //Allow outside code to mark the project as needing saving
                new ActionProviderImpl(), //Provides standard actions like Build and Clean
                new QuorumDeleteOperation(),
                new QuorumCopyOperation(this),
                loadProperties(),   //The project properties
                new Info(),     //Project information implementation
                logicalView,    //Logical view of project implementation
                new MainFileProvider(this),
                //new QuorumPrivilegedTemplates(),
                //new QuorumCustomizer(this),
            });
        }
        return lookup;
    }
    
    private Properties loadProperties() {
        FileObject fob = projectDir.getFileObject(QuorumProjectFactory.PROJECT_DIR +
             "/" + QuorumProjectFactory.PROJECT_PROPFILE);
        Properties properties = new NotifyProperties(state);
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (Exception e) {
                Exceptions.printStackTrace(e);
            }
        }
        return properties;
    }
    
    private static class NotifyProperties extends Properties {
        private final ProjectState state;
        NotifyProperties (ProjectState state) {
            this.state = state;
        }

        @Override
        public Object put(Object key, Object val) {
            Object result = super.put(key, val);
            if (((result == null) != (val == null)) || (result != null &&
                val != null && !val.equals(result))) {
                state.markModified();
            }
            return result;
        }
    }
    
    private final class ActionProviderImpl implements ActionProvider {
        private String[] supported = new String[]{
            ActionProvider.COMMAND_DELETE,
            ActionProvider.COMMAND_COPY,
            ActionProvider.COMMAND_MOVE,
            ActionProvider.COMMAND_BUILD,
            ActionProvider.COMMAND_CLEAN,
            ActionProvider.COMMAND_REBUILD,
            ActionProvider.COMMAND_RUN,
            ActionProvider.COMMAND_DEBUG
        };

        
        public String[] getSupportedActions() {
            return supported;
        }

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
//                debug.actionPerformed(null);
//                if(debug.wasBuildSuccessful()) {
//                    debugger.startDebugging();
//                }
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_BUILD)) {
                //build.actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_CLEAN)) {
                //clean.actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_REBUILD)) {
                //rebuild.actionPerformed(null);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_RUN)) {
                //run.actionPerformed(null);
            }
        }

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

        public void notifyDeleting() throws IOException {
        }

        public void notifyDeleted() throws IOException {
        }

        public List<FileObject> getMetadataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }

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

        public List<FileObject> getMetadataFiles() {
            return Collections.EMPTY_LIST;
        }

        public List<FileObject> getDataFiles() {
            return Collections.EMPTY_LIST;
        }

        public void notifyCopying() throws IOException {
        }

        public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
        }
    }
    
    /** Implementation of project system's ProjectInformation class */
    private final class Info implements ProjectInformation {
        @Override
        public Icon getIcon() {
            return new ImageIcon (ImageUtilities.loadImage(
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
        public void addPropertyChangeListener (PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener (PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return QuorumProject.this;
        }
    }
}
