package com.greenature.dockerwatch.model;

public class Host {
    String hostId;
    String hostURL;
    boolean isActive;

    public Host() {

    }

    public Host(String hostId, String hostURL) {
        this(hostId, hostURL, true);
    }

    public Host(String hostId, String hostURL, boolean isActive) {
        this.hostId = hostId;
        this.hostURL = hostURL;
        this.isActive = isActive;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostURL() {
        return hostURL;
    }

    public void setHostURL(String hostURL) {
        this.hostURL = hostURL;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
