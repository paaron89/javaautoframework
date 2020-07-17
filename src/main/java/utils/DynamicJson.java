package utils;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import payloads.Payloads;

public class DynamicJson {

    @Test
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        String responseAddBokk = RestAssured.given().log().all().header("Content-Type","application/json")
                .body(Payloads.addBook("valamike", "aisle"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println(responseAddBokk);
        String bookId = Helpers.rawToJson(responseAddBokk).get("ID");
        System.out.println("Your book id is: " + bookId);
    }
}
