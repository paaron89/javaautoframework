package utils;

import io.restassured.path.json.JsonPath;

public class RawToJson {
    public static JsonPath rawToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

}
