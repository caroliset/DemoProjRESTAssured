import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


/**
 * 
 */

/**
 * @author CJaimes
 *
 */
public class DynamicJson {
	
//Dynamic JSON passing parameters through the test method to pass the parameters through the payload class method so the JSON changes for each test 
//Dynamic JSON using multidimensional array (array of arrays) to complete the test multiple times
//@Test @DataProvider - Annotations
	
	@Test(dataProvider = "BooksData")
	public void AddBook(String isbn, String aisle) {		
		 
	    RestAssured.baseURI = "http://216.10.245.166";	    
		String respAddBook =	given().log().all().header("Content-Type", "application/json").body(payload.AddBookBody(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();	
		
		System.out.print(respAddBook);
		
		
	}
	
	
	@Test(dataProvider = "BooksData")
	public void DeleteBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String respDeleteBook = given().log().all().header("Content-Type", "application/json").body(payload.DeleteBook(isbn+aisle))
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(respDeleteBook);
		
		JsonPath js = new JsonPath(respDeleteBook);
		
		String respMsg = js.getString("msg");
		
		Assert.assertEquals("book is successfully deleted", respMsg);		
		
		
	}	
	
	
	
	@DataProvider
	public Object[][] BooksData(){
		
		//array= collection of elements
		//multidimensional array = collection of arrays
		//Each array is a set of data for 1 test. 
		return new Object[][] {{"r2d2", "1970"}, {"cljv", "1900"}, {"rzcr", "1956"}};
	}

}
