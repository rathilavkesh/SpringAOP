package com.lavkesh.dao.impl;

import org.springframework.stereotype.Repository;

import com.lavkesh.dao.TestDao;

@Repository("testDao")
public class TestDaoImpl implements TestDao{

	@Override
	public String helloUser(String userName) {
		if(userName != null && userName.trim().length() > 0){
			return "Hello "+userName;
		}
		return "Welcome Guest";
	}

	@Override
	public Long doCalculation(Long num1, Long num2, String operation) {
		if(num1 != null && num2 != null){
			if(operation != null && operation.trim().length() > 0){
				if("Add".equals(operation)){
					return num1 + num2;
				}else if("Sub".equals(operation)){
					return num1 - num2;
				}else if("Mul".equals(operation)){
					return num1 * num2;
				}else if("Div".equals(operation)){
					return num1 / num2;
				}else if("Rem".equals(operation)){
					return num1 % num2;
				}
			}
		}
		return null;
	}

}
