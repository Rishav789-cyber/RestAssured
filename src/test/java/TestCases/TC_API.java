package TestCases;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class TC_API {
	
	
	
	

public static void getAllAuthors() {
		
	given()
	.when()
	 .get("/Authors")
	 .then()
	 .statusCode(200);
}
	
	public static void getAuthorById(String ID) {
	Response res=	given()
		.when()
		.get("/Authors/"+ID)
		.then()
		.log().body()
		.statusCode(200)
		.extract().response();
	ResponseBody resBody=res.getBody();
	String JsonString=resBody.asString();
	Assert.assertTrue(JsonString.contains("id"));
	Assert.assertTrue(JsonString.contains("idBook"));
	Assert.assertTrue(JsonString.contains("firstName"));
	Assert.assertTrue(JsonString.contains("lastName"));
	JsonPath jsonParse=res.jsonPath();
	String firstName=jsonParse.get("firstName");
	String lastName=jsonParse.get("lastName");
	Assert.assertTrue(firstName.equalsIgnoreCase("First Name "+ID));
	Assert.assertTrue(lastName.equalsIgnoreCase("Last Name "+ID));
	}

	public static void createAuthor(String id,String idBook,String firstName,String lastName) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("id", id);
		data.put("idBook", idBook);
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		given()
		.header("Content-type","application/json")
		.body(data)
		.when()
		.post("/Authors")
		.then()
		.log().body()
		.statusCode(200);
	}


public static void updateAuthor(String id,String idBook,String firstName,String lastName) {
	HashMap<String,Object> data=new HashMap<String,Object>();
	data.put("id", id);
	data.put("idBook", idBook);
	data.put("firstName", firstName);
	data.put("lastName", lastName);
	given()
	.header("Content-type","application/json")
	.body(data)
	.when()
	.put("/Authors/"+id)
	.then()
	.log().body()
	.statusCode(200);
}

public static void deleteAuthor(String id) {
	given()
	.when()
	.delete("/Authors/"+id)
	.then()
	.statusCode(200);
}
}

