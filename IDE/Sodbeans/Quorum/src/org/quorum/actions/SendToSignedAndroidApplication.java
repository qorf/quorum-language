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
import java.util.Properties;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.filesystems.FileUtil;
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
public class SendToSignedAndroidApplication extends QuorumAction implements ActionListener{
    public SendToSignedAndroidApplication(QuorumProject project) {
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
                String runName = runDirectory.getName() + "/" + project.getExecutableName(info.request);
                
                /* NOTE: 
                
                    By default, the system leaves these blank. If they are the empty
                    string, assume that the user has not defined them and the default
                    should be used.
                */
                String androidPath = project.getAndroidPath();
                String androidKeystorePath = project.getAndroidKeystorePath();
                String androidKeystorePassword = project.getAndroidKeystorePassword();
                String androidKeyAlias = project.getAndroidKeyAlias();
                String androidKeyPassword = project.getAndroidKeyPassword();
                String androidAlternateJDK = project.getAndroidAlternateJDK();
                
                String jarName = project.getExecutableName(info.request);
                String applicationName = project.getExecutableNameNoExtension();
                
                AndroidSetup setup = new AndroidSetup();
                InstalledFileLocator locator = InstalledFileLocator.getDefault();
                File androidLocation = locator.locate("modules/Android", "org.quorum", false);
                setup.copyAndRename(androidLocation.getAbsolutePath(), runDirectory.getAbsolutePath(), applicationName, androidAlternateJDK);
                
                //get all the properties, in case they are there.
                
                RunAndroid droid = new RunAndroid(runDirectory.getAbsolutePath(), jarName);
                
                if (androidPath != null && !androidPath.equals("")) {
                    droid.setAndroidSDKPath(androidPath);
                }
                if (androidKeystorePath != null && !androidKeystorePath.equals("")) {
                    droid.setKeyStorePath(androidKeystorePath);
                }
                if (androidKeystorePassword != null && !androidKeystorePassword.equals("")) {
                    droid.setKeyStorePassword(androidKeystorePassword);
                }
                if (androidKeyAlias != null && !androidKeyAlias.equals("")) {
                    droid.setKeyAlias(androidKeyAlias);
                }
                if (androidKeyPassword != null && !androidKeyPassword.equals("")) {
                    droid.setKeyPassword(androidKeyPassword);
                }
                
                // Nothing should happen without keystore info. Error message!
                boolean hasKeyStoreInfo = droid.hasKeystoreInfo();
                
                
                
            try {
                Process assembleReleaseProcess = droid.GetAssembleReleaseProcess();
                QuorumAction.QuorumProcessWatcher watch = new QuorumAction.QuorumProcessWatcher(assembleReleaseProcess.getInputStream());
                OutputStream outputStream = assembleReleaseProcess.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = assembleReleaseProcess;
                cancel.watcher = watch;
                assembleReleaseProcess.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                assembleReleaseProcess.destroy();
                
                Process zipalignProcess = droid.GetZipalignProcess();
                watch = new QuorumAction.QuorumProcessWatcher(zipalignProcess.getInputStream());
                outputStream = zipalignProcess.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = zipalignProcess;
                cancel.watcher = watch;
                zipalignProcess.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                zipalignProcess.destroy();
                
                Process apkSignerProcess = droid.GetAPKSignerProcess();
                watch = new QuorumAction.QuorumProcessWatcher(apkSignerProcess.getInputStream());
                outputStream = apkSignerProcess.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = apkSignerProcess;
                cancel.watcher = watch;
                apkSignerProcess.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                apkSignerProcess.destroy();
                
                progress.finish();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
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
        return "Generate Android Application for Store";
    }
}
