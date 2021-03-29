package com.greenature.dockerwatch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greenature.dockerwatch.model.BaseHost;
import com.greenature.dockerwatch.model.MessagePattern;
import com.greenature.dockerwatch.model.ResponseCode;
import com.greenature.dockerwatch.shared.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class HostController {
    private static final Logger logger = LoggerFactory.getLogger(HostController.class);


    @GetMapping("/hosts")
    public MessagePattern<Set<BaseHost>> getAllHosts() throws JsonProcessingException {
        return new MessagePattern<>("0", "", LocalDate.now().toEpochDay(), Values.allHosts );
    }

    //ADD
    @PostMapping("/hosts")
    public MessagePattern<ResponseCode> updateHost(@RequestBody MessagePattern<BaseHost> request) throws JsonProcessingException {
        // TODO: Validate if host is valid and alive (Best Practice)
        System.out.println(Values.allHosts);
        BaseHost host = request.getMessage();
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> messagePattern = new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode );
        if (host.validate()) {
            System.out.println(Values.allHosts);
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
    public MessagePattern<ResponseCode> deleteHost(@RequestBody MessagePattern<BaseHost> request) throws JsonProcessingException {
        // TODO:  Check if removed successfully
        BaseHost host = request.getMessage();
        System.out.println(host.id);
        ResponseCode responseCode = new ResponseCode();
        MessagePattern<ResponseCode> messagePattern = new MessagePattern<>(request.getRequestid(), "", LocalDate.now().toEpochDay(), responseCode);
        if( Values.allHosts.removeIf(element-> element.equals(host))) {
            responseCode.setMessage("");
            responseCode.setStatus("DELETE SUCCESS");
        }
        else {
            responseCode.setMessage("");
            responseCode.setStatus("DELETE FAILED");
        }
        System.out.println(Values.allHosts);

        return messagePattern;
    }

}
