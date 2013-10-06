package com.lavkesh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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

	/**
	 * A join point is in the service layer if the method is defined in a type
	 * in the com.lavkesh.business package or any sub-package under that.
	 */
	@Pointcut("within(com.lavkesh.business.*.*)")
	public void inBusinessLayer() {
	}

	/**
	 * A dao layer operation is the execution of any method defined in a
	 * com.lavkesh.dao package and it's sub-packages. This definition assumes
	 * that interfaces are placed in the "dao" package, and that implementation
	 * types are in sub-packages.
	 */
	@Pointcut("execution(* com.lavkesh.dao.*.*(..))")
	public void daoLayerExecution() {
	}

	@Before("inBusinessLayer() && ! daoLayerExecution()")
	public void logBefore(final JoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("Before executing business layer methode");
		logger.debug("Methode Signature : " + signature.toShortString());

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
		logger.debug("******************************************************************");
	}

	@AfterReturning(pointcut = "inBusinessLayer()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("After Returning business layer methode");
		logger.debug("Methode Signature : " + signature.toShortString());
		logger.debug("Return Value : " + result);
		logger.debug("******************************************************************");
	}

	@Around("daoLayerExecution()")
	public Object logDaoAction(final ProceedingJoinPoint pjp) throws Throwable {
		Signature signature = pjp.getSignature();
		logger.debug("******************************************************************");
		logger.debug("Executing dao layer methode");
		logger.debug("Methode Signature : " + signature.toShortString());

		StringBuffer arguments = new StringBuffer("Arguments : [ ");
		int i = 1;
		Object[] args = pjp.getArgs();
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

		Object returnValue = pjp.proceed();
		logger.debug("Return Value : " + returnValue);
		logger.debug("******************************************************************");

		return returnValue;
	}

	@AfterThrowing(pointcut = "inBusinessLayer() || daoLayerExecution()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		Signature signature = joinPoint.getSignature();
		logger.debug("******************************************************************");
		logger.debug("Error while executing methode");
		logger.debug("Methode Signature : " + signature.toShortString());

		logger.debug("Exception : ", error);
		logger.debug("******************************************************************");
	}
}