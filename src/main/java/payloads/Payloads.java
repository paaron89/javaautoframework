package payloads;

public class Payloads {

    public static String addPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Rahul Shetty Academy\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public static String patchPlace(String place_id, String adress){
        return "{\n" +
                "\t\"place_id\": \"" + place_id + "\",\n" +
                "\t\"address\": \"" + adress +"\",\n" +
                "\t\"key\": \"qaclick123\"\n" +
                "}";
    }


    }


