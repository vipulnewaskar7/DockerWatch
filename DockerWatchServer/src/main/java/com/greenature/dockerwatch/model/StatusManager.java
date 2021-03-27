package com.greenature.dockerwatch.model;

import com.greenature.dockerwatch.shared.Values;
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
    boolean isPollingOn = true;
    ScheduledExecutorService scheduler;
    List<ScheduledFuture<?>> scheduledFutureList;

    public StatusManager() {
        scheduler = Executors.newScheduledThreadPool(5);
        this.startPolling();
    }

    private void startPolling() {

        scheduledFutureList = new ArrayList<>();
        Values.allHosts.parallelStream().forEach(host -> {
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
                 host.refreshContainersList();
                 // Refresh Images List

             }
        }
    }


}
