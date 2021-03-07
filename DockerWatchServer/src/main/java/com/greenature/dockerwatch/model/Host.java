package com.greenature.dockerwatch.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Host {

    Logger logger = LoggerFactory.getLogger(Host.class);

    public String id;
    public String name;
    public String address;
    public String status;

    private boolean isActive;

    public Host() {

    }

    public Host(String id, String address) {
        this(id, address, true);
        this.status = "Connected";
    }

    public Host(String id, String address, boolean isActive) {
        this.id = id;
        this.address = address;
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
}
