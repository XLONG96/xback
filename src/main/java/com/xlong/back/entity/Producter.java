package com.xlong.back.entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class Producter {
    private String id;

    private String host;

    private String port;

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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Producter{" +
                "id='" + id + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
