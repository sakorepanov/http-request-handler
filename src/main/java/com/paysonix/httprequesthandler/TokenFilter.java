package com.paysonix.httprequesthandler;

import com.paysonix.httprequesthandler.service.HashPresentationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergei Korepanov
 */
@Component
public class TokenFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    public static final String TOKEN_HEADER_NAME = "Token";
    @Value("${token}")
    private String verifiedToken;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestToken = httpServletRequest.getHeader(TOKEN_HEADER_NAME);
        logger.info("Checking token");

        if (verifiedToken.equals(requestToken)) {
            logger.info("Token is valid");
            chain.doFilter(request, response);
        } else {
            logger.info("Token is not valid");
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
