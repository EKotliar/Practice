import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js= new JsonPath(payload.CoursePrice());
		
        //Print No of courses returned by API
		int countCourses=js.getInt("courses.size()");
		System.out.println("Number of courses "+ countCourses);
		
		//Print Purchase total price
		int countTotal= js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Courses Price "+ countTotal);
		
		
		//Print Title of the first course
		
		String firstTitle=js.getString("courses[1].title");
		System.out.println("Second Courses Title is "+ firstTitle);
		

		//Print All course titles and their respective Prices
		
		for (int i=0 ; i<countCourses; i++)
		{
			String allTitles = js.get("courses["+i+"].title");
			int coursePrices=js.getInt("courses["+i+"].price");
			
			
			// how output can be printed without converting to a string variable 
			
			System.out.println("Courses Title is "+ js.get("courses["+i+"].title").toString());
			
			// how output can be printed with converting to a string variable 
			System.out.println("Courses Title is "+ allTitles);
			System.out.println("Course Price is  "+ coursePrices);
			
		}
		

		//Print no of copies sold by RPA Course
		for (int i=0 ; i<countCourses; i++)
		{
			String allTitles = js.get("courses["+i+"].title");
			if(allTitles.equalsIgnoreCase("Cypress"))
			{
				int copyNumberCypress= js.getInt("courses["+i+"].copies");
				
				System.out.println("This many copies were sold for Cypress " + copyNumberCypress);
				break;
				
			 }
			
		}
			
	}
	
}
		
		
	


