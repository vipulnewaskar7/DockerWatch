package com.greenature.dockerwatch.model;

import com.github.dockerjava.api.model.Container;

public class LogRequest {
    BaseHost host;
    Container container;

    public BaseHost getHost() {
        return host;
    }

    public void setHost(BaseHost host) {
        this.host = host;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
