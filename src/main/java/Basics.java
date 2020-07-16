import io.restassured.RestAssured;
import org.testng.Assert;
import payloads.Payloads;
import utils.Helpers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Basics {
    public static void main(String[] args) {



//        Add place

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String rPostPlace = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payloads.addPlace())
        .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
        String placeId = Helpers.rawToJson(rPostPlace).getString("place_id");
        System.out.println("Place id in response is: " + placeId);

        //    Update place
        String updatedAddress = "macska utca 4";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payloads.patchPlace(placeId, updatedAddress)).
                when().put("maps/api/place/update/json").
                then().log().all().assertThat().statusCode(200);

        // Get

        String rGet = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                header("Content-Type", "application/json").
                when().get("maps/api/place/get/json").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        String updatedAdressGet = Helpers.rawToJson(rGet).getString("address");
        Assert.assertEquals(updatedAdressGet, updatedAddress,
                "Updated address mismatch in GET response");

    }
}
