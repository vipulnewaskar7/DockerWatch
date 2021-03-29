package com.greenature.dockerwatch.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class DockerService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public String getImages() {
        return "";
    }

}
