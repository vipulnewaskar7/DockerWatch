package com.greenature.dockerwatch.controllers;

import com.github.dockerjava.api.model.Container;
import com.greenature.dockerwatch.model.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HostController {
    private static final Logger logger = LoggerFactory.getLogger(HostController.class);

    @PutMapping("/hosts")
    public Host addHost(@RequestBody Host host) {
        // Validate if host is valid and alive
        if(host.isActive()) {
            return host;
        }
        // TODO: Handle if host is not valid
        return null;
    }


}
