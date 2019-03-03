package com.xlong.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reponses")
public class Response implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String requestId;

    private String responseError;

    private String responseResult;

    private Date responseTime;

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

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", requestId='" + requestId + '\'' +
                ", responseError='" + responseError + '\'' +
                ", responseResult='" + responseResult + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}
