package com.qa.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Test_1_post {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream(".//resource//env.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("HOST");
	}
	
	@Test
	public void postTest(){
		
	}
}
