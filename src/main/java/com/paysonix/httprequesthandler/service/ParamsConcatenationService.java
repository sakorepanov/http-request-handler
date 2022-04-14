package com.paysonix.httprequesthandler.service;

import java.util.Map;

/**
 * @author Sergei Korepanov
 */
public interface ParamsConcatenationService {

    String concatParams(Map<String, String> params);

}
