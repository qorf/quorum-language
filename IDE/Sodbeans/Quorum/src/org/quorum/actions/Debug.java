/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import org.debugger.Debugger;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.quorum.projects.QuorumProject;
import org.netbeans.api.debugger.DebuggerInfo;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.spi.debugger.SessionProvider;
import org.openide.util.Cancellable;
import org.quorum.debugger.QuorumDebuggerCookie;
import org.quorum.projects.QuorumProjectType;

/**
 *
 * @author stefika
 */
public class Debug extends QuorumAction implements ActionListener{
    public static final String QUORUM_DEBUGGER_INFO = "QuorumDebuggerInfo";
    public static final String QUORUM_SESSION = "QuorumSession";
    private Debugger debugger;
    public Debug(QuorumProject project) {
        super(project);
    //    project.getLookup().lookup(Debugger.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QuorumDebugger runner = new QuorumDebugger();
        Thread thread = new Thread(runner);
        thread.start();
    }
    
    private class QuorumDebugger implements Runnable {

        @Override
        public void run() {
            debugger = project.getLookup().lookup(Debugger.class);
            ProcessCancel cancel = new ProcessCancel();
            makeVisualDebuggerControls(cancel);
            String taskName = project.getProjectDirectory().getName() + " (debug)";
            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
            cancel.progress = progress;
            progress.start();
            
            boolean success = build();
            if(!success) {
                progress.finish();
                return;
            }
            
            final QuorumProjectType type = project.getProjectType();
            boolean legos = false;
            if(type == QuorumProjectType.LEGO) {
                //io.getOut().println("To run the robot, use the lego robot itself. The reason is because I cannot debug the robot from within the development environment. ");
                //io.getOut().close();
                return;
            }

            String location = project.getExecutableLocation();
            debugger.setExecutable(location);
            debugger.setWorkingDirectory(project.getRunDirectory().getParentFile().getAbsolutePath());
            debugger.launch();

            QuorumProcessWatcher watch = new QuorumProcessWatcher(debugger.getInputStream());
            OutputStream outputStream = debugger.getOutputStream();
            watch.setStream(outputStream);
            watch.start();
            cancel.watcher = watch;
            
            debugger.forward();
        }
    }
    
    public void makeVisualDebuggerControls(Cancellable cancel) {
        QuorumDebuggerCookie cookie = new QuorumDebuggerCookie();
        cookie.setDebugger(debugger);
        cookie.setProject(project);
        cookie.setCancel(cancel);
        DebuggerManager manager = DebuggerManager.getDebuggerManager();
        DebuggerInfo info = DebuggerInfo.create(QUORUM_DEBUGGER_INFO,
            new Object[]{
                new SessionProvider() {

                    @Override
                    public String getSessionName() {
                        return "Quorum Program";
                    }

                    @Override
                    public String getLocationName() {
                        return "localhost";
                    }

                    @Override
                    public String getTypeID() {
                        return QUORUM_SESSION;
                    }

                    @Override
                    public Object[] getServices() {
                        return new Object[]{};
                    }
                }, cookie
            });

        manager.startDebugging(info);
    }
    @Override
    protected String getDisplayName() {
        return "Debug";
    }
}
