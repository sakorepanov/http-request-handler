package com.paysonix.httprequesthandler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Sergei Korepanov
 */
@Service
public class HashPresentationServiceImpl implements HashPresentationService {
    private static final Logger logger = LoggerFactory.getLogger(HashPresentationServiceImpl.class);

    private String key;
    private String algorithm;

    public HashPresentationServiceImpl(@Value("${hash.calculation.key}") String key,
                                       @Value("${hash.calculation.algorithm}") String algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    public String presentParamsHashAsString(String sortedParams) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        String hashAsString = null;
        logger.info("Presenting params hash as string");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKeySpec);
            hashAsString = bytesToHex(mac.doFinal(sortedParams.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            logger.error(exception.getMessage());
        }
        return hashAsString;
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte h : hash) {
            String hex = Integer.toHexString(0xff & h);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
