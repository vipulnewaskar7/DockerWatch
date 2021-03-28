package com.greenature.dockerwatch.model;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Host {

    public String id;
    public String name;
    public String address;
    public String status;
    Logger logger = LoggerFactory.getLogger(Host.class);
    List<Container> containerList;
    List<Image> imageList;
    private boolean isActive;
    private DockerClient dockerClient;


    public Host(String address) {
        this(address, true);
        this.status = "Connected";
    }

    public Host(String address, boolean isActive) {
        this.address = address;
        this.isActive = isActive;
    }

    public boolean validate() {
        try {
            DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost(address)
                    .withDockerTlsVerify(false)
                    .build();
            DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                    .dockerHost(custom.getDockerHost())
                    .sslConfig(custom.getSSLConfig())
                    .build();
            this.dockerClient = DockerClientImpl.getInstance(custom, httpClient);
            this.containerList = dockerClient.listContainersCmd().exec();
            this.imageList = dockerClient.listImagesCmd().exec();
            this.id = dockerClient.infoCmd().exec().getId();
            return true;
        }
        catch (Exception ex) {
            logger.debug("Something went wrong while validating Host");
            logger.debug(ex.getMessage());
            return false;
        }



    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }

    public void setDockerClient(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public String getHostId() {
        return new String(this.id);
    }

    public String getHostURL() {
        return this.address;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        if (active) {
            this.status = "Connected";
        } else {
            this.status = "Disconnected";
        }
        this.isActive = active;
    }


    public String getDockerEngineId() {
        return this.dockerClient.infoCmd().exec().getId();
    }

    public List<Container> getContainers() {
        refreshContainersList();
        return Lists.newArrayList(containerList);
    }

    public void refreshContainersList() {
        this.containerList = this.dockerClient.listContainersCmd().exec();

    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Host) {
            Host otherHost = (Host) other;
            return otherHost.id.equals(this.id);
        }
        return false;
    }

}
