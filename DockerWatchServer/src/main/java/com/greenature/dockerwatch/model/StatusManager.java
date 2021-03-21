package com.greenature.dockerwatch.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class StatusManager {
    Set<Host> allHosts;
    boolean isPollingOn = true;
    ScheduledExecutorService scheduler;
    List<ScheduledFuture<?>> scheduledFutureList;

    public StatusManager() {
        allHosts = new HashSet<Host>();
        Host host = new Host("0", "tcp://localhost:2375/", true);
        allHosts.add(host);
        scheduler = Executors.newScheduledThreadPool(5);
        this.startPolling();
    }

    private void startPolling() {

        scheduledFutureList = new ArrayList<>();
        allHosts.parallelStream().forEach(host -> {
            scheduledFutureList.add(this.scheduler.scheduleAtFixedRate(new HostWatcher(host), 2, 2, TimeUnit.SECONDS));
        });



    }

    @PreDestroy
    private void cleanup() {
        scheduledFutureList.forEach(handler-> {
            handler.cancel(true);
        });
        this.scheduler.shutdownNow();
    }


    static class HostWatcher implements Runnable {
        private final Host host;
        public HostWatcher(Host host) {
            this.host = host;
        }

        public void run() {

             if(host.isAlive()) {
                 // Refresh Containers List
                 System.out.println(host.getHostURL() + host.getContainers());
//                 host.refreshContainersList();
                 // Refresh Images List

             }
        }
    }


}
