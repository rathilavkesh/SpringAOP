package com.lavkesh.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavkesh.business.TestBusiness;
import com.lavkesh.dao.TestDao;

@Service("testBusiness")
public class TestBusinessImpl implements TestBusiness {
	
	@Autowired
	private TestDao testDao;

	@Override
	public String helloUser(String userName) {
		return testDao.helloUser(userName);
	}

	@Override
	public Long doCalculation(Long num1, Long num2, String operation) {
		return testDao.doCalculation(num1, num2, operation);
	}

}