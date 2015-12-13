package utils;

import model.DVD;

import java.io.*;

/**
 * Created by radud on 13/12/2015.
 */
public class FileUtils {

    public static void writeToFile(String filename, DVD dvd) throws FileNotFoundException, UnsupportedEncodingException {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/" + filename, true)));
                out.println(dvd.toString());
                out.flush();
                //more code
            }catch (IOException e) {
                System.out.println("Error writing to file");
            }
    }

}
