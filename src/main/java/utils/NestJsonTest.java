package utils;

import io.restassured.path.json.JsonPath;
import payloads.Payloads;

public class NestJsonTest {

    public static void main(String[] args) {

        JsonPath coursesJson = Helpers.rawToJson(Payloads.CoursePriceMock());
        System.out.println(coursesJson);

        Integer purchaseAmount = coursesJson.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        Integer sizeOfCourses = coursesJson.getInt("courses.size()");
        System.out.println(sizeOfCourses);

        for (int i = 0; i < sizeOfCourses; i++) {
            String title = coursesJson.getString("courses[ " + i + "].title");
            System.out.println(title);
            if (title.equalsIgnoreCase("rpa")) {
                int price = coursesJson.getInt("courses[ " + i + "].price");
                System.out.println("Course called: " + title + " costs: " + price);

            }

        }

        System.out.println("Print number of copies if title RPA");
        for (int i = 0; i < sizeOfCourses; i++) {
            if (coursesJson.getString("courses[ " + i + "].title").equalsIgnoreCase("RPA")) {
                System.out.println(coursesJson.get("courses[ " + i + "].copies"));
                break;
            }
        }


        String firstCourse = coursesJson.getString("courses[0].title");
        System.out.println("The first course is: " + firstCourse);


    }
}
