/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;
import org.quorum.android.AndroidSetup;
import org.quorum.android.RunAndroid;
import org.quorum.projects.QuorumProject;
import org.quorum.projects.QuorumProjectType;
import org.sodbeans.phonemic.OperatingSystem;

/**
 *
 * @author stefika
 */
public class SendToAndroidApplication extends QuorumAction implements ActionListener{
    public SendToAndroidApplication(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            QuorumRunner runner = new QuorumRunner();
            Thread thread = new Thread(runner);
            thread.start();
    }
    
    private class QuorumRunner implements Runnable {
        @Override
        public void run() {
            try {
                QuorumAction.ProcessCancel cancel = new QuorumAction.ProcessCancel();
                String taskName = project.getProjectDirectory().getName() + " (Android)";
                
                final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
                cancel.progress = progress;
                progress.start();
                BuildInformation info = build();
                boolean success = info.success;
                if(!success) {
                    progress.finish();
                    return;
                }
                
                final QuorumProjectType type = project.getProjectType();
                boolean legos = false;
                if(type == QuorumProjectType.LEGO) {
                    //io.getOut().println("To run the robot, use the lego robot itself. The reason is because I cannot execute the robot from within the development environment. ");
                    //io.getOut().close();
                    progress.finish();
                    return;
                }
                
                //to do this, I need the
                //executable name and path
                // Compute the location of the project's root directory.
                File runDirectory = project.getRunDirectory();
                File parentFile = runDirectory.getParentFile();
                File media = new File(parentFile.getAbsolutePath() + "/" + project.getMobileAssetsFolder());
                
                String runName = runDirectory.getName() + "/" + project.getExecutableName(info.request);
                
                /* NOTE: 
                
                    By default, the system leaves these blank. If they are the empty
                    string, assume that the user has not defined them and the default
                    should be used.
                */
                String androidSDKPath = project.getAndroidPath();
                String androidAlternateJDK = project.getAndroidAlternateJDK();
                String jarName = project.getExecutableNameNoExtension();
                String applicationName = jarName;
                String[] nameComponents = jarName.split("\\.");
                if (nameComponents.length >= 1) {
                   applicationName = nameComponents[0];
                }
                
                AndroidSetup setup = new AndroidSetup();
                InstalledFileLocator locator = InstalledFileLocator.getDefault();
                File androidLocation = locator.locate("modules/Android", "org.quorum", false);
                
                RunAndroid droid = new RunAndroid(runDirectory.getAbsolutePath(), jarName);
                if (androidSDKPath != null && !androidSDKPath.equals("")) {
                    droid.setAndroidSDKPath(androidSDKPath);
                    setup.setAndroidSDKPath(androidSDKPath);
                }
                
                setup.copyAndRename(androidLocation.getAbsolutePath(), runDirectory.getAbsolutePath(), applicationName, androidAlternateJDK);
                //get all the properties, in case they are there.
                
                droid.copyLibraries(droid.getLibrarySources(), droid.getLibraryDestinations());
                
                if(media.exists()) {
                    droid.copyAssets(media);
                }     
            try {
                Process buildProcess = droid.GetAPKDebugBuildProcess();
                QuorumAction.QuorumProcessWatcher watch = new QuorumAction.QuorumProcessWatcher(buildProcess.getErrorStream());
                OutputStream outputStream = buildProcess.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = buildProcess;
                cancel.watcher = watch;
                buildProcess.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                buildProcess.destroy();
                
                Process installProcess = droid.GetDebugInstallProcess();
                watch = new QuorumAction.QuorumProcessWatcher(installProcess.getErrorStream());
                outputStream = installProcess.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = installProcess;
                cancel.watcher = watch;
                installProcess.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                installProcess.destroy();
                progress.finish();
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
;
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                System.out.println(ex);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    @Override
    public boolean isEnabled() {
        //check if we are on a mac. If not, disable the action
        OperatingSystem os = OperatingSystem.getOS();
        if(os != OperatingSystem.LINUX) {
            return true;
        }
        
        return false;
    }
    
    @Override
    protected String getDisplayName() {
        return "Send to Android Application";
    }
}
