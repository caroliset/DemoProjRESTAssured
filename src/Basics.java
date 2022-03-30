import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //Import all methods, evens static.
import static org.hamcrest.Matchers.*; //EqualTo static method

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

//import files.payload;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		
		//given, when, then
		//given - all the input details
		//when - Submit the API - resource, http method
		//Then - validate the response
		//Content of the file to String -> 
		
		/*validate is Add Place is working as expected, validates content of the response and validates the server that is responding. 
		 * The JSON was moved to a files package, there ´s class payload.java with a static method that returns the string with the JSON structure. 		  */	
		 
		//Add place--> Update Place with new Address -> Get Place to validate if New address is present in response
		//1. Using the previous test for add place and putting the response in a String variable. 
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
		//.body(payload.AddPlace())
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\cjaimes\\Documents\\addPlace.json"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString(); //extract the response of the request an store it in a String variable
		
		//2.Parsing the response to JSON.
		JsonPath js = new JsonPath(response);		
		System.out.println(response);
		
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		
		//Update place API and verify the message is ok
		
		String newAddress = "222 winter road NY, USA";
		
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\"" + placeId + "\",\r\n"
				+ "\"address\":\""+ newAddress +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get Place
		String getPlaceResponse = given().log().all().queryParams("key","qaclick123")
		.queryParams("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//Extract the address from the JSON response
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");	
		
		
		//TestNG----------------
		Assert.assertEquals(actualAddress, newAddress);
		//Assert.assertEquals(actualAddress, "Fail test");
		//Assert.assertEquals("fail two", newAddress);
	
		
		System.out.println(actualAddress);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
