package com.greenature.dockerwatch.controllers;


import com.greenature.dockerwatch.services.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    DockerService service;

    @RequestMapping(value = "/docker", method = RequestMethod.GET)
    public String getImages() {
        return service.getDockerImages();
    }
}
