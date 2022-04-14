package com.paysonix.httprequesthandler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequestHandlerApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${token}")
    private String token;

    @Test
    void shouldSendOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", token);

        Map<String, String> map = Map.of("one", "two");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/operations/post?operationId=1", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldSendForbidden() {

        Map<String, String> map = Map.of("one", "two");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map);

        ResponseEntity<String> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/operations/post?operationId=1", request, String.class);

        assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
    }

}
