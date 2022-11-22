package com.employeeapi.testCases;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase
{

	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		BasicConfigurator.configure();

		
		logger.info("*************Started TC001_GET_All_Employees ****************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("************* Checking Response Body ****************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body :- " +responseBody);
		Assert.assertTrue(responseBody !=null);
	}
	
	@Test
	void checkStatuscode()
	{
		logger.info("************* Checking Status Code ****************");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code Is :- " +statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("************* Checking Reponse Time ****************");
		
		Long responseTime = response.getTime();
		logger.info("Response Time Is :- " +responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	void checkstatusLine()
	{
		logger.info("************* Checking Status Line ****************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line Is :- " +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("************* Checking Content Type ****************");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type IS :- " +contentType);
		Assert.assertEquals(contentType, "application/json"); //change application/json text/xml; charset=UTF-8
	}
	
	@Test
	void checkserverType()
	{
		logger.info("************* Checking Server Type ****************");
		
		String serverType = response.header("Server");
		logger.info("Server Type Is :- " +serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6"); //nginx/1.21.6 nginx/1.14.1
	}
	
	@Test
	void checkcontentEncoding()
	{
		logger.info("************* Checking Content Encoding ****************");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding Is :- " +contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip"); // br gzip
	}
	
	//@Test
	void checkContentLength()
	{
		logger.info("************* Checking Content Length ****************");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Lenght Is :- " +contentLength);
		System.out.println("Content Lenght Is :- " +contentLength);
		
		if(Integer.parseInt(contentLength)<600)
			logger.warn("Content Length is less than 600");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>500); //581 100
	}
	
	@Test
	void checkCookies()
	{
		logger.info("************* Checking Cookies ****************");
		
		String cookie = response.getCookie("PHP");
		//Assert.assertEquals(cookie, "");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*************Finish TC001_GET_All_Employees ****************");
	}
	
}
