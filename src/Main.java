import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String unEmploymentData = Utils.readFileAsString("data/API_SL.UEM.TOTL.ZS_DS2_en_csv_v2_10473697.csv");
        ArrayList<CountryUnemploymentData> list = Utils.parseCountryUnemploymentData(unEmploymentData);
//        for (CountryUnemploymentData c : list) {
//            System.out.println(c.toString());
//        }
        System.out.println(Utils.countryUnemploymentDataToCSVString(list));
    }
}
