package files;

public class payload {
	
	public static String AddPlace() {		
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house CJTest\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"30, side layout, cohen 03\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String CoursePrice() {
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 1162,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Appium\",\r\n"
				+ "      \"price\": 36,\r\n"
				+ "      \"copies\": 7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
	}
	
	public static String AddBookBody(String isbn, String aisle) {
		
		String payload = "{\r\n"
				+ "  \"name\": \"New Selenium for Dummies New\",\r\n"
				+ "  \"isbn\": \"" + isbn +"\",\r\n"
				+ "  \"aisle\": \""+ aisle +"\",\r\n"
				+ "  \"author\": \"PoloM\"\r\n"
				+ "}";
		return payload;
	}
	
	
	public static String DeleteBook(String id) {
		
		String payload = "{ \r\n"
				+ "\"ID\" : \""+ id +"\" \r\n"
				+ "} \r\n"
				+ "";
		return payload;
		
	}

}
