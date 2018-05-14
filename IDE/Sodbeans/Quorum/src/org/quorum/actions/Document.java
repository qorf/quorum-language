/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.windows.WindowManager;
import org.quorum.projects.QuorumProject;
import org.quorum.windows.GenerateDocumentationWindow;

/**
 *
 * @author stefika
 */
public class Document extends QuorumAction implements ActionListener{

    public Document(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        GenerateDocumentationWindow dialog = new GenerateDocumentationWindow(frame, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
        if(dialog.isOK()) {
            buildDocumentation = dialog.isDocumentingMyCode();
            buildLibrary = dialog.isDocumentingStandardLibrary();
            Document.QuorumDocumenter runner = new Document.QuorumDocumenter();
            Thread thread = new Thread(runner);
            thread.start();
        }
    }
    
    private class QuorumDocumenter implements Runnable {
        
        @Override
        public void run() {
            ProcessCancel cancel = new ProcessCancel();
            String taskName = project.getProjectDirectory().getName() + " (documenting)";

            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
            progress.start();
            build();
            progress.finish();
        }
    }
    
    @Override
    protected String getDisplayName() {
        return "Document";
    }
}
