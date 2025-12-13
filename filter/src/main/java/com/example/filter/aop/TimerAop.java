package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimerAop {

  // UserApiController의 메서드 시작 전후
  @Pointcut("within(com.example.filter.controller.UserApiController)")
  public void timerPointerCut() {

  }

  @Before("timerPointerCut()")
  public void before(JoinPoint joinPoint) {
    log.info("before");
  }

  @After("timerPointerCut()")
  public void after(JoinPoint joinPoint) {
    log.info("after");
  }

  @AfterReturning(pointcut = "timerPointerCut()", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Object result) {
    log.info("afterReturning");
  }

  @AfterThrowing(pointcut = "timerPointerCut()", throwing = "ex")
  public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
    log.info("afterThrowing");
  }

  @Around("timerPointerCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Before proceed");

    Object[] args = joinPoint.getArgs();
    Object[] newArgs = Arrays.copyOf(args, args.length);

    for (int i = 0; i < args.length; i++) {
      if (args[i] instanceof UserRequest userRequest) {
        String newPhoneNumber = userRequest.phoneNumber().replace("-", "");
        UserRequest newUserRequest = userRequest.withPhoneNumber(newPhoneNumber);
        newArgs[i] = newUserRequest;
      }
    }

    Object result = joinPoint.proceed(newArgs);

    log.info("After proceed");

    return result;
  }

}
