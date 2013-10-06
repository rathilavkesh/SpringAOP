package com.lavkesh.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lavkesh.business.TestBusiness;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml"})
public class AOPTest {
	
	@Autowired
	private TestBusiness testBusiness;
	
	@Test
	public void testAddOperation(){
		long num1 = 10;
		long num2 = 5;
		long result = testBusiness.doCalculation(num1, num2, "Add");
		Assert.assertEquals("Incorrect add result.",result, 15);
	}
	
	@Test
	public void testSubOperation(){
		long num1 = 10;
		long num2 = 5;
		long result = testBusiness.doCalculation(num1, num2, "Sub");
		Assert.assertEquals("Incorrect subtraction result.",result, 5);
	}
	
	
	@Test
	public void testMulOperation(){
		long num1 = 10;
		long num2 = 5;
		long result = testBusiness.doCalculation(num1, num2, "Mul");
		Assert.assertEquals("Incorrect multiplication result.",result, 50);
	}
	
	
	@Test
	public void testDivOperation(){
		long num1 = 10;
		long num2 = 5;
		long result = testBusiness.doCalculation(num1, num2, "Div");
		Assert.assertEquals("Incorrect division result.",result, 2);
	}
	
	@Test
	public void testRemOperation(){
		long num1 = 10;
		long num2 = 5;
		long result = testBusiness.doCalculation(num1, num2, "Rem");
		Assert.assertEquals("Incorrect remainder result.",result, 0);
	}
	
	@Test
	public void testHello(){
		String helloUser = testBusiness.helloUser("Lavkesh");
		Assert.assertEquals("Incorrect hello user result.",helloUser, "Hello Lavkesh");
		
		helloUser = testBusiness.helloUser("   ");
		Assert.assertEquals("Incorrect Welcome Guest result.",helloUser, "Welcome Guest");
	}

}
