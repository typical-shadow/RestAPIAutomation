package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
		AddPlace ad = new AddPlace();
		ad.setAccuracy(50);
		ad.setName(name);
		ad.setAddress(address);
		ad.setPhone_number("12345677888");

		List<String> type = new ArrayList<>();
		type.add("shoe");
		type.add("cloth");

		ad.setTypes(type);
		ad.setWebsite("jack.com");
		ad.setLanguage(language);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ad.setLocation(loc);
		
		return ad;
		
		
	}

	public String getDeletePayload(String place_Id) {
		// TODO Auto-generated method stub
		return "{\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}";
	}

}
