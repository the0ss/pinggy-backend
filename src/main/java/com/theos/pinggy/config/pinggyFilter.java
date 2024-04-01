package com.theos.pinggy.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class pinggyFilter implements Filter{

    private static final ThreadLocal<String> headerThreadLocal = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("PinggyAuthHeader");

        if (authHeader != null && !authHeader.isEmpty()) {
            headerThreadLocal.set(authHeader);
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.sendError(401, "Unauthorized");
        }
    }

    public static String getAuthHeaderValue() {
        return headerThreadLocal.get();
    }
    
}
