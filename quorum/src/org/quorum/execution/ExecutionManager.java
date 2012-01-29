/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Andreas Stefik
 */
public class ExecutionManager{
    ExecutorService service;

    public ExecutionManager() {
        service = getExecutorService();
    }

    public void add(Runnable run) {
        if(service.isShutdown()) {
            service = getExecutorService();
        }
        service.submit(run);
    }

    public void stopNow() {
        service.shutdownNow();
    }

    private ExecutorService getExecutorService() {
        ExecutorService serv = Executors.newSingleThreadExecutor();
        return serv;
    }

}
