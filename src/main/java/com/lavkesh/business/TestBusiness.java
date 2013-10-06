package com.lavkesh.business;

public interface TestBusiness {
	
	/**
	 * Get hello user message
	 * @param userName
	 * @return
	 */
	public String helloUser(String userName);
	
	/**
	 * Do operation on Number
	 * @param num1
	 * @param num2
	 * @param operation
	 * @return
	 */
	public Long doCalculation(Long num1, Long num2, String operation);

}
