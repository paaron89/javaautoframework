package tests.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;
import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    @Test
    public void serializeTest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("Macska utca 4");
        addPlace.setWebsite("www.macskahhhk.com");
        addPlace.setLanguage("HUN");
        addPlace.setName("Macska house");

        List<String> myList = new ArrayList<String>();
        myList.add("element1");
        myList.add("element2");
        addPlace.setTypes(myList);
        Location location = new Location();
        addPlace.setLocation(location);
        location.setLat(-38.38);
        location.setLng(-11.22334);


        Response r_postPlace = given().log().all().queryParam("key", "qaclickacademy123").
                body(addPlace).
                when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response();
        String postPlace = r_postPlace.getBody().asString();
        String language_request = addPlace.getLanguage();
        System.out.println("MY language request " + language_request);
        JsonPath r_JsonPath = Helpers.rawToJson(postPlace);
        String place_id = r_JsonPath.get("place_id");
        System.out.println("My place id: " + place_id);

        System.out.println("My response " + postPlace);

    }

}
