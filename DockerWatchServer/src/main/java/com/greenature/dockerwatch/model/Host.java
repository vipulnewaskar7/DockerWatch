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

import java.util.Collection;
import java.util.List;

public class Host {

    Logger logger = LoggerFactory.getLogger(Host.class);

    public String id;
    public String name;
    public String address;
    public String status;
    private boolean isActive;
    private boolean isAlive;
    List<Container> containerList;
    List<Image> imageList;
    public DockerClient getDockerClient() {
        return dockerClient;
    }

    public void setDockerClient(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    private DockerClient dockerClient;



    public Host(String id, String address) {
        this(id, address, true);
        this.status = "Connected";
    }

    public Host(String id, String address, boolean isActive) {
        this.id = id;
        this.address = address;
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
        this.isActive = isActive;
    }

    public String getHostId() {
        return this.id;
    }

    public void setHostId(String id) {
        this.id = id;
    }

    public String getHostURL() {
        return this.address;
    }

    public void setHostURL(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        if(active){
            this.status = "Connected";
        } else {
            this.status = "Disconnected";
        }
        this.isActive = active;
    }

    public boolean isAlive() {
        return true;
    }

    public String getDockerEngineId(){
        return this.dockerClient.infoCmd().exec().getId();
    }

    public List<Container> getContainers() {
        refreshContainersList();
        return Lists.newArrayList(containerList);
    }

    public void refreshContainersList() {
        this.containerList =  this.dockerClient.listContainersCmd().exec();

    }



}
