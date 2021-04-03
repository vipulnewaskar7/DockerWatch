package com.greenature.dockerwatch.controllers;

import com.github.dockerjava.api.model.Container;
import com.greenature.dockerwatch.model.BaseHost;
import com.greenature.dockerwatch.model.MessagePattern;
import com.greenature.dockerwatch.model.ResponseCode;
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
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> response = new MessagePattern<>("", messagePattern.getUser(), 0, responseCode);
        messagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", response);
    }

    @MessageMapping("/disconnecthost")
    public void disconenctHost(@Payload MessagePattern<BaseHost> messagePattern) {
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> response = new MessagePattern<>("", "", 0, responseCode);
        messagingTemplate.convertAndSendToUser(messagePattern.getUser(), "/topic/dockerwatch", response);
    }

    @MessageMapping("/getlogs")
    public void getLogs(@Payload MessagePattern<Container> messagePattern) {
        notifierService.startSendingLogs(messagePattern);
    }

    @MessageMapping("/getimages")
    public void getImages(@Payload MessagePattern<BaseHost> messagePattern) {
        notifierService.startSendingImageList(messagePattern);
    }

    @MessageMapping("/getcontainers")
    public void getContainers(@Payload MessagePattern<BaseHost> messagePattern) {
        notifierService.startSendingContainerList(messagePattern);
    }


}