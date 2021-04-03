package com.greenature.dockerwatch.model;

public class SocketPattern<T> {
    MessageType type;
    T data;

    public SocketPattern(MessageType type, T data) {
        this.type = type;
        this.data = data;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
