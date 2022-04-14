package com.paysonix.httprequesthandler.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sergei Korepanov
 */
class ParamsConcatenationServiceTest {

    private final ParamsConcatenationService paramsConcatenationService = new SortedParamsConcatenationService();

    @Test
    void concatParams() {
        String concatParams = paramsConcatenationService.concatParams(
                Map.of("three", "four", "one", "two"));
        assertEquals("one=two&three=four", concatParams);
    }
}