package com.paysonix.httprequesthandler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sergei Korepanov
 */
@Service
public class SortedParamsConcatenationService implements ParamsConcatenationService {
    private static final Logger logger = LoggerFactory.getLogger(SortedParamsConcatenationService.class);
    @Override
    public String concatParams(Map<String, String> params) {
        logger.info("Concatenating params");
        return params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
