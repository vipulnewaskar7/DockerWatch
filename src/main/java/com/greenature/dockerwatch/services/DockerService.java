package com.greenature.dockerwatch.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class DockerService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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

        String containerId = containers.get(0).getId();

        CompletableFuture.runAsync(() -> {
            if (null == containerId) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }

            LogContainerCmd logContainerCmd = dockerClient.logContainerCmd(containerId);
            logContainerCmd.withStdOut(true).withStdErr(true).withFollowStream(true);
            logContainerCmd.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
//                    log.info(new String(item.getPayload(), UTF_8));
                    System.out.println(new String(item.getPayload()));

                    simpMessagingTemplate.convertAndSend("/subscribe/logs", new String(item.getPayload()));
                }
            });
        });













        //TODO: get inputStream and forward it to WebSocket
        return "";
    }

}
