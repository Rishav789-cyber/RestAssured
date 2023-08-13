package TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TE_API {
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI ="https://fakerestapi.azurewebsites.net/api/v1";
	}
	@DataProvider(name="post_Data")
	public Object[][] postData() {
		return new Object[][]{{"1","2","Red","Blue"},{"2","3","Green","Yellow"}};
	}
	@DataProvider(name="get_Data")
		public Object[][] getData(){
			return new Object[][] {{"1"},{"2"}};
		}
	@DataProvider(name="put_Data")
	public Object[][] putData(){
		return new Object[][] {{"1","2","Pink","White"},{"2","20","Brown","Violet"}};
	}

@DataProvider(name="delete_Data")
public Object[][] deleteData(){
	return new Object[][] {{"1"},{"2"}};
}
	@Test(priority=1)
	public static void get() {
		 TC_API.getAllAuthors();
	}
	@Test(priority=2,dataProvider="get_Data")
	public static void getById(String id) {
		TC_API.getAuthorById(id);
	}
	@Test(priority=3,dataProvider="post_Data")
	public static void post(String id,String idBook,String firstName,String lastName) {
		TC_API.createAuthor(id, idBook, firstName, lastName);;
	}
	@Test(priority=4,dataProvider="put_Data")
	public static void put(String id,String idBook,String firstName,String lastName) {
		TC_API.updateAuthor(id, idBook, firstName, lastName);
	}
	@Test(priority=5,dataProvider="delete_Data")
	public static void delete(String id) {
		TC_API.deleteAuthor(id);
	}
}
