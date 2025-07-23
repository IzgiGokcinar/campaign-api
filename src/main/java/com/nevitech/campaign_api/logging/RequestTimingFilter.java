package com.nevitech.campaign_api.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestTimingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTimingFilter.class);
    private static final long THRESHOLD_MS = 5;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;

        if (duration > THRESHOLD_MS && request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            logger.warn("Yavaş istek: {} {} - Süre: {} ms",
                    httpRequest.getMethod(),
                    httpRequest.getRequestURI(),
                    duration);
        }
    }
}
