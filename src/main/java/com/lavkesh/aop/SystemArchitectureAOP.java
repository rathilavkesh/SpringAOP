package com.lavkesh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemArchitectureAOP {

	private static Logger logger = LoggerFactory.getLogger(SystemArchitectureAOP.class);

	@Pointcut("execution(* com.lavkesh..*(..))")
	public void allMethodExecution() {
	}

	@Before("allMethodExecution()")
	public void logBefore(final JoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("Start Executing Methode Signature : " + signature.toShortString());

		StringBuffer arguments = new StringBuffer("Arguments : [ ");
		int i = 1;
		Object[] args = joinPoint.getArgs();
		if (args != null) {
			for (Object object : args) {
				arguments.append(object.toString());
				if (i != args.length) {
					arguments.append(", ");
				}
				i++;
			}
		}
		arguments.append(" ]");
		logger.debug(arguments.toString());
	}

	@AfterReturning(pointcut = "allMethodExecution()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("End Executing Methode Signature : " + signature.toShortString());
		logger.debug("Return Value : " + result);
	}

	@AfterThrowing(pointcut = "allMethodExecution()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("Error while executing methode");
		logger.debug("Methode Signature : " + signature.toShortString());

		logger.debug("Exception : ", error);
	}
}