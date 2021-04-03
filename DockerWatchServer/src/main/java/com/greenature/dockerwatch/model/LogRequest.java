package com.greenature.dockerwatch.model;

import com.github.dockerjava.api.model.Container;

public class LogRequest {
    BaseHost baseHost;
    Container container;

    public BaseHost getBaseHost() {
        return baseHost;
    }

    public void setBaseHost(BaseHost baseHost) {
        this.baseHost = baseHost;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
