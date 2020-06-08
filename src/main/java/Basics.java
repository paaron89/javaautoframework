import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloads.Payloads;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Basics {
    public static void main(String[] args) {



//        Add place

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payloads.addPlace())
        .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        String placeId = jsonPath.getString("place_id");
        System.out.println("Place id in response is: " + placeId);

        //    Update place
        String updatedAddress = "macska utca 4";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payloads.patchPlace(placeId, updatedAddress)).
                when().put("maps/api/place/update/json").
                then().log().all().assertThat().statusCode(200);

        // Get

        given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                header("Content-Type", "application/json").
                when().get("maps/api/place/get/json").
                then().log().all().assertThat().statusCode(200).body("address", equalTo(updatedAddress));
    }
}
