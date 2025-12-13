package com.example.filter.config;

import com.example.filter.interceptor.OpenApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final OpenApiInterceptor openApiInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(openApiInterceptor)
//        .excludePathPatterns("/api/users") // 특정 경로 제외 가능
        .addPathPatterns("/**")
        .order(0); // 작을수록 우선적
  }

}
