package com.paysonix.httprequesthandler.domain;

import java.util.List;

/**
 * @author Sergei Korepanov
 */
public class Response {
    private String status;
    private List<Result> result;

    public Response(String status, List<Result> result) {
        this.status = status;
        this.result = result;
    }

    public Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
