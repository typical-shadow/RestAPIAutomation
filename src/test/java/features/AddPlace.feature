Feature: Validate Add palce API,s

@AddPlace
 Scenario Outline: validate place is added successfully using add place api
   Given Add place api payload "<name>" "<language>" "<address>"
   When user call "AddPlaceAPI" with "POST" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_id created maps to "<name>" using "GetPlaceAPI"
   
Examples:
|name	|language	|address	|
|Jack Sparrow	|English-UK	|World Largest location	|
|Pirates	|French-EN	|Super World	|

@DeletePlace @Smoke
Scenario: validate Delete API work fine
	Given Delete place payload
	When user call "DeletePlaceAPI" with "POST" http request
  Then the API call got success with status code 200
  Then the API call got success with status code 200
  And "status" in response body is "OK"