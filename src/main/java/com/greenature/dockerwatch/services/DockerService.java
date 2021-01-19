package com.greenature.dockerwatch.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerService {


    public List<Image> getImages() {
        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .withDockerTlsVerify(false)
                .build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(custom.getDockerHost())
                .sslConfig(custom.getSSLConfig())
                .build();
        DockerClient dockerClient = DockerClientImpl.getInstance(custom, httpClient);
        return dockerClient.listImagesCmd().exec();
    }

    public List<Container> getContainers() {
        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .withDockerTlsVerify(false)
                .build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(custom.getDockerHost())
                .sslConfig(custom.getSSLConfig())
                .build();
        DockerClient dockerClient = DockerClientImpl.getInstance(custom, httpClient);
//        Map<String ,Object> responsePayload = new TreeMap<String, Object>();
//        containers.forEach(container -> {
//            responsePayload.put("Container ID", container.getId());
//            responsePayload.put("Image ID", container.getImageId());
//            responsePayload.put("Image Name", container.getImage());
//            responsePayload.put("Created", container.getCreated());
//            responsePayload.put("Status", container.getStatus());
//            responsePayload.put("State", container.getState());
//        });

        // return all containers running without processing anything
        return dockerClient.listContainersCmd().exec();
    }

    public String tailLogs() {
        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .withDockerTlsVerify(false)
                .build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(custom.getDockerHost())
                .sslConfig(custom.getSSLConfig())
                .build();
        DockerClient dockerClient = DockerClientImpl.getInstance(custom, httpClient);
        List<Container> containers = dockerClient.listContainersCmd().exec();
        //dockerClient.logContainerCmd(containers.get(0).getId()).withFollowStream(true).exec();
        //TODO: get inputStream and forward it to WebSocket
        return "";
    }

}
