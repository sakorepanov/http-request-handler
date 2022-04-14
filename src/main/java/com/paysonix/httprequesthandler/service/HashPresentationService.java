package com.paysonix.httprequesthandler.service;

/**
 * @author Sergei Korepanov
 */
public interface HashPresentationService {

    String presentParamsHashAsString(String sortedParams);
}
