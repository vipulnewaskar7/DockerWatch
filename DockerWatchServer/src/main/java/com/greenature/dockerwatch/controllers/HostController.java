package com.greenature.dockerwatch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Container;
import com.greenature.dockerwatch.model.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/v1")
public class HostController {
    private static final Logger logger = LoggerFactory.getLogger(HostController.class);

    @PutMapping("/hosts")
    public ResponseEntity<String> addHost(@RequestBody Host host) throws JsonProcessingException {
        // Validate if host is valid and alive
        if(host.isActive()) {
            Map<String, Object> responseMap = new TreeMap<String, Object>();
            responseMap.put("hostId", host.getHostId());
            String response = new ObjectMapper().writeValueAsString(responseMap);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        Map<String, Object> responseMap = new TreeMap<String, Object>();
        responseMap.put("reason", "Requested server not available");
        String response = new ObjectMapper().writeValueAsString(responseMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
