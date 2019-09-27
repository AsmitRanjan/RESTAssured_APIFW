package com.qa.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.util.ReusableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test_2_Get {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream(".//resource//env.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("HOST");
	}
	
	@Test
	public void get_1_Test(){
		Response res = 
				given().
					queryParam("page", 2).and().
				when().
						get("api/users").
				then().
						assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
						extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		int placeId = js.get("page");
		System.out.println(placeId);
	}
}
