package com.greenature.dockerwatch.controllers;

import com.greenature.dockerwatch.model.StatusManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.greenature.dockerwatch.model.Host;
import com.greenature.dockerwatch.services.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    DockerService service;


    StatusManager statusManager = new StatusManager();

//    @RequestMapping(value = "/images", method = RequestMethod.GET)
//    @CrossOrigin(origins = "*")
//    public List<Image> getImages() {
//        return service.getImages();
//    }

    @RequestMapping(value = "/containers", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public List<Container> getContainers(@RequestBody Host host) {
        return service.getContainers(host.getHostURL());
    }


    @RequestMapping(value = "/images", method = RequestMethod.POST )
    @CrossOrigin(origins = "*")
    public List<Image> getImagesFromHost(@RequestBody Host host)
    {
        return service.getImages(host.getHostURL());
    }


}
