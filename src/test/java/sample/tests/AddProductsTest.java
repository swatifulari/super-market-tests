package sample.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import sample.utilities.RandomValueGenerator;

public class AddProductsTest {

	/**
	 * Error Scenario.
	 */
	@Test
	public void testDuplicateProductAddition() {

		String jsonString = "[{" + "\"productName\": \"D\"," + "\"price\": 25.0" + "}]";
		Response response = RestAssured.given().body(jsonString).contentType(ContentType.JSON).log().all().when()
				.post("http://localhost:8080/products").then().log().all().statusCode(400).extract().response();
		String responseBody = response.getBody().asString();
		Assert.assertEquals("Product Already Exists", responseBody);
	}

	/**
	 * Success Scenario.
	 */
	@Test
	public void testNewProductAddition() {

		String productName = RandomStringUtils.randomAlphabetic(2);
		double price = RandomValueGenerator.randomDoubleValue();
		String jsonString = "[{" + "\"productName\": " + "\"" + productName + "\"," + "\"price\":" + price + "}]";
		
		//Call POST to create a new Product
		RestAssured.given().body(jsonString).contentType(ContentType.JSON).log().all().when()
				.post("http://localhost:8080/products").then().log().all().statusCode(201);

		//Call GET to validate the new Product is added
		Response response = RestAssured.given().log().all().when().get("http://localhost:8080/products").then().log()
				.all().statusCode(200).extract().response();

		JsonPath jsonPath = JsonPath.from(response.asString());
		assertThat(jsonPath.getList("productName"), hasItem(productName));
	}
	
}
