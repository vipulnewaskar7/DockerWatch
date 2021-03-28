package com.greenature.dockerwatch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Container;
import com.greenature.dockerwatch.model.Host;
import com.greenature.dockerwatch.shared.Values;
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
    public ResponseEntity<String> connectHost(@RequestBody Host host) throws JsonProcessingException {
        // TODO: Validate if host is valid and alive (Best Practice)
        if(host.validate()) {
            Map<String, Object> responseMap = new TreeMap<String, Object>();
            responseMap.put("hostId", host.getHostId());
            String response = new ObjectMapper().writeValueAsString(responseMap);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        Map<String, Object> responseMap = new TreeMap<String, Object>();
        responseMap.put("reason", "Unknown");
        String response = new ObjectMapper().writeValueAsString(responseMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/hosts")
    public ResponseEntity<String> disconnectHost(@RequestBody Host host) throws JsonProcessingException {
        // TODO:  Check if removed successfully
        Values.allHosts.remove(host);

        Map<String, Object> responseMap = new TreeMap<String, Object>();
        responseMap.put("hostId", host.getHostId());
        String response = new ObjectMapper().writeValueAsString(responseMap);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
