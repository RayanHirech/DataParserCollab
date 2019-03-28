import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String unEmploymentData = Utils.readFileAsString("data/API_SL.UEM.TOTL.ZS_DS2_en_csv_v2_10473697.csv");
        ArrayList<CountryUnemploymentData> list1 = Utils.parseCountryUnemploymentData(unEmploymentData);
        String str1 = Utils.countryUnemploymentDataToCSVString(list1);
        Utils.writeDataToFile("data/data1.csv", str1);

        String otherEmploymentData = Utils.readFileAsString("data/UnemploymentDataSet.csv");
        ArrayList<StateUnemploymentData> list2 = Utils.parseStateUnemploymentData(otherEmploymentData);
        String str2 = Utils.stateUnemploymentDataToCSVString(list2);
        System.out.println(str2);
        Utils.writeDataToFile("data/data2.csv", str2);

    }
}
