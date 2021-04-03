package com.greenature.dockerwatch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greenature.dockerwatch.model.BaseHost;
import com.greenature.dockerwatch.model.MessagePattern;
import com.greenature.dockerwatch.model.ResponseCode;
import com.greenature.dockerwatch.model.UserDetails;
import com.greenature.dockerwatch.services.DockerService;
import com.greenature.dockerwatch.shared.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class HostController {
    private static final Logger logger = LoggerFactory.getLogger(HostController.class);
    @Autowired
    DockerService dockerService;

    @GetMapping("/hosts")
    @CrossOrigin(origins = "*")
    public MessagePattern<Set<BaseHost>> getAllHosts() throws JsonProcessingException {
        //TODO: Adding localhost by default
        if (Values.allHosts.isEmpty()) {
            BaseHost baseHost = new BaseHost();
            baseHost.setName("Localhost");
            baseHost.setAddress("tcp://localhost:2375");
            dockerService.validate(baseHost);
            Values.allHosts.add(baseHost);
        }
        return new MessagePattern<>("0", "", LocalDate.now().toEpochDay(), Values.allHosts);
    }

    //ADD
    @PostMapping("/hosts")
    @CrossOrigin(origins = "*")
    public MessagePattern<ResponseCode> addHost(@RequestBody MessagePattern<BaseHost> request) throws JsonProcessingException {
        // TODO: Validate if host is valid and alive (Best Practice)
        System.out.println(Values.allHosts);
        BaseHost host = request.getMessage();
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> messagePattern = new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode);
        if (dockerService.validate(host)) {
            System.out.println(Values.allHosts);
            boolean exists = false;
            Values.allHosts.removeIf(element -> element.equals(host));
            Values.allHosts.add(host);
            System.out.println(Values.allHosts);
            responseCode.setMessage("");
            responseCode.setStatus("SUCCESS");

            return messagePattern;
        }
        responseCode.setStatus("FAILED");
        responseCode.setMessage("UNKNOWN REASON");
        return messagePattern;
    }

    //DELETE
    @DeleteMapping("/hosts")
    @CrossOrigin(origins = "*")
    public MessagePattern<ResponseCode> deleteHost(@RequestBody MessagePattern<BaseHost> request) throws JsonProcessingException {
        // TODO:  Check if removed successfully
        BaseHost host = request.getMessage();
        System.out.println(host.id);
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> messagePattern = new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode);
        if (Values.allHosts.removeIf(element -> element.equals(host))) {
            responseCode.setMessage("");
            responseCode.setStatus("SUCCESS");
        } else {
            responseCode.setMessage("");
            responseCode.setStatus("FAILED");
        }
        System.out.println(Values.allHosts);

        return messagePattern;
    }

    @PostMapping("/login")
    public MessagePattern<ResponseCode> login(@RequestBody MessagePattern<UserDetails> request) {
        UserDetails userDetails = request.getMessage();
        ResponseCode responseCode = new ResponseCode();
        responseCode.setStatus("SUCCESS");
        responseCode.setMessage(userDetails.getUsername());
        return new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode);
    }

    @PostMapping("/logout")
    public MessagePattern<ResponseCode> logout(@RequestBody MessagePattern<String> request) {
        String username = request.getUser();
        ResponseCode responseCode = new ResponseCode();
        responseCode.setStatus("SUCCESS");
        responseCode.setMessage(username);
        return new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode);
    }

}
