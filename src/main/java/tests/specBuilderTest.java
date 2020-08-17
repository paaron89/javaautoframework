package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class specBuilderTest {

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


        RequestSpecification rAddPlace = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key", "qaclickacademy123")
                .setContentType(ContentType.JSON).build() ;
        ResponseSpecification rAddplaceResponse = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        RequestSpecification res = given().spec(rAddPlace).body(addPlace);

        Response response = res.log().all().when().post("/maps/api/place/add/json").then().assertThat().spec(rAddplaceResponse).extract().response();
        String responseString = response.asString();
        System.out.println("MYRESPONSE " + responseString);


    }

}
