package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileHandler {
    public static String fileReader(String filepath) {
        String filedata = null;
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                filedata = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return filedata;
    }

}
