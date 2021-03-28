package com.greenature.dockerwatch.model;

public class LogChunk {

    String hostId;
    String containerId;
    byte[] logChunk;

    public LogChunk(String hostId, String containerId, byte[] logChunk) {
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

    public byte[] getLogChunk() {
        return logChunk;
    }

    public void setLogChunk(byte[] logChunk) {
        this.logChunk = logChunk;
    }
}
