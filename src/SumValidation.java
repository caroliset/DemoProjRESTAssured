import org.testng.Assert;
import org.testng.annotations.Test;
import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses() {	
		
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int sumValues = 0;		
				
		for(int i=0; i<count; i++) {			 
			sumValues = sumValues +  (js.getInt("courses["+ i +"].price")*js.getInt("courses["+ i +"].copies"));
			System.out.println(sumValues);
			}
		
		Assert.assertEquals(totalAmount, sumValues);
				
		if(totalAmount==sumValues) {
			System.out.println("Are equals!");
			}
		
	}

}
