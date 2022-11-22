package com.employeeapi.testCases;

import org.apache.log4j.BasicConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase
{

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getAllEmployeeData() throws InterruptedException
	{
		BasicConfigurator.configure();
		
		logger.info("*************Started TC002_GET_Single_Employee_Record ****************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		logger.info("Response Body :- " +responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatuscode()
	{
		int statusCode = response.getStatusCode();
		logger.info("Status Code Is :- " +statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		Long responseTime = response.getTime();
		logger.info("Response Time Is :- " +responseTime);
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine();
		logger.info("Status Line Is :- " +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		logger.info("Content Type IS :- " +contentType);
		Assert.assertEquals(contentType, "application/json"); //change application/json text/xml; charset=UTF-8
	}
	
	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		logger.info("Server Type Is :- " +serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6"); //nginx/1.21.6 nginx/1.14.1
	}
	
		
	@Test
	void checkContentLength()
	{
		String contentLength = response.header("Content-Length");
		logger.info("Content Lenght Is :- " +contentLength);
		System.out.println("Content Lenght Is :- " +contentLength);
		
		Assert.assertTrue(Integer.parseInt(contentLength)<1500); //581 100
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*************Finish TC002_GET_Single_Employee_Record  ****************");
	}
}
