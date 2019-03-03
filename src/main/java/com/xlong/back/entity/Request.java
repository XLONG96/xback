package com.xlong.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String requestId;

    private String requestClassName;

    private String requestMethodName;

    private String requestParams;

    private String requestHost;

    private Date requestTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestClassName() {
        return requestClassName;
    }

    public void setRequestClassName(String requestClassName) {
        this.requestClassName = requestClassName;
    }

    public String getRequestMethodName() {
        return requestMethodName;
    }

    public void setRequestMethodName(String requestMethodName) {
        this.requestMethodName = requestMethodName;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestHost() {
        return requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestId='" + requestId + '\'' +
                ", requestClassName='" + requestClassName + '\'' +
                ", requestMethodName='" + requestMethodName + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", requestHost='" + requestHost + '\'' +
                ", requestTime=" + requestTime +
                '}';
    }
}
