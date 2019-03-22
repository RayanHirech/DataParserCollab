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

    public static ArrayList<Year> parseUnemploymentData(String data){
        ArrayList<CountryUnemployment> temp = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] newrows = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
            int input = 0;
            String row = rows[i];
            if(row.isEmpty()) input++;
            else newrows[i - input] = row;
        }

        for (int i = 0; i < newrows.length; i++) {
            String newrow = newrows[i];
            
        }

        return null;
    }

}
