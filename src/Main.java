import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        String unEmploymentData = Utils.readFileAsString("data/API_SL.UEM.TOTL.ZS_DS2_en_csv_v2_10473697.csv");
//        ArrayList<CountryUnemploymentData> list = Utils.parseCountryUnemploymentData(unEmploymentData);
////        for (CountryUnemploymentData c : list) {
////            System.out.println(c.toString());
////        }
//        String str = Utils.countryUnemploymentDataToCSVString(list);
//        Utils.writeDataToFile("data/data.csv", str);

        String otherUnemploymentData = Utils.readFileAsString("data/UnemploymentDataSet.csv");
        ArrayList<StateUnemploymentData> list2 = Utils.parseStateUnemploymentData(otherUnemploymentData);

    }
}
