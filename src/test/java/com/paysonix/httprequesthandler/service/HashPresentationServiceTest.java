package com.paysonix.httprequesthandler.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sergei Korepanov
 */
class HashPresentationServiceTest {

    private final HashPresentationService hashPresentationService =
            new HashPresentationServiceImpl("key", "HmacSHA256");

    @Test
    void presentParamsHashAsString() {
        String hashAsString = hashPresentationService.presentParamsHashAsString("one=two&three=four");
        assertEquals("9e8e3f2b8c76d26ab92bb063b8ad5f85ea480081e7475076a9a63589abdd7426", hashAsString);
    }
}