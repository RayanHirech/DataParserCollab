import java.util.ArrayList;

public class CountryUnemployment {
    //Use this class to store the data under the name of a country

    private String countryName;
    private String countryCode;
    private String indicatorName;

    public ArrayList<DataPoint> data;
    
    public CountryUnemployment(){

        data = new ArrayList<>();
    }

    public String getCountryName() {
        return this.countryName;
    }
    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getAverageData() {
        return 0;
    }

}
