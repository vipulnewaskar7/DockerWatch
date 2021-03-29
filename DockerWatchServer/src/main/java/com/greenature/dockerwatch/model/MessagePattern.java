package com.greenature.dockerwatch.model;

public class MessagePattern<T> {
    private String requestid;
    private String user;
    private long time;
    private T message;

    public MessagePattern(String requestid, String user, long time, T message) {
        this.requestid = requestid;
        this.user = user;
        this.time = time;
        this.message = message;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }


}


/*
*
*
* {
    "requestid":"string",
    "user": "string",
    "time":"number"
    "message": {
        "id": "string",
        "name": "string",
        "address": "string",
        "status": "string"
    }
}
*
* */