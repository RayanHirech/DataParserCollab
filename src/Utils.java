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
        String out = "\"Country Name\",\"Country Code\",\"Indicator Name\",\"Indicator Code\",\"1960\",\"1961\",\"1962\",\"1963\",\"1964\",\"1965\",\"1966\",\"1967\",\"1968\",\"1969\",\"1970\",\"1971\",\"1972\",\"1973\",\"1974\",\"1975\",\"1976\",\"1977\",\"1978\",\"1979\",\"1980\",\"1981\",\"1982\",\"1983\",\"1984\",\"1985\",\"1986\",\"1987\",\"1988\",\"1989\",\"1990\",\"1991\",\"1992\",\"1993\",\"1994\",\"1995\",\"1996\",\"1997\",\"1998\",\"1999\",\"2000\",\"2001\",\"2002\",\"2003\",\"2004\",\"2005\",\"2006\",\"2007\",\"2008\",\"2009\",\"2010\",\"2011\",\"2012\",\"2013\",\"2014\",\"2015\",\"2016\",\"2017\",\"2018\"\n\n";
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
