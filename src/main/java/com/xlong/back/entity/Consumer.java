package com.xlong.back.entity;

import java.util.Date;

public class Consumer {
    private String id;

    private String host;

    private Date creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id='" + id + '\'' +
                ", host='" + host + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
