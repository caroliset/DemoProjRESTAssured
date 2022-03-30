package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
		
		//Login into the Jira API - Session Filter Cookie
		
		//you have to create an object SessionFilter type. In the request the .filter(session) 
		SessionFilter session = new SessionFilter();
		
		
		String cookieResponse = given().headers("Content-Type", "application/json")
				.body("{ \"username\": \"cjaimes37\", \"password\": \"roja1949\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();	
		
		System.out.println(cookieResponse);
		
		
		//Get Issue
		//Response returns a JSON structure with the issue details
		given().filter(session).pathParam("id", "10200").queryParam("fields", "comment")		
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all();
				
		

	}

}
