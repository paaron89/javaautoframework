package tests;
import static io.restassured.RestAssured.given;

public class oAuthTest {
    public static void main(String[] args) {


        String response = given().queryParam("access_token", "")
                .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("My response: " + response);

    }
}
