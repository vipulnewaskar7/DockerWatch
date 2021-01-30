package com.greenature.dockerwatch.controllers;


import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.greenature.dockerwatch.services.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @Autowired
    DockerService service;

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Image> getImages() {
        return service.getImages();
    }

    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Container> getContainers() {
        return service.getContainers();
    }

    // TODO: WebSocket implementation
    @RequestMapping(value = "/{container}/logs", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public String tailLogs(@RequestParam("container") String container) {
        return "";
    }
}
