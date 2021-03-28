package com.greenature.dockerwatch.controllers;

import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.greenature.dockerwatch.model.Host;
import com.greenature.dockerwatch.model.LogChunk;
import com.greenature.dockerwatch.shared.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ScheduledUpdatesController{
    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 1000L)
    public void startTailingLogs(){
        for(Host host: Values.allHosts) {
            String hostId = host.getHostId();
            for(Container container : host.getContainers()) {
                String containerId = container.getId();
                CompletableFuture.runAsync(() -> {
                    LogContainerCmd logContainerCmd = host.getDockerClient().logContainerCmd(containerId);
                    logContainerCmd.withStdOut(true).withStdErr(true).withFollowStream(true);
                    logContainerCmd.exec(new LogContainerResultCallback() {
                        @Override
                        public void onNext(Frame item) {
//                    log.info(new String(item.getPayload(), UTF_8));
                            System.out.println(new String(item.getPayload()));
                            String destination = "/subscribe/logs/".concat(hostId).concat("/").concat(containerId);
                            template.convertAndSend(destination, new LogChunk(hostId, containerId,item.getPayload()));
                        }
                    });
                });
            }
        }
    }
}
