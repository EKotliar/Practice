package demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.ApiCourse;
import pojo.GetCourses;
import pojo.MobileCourse;
import pojo.WebAutomationCourse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oAuthDemo {
	
	@Test
	public void getCourses() {
		
		
	//	RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		//expected web course titles 
		       String[] courseTitlesWeb = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		//expected API course Titles
		       String[] courseTitlesApi = {"Rest Assured Automation using Java", "SoapUI Webservices testing"};
		       
	    //expected mobile course Titles
		       String[] courseTitlesMobile = {"Appium-Mobile Automation using Java"};       
		       
		       
		       
		 // Receiving courses with using access token     
		
				String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("grant_type", "client_credentials")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("scope", "trust")
                //.when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
			
				System.out.println("Response is received " + response);
				
				JsonPath js= new JsonPath(response);
				String accessToken = js.get("access_token");
				
				
				GetCourses gc = given()
				.queryParams("access_token", accessToken)
				//.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
				
				System.out.println("Response received with - Instructor equals to -  " + gc.getInstructor());
				
				System.out.print("Response received with - Course title #3 equals to -  " + gc.getCourses().getWebAutomation().get(2).getCourseTitle() + ".\nResponse received with - Course price #3 equals to - $ " + gc.getCourses().getWebAutomation().get(2).getPrice() );
				
				
				
			
			//getting web courses titles 
				
				List<WebAutomationCourse> webCourse = gc.getCourses().getWebAutomation();
				for (int i=0; i<webCourse.size(); i++)
				{ 
					if (webCourse.get(i).getCourseTitle().equalsIgnoreCase("Selenium Webdriver Java"))
				
                           {     
						//getting price for web driver course
						
						System.out.println(".\nSelenium Webdriver Java course price is " + webCourse.get(i).getPrice());
                           }
					
				}
				
				//adding course titles into array-list, converting array-list into list and comparing to array of titles at the beginning of script 
				
				ArrayList<String> a = new ArrayList<String>();
				
				List<WebAutomationCourse> w = gc.getCourses().getWebAutomation();

				for (int j=0; j<w.size(); j++)
				{
					
					System.out.println("This is Web Courses title - " + w.get(j).getCourseTitle());
					a.add(w.get(j).getCourseTitle());
					
				}
				List<String> expectedTitles=Arrays.asList(courseTitlesWeb);
				
				Assert.assertTrue(a.equals(expectedTitles));
				System.out.println("Expected WEB course titles are correct");
				
				
				

				
	
	
	       //getting api courses titles 
	
	        List<ApiCourse> apiCourse = gc.getCourses().getApi();
	             for (int i=0; i<apiCourse.size(); i++)
	                  { 
		                   if (apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
	
                               {     
			                    //getting price for SOAPUI course
			
			                    System.out.println("SoapUI Webservices testing course price is " + apiCourse.get(i).getPrice());
                               }
		
	                   }
	
	       //adding course titles into array-list, converting array-list into list and comparing to array of titles at the beginning of script 
	
	        ArrayList<String> a1 = new ArrayList<String>();
	
	              List<ApiCourse> w1 = gc.getCourses().getApi();

	                   for (int j=0; j<w1.size(); j++)
	                          {
		
		                          System.out.println("This is API Courses title - " + w1.get(j).getCourseTitle());
		                          a1.add(w1.get(j).getCourseTitle());
		
	                           }
	                   List<String> expectedTitlesApi=Arrays.asList(courseTitlesApi);
	                   Assert.assertTrue(a1.equals(expectedTitlesApi));
	   				
	   				//Assert.assertTrue(a.equals(expectedTitleApi));
	             System.out.println("Expected API course titles are correct");

	


	        //getting mobile courses titles 
	
                  List<MobileCourse> mobileCourse = gc.getCourses().getMobile();
                		  
                     for (int i=0; i<mobileCourse.size(); i++)
                          { 
                               if (mobileCourse.get(i).getCourseTitle().equalsIgnoreCase("Appium-Mobile Automation using Java"))

                                    {     
	                                   //getting price for Appium course
	
	                                   System.out.println("Appium-Mobile Automation using Java course price is " + mobileCourse.get(i).getPrice());
                                      }

                         }

          //adding course titles into array-list, converting array-list into list and comparing to array of titles at the beginning of script 

                   ArrayList<String> a2 = new ArrayList<String>();

          List<MobileCourse> w2 = gc.getCourses().getMobile();

               for (int j=0; j<w2.size(); j++)
                      {

                          System.out.println("This is Mobile Courses title - " + w2.get(j).getCourseTitle());
                           
                          a2.add(w2.get(j).getCourseTitle());

                       }
               List<String> expectedTitlesMobile=Arrays.asList(courseTitlesMobile);
               Assert.assertTrue(a2.equals(expectedTitlesMobile));
				
				//Assert.assertTrue(a.equals(expectedTitleApi));
         System.out.println("Expected Mobile course titles are correct");




        }
	
	
}
