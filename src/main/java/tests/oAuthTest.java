package tests;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import utils.Helpers;
import utils.fileHandler;
import static io.restassured.RestAssured.given;

public class oAuthTest {

    private String user = fileHandler.fileReader("src/resources/google_usr");
    private String pswd = fileHandler.fileReader("src/resources/google_crd");

    @Test
    public void ouathLogin() {
//        At this point this part is deprecated and banned by Google
//    System.setProperty("webdriver.gecko.driver", "C:\\stuff\\geckodriver\\0_27\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        driver.findElement(By.id("identifierId")).sendKeys(this.user);
//        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(this.pswd);
//        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(Keys.ENTER);
//        String url = driver.getCurrentUrl();
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F2wEQg8wCwgSg3GDOp_56Fvfel1yGEYmVdzA1QlH47LFj8jYU6uBLz9NCuD7qjYMlikwCby9y9sWgyGpVNVNjaEA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("scope=")[0];
        System.out.println("MYCode is : " + code);

        //Get the Access token
        String accesTokenResponse =
                given().urlEncodingEnabled(false)
                .queryParams("code", code)
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
