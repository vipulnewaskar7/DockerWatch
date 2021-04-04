package com.greenature.dockerwatch.controllers;

import com.github.dockerjava.api.model.Container;
import com.greenature.dockerwatch.model.*;
import com.greenature.dockerwatch.services.NotifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    NotifierService notifierService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/connecthost")
    public void connectHost(@Payload MessagePattern<BaseHost> messagePattern) {
        //TODO: start threads sending details to this user
        SocketPattern<String> resp = new SocketPattern<>(MessageType.HOSTS, "CONNECTED");
        messagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", resp);
    }

    @MessageMapping("/disconnecthost")
    public void disconenctHost(@Payload MessagePattern<BaseHost> messagePattern) {
        //TODO: remove threads sending details to this user
        SocketPattern<String> resp = new SocketPattern<>(MessageType.HOSTS, "DISCONNECTED");
        messagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", resp);
    }

    @MessageMapping("/logs")
    public void getLogs(@Payload MessagePattern<LogRequest> messagePattern) {
        notifierService.startSendingLogs(messagePattern);
    }

    @MessageMapping("/images")
    public void getImages(@Payload MessagePattern<BaseHost> messagePattern) {
        notifierService.startSendingImageList(messagePattern);
    }

    @MessageMapping("/containers")
    public void getContainers(@Payload MessagePattern<BaseHost> messagePattern) {
        notifierService.startSendingContainerList(messagePattern);
    }


}