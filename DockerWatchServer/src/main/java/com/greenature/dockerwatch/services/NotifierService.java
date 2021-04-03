package com.greenature.dockerwatch.services;

import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.greenature.dockerwatch.model.BaseHost;
import com.greenature.dockerwatch.model.LogChunk;
import com.greenature.dockerwatch.model.MessagePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotifierService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void startSendingLogs(MessagePattern<Container> messagePattern) {
        BaseHost host = new BaseHost();
        String containerId = "";
        CompletableFuture.runAsync(() -> {
            LogContainerCmd logContainerCmd = host.getDockerClient().logContainerCmd(containerId);
            logContainerCmd.withStdOut(true).withStdErr(true).withFollowStream(true);
            //TODO : Using ResultCallback.Adapter instead of deprecated
            logContainerCmd.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    System.out.println(new String(item.getPayload()));
                    LogChunk logChunk = new LogChunk(host.getId(), containerId, item.getPayload());
                    MessagePattern<LogChunk> logs = new MessagePattern<>("", "", 0L, logChunk);
                    simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", logs);
                }
            });
        });
    }

    public void startSendingImageList(MessagePattern<BaseHost> messagePattern) {
        //TODO: Every after some interval
        BaseHost baseHost = messagePattern.getMessage();
        List<Image> images = baseHost.getDockerClient().listImagesCmd().exec();
        simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", images);
    }

    public void startSendingContainerList(MessagePattern<BaseHost> messagePattern) {
        //TODO: Every after some interval
        BaseHost baseHost = messagePattern.getMessage();
        List<Image> images = baseHost.getDockerClient().listImagesCmd().exec();
        simpMessagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", images);
    }

}
