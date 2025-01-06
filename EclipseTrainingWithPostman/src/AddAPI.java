import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;


public class AddAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Validate if Add place is working as expected
		
		//given - all input details 
		//when - submit request
		//then - validate response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlaceBody())
		
		.when().post("maps/api/place/add/json")
		
		.then().statusCode(200).body("scope", equalTo("APP")).extract().asString();
		
		System.out.println(response);
		
	/*	JsonPath js=new JsonPath(response);
		String placeID= js.getString("place_id");
		*/
		JsonPath js=ReUsableMethods.rawToJson(response);
		String placeID = js.get("place_id");
		
		System.out.println(placeID);
		
		
		//UPdate address
		String newAddress="593400, Yonge street, Appartment 1023";
		String newPhone= "(+905) 234 4567";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body("{\n"
				+ "    \"place_id\": \""+placeID+"\",\n"
				+ "    \"phone_number\": \""+newPhone+"\",\n"
				+ "    \"address\": \""+newAddress+"\",\n"
				+ "    \"key\" :\"qaclick123\"\n"
				+ "    \n"
				+ "}")
		
		.when().put("maps/api/place/update/json")
		
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		System.out.println("Address successfully updated");
		
		
		//Get address
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
		.queryParam("place_id", placeID)
		
		.when().get("maps/api/place/get/json")
		
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		String extractedNewAddress = js1.get("address");
		
	    Assert.assertEquals(newAddress, extractedNewAddress);
	
	
      }
}

