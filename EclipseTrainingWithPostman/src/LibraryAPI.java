import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryAPI {
	
	
	@Test(dataProvider="BooksData")
	
	public void AddBook(String isbn, String aisle) {
		
		
		RestAssured.baseURI= "http://216.10.245.166";
		//RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().header("Content-type", "application/json")
		.body(payload.Book(isbn, aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.extract().response().asString();
		
		System.out.println("Response Body: " + response);
		
	     JsonPath js= ReUsableMethods.rawToJson(response);
		        String id=js.get("ID");
		
		System.out.println("Added book to Library with ID " + id);
	}
	
/*
	
	 // Test method to delete a book by ID using DataProvider
	
    @Test(dataProvider = "BooksData")
    public void DeleteBook(String isbn, String aisle) {
    	
    	// Construct the book ID from ISBN and aisle
        String bookId = isbn + aisle;
        
        // Log the bookId to verify it's coming from the DataProvider
        System.out.println("Deleting book with ID: " + bookId);
        
    	// Send the DELETE request to remove the book, using the ID in the URL path
        RestAssured.baseURI= "https://rahulshettyacademy.com";
                Response response = given()
                .header("Content-Type", "application/json")
                .queryParam("isbn", isbn)  // Use query parameter to pass bookId
                .queryParam("aisle", aisle) 
                .when()
                .delete("/Library/DeleteBook.php")  // DELETE request with ID in URL path
                .then()
                //.log()
                //.all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract()
                .response();
                

        // Print the response to verify the deletion
                System.out.println("Response " + response);
               // System.out.println("Response Body: " + response.prettyPrint());
        	
        	
    }
    
*/
	
	
	@Test(dataProvider = "BooksData")
	public String getBody(String isbn, String aisle){
		String s = "{\n" + 
		  		"\n" + 
		  		"\"name\":\"Learn Aomat Java\",\n" + 
		  		"\"isbn\":\""+isbn+"\",\n" + 
		  		"\"aisle\":\""+aisle+"\",\n" + 
		  		"\"author\":\"Jn foe\"\n" + 
		  		"}\n" + 
		  		" ";
				System.out.println(s);
				 return s;	
		
	}

	@DataProvider(name="BooksData")

	public Object[][] BookData() {
		
		return new Object[][] {{"asdfr", "1234"}, {"lrkjh", "12345"}, {"hdggss", "423452"}, {"sfsdf", "5355"}};
		
	}
    
	    
}
    

    
