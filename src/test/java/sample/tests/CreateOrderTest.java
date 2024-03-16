package sample.tests;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateOrderTest {

	@Test
	public void testWithSingleItem() {
		String json = "{\"cartItems\":[\n" + "    {\n" + "        \"productName\": \"A\",\n"
				+ "        \"price\": 40.0,\n" + "        \"totalNumberOfItems\" : 5\n" + "        \n" + "    }\n"
				+ "]}";

		Response response = RestAssured.given().body(json).contentType(ContentType.JSON).log().all().when()
				.post("http://localhost:8080/createOrder").then().log().all().statusCode(200).extract().response();
		JsonPath jsonPath = JsonPath.from(response.asString());
		Assert.assertNotNull(jsonPath.getDouble("cartTotal"));
	}

	@Test
	public void testWithMultipleItems() {
		String jsonData = "{\"cartItems\":[\n" + "    {\n" + "        \"productName\": \"A\",\n"
				+ "        \"price\": 40.0,\n" + "        \"totalNumberOfItems\" : 5\n" + "    },\n" + "     {\n"
				+ "        \"productName\": \"B\",\n" + "        \"price\": 10.0,\n"
				+ "        \"totalNumberOfItems\" : 5\n" + "    }\n" + "]}";

		Response response = RestAssured.given().body(jsonData).contentType(ContentType.JSON).log().all().when()
				.post("http://localhost:8080/createOrder").then().log().all().statusCode(200).extract().response();
		JsonPath jsonPath = JsonPath.from(response.asString());
		Assert.assertNotNull(jsonPath.getDouble("cartTotal"));
	}

	/**
	 * If user adds Product not available in the Shop,API must return 400 Bad
	 * Request.
	 */
	@Test
	public void testError() {
		String json = "{\"cartItems\":[\n" + "    {\n" + "        \"productName\": \"Z\",\n"
				+ "        \"price\": 40.0,\n" + "        \"totalNumberOfItems\" : 5\n" + "        \n" + "    }\n"
				+ "]}";

		Response response = RestAssured.given().body(json).contentType(ContentType.JSON).log().all().when()
				.post("http://localhost:8080/createOrder").then().log().all().statusCode(400).extract().response();
		JsonPath jsonPath = JsonPath.from(response.asString());
		Assert.assertEquals("Product  is not available in market. Choose Valid Product", jsonPath.getString("message"));
	}
}
