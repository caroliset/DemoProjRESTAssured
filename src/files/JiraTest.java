package files; 
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert; 


public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		//Login into the Jira API - Session Filter Cookie
		
		//you have to create an object SessionFilter type. In the request the .filter(session) 
		SessionFilter session = new SessionFilter();
		
		//relaxedHTTPValidation() method added -> This means that you'll trust all hosts regardless if the SSL certificate is invalid 
		
		String cookieResponse = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{ \"username\": \"cjaimes37\", \"password\": \"roja1949\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();	
		
		System.out.println(cookieResponse);
		
		
				
		//Add comment to existing issue using Add Comment API - Using Path Parameters
		String messageIssue = "Hello world!"; 
		
		String addCommentResponse = given().pathParam("id", "10200").log().all().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \""+ messageIssue +"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asPrettyString();
		
		
		JsonPath js = new JsonPath(addCommentResponse); 
		String commentID = js.getString("id");
		
		
		
		
		//Add Attachment using multipart method
		
		//We're sending the header Content-Type multipart/form-data item as an input.		
		given().pathParam("id", "10200").log().all().header("X-Atlassian-Token","no-check").filter(session)
		.header("Content-Type", "multipart/form-data")
		.multiPart("file", new File("jira.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		//Get Issue
		//Response returns a JSON structure with the issue details
		//Limiting response using query parameters
		String getIssueResponse = given().filter(session).pathParam("id", "10200").queryParam("fields", "comment")		
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(getIssueResponse);
		// As comments is an array we can use the size() method of the JSON and store it in a integer variable.
		int commentCount = js1.getInt("fields.comment.comments.size()");
		
		//go through the JSON array
		for(int i=0; i<commentCount; i++) {
			String commentID2 = js1.get("fields.comment.comments["+ i +"].id").toString();
			System.out.println(commentID2);
			if(commentID.equalsIgnoreCase(commentID2)) { //is Checking if the comment added in the previous tests is in the array
				
				String message = js1.get("fields.comment.comments["+ i +"].body").toString();
				System.out.println(message);
				Assert.assertEquals(messageIssue, message);
				break;
				
			}
			
			
		}
		
						
		
		

	}

}
