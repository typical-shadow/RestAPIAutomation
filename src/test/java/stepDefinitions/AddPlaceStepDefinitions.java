package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIResources;
import resource.TestDataBuild;
import resource.Utils;

public class AddPlaceStepDefinitions extends Utils {

	RequestSpecification requestSpec;
	private ResponseSpecification responseSpecs;
	private Response response;
	private static String place_Id;
	TestDataBuild testData = new TestDataBuild();

	@Given("Add place api payload {string} {string} {string}")
	public void add_place_api_payload(String name, String language, String address) throws IOException {

		requestSpec = given().spec(requestSpec()).body(testData.addPlacePayload(name, language, address));

		responseSpecs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

	}

	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String api, String method) {

		APIResources resources = APIResources.valueOf(api);

		if (method.equalsIgnoreCase("POST"))
//			response = requestSpec.when().post(resources.getResource()).then().spec(responseSpecs).extract().response();
			response = requestSpec.when().post(resources.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = requestSpec.when().get(resources.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int code) {

		Assert.assertEquals(response.getStatusCode(), code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.get(keyValue).toString(), expectedValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String endpoint) {

		place_Id = getJsonParameterValue(response, "place_id");
		requestSpec = given().spec(requestSpec).queryParam("place_id", place_Id);
		user_call_with_post_http_request(endpoint, "GET");

		String actualName = getJsonParameterValue(response, "name");
		assertEquals(actualName, expectedName);

	}

	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		System.out.println("Delet PaYLOAD");
		requestSpec = given().spec(requestSpec()).body(testData.getDeletePayload(place_Id));

	}

}
