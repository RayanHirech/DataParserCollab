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

    public static ArrayList<CountryUnemploymentData> parseUnemploymentData(String data){ //TODO replace year with UnemploymentResults

        ArrayList<CountryUnemploymentData> output = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] labels = rows[4].split(",");
        labels = removeUnnecessaryStuff(labels);
//        for (int i = 0; i < labels.length; i++) {
//            System.out.print(labels[i] + ", ");
//        }
        String[] dataSet = new String[rows.length - 5];
        for (int i = 5; i < rows.length; i++) {
            dataSet[i - 5] = removeUnnecessaryStuff(rows[i]);
        }

//        for (int i = 0; i < dataSet.length; i++) {
//            System.out.println(dataSet[i]);
//        }

        for (int i = 0; i < dataSet.length; i++) {
            CountryUnemploymentData unemploymentData = createUnemploymentData(labels, dataSet[i].split(","));
            output.add(unemploymentData);
        }

        return output;
    }

//    public static ArrayList<CountryUnemploymentData> parseData(String data) {
//
//        ArrayList<CountryUnemploymentData> output = new ArrayList<>();
//
//
//
//        data = data.substring(data.indexOf("\n") + 1);
//
//        String[] dataSet = data.split("\n");
//
//
//
//        for (int i = 0; i < dataSet.length; i++) {
//
//            String str = removeUnnecessaryStuff(dataSet[i]);
//
//            String[] values = str.split(",");
//
//            CountryUnemploymentData unemploymentData = createUnemploymentData(values);
//
//            output.add(unemploymentData);
//
//        }
//
//
//
//        return output;
//
//    }

    public static String[] removeUnnecessaryStuff(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = removeUnnecessaryStuff(input[i]);
        }
        return input;
    }

    public static String removeUnnecessaryStuff(String str) {
        if (str == null) {
            return "";
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
        String countryName = values[0];
        String countryCode = values[1];
        String indicatorName = values[2];
        String indicatorCode = values[3];
        for (int i = 4; i < values.length; i++) {
            if (!values[i].equals("")) {
                DataPoint p = new DataPoint(Integer.parseInt(labels[i]), Double.parseDouble(values[i]));
                output.addData(p);
            }
        }
        return output;
    }

}
