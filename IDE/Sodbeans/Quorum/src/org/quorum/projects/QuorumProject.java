/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
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
import org.quorum.actions.SendToIPhoneApplication;
import org.quorum.actions.SendToIPhoneSimulator;
import org.quorum.support.Utility;
import quorum.Libraries.Language.Compile.CompilerRequest;
import quorum.Libraries.Language.Compile.CompilerRequest_;
import quorum.Libraries.Language.Compile.CompilerResult_;
import quorum.Libraries.System.File_;
import quorum.Libraries.Language.Compile.Library_;

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
    public static final String QUORUM_LEGO_PROJECT = "Quorum_Lego_Project";
    
    public static final String QUORUM_EXECUTABLE_NAME = "Quorum_Executable_Name";
    public static final String ADDITIONAL_PLUGIN_FOLDERS = "Additional_Plugin_Folders";
    public static final String ADDITIONAL_JARS = "Additional_Jars";
    public static final String ADDITIONAL_SOURCES = "Additional_Sources";
    
    public static final String QUORUM_IPHONE_PROVISION = "Quorum_IPhone_Provision";
    public static final String QUORUM_IPHONE_SIGNING_KEY = "Quorum_IPhone_Signing_Key";
    public static final String QUORUM_MOBILE_ASSETS_FOLDER = "Quorum_Mobile_Assets_Folder";

    public static final String QUORUM_PROJECT_ICON = "org/quorum/resources/project.png";
    public static final String QUORUM_FILE_ICON = "org/quorum/resources/file.png";

    public static final String SOURCE_CODE_DIRECTORY = "SourceCode";
    public static final String PROJET_PROPERTIES_DIRECTORY = "Project";
    public static final String BUILD_DIRECTORY = "Build";
    public static final String DISTRIBUTION_DIRECTORY = "Run";
    public static final String DOCUMENTS_DIRECTORY = "Documentation";
    public QuorumProjectType projectType = QuorumProjectType.STANDARD;
    
    private String mobileAssetsFolder = "";
    private String iPhoneProvisioningKey = "";
    private String iPhoneSigningKey = "";
    
    public static final String MIME_TYPE = "text/x-quorum";
    private final FileObject projectDir;
    private LogicalViewProvider logicalView = new QuorumLogicalView(this);
    private final ProjectState state;
    private Lookup lookup;
    
    private Debugger debugger;
    private Document document;
    private Debug debug;
    private SendToIPhoneApplication sendToIPhoneApplication;
    private SendToIPhoneSimulator sendToIPhoneSimulator;
    private Build build;
    private Clean clean;
    private CleanBuild cleanBuild;
    private Run run;
    private final quorum.Libraries.Language.Compile.Compiler compiler = 
            new quorum.Libraries.Language.Compile.Compiler();
    private MainFileProvider mainFileProvider = new MainFileProvider(this);
    private quorum.Libraries.Language.Compile.CompilerResult_ sandboxResult = null;
    private ArrayList<quorum.Libraries.System.File> extraSourceFiles = new ArrayList<quorum.Libraries.System.File>();
    private static quorum.Libraries.Language.Compile.Library_ quorumStandardLibrary = null;
    private Library_ myProjectsLibrary = null;
    private File_ mainFile = null;
    private CompilerResult_ lastCompileResult;
    
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
        
        debugger = new JDIDebugger();
        document = new Document(this);
        debug = new Debug(this);
        build = new Build(this);
        sendToIPhoneApplication = new SendToIPhoneApplication(this);
        sendToIPhoneSimulator = new SendToIPhoneSimulator(this);
        clean = new Clean(this);
        cleanBuild = new CleanBuild(this);
        run = new Run(this);
        
        //now index the standard library
        //ask the compiler for a copy of its standard library
        //if it's not been scanned, scan it. Otherwise, use a pre-scanned one.
        if(quorumStandardLibrary == null) {
            //compiler.ScanStandardLibrary();
            quorumStandardLibrary = new quorum.Libraries.Language.Compile.Library();
            quorumStandardLibrary.SetCachingLibraryOpcodes(true);
            quorumStandardLibrary.SetLocation(standardLibrary);
            
            quorum.Libraries.System.File outputLocation = new quorum.Libraries.System.File();
            outputLocation.SetWorkingDirectory(standardInNB.getAbsolutePath());
            outputLocation.SetPath("Compiled");
            quorumStandardLibrary.SetOutputFolder(outputLocation);
            quorumStandardLibrary.Scan();
            myProjectsLibrary = quorumStandardLibrary;
        } else {
            myProjectsLibrary = quorumStandardLibrary;
        }
    }

    public Library_ GetStandardLibrary() {
        return myProjectsLibrary;
    }
    
    public QuorumProjectType getProjectType() {
        return projectType;
    }
    
    public void SetProjectType(QuorumProjectType type) {
        projectType = type;
    }
    
    public quorum.Libraries.Language.Compile.CompilerResult_ getSandboxCompilerResult() {
        return sandboxResult;
    }
    
    public void setSandboxCompilerResult(quorum.Libraries.Language.Compile.CompilerResult_ result) {
        sandboxResult = result;
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

    public String getExecutableLocation(CompilerRequest_ request) {
        File_ output = getCompiler().GetRunFolder();
        String path = output.GetAbsolutePath();
        
        return path + "/" + getExecutableName(request);
    }
    
    public String getExecutableName(CompilerRequest_ request) {
        return request.GetName(getCompiler().GetName());
    }
    
    public String getExecutableNameNoExtension() {
        return getCompiler().GetName();
    }
    
    public File getRunDirectory() {
        File_ output = getCompiler().GetRunFolder();
        return org.quorum.support.Utility.toQuorumFile(output);
    }
    
    /**
     * This obtains the ImageSheetManager to see if there are any properties
     * set related to the automatic creation of image sheets.
     */
    public ImageSheetManager getImageSheetManager() {
        ImageSheetManager manager = ImageSheetManager.getImageSheetManager(getLookup().lookup(Properties.class));
        return manager;
    }
    
    private Properties loadProperties() {
        FileObject fob = projectDir.getFileObject(QuorumProjectFactory.PROJECT_DIR
                + "/" + QuorumProjectFactory.PROJECT_PROPFILE);
        Properties properties = new NotifyProperties(state);
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (Exception e) {
                //this seems to throw an error on various kinds of loads. Just ignore it.
                //Exceptions.printStackTrace(e);
            }
        }
        
        String key = properties.getProperty(KEY_MAINFILE);
        if(key != null) {
            FileObject directory = this.getProjectDirectory();
            FileObject mainFile = directory.getFileObject(key);
            mainFileProvider.setMainFile(mainFile);
        }
        
        String property = properties.getProperty(QUORUM_PROJECT_TYPE);
        if(property == null || property.compareTo(QuorumProject.QUORUM_CONSOLE_PROJECT) == 0) {
            projectType = QuorumProjectType.STANDARD;
        } else if(property.compareTo(QuorumProject.QUORUM_COMPILED_WEB_PROJECT) == 0) {
            projectType = QuorumProjectType.WEB;
        } else if(property.compareTo(QuorumProject.QUORUM_WEB_PROJECT) == 0) {
            projectType = QuorumProjectType.WEB_BROWSER;
        } else if(property.compareTo(QuorumProject.QUORUM_LEGO_PROJECT) == 0) {
            projectType = QuorumProjectType.LEGO;
        }
        
        String plugins = properties.getProperty(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS);
        resetPluginFolder(plugins);
        
        String jars = properties.getProperty(QuorumProject.ADDITIONAL_JARS);
        resetJars(jars);
        
        String sources = properties.getProperty(QuorumProject.ADDITIONAL_SOURCES);
        resetSources(sources);
        
        String name = properties.getProperty(QuorumProject.QUORUM_EXECUTABLE_NAME);
        if(name != null) {
            compiler.SetName(name);
        }
        
        setMobileAssetsFolder(properties.getProperty(QuorumProject.QUORUM_MOBILE_ASSETS_FOLDER));
        setiPhoneProvisioningKey(properties.getProperty(QuorumProject.QUORUM_IPHONE_PROVISION));
        setiPhoneSigningKey(properties.getProperty(QuorumProject.QUORUM_IPHONE_SIGNING_KEY));
        
        return properties;
    }
    
    public Iterator<quorum.Libraries.System.File> getExtraSourceFiles() {
        return extraSourceFiles.iterator();
    }
    
    public File_ GetMain() {
        return mainFile;
    }
    
    public void SetMain(File_ main) {
        mainFile = main;
    }
    
    public void resetSources(String sources) {
        File directory = FileUtil.toFile(this.getProjectDirectory());
        if(sources != null) {
            extraSourceFiles.clear();
            String[] split = sources.split(";");
            for(int i = 0; i < split.length; i++) {
                String val = split[i];
                File path = Utility.computeRelativePath(directory, val);
                
                quorum.Libraries.System.File toQuorumFile = Utility.toQuorumFile(path);
                extraSourceFiles.add(toQuorumFile);
            }
        } else {
            extraSourceFiles.clear();
        }
    }
    
    public void resetJars(String jars) {
        File directory = FileUtil.toFile(this.getProjectDirectory());
        if(jars != null) {
            compiler.EmptyAdditionalJars();
            String[] split = jars.split(";");
            for(int i = 0; i < split.length; i++) {
                String val = split[i];
                File path = Utility.computeRelativePath(directory, val);
                
                quorum.Libraries.System.File toQuorumFile = Utility.toQuorumFile(path);
                compiler.AddJar(toQuorumFile);
            }
        } else {
            compiler.EmptyAdditionalJars();
        }
    }
    
    public void resetPluginFolder(String plugins) {
        File directory = FileUtil.toFile(this.getProjectDirectory());
        if(plugins != null) {
            compiler.EmptyAdditionalPluginFolders();
            String[] split = plugins.split(";");
            for(int i = 0; i < split.length; i++) {
                String val = split[i];
                File path = Utility.computeRelativePath(directory, val);
                
                quorum.Libraries.System.File toQuorumFile = Utility.toQuorumFile(path);
                compiler.AddPluginFolder(toQuorumFile);
            }
        } else {
            compiler.EmptyAdditionalPluginFolders();
        }
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
     * Return the action that can send a quorum program to an iPhone .ipa File.
     * @return 
     */
    public SendToIPhoneApplication getSendToIPhoneApplication() {
        return sendToIPhoneApplication;
    }
    
    /**
     * Return the action that can send a quorum program to an iPhone .ipa File.
     * @return 
     */
    public SendToIPhoneSimulator getSendToIPhoneSimulator() {
        return sendToIPhoneSimulator;
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

    /**
     * @return the iPhoneSigningKey
     */
    public String getiPhoneSigningKey() {
        return iPhoneSigningKey;
    }

    /**
     * @param iPhoneSigningKey the iPhoneSigningKey to set
     */
    public void setiPhoneSigningKey(String iPhoneSigningKey) {
        this.iPhoneSigningKey = iPhoneSigningKey;
    }

    /**
     * @return the iPhoneProvisioningKey
     */
    public String getiPhoneProvisioningKey() {
        return iPhoneProvisioningKey;
    }

    /**
     * @param iPhoneProvisioningKey the iPhoneProvisioningKey to set
     */
    public void setiPhoneProvisioningKey(String iPhoneProvisioningKey) {
        this.iPhoneProvisioningKey = iPhoneProvisioningKey;
    }

    /**
     * @return the mobileAssetsFolder
     */
    public String getMobileAssetsFolder() {
        return mobileAssetsFolder;
    }

    /**
     * @param mobileAssetsFolder the mobileAssetsFolder to set
     */
    public void setMobileAssetsFolder(String mobileAssetsFolder) {
        this.mobileAssetsFolder = mobileAssetsFolder;
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

        /**
         * A table of hex digits
         */
        private static final char[] hexDigit = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };

        private static char toHex(int nibble) {
            return hexDigit[(nibble & 0xF)];
        }

        private static void writeComments(BufferedWriter bw, String comments) throws IOException {
            bw.write("#");
            int len = comments.length();
            int current = 0;
            int last = 0;
            char[] uu = new char[6];
            uu[0] = '\\';
            uu[1] = 'u';
            while (current < len) {
                char c = comments.charAt(current);
                if (c > '\u00ff' || c == '\n' || c == '\r') {
                    if (last != current) {
                        bw.write(comments.substring(last, current));
                    }
                    if (c > '\u00ff') {
                        uu[2] = toHex((c >> 12) & 0xf);
                        uu[3] = toHex((c >> 8) & 0xf);
                        uu[4] = toHex((c >> 4) & 0xf);
                        uu[5] = toHex(c & 0xf);
                        bw.write(new String(uu));
                    } else {
                        bw.newLine();
                        if (c == '\r'
                                && current != len - 1
                                && comments.charAt(current + 1) == '\n') {
                            current++;
                        }
                        if (current == len - 1
                                || (comments.charAt(current + 1) != '#'
                                && comments.charAt(current + 1) != '!')) {
                            bw.write("#");
                        }
                    }
                    last = current + 1;
                }
                current++;
            }
            if (last != current) {
                bw.write(comments.substring(last, current));
            }
            bw.newLine();
        }

        public void myStore(BufferedWriter bw, String comments, boolean escUnicode) throws IOException {
            if (comments != null) {
                writeComments(bw, comments);
            }
            //bw.write("#" + new Date().toString());
            //bw.newLine();
            synchronized (this) {
                for (Enumeration<?> e = keys(); e.hasMoreElements();) {
                    String key = (String) e.nextElement();
                    String val = (String) get(key);
                    key = saveConvert(key, true, escUnicode);
                    /* No need to escape embedded and trailing spaces for value, hence
                     * pass false to flag.
                     */
                    val = saveConvert(val, false, escUnicode);
                    bw.write(key + "=" + val);
                    bw.newLine();
                }
            }
            bw.flush();
        }

        public void store(Writer writer, String comments) throws IOException {
            myStore((writer instanceof BufferedWriter) ? (BufferedWriter) writer
                    : new BufferedWriter(writer),
                    comments,
                    false);
        }

        @Override
        public void store(OutputStream out, String comments) throws IOException {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "8859_1"));
            myStore(bw, comments, true);
        }

        private String saveConvert(String theString,
                boolean escapeSpace,
                boolean escapeUnicode) {
            int len = theString.length();
            int bufLen = len * 2;
            if (bufLen < 0) {
                bufLen = Integer.MAX_VALUE;
            }
            StringBuffer outBuffer = new StringBuffer(bufLen);

            for (int x = 0; x < len; x++) {
                char aChar = theString.charAt(x);
                // Handle common case first, selecting largest block that
                // avoids the specials below
                if ((aChar > 61) && (aChar < 127)) {
                    if (aChar == '\\') {
                        outBuffer.append('\\');
                        outBuffer.append('\\');
                        continue;
                    }
                    outBuffer.append(aChar);
                    continue;
                }
                switch (aChar) {
                    case ' ':
                        if (x == 0 || escapeSpace) {
                            outBuffer.append('\\');
                        }
                        outBuffer.append(' ');
                        break;
                    case '\t':
                        outBuffer.append('\\');
                        outBuffer.append('t');
                        break;
                    case '\n':
                        outBuffer.append('\\');
                        outBuffer.append('n');
                        break;
                    case '\r':
                        outBuffer.append('\\');
                        outBuffer.append('r');
                        break;
                    case '\f':
                        outBuffer.append('\\');
                        outBuffer.append('f');
                        break;
                    case '=': // Fall through
                    case ':': // Fall through
                    case '#': // Fall through
                    case '!':
                        outBuffer.append('\\');
                        outBuffer.append(aChar);
                        break;
                    default:
                        if (((aChar < 0x0020) || (aChar > 0x007e)) & escapeUnicode) {
                            outBuffer.append('\\');
                            outBuffer.append('u');
                            outBuffer.append(toHex((aChar >> 12) & 0xF));
                            outBuffer.append(toHex((aChar >> 8) & 0xF));
                            outBuffer.append(toHex((aChar >> 4) & 0xF));
                            outBuffer.append(toHex(aChar & 0xF));
                        } else {
                            outBuffer.append(aChar);
                        }
                }
            }
            return outBuffer.toString();
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
            } else if ((command.equals(ActionProvider.COMMAND_RUN))) {
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

    /**
     * @return the lastCompileResult
     */
    public CompilerResult_ getLastCompileResult() {
        return lastCompileResult;
    }

    /**
     * @param lastCompileResult the lastCompileResult to set
     */
    public void setLastCompileResult(CompilerResult_ lastCompileResult) {
        this.lastCompileResult = lastCompileResult;
    }
}
