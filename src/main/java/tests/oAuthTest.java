package tests;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Helpers;

import static io.restassured.RestAssured.given;

public class oAuthTest {
    public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "C:\\stuff\\geckodriver\\0_27\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

        //Get the Access token
        String accesTokenResponse =
                given()
                .queryParams("code", "")
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println("MyAccessTokenResponse " + accesTokenResponse);
        String accessToken = Helpers.rawToJson(accesTokenResponse).get("access_token");

        //Use accesToken to get courses
        String response = given().queryParam("access_token", accessToken)
                .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("My response: " + response);

    }
}
