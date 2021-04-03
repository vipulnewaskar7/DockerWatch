package com.greenature.dockerwatch.services;

import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.EventType;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.greenature.dockerwatch.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotifierService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    DockerService dockerService;

    public void startSendingLogs(MessagePattern<LogRequest> messagePattern) {
        LogRequest logRequest = messagePattern.getMessage();
        BaseHost baseHost = logRequest.getBaseHost();
        Container container = logRequest.getContainer();
        String containerId = "";
        CompletableFuture.runAsync(() -> {
            LogContainerCmd logContainerCmd = dockerService.getDockerClient(baseHost).logContainerCmd(containerId);
            logContainerCmd.withStdOut(true).withStdErr(true).withFollowStream(true);
            //TODO : Using ResultCallback.Adapter instead of deprecated
            logContainerCmd.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    System.out.println(new String(item.getPayload()));
                    LogChunk logChunk = new LogChunk(baseHost.getId(), containerId, item.getPayload());
                    SocketPattern<LogChunk> response = new SocketPattern<>(MessageType.LOGS, logChunk);
                    simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", response);
                }
            });
        });
    }

    public void startSendingImageList(MessagePattern<BaseHost> messagePattern) {
        //TODO: Every after some interval
        BaseHost baseHost = messagePattern.getMessage();
        List<Image> images = dockerService.getDockerClient(baseHost).listImagesCmd().exec();
        SocketPattern<List<Image>> response = new SocketPattern<>(MessageType.IMAGES, images);
        simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", response);
    }

    public void startSendingContainerList(MessagePattern<BaseHost> messagePattern) {
        //TODO: Every after some interval
        BaseHost baseHost = messagePattern.getMessage();
        List<Container> containers = dockerService.getDockerClient(baseHost).listContainersCmd().exec();
        SocketPattern<List<Container>> response = new SocketPattern<>(MessageType.CONTAINERS, containers);
        simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", response);
    }

    public void test() {
        BaseHost baseHost = new BaseHost();
        dockerService.getDockerClient(baseHost).eventsCmd().withEventTypeFilter(EventType.IMAGE);
    }

}
