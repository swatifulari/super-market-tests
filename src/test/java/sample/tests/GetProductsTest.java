package sample.tests;

import org.junit.Test;

import io.restassured.RestAssured;

public class GetProductsTest {

	@Test
	public void testSuccess() {
		RestAssured.given().log().all().when().get("http://localhost:8080/products").then().log().all().statusCode(200);
	}
}
