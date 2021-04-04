package com.greenature.dockerwatch.model;

public class LogChunk {

    String hostId;
    String containerId;
    String logChunk;

    public LogChunk(String hostId, String containerId, String logChunk) {
        this.hostId = hostId;
        this.containerId = containerId;
        this.logChunk = logChunk;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getLogChunk() {
        return logChunk;
    }

    public void setLogChunk(String logChunk) {
        this.logChunk = logChunk;
    }
}
