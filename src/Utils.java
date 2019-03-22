import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<Year> parseUnemploymentData(String data){ //TODO replace year with UnemploymentResults

//      ArrayList<CountryUnemployment> temp = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] dataSet = new String[rows.length - 4];
        for (int i = 4; i < rows.length; i++) {
            dataSet[i - 4] = rows[i];
        }

        return null;
    }

}
