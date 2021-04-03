package com.greenature.dockerwatch.services;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.greenature.dockerwatch.model.BaseHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class DockerService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public String getImages() {
        return "";
    }

    public DockerClient getDockerClient(BaseHost host){
        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(host.address)
                .withDockerTlsVerify(false)
                .build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(custom.getDockerHost())
                .sslConfig(custom.getSSLConfig())
                .build();
        return DockerClientImpl.getInstance(custom, httpClient);
    }

    public boolean validate(BaseHost baseHost) {
        String hostId = this.getDockerClient(baseHost).infoCmd().exec().getId();
        baseHost.setId(hostId);
        return true;
    }


}
