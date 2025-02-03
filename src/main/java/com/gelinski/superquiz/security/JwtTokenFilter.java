package com.gelinski.superquiz.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.gelinski.superquiz.logger.LoggerFacade;
import com.gelinski.superquiz.logger.LoggerFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider tokenProvider;
    private final LoggerFacade log = LoggerFactory.getLogger(this.getClass());

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

            if (token != null && tokenProvider.validateToken(token)) {
                Authentication auth = tokenProvider.getAuthentication(token);
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureVerificationException e) {
            log.error("Invalid token");
            throw new ServletException("Invalid token");
        }
    }
}
