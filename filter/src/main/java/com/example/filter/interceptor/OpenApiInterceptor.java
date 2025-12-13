package com.example.filter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class OpenApiInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 반환값에 따라 controller에게 전달할지를 결정

    log.info("preHandle");

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    // 메서드 레벨에 @OpenApi가 달려있는지 확인
    OpenApi methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
    if (methodLevel != null) {
      log.info("methodLevel");
      return true;
    }

    // 클래스 레벨에 @OpenApi가 달려있는지 확인
    OpenApi classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
    if (classLevel != null) {
      log.info("classLevel");
      return true;
    }

    log.info("not openApi");
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {
    log.info("postHandler");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, @Nullable Exception ex) throws Exception {
    log.info("afterCompletion");
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }

}
