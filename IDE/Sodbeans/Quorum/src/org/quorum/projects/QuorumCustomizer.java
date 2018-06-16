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
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;

/**
 *
 * @author stefika
 */
public class QuorumCustomizer implements CustomizerProvider{

    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    private ProjectInformationPanel infoPanel;
    private GamePanel gamePanel;
    private MobilePanel mobilePanel;
    
    private static final String PROJECT_INFO_CATEGORY = "ProjectInfoCategory";
    private static final String GAME_CATEGORY = "GameCategory";
    private static final String MOBILE_CATEGORY = "MobileCategory";
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
        
        ProjectCustomizer.Category mobileInfo = ProjectCustomizer.Category.create(
                MOBILE_CATEGORY,
                "Mobile",
                null,
                null);
        
        categories = new ProjectCustomizer.Category[] {
            projectInfo, gameInfo, mobileInfo
        };
        
        HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
        infoPanel = new ProjectInformationPanel();
        infoPanel.setProject(project);
        panels.put(PROJECT_INFO_CATEGORY, infoPanel);
        
        gamePanel = new GamePanel();
        gamePanel.setProject(project);
        panels.put(GAME_CATEGORY, gamePanel);
        
        mobilePanel = new MobilePanel();
        mobilePanel.setProject(project);
        panels.put(MOBILE_CATEGORY, mobilePanel);
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
            
            //set the properties from the information panel
            String type = infoPanel.getQuorumProjectType();
            properties.setProperty(QuorumProject.QUORUM_PROJECT_TYPE, type);
            if(type == null || type.compareTo(QuorumProject.QUORUM_CONSOLE_PROJECT) == 0) {
                ((QuorumProject) project).SetProjectType(QuorumProjectType.STANDARD);
            } else if(type.compareTo(QuorumProject.QUORUM_COMPILED_WEB_PROJECT) == 0) {
                ((QuorumProject) project).SetProjectType(QuorumProjectType.WEB);
            } else if(type.compareTo(QuorumProject.QUORUM_LEGO_PROJECT) == 0) {
                ((QuorumProject) project).SetProjectType(QuorumProjectType.LEGO);
            } else if(type.compareTo(QuorumProject.QUORUM_WEB_PROJECT) == 0) {
                ((QuorumProject) project).SetProjectType(QuorumProjectType.WEB_BROWSER);
            }
            
            String jarList = infoPanel.getJarList();
            
            if(jarList != null) {
                properties.setProperty(QuorumProject.ADDITIONAL_JARS, jarList);
            } else {
                properties.remove(QuorumProject.ADDITIONAL_JARS);
            }
            
            String sourceList = infoPanel.getSourceList();
            
            if(sourceList != null) {
                properties.setProperty(QuorumProject.ADDITIONAL_SOURCES, sourceList);
            } else {
                properties.remove(QuorumProject.ADDITIONAL_SOURCES);
            }
            ((QuorumProject)project).resetSources(sourceList);
            
            String pluginList = infoPanel.getPluginList();
            
            if(pluginList != null) {
                properties.setProperty(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS, pluginList);
            } else {
                properties.remove(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS);
            }
            String name = infoPanel.getExecutableName();
            if(name != null) {
                properties.setProperty(QuorumProject.QUORUM_EXECUTABLE_NAME, name);
                ((QuorumProject) project).getCompiler().SetName(name);
            } else {
                properties.remove(QuorumProject.QUORUM_EXECUTABLE_NAME);
            }
            
            String plugins = properties.getProperty(QuorumProject.ADDITIONAL_PLUGIN_FOLDERS);
            ((QuorumProject) project).resetPluginFolder(plugins);
            
            String jars = properties.getProperty(QuorumProject.ADDITIONAL_JARS);
            ((QuorumProject) project).resetJars(jars);
            
            
            //set the properties from the game panel
            ImageSheetManager manager = gamePanel.getManager();
            
            if(manager.isEnableImageSheetSupport()) {
                properties.setProperty(ImageSheetManager.IMAGE_SHEETS_ENABLED, "true");
                if(manager.isRebuildOnCompile()) {
                    properties.setProperty(ImageSheetManager.REBUILD_IMAGE_SHEETS_ON_COMPILE, "true");
                } else {
                    properties.remove(ImageSheetManager.REBUILD_IMAGE_SHEETS_ON_COMPILE);
                }
                String sheets = manager.save();
                properties.setProperty(ImageSheetManager.IMAGE_SHEETS, sheets);
                properties.setProperty(ImageSheetManager.IMAGE_SHEET_BUILD_PATH, manager.getBuildPath());
            } else {
                properties.remove(ImageSheetManager.IMAGE_SHEETS_ENABLED);
                properties.remove(ImageSheetManager.REBUILD_IMAGE_SHEETS_ON_COMPILE);
                properties.remove(ImageSheetManager.IMAGE_SHEETS);
                properties.remove(ImageSheetManager.IMAGE_SHEET_BUILD_PATH);
            }
            
            //set the properties from the mobile panel
            if(mobilePanel.getMobileAssetsFolder().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_MOBILE_ASSETS_FOLDER);
            } else {
                properties.setProperty(QuorumProject.QUORUM_MOBILE_ASSETS_FOLDER, mobilePanel.getMobileAssetsFolder());
            }
            
            if(mobilePanel.getiPhoneProvisioning().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_IPHONE_PROVISION);
            } else {
                properties.setProperty(QuorumProject.QUORUM_IPHONE_PROVISION, mobilePanel.getiPhoneProvisioning());
            }
            
            if(mobilePanel.getiPhoneSigningKey().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_IPHONE_SIGNING_KEY);
            } else {
                properties.setProperty(QuorumProject.QUORUM_IPHONE_SIGNING_KEY, mobilePanel.getiPhoneSigningKey());
            }
            
            //now android
            if(mobilePanel.getAndroidPath().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_ANDROID_PATH);
            } else {
                properties.setProperty(QuorumProject.QUORUM_ANDROID_PATH, mobilePanel.getAndroidPath());
            }
            
            if(mobilePanel.getAndroidKeystorePath().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_ANDROID_KEYSTORE_PATH);
            } else {
                properties.setProperty(QuorumProject.QUORUM_ANDROID_KEYSTORE_PATH, mobilePanel.getAndroidKeystorePath());
            }
            
            if(mobilePanel.getAndroidKeystorePassword().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_ANDROID_KEYSTORE_PASSWORD);
            } else {
                properties.setProperty(QuorumProject.QUORUM_ANDROID_KEYSTORE_PASSWORD, mobilePanel.getAndroidKeystorePassword());
            }
            
            if(mobilePanel.getAndroidKeyAlias().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_ANDROID_KEY_ALIAS);
            } else {
                properties.setProperty(QuorumProject.QUORUM_ANDROID_KEY_ALIAS, mobilePanel.getAndroidKeyAlias());
            }
            
            if(mobilePanel.getAndroidKeyPassword().isEmpty()) {
                properties.remove(QuorumProject.QUORUM_ANDROID_KEY_PASSWORD);
            } else {
                properties.setProperty(QuorumProject.QUORUM_ANDROID_KEY_PASSWORD, mobilePanel.getAndroidKeyPassword());
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }
    }
}
