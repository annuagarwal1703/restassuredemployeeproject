package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.http.Method;

public class TC_005_DeleteRecord extends TestBase {

	@BeforeClass
	public void DeleteEmpData() throws InterruptedException {
		logger.info("**************Started TC_005_DeleteRecord****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.get("/employees");
		System.out.println("------------------------"+response.asString());
		JsonPath obj= response.jsonPath();
		String Emp_id= obj.get("data[0].id");
		System.out.println(Emp_id);
		response = httpRequest.delete("/delete/" +Emp_id);
		System.out.println("http://dummy.restapiexample.com/api/v1"+"/delete/"+Emp_id);

		Thread.sleep(3);

	}

	@Test
	public void CheckResponseBody() {
		//System.out.println( "the emp id viewed is :::"+emp_id);
		logger.info("**************Checking Response Body****************");
		String responseBody = response.getBody().asString();
		logger.info("The response body is" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
	}

	@Test
	public void CheckStatusCode() {
		logger.info("**************Checking Status Code****************");
		int statusCode = response.getStatusCode();
		logger.info("The status code is" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void CheckResponseTime() {
		logger.info("**************Checking Response Time****************");
		long responseTime = response.getTime();
		logger.info("The response time is" + responseTime);
		if (responseTime > 2000)
			logger.warn("The response time is greater than 2000");
		Assert.assertTrue(responseTime < 3000);
	}

	@Test
	public void CheckStatusLine() {
		logger.info("**************Checking Status Line****************");
		String statusLine = response.getStatusLine();
		logger.info("The status line is" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void CheckContentType() {
		logger.info("**************Checking Content Type****************");
		String ContentType = response.getContentType();
		logger.info("The content type is" + ContentType);
		Assert.assertEquals(ContentType, "application/json;charset=utf-8");
	}

	@Test
	public void CheckServerType() {
		logger.info("**************Checking Server Type****************");
		String ServerType = response.header("Server");
		logger.info("The content type is" + ServerType);
		Assert.assertEquals(ServerType, "nginx/1.16.0");
	}

	/*@Test
	public void CheckContentEncoding() {
		logger.info("**************Checking Content Encoding****************");
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("The Content-Encoding is" + ContentEncoding);
		Assert.assertEquals(ContentEncoding, "gzip");
	}*/

	@Test
	public void CheckContentLength() {
		logger.info("**************Checking Content Length****************");
		String ContentLength = response.header("Content-Length");
		if (Integer.parseInt(ContentLength) < 100)
			logger.warn("The content length is less than 100");
		logger.info("The content length is" + ContentLength);
		Assert.assertTrue(Integer.parseInt(ContentLength) > 10);
	}

	@Test
	public void CheckCookies() {
		logger.info("**************Checking Cookies****************");
		String Cookie = response.getCookie("PHPSESSID");
		Assert.assertTrue(Cookie != null);
	}

	@AfterClass
	public void TearDown() {
		logger.info("*************Finished TC_005_DeleteRecord*****************");

	}

}

