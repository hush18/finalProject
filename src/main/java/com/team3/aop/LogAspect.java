package com.team3.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public class LogAspect {
	public static Logger logger=Logger.getLogger(LogAspect.class.getName());
	public static final String logMsg="LogMsg------------------";
	
	@Around(value="within(com.team3..*)")
	public Object advice(ProceedingJoinPoint joinPoint) {
		Object obj=null;
		
		try {
			logger.info(logMsg+joinPoint.getTarget().getClass().getName()+"\t"+joinPoint.getSignature().getName());
			obj=joinPoint.proceed();
		}catch (Throwable e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.severe(logMsg+e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
}
