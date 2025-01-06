import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class CompareSummaryPrice {

	
	@Test
	public void sumOfCourses() {
		
		
		//get course prices
		JsonPath js= new JsonPath(payload.CoursePrice());
		
		//get number of courses
		int countCourses=js.getInt("courses.size()");
		
		//initialize price total of all courses*copies(total equals to zero at beginning)
		int total = 0;
		
		//iterate between items and get price and number of copies for each item
		
		for (int i=0;i<countCourses;i++)
		{
			int coursePrice =js.getInt("courses["+i+"].price");
			int courseCopies =js.getInt("courses["+i+"].copies");
			
			//multiply received prices and number of copies
			int amountPrice = coursePrice * courseCopies;
			System.out.println(amountPrice);
			
			//Adding price to total as it it calculated though the loop
			total= total + amountPrice;	
		}
		
		System.out.println("Total courses price equals to " + total);
		
		int totalCoursesPurchase = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(total, totalCoursesPurchase);
	}
	
}
