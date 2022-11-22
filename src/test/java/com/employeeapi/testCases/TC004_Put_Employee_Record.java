package com.employeeapi.testCases;

import org.apache.log4j.BasicConfigurator;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase
{

	RequestSpecification httpRequest;
	Response response;
	
	String empNAme = RestUtils.empName();
	String empSalary= RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		BasicConfigurator.configure();
		
		logger.info("*********** Started TC004_Put_Employee_Record *************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name",empNAme);
		requestParams.put("salary",empSalary);
		requestParams.put("age",empAge);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/update/" +empID);
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empNAme), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json"); //change application/json text/xml; charset=UTF-8
	}
	
	//@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.21.6"); //nginx/1.21.6 nginx/1.14.1
	}
	
	//@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "br"); // br gzip
	}
		
	
	@AfterClass
	void tearDown()
	{
		logger.info("*************Finish TC004_Put_Employee_Record  ****************");
	}
}
