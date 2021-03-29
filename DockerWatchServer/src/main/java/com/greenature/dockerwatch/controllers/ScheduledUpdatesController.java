package com.greenature.dockerwatch.controllers;

import com.greenature.dockerwatch.model.BaseHost;
import com.greenature.dockerwatch.shared.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdatesController {
    @Autowired
    private SimpMessagingTemplate template;

    public ScheduledUpdatesController() {
        System.out.println("Initializing scheduled updates");
        startTailingLogs();
    }


    public void startTailingLogs() {
        System.out.println("Executed");
        for (BaseHost host : Values.allHosts) {
            System.out.println("TAILING...");
            String hostId = host.getId();
//            for(Container container : host.getContainers()) {
//                String containerId = container.getId();
//
//                CompletableFuture.runAsync(() -> {
//                    System.out.println("TAILING...");
//                    LogContainerCmd logContainerCmd = host.getDockerClient().logContainerCmd(containerId);
//                    logContainerCmd.withStdOut(true).withStdErr(true).withFollowStream(true);
//                    logContainerCmd.exec(new LogContainerResultCallback() {
//                        @Override
//                        public void onNext(Frame item) {
////                    log.info(new String(item.getPayload(), UTF_8));
//                            System.out.println(new String(item.getPayload()));
//                            String destination = "/subscribe/logs"; //.concat(hostId).concat("/").concat(containerId)
//                            template.convertAndSend(destination, new LogChunk(hostId, containerId,item.getPayload()));
//                        }
//                    });
//                });
//            }
        }
    }
}
