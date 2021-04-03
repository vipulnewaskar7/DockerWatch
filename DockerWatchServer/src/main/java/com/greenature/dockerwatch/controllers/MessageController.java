package com.greenature.dockerwatch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.greenature.dockerwatch.model.MessagePattern;

@Controller
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/logs")
    public void sendLogs(@Payload MessagePattern messagePattern) {
        
        messagingTemplate.convertAndSend(
                chatMessage.getRecipientId(),"/topic/logs",
                service.tailLogs());
    }

    @MessageMapping("/containerlists")
    public void sendImageLists(@Payload MessagePattern messagePattern) {
        
        messagingTemplate.convertAndSend(
                chatMessage.getRecipientId(),"/topic/containerlists",
                service.tailContainerLists());
    }

    @MessageMapping("/imagelists")
    public void sendImageLists(@Payload MessagePattern messagePattern) {
        
        messagingTemplate.convertAndSend(
                chatMessage.getRecipientId(),"/topic/imagelists",
                service.tailImageLists);
    }
}