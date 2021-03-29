package com.greenature.dockerwatch.model;

import com.greenature.dockerwatch.shared.Values;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


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
        scheduledFutureList.forEach(handler -> {
            handler.cancel(true);
        });
        this.scheduler.shutdownNow();
    }


    static class HostWatcher implements Runnable {
        private final BaseHost baseHost;

        public HostWatcher(BaseHost baseHost) {
            this.baseHost = baseHost;
        }

        public void run() {

            if (baseHost.validate()) {
                // Refresh Containers List
//                host.refreshContainersList();
                // Refresh Images List

            }
        }
    }


}
