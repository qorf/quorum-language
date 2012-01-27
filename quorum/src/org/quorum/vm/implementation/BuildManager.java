/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author stefika
 */
public class BuildManager {
    ExecutorService service;

    public BuildManager() {

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
