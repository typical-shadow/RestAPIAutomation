package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpecification;
	public static ResponseSpecification responseSpecification;

	public RequestSpecification requestSpec() throws IOException {

		if (requestSpecification == null) {

			PrintStream printStream = new PrintStream(new File("logging.txt"));
			requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(printStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(printStream)).build();
			return requestSpecification;
		}
		return requestSpecification;

	}
	
	public ResponseSpecification responseSpec() {
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpecification;
		
	}

	//Fetch data from properties file
	public static String getGlobalValue(String properties) throws IOException {

		Properties prop = new Properties();
		FileInputStream fin = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/resource/global.properties");
		prop.load(fin);

		return prop.getProperty(properties);

	}
	
	public String getJsonParameterValue(Response response, String key) {
		JsonPath json = new JsonPath(response.asString());
		return json.get(key).toString();
		
	}

}
