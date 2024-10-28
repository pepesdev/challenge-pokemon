package com.request.log.request_log.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="request_log")
public class RequestLog {

    @Id
    private Integer id;
    @Column(name="ip_origin")
    private String ip_origin;

    @Column(name="date_request")
    private Date date_request;

    @Column(name="method")
    private String method;

    @Column(name="elapsed_time")
    private String elapsedTime;

    @Column(name="request")
    private String request;

    @Column(name="response")
    private String response;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp_origin() {
        return ip_origin;
    }

    public void setIp_origin(String ip_origin) {
        this.ip_origin = ip_origin;
    }

    public Date getDate_request() {
        return date_request;
    }

    public void setDate_request(Date date_request) {
        this.date_request = date_request;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestDTO{");
        sb.append("id=").append(id);
        sb.append(", ip_origin='").append(ip_origin).append('\'');
        sb.append(", date_request=").append(date_request);
        sb.append(", method='").append(method).append('\'');
        sb.append(", elapsedTime='").append(elapsedTime).append('\'');
        sb.append(", request='").append(request).append('\'');
        sb.append(", response='").append(response).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
