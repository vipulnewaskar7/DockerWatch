package com.greenature.dockerwatch.controllers;

import com.greenature.dockerwatch.model.StatusManager;
import com.greenature.dockerwatch.services.DockerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    DockerService service;

    StatusManager statusManager = new StatusManager();


}
