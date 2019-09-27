package com.twitter.testcases;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.util.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Post_Test_1 {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream(".//resource//com//twitter//config//config.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("HOST");
	}
	
	@Test
	public void post_1_test() {
		Response res = 
				given().
					auth().oauth("dWuqD4UvkLSYFG2nrnD2FcPuF",
							"3yC7r3HnGLxHl9bdpIR2jLAUqcspHQ7xALyvr5vg39Uuo5qeZ6",
							"527103464-A7XvpOeqcRWpw1Ot8YYOufV3auOza5uA6HQvBpZY",
							"6yq2W0QBJnwboWxtKcsaRPoXMQqOyYSSRGlwNNOUMiZ7B").and().
							queryParam("status", "Hello Wolrd 2").and().
					when().
						post("1.1/statuses/update.json").
				then().
						assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
						extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		System.out.println(js.prettify());
		String tweetId = js.get("id_str");
		System.out.println("My tweet id is"+tweetId);
	}
	
}
