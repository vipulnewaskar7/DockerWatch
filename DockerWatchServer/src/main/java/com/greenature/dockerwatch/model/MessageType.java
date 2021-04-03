package com.greenature.dockerwatch.model;

public enum MessageType {
    IMAGES("IMAGES"),
    CONTAINERS("CONTAINERS"),
    LOGS("LOGS"),
    HOSTS("HOSTS")
    ;

    String type;

    MessageType(String type) {
        this.type = type;
    }


}

