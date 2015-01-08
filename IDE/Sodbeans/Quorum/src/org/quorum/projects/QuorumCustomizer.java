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
    
    private static final String PROJECT_INFO_CATEGORY = "ProjectInfoCategory";
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
        
        categories = new ProjectCustomizer.Category[] {
            projectInfo
        };
        
        HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
        infoPanel = new ProjectInformationPanel();
        infoPanel.setProject(project);
        panels.put(PROJECT_INFO_CATEGORY, infoPanel);
        panelProvider = new PanelProvider(panels);
    }
    
    
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
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }
    }
}
