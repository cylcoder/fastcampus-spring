package com.example.filter.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

//@Component
@Slf4j
public class LoggerFilter implements Filter {

  private static final int CACHE_LIMIT = 1024 * 1024; // 1MB

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(
        (HttpServletRequest) request, CACHE_LIMIT);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
        (HttpServletResponse) response);

    log.info("beforeFilter");
    chain.doFilter(requestWrapper, responseWrapper);
    log.info("afterFilter");

    byte[] requestContentAsByteArray = requestWrapper.getContentAsByteArray();
    byte[] responseContentAsByteArray = responseWrapper.getContentAsByteArray();

    String requestContent = new String(requestContentAsByteArray);
    String responseContent = new String(responseContentAsByteArray);

    log.info("requestContent={}", requestContent);
    log.info("responseContent={}", responseContent);

    responseWrapper.copyBodyToResponse();
  }

}
