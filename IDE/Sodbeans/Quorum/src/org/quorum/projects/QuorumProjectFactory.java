/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectFactory2;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author stefika
 */
@ServiceProvider(service=ProjectFactory.class)
public class QuorumProjectFactory implements ProjectFactory2{

    public static final String PROJECT_DIR = "Project";
    public static final String PROJECT_PROPFILE="project.properties";
    private final ImageIcon icon = new ImageIcon (ImageUtilities.loadImage(
                    QuorumProject.QUORUM_PROJECT_ICON));
    
    //@Override
    public ProjectManager.Result isProject2(FileObject projectDirectory) {
        boolean test = projectDirectory.getFileObject(PROJECT_DIR) != null;
        if(test) {
            ProjectManager.Result result = new ProjectManager.Result("Quorum Project", "Quorum Project", icon);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_DIR) != null;
    }

    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        return isProject (dir) ? new QuorumProject(dir, state) : null;
    }

    @Override
    public void saveProject(Project project) throws IOException, ClassCastException {
        FileObject projectRoot = project.getProjectDirectory();
        if (projectRoot.getFileObject(PROJECT_DIR) == null) {
            throw new IOException ("Project dir " + projectRoot.getPath() + " deleted, " +
                    " cannot save project");
        }

        //Find the properties file nb/project.properties,
        //creating it if necessary
        String propsPath = PROJECT_DIR + "/" + PROJECT_PROPFILE;
        FileObject propertiesFile = projectRoot.getFileObject(propsPath);
        if (propertiesFile == null) {
            //Recreate the properties file if needed.
            propertiesFile = projectRoot.createData(propsPath);
        }

        Properties properties = project.getLookup().lookup(Properties.class);

        File f = FileUtil.toFile(propertiesFile);
        properties.store(new FileOutputStream(f), "NetBeans Quorum Project Properties");
    }
    
}
