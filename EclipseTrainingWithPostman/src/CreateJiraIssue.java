import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;





public class CreateJiraIssue {
	
	
	@Test
	public void CreateIssue() {
		
	
		RestAssured.baseURI = "https://elenakotliar.atlassian.net/";
		
		String createIssueResponse = given()
		.headers("Content-Type", "application/json")
		.headers("Authorization", "Basic ZWxlbmFrb3RsaWFyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBOQVdiQ1ZNaFpwMUlCTFlNbkZValIweHhIMFFzbFpZR082MkQwOUhwaWFGd29BUW0ycmxtT1d0UExoWExiRENlNlYtUF90VmFDR1dqdTVyOUIxZmd4cm5STXQ0X3B4c3FJRGJsUURXQXlCc1pPdUFoU0E5SnJWeDdSajBNOGdJc0RlUWdUcVhINHNxbXlCSDIzX0xTMEZyZks2YWlaZC1qeVN5RE5JTm83dU09RDM2RkUyMUM")
		.body("{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"SCRUM\"\n"
				+ "       },\n"
				+ "       \"summary\": \"New bug with attachment from.\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Bug\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}")
		
		.log().all()
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();

		JsonPath js=new JsonPath(createIssueResponse);
		String issueID = js.getString("id");
		System.out.println("Response is "+ issueID);
		
		
		
		
		//Add attachment
		
		given()
		.param("key", issueID)
		.headers("X-Atlassian-Token", "no-check")
		.header("Content-Type","multipart/form-data")
		.headers("Authorization", "Basic ZWxlbmFrb3RsaWFyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBOQVdiQ1ZNaFpwMUlCTFlNbkZValIweHhIMFFzbFpZR082MkQwOUhwaWFGd29BUW0ycmxtT1d0UExoWExiRENlNlYtUF90VmFDR1dqdTVyOUIxZmd4cm5STXQ0X3B4c3FJRGJsUURXQXlCc1pPdUFoU0E5SnJWeDdSajBNOGdJc0RlUWdUcVhINHNxbXlCSDIzX0xTMEZyZks2YWlaZC1qeVN5RE5JTm83dU09RDM2RkUyMUM")
		
		.multiPart("file", new File ("/Desktop/REST_COURSE/Some_test.docx"))
		.log().all()
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
	}

}
