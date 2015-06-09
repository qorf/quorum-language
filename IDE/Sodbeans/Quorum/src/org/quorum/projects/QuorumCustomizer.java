/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.filesystems.FileUtil;
import org.quorum.support.Utility;

/**
 *
 * @author stefika
 */
public class QuorumCustomizer implements CustomizerProvider{

    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    private ProjectInformationPanel infoPanel;
    private GamePanel gamePanel;
    
    private static final String PROJECT_INFO_CATEGORY = "ProjectInfoCategory";
    private static final String GAME_CATEGORY = "GameCategory";
    private final QuorumProject project;
    public QuorumCustomizer(QuorumProject project) {
        this.project = project;
    }
    
    private void init() {
        ProjectCustomizer.Category projectInfo = ProjectCustomizer.Category.create(
                PROJECT_INFO_CATEGORY,
                "Project Information",
                null,
                null);
        
        ProjectCustomizer.Category gameInfo = ProjectCustomizer.Category.create(
                GAME_CATEGORY,
                "Games",
                null,
                null);
        
        categories = new ProjectCustomizer.Category[] {
            projectInfo, gameInfo
        };
        
        HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
        infoPanel = new ProjectInformationPanel();
        infoPanel.setProject(project);
        panels.put(PROJECT_INFO_CATEGORY, infoPanel);
        
        gamePanel = new GamePanel();
        gamePanel.setProject(project);
        panels.put(GAME_CATEGORY, gamePanel);
        panelProvider = new PanelProvider(panels);
    }
    
    
    @Override
    public void showCustomizer() {
        init();
        OptionListener listener = new OptionListener(project);
        Dialog dialog = ProjectCustomizer.createCustomizerDialog(categories, panelProvider, null, listener, null);
        dialog.addWindowListener(listener);
        dialog.setTitle(ProjectUtils.getInformation(project).getDisplayName());
        dialog.setVisible(true);
    }
    
    private static class PanelProvider implements ProjectCustomizer.CategoryComponentProvider {
        private HashMap<String, JPanel> panels;
        
        private JPanel EMPTY_PANEL = new JPanel();
        
        public PanelProvider(HashMap<String, JPanel> panels) {
            this.panels = panels;
        }
        
        @Override
        public JComponent create(ProjectCustomizer.Category category) {
            JComponent panel = (JComponent) panels.get(category.getName());
            return panel == null ? EMPTY_PANEL : panel;
        }
    }
    
    private class OptionListener extends WindowAdapter implements ActionListener {
        private Project project;
        OptionListener(Project project) {
            this.project = project;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Properties properties = project.getLookup().lookup(Properties.class);
            String type = infoPanel.getQuorumProjectType();
            properties.setProperty(QuorumProject.QUORUM_PROJECT_TYPE, type);
            
            String jarList = infoPanel.getJarList();
            
            if(jarList != null) {
                properties.setProperty(QuorumProject.ADDITIONAL_JARS, jarList);
            } else {
                properties.remove(QuorumProject.ADDITIONAL_JARS);
            }
            
            String pluginList = infoPanel.getPluginList();
            
            if(pluginList != null) {
                properties.setProperty(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS, pluginList);
            } else {
                properties.remove(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS);
            }
            String name = infoPanel.getExecutableName();
            
            if(name != null) {
                properties.setProperty(QuorumProject.QUORUM_EXECUTABLE_NAME, name);
            } else {
                properties.remove(QuorumProject.QUORUM_EXECUTABLE_NAME);
            }
            
            String plugins = properties.getProperty(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS);
            ((QuorumProject) project).resetPluginFolder(plugins);
            
            String jars = properties.getProperty(QuorumProject.ADDITIONAL_JARS);
            ((QuorumProject) project).resetJars(jars);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }
    }
}
