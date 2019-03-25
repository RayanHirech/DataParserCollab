import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String unEmploymentData = Utils.readFileAsString("data/API_SL.UEM.TOTL.ZS_DS2_en_csv_v2_10473697.csv");
        ArrayList<CountryUnemploymentData> list = Utils.parseUnemploymentData(unEmploymentData);
        System.out.println(list.get(0).toString());
//        Utils.parseData(unEmploymentData);
    }
}
