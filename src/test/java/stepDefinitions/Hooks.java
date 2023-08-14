package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		System.out.println("Before Extecutd");
		AddPlaceStepDefinitions ad = new AddPlaceStepDefinitions();
		ad.add_place_api_payload("jav", "Hindi-EN", "Jack Sparrow Street");
		ad.user_call_with_post_http_request("AddPlaceAPI", "POST");
		ad.verify_place_id_created_maps_to_using("jav", "GetPlaceAPI");
	}
	
}
