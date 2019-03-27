import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static ArrayList<CountryUnemploymentData> parseCountryUnemploymentData(String data){ //TODO replace year with UnemploymentResults

        ArrayList<CountryUnemploymentData> output = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] labels = rows[4].split(",");
        labels = removeUnnecessaryStuff(labels);
        String[] dataSet = new String[rows.length - 4];
        for (int i = 5; i < rows.length; i++) {
            dataSet[i - 5] = removeUnnecessaryStuff(rows[i]);
        }
        for (int i = 0; i < dataSet.length - 1; i++) {
            CountryUnemploymentData unemploymentData = createUnemploymentData(labels, dataSet[i].split(","));
            output.add(unemploymentData);
        }
        return output;

    }

    public static String countryUnemploymentDataToCSVString(ArrayList<CountryUnemploymentData> list) {
        String out = "\"Country Name\",\"Country Code\",\"Indicator Name\",\"Indicator Code\",\"Average from 1991 to 2018\"\n\n";
        for (CountryUnemploymentData c : list) {
            out += c.toCSVLine() + "\n";
        }
        return out;
    }

    public static String[] removeUnnecessaryStuff(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = removeUnnecessaryStuff(input[i]);
        }
        return input;
    }

    public static String removeUnnecessaryStuff(String str) {
        if (str == null) {
            return null;
        }
        while (str.indexOf(", ") != -1) {
            String str1 = str.substring(0, str.indexOf(", "));
            String str2 = str.substring(str.indexOf(", ") + 1);
            str = str1 + str2;
        }
        while (str.indexOf("\"") != -1) {
            String str1 = str.substring(0, str.indexOf("\""));
            String str2 = str.substring(str.indexOf("\"") + 1);
            str = str1 + str2;
        }
        return str;
    }

    public static CountryUnemploymentData createUnemploymentData(String[] labels, String[] values) {
//        labels = removeUnnecessaryStuff(labels);
//        values = removeUnnecessaryStuff(values);
        CountryUnemploymentData output = new CountryUnemploymentData();
        output.setCountryName(values[0]);
        output.setCountryCode(values[1]);
        output.setIndicatorName(values[2]);
        output.setIndicatorCode(values[3]);
        for (int i = 4; i < values.length; i++) {
            if (!values[i].equals("")) {
                DataPoint p = new DataPoint(Integer.parseInt(labels[i]), Double.parseDouble(values[i]));
                output.addData(p);
            }
        }
        return output;
    }

}
