import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//Convert the mock response into JSON
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print number of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print title of the first course		
		String title_0 = js.get("courses[0].title");
		System.out.println(title_0);
		
		
		//Print All course titles and their respective prices
		//Using the count of elements in the array 
		
		for(int i=0; i<count; i++) {
			String title = js.get("courses["+ i +"].title");
			int price = js.getInt("courses["+ i +"].price");
			System.out.println("Course: "+ title +" Price: "+ price );
		}
		
		//Print no of copies sold by RPA Course - not using position
		
		for(int i=0; i<count; i++) {
			String title = js.get("courses["+ i +"].title");
			if(title.equalsIgnoreCase("RPA")) {		
				int copies = js.getInt("courses["+ i +"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int sumValues = 0;		
		
		for(int i=0; i<count; i++) {			 
			sumValues = sumValues +  (js.getInt("courses["+ i +"].price")*js.getInt("courses["+ i +"].copies"));
			System.out.println(sumValues);
			}
		
		if(totalAmount==sumValues) {
			System.out.println("Are equals!");
			
		}
	}				
		
}


