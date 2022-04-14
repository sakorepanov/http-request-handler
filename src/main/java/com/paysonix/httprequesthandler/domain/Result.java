package com.paysonix.httprequesthandler.domain;

/**
 * @author Sergei Korepanov
 */
public class Result {
    private String signature;

    public Result(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
