package tests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payloads.Payloads;
import utils.Helpers;
import utils.fileHandler;

public class DynamicJson {

    private String baseUrl = "http://216.10.245.166";

    @Test(dataProvider="dataprovider")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = baseUrl;
        String responseAddBokk = RestAssured.given().log().all().header("Content-Type","application/json")
                .body(Payloads.addBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println(responseAddBokk);
        String bookId = Helpers.rawToJson(responseAddBokk).get("ID");
        System.out.println("Your book id is: " + bookId);

    }

    @Test(dataProvider = "dataprovider")
    public void deleteBook(String isbn, String aisle){
        RestAssured.baseURI = baseUrl;
        String rDeleteBook = RestAssured.given().log().all().header("Content-Type", "application/json")
                .body("{\"ID\":isbn+aisle}")
                .when().post("/Library/DeleteBook.php")
                .then().assertThat().statusCode(200).extract().response().asString();
    }

    @Test
    public void fileHandlerTest(){
        String filedata = fileHandler.fileReader("src/resources/filereadtest");
        System.out.println(filedata);
        Assert.assertEquals(filedata, "alma", "File content mismatch");
    }



    @DataProvider(name = "dataprovider")
    public Object [][] getdata(){
        return new Object[][] {{"testisbn", "testaisle"}, {"testdata1", "testdata2"}, {"valami1", "valami2"}};
    }
}
