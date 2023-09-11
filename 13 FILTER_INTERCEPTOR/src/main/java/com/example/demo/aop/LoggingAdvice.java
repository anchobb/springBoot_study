package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //AOP처리
@Component //Bean등록
public class LoggingAdvice {
	
	//
	//Pointcut 표현식 : "execution(* com.test.app.domain.service.SimpleService.get1())"
	//execution() : 메서드 실행
	//* : 모든 리턴 타입
	//com.test.app.domain.service.SimpleService.get1() : 실행할 함수 위치
	@Before("execution(* com.example.demo.domain.service.SimpleService.get1())") //대상 서비스로직 지정 "execution(반환타입 서비스로직위치)"
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("BEFORE..."+joinPoint.getSignature().getName());
	}
	@After("execution(* com.example.demo.domain.service.SimpleService.get2())") //대상 서비스로직 지정 "execution(반환타입 서비스로직위치)"
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("AFTER..."+joinPoint.getTarget());
	}
	@Around("execution(* com.example.demo.domain.service.SimpleService.get3())")
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("[AOP] START : " + pjp.getSignature().getName());
		Object result = pjp.proceed(); //대상 타겟 함수(get3())을 실행
		System.out.println("[AOP] RESULT : " + result);
		long end = System.currentTimeMillis();
		System.out.println("[AOP] TIME : " +(end-start)+"ms" );
		System.out.println("[AOP] END : ");
	}
	@Around("execution(* com.example.demo.domain.service.*.*(..))")
	public Object logAroundAll(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("[AOP] START : " + pjp.getSignature().getName());
		Object result = pjp.proceed(); //대상 타겟 함수(get3())을 실행
		System.out.println("[AOP] RESULT : " + result);
		long end = System.currentTimeMillis();
		System.out.println("[AOP] TIME : " +(end-start)+"ms" );
		System.out.println("[AOP] END : ");
		return result;
	}
}
