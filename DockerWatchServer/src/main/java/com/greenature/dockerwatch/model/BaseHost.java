package com.greenature.dockerwatch.model;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHost {
    public String id;
    public String name;
    public String address;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean validate() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BaseHost) {
            BaseHost otherHost = (BaseHost) other;
            System.out.println("" + this.id);
            System.out.println("" + otherHost.id);
            System.out.println("host equals " + otherHost.id.equals(this.id));
            return otherHost.id.equals(this.id);
        }
        System.out.println("else :host equals false");
        return false;
    }
}

class Host extends BaseHost {

    Logger logger = LoggerFactory.getLogger(Host.class);
    private boolean isActive;

    private DockerClient dockerClient;

    public Host() {

    }

    public Host(String address) {
        this(address, "undefined");
        this.status = "Connected";
    }

    public Host(String address, String name) {
        this.address = address;
        this.name = name;
    }


    public DockerClient getDockerClient() {
        return dockerClient;
    }

    public void setDockerClient(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }


    public String getDockerEngineId() {
        return this.dockerClient.infoCmd().exec().getId();
    }


    @Override
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
            this.id = dockerClient.infoCmd().exec().getId();
            return true;
        } catch (Exception ex) {
            logger.debug("Something went wrong while validating Host");
            logger.debug(ex.getMessage());
            return false;
        }


    }


}
