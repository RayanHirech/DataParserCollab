import javax.swing.plaf.nimbus.State;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static ArrayList<CountryUnemploymentData> parseCountryUnemploymentData(String data){

        ArrayList<CountryUnemploymentData> output = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] labels = rows[4].split(",");
        labels = removeUnnecessaryStuff(labels);
        String[] dataSet = new String[rows.length - 4];
        for (int i = 5; i < rows.length; i++) {
            dataSet[i - 5] = removeUnnecessaryStuff(rows[i]);
        }
        for (int i = 0; i < dataSet.length - 1; i++) {
            CountryUnemploymentData unemploymentData = createCountryUnemploymentData(labels, dataSet[i].split(","));
            output.add(unemploymentData);
        }
        return output;

    }

    public static ArrayList<StateUnemploymentData> parseStateUnemploymentData(String data) {

        ArrayList<StateUnemploymentData> output = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] labels = rows[7].split(",");
        labels = removeUnnecessaryStuff(labels);
        String[] dataSet = new String[rows.length - 7];
        for (int i = 8; i < rows.length; i++) {
            dataSet[i - 8] = removeUnnecessaryStuff2(rows[i]); //TODO create new removeJunk method for this data set
        }
        for (int i = 0; i < dataSet.length - 1; i++) {
            StateUnemploymentData unemploymentData = createStateUnemploymentData(labels, dataSet[i].split(","));
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

    public static String[] removeUnnecessaryStuff2(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = removeUnnecessaryStuff2(input[i]);
        }
        return input;
    }

    public static String removeUnnecessaryStuff2(String str) {
        if (str == null) {
            return null;
        }
        while (str.indexOf("\"") != -1) {
            int firstQuote = str.indexOf("\"");
            int secondQuote = str.indexOf("\"", firstQuote + 1);
            int commaIndex = str.indexOf(",", firstQuote);
//            System.out.println(firstQuote + ", " + commaIndex + ", " + secondQuote);
            while (commaIndex > firstQuote && commaIndex < secondQuote) {
                firstQuote = str.indexOf("\"");
                secondQuote = str.indexOf("\"", firstQuote + 1);
                String str1 = str.substring(0, commaIndex);
                String str2 = str.substring(commaIndex + 1);
                str = str1 + str2;
                commaIndex = str.indexOf(",", firstQuote);
            }
            firstQuote = str.indexOf("\"");
            secondQuote = str.indexOf("\"", firstQuote + 1);
            if (firstQuote != -1 && secondQuote == -1) {
                String str1 = str.substring(0, firstQuote);
                String str2 = str.substring(firstQuote + 1);
                str = str1 + str2;
            } else if (firstQuote < str.length() - 1 && secondQuote < str.length() - 1) {
                String str1 = str.substring(0, firstQuote);
                String str2 = str.substring(firstQuote + 1, secondQuote);
                String str3 = str.substring(secondQuote + 1);
                str = str1 + str2 + str3;
            }
        }
        return str;
    }

    public static CountryUnemploymentData createCountryUnemploymentData(String[] labels, String[] values) {
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

    public static StateUnemploymentData createStateUnemploymentData(String[] labels, String[] values) {
//        labels = removeUnnecessaryStuff(labels);
//        values = removeUnnecessaryStuff(values);
        StateUnemploymentData output = new StateUnemploymentData();
        output.setFIPS(Integer.parseInt(values[0]));
        output.setState(values[1]);
        output.setAreaName(values[2]);
        output.setRuralUrbanContinuumCode(Integer.parseInt(values[3]));
        output.setUrbanInfluenceCode(Integer.parseInt(values[4]));
        output.setMetro(Integer.parseInt(values[5]));
        for (int i = 6; i < values.length; i++) {
            if (!values[i].equals("")) {
                DataPoint p = new DataPoint(Integer.parseInt(labels[i]), Double.parseDouble(values[i])); //TODO Fix what's in this for-loop
                output.addData(p);
            }
        }
        return output;
    }

    public static void writeDataToFile(String filepath, String data) {
        File outFile = new File(filepath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
