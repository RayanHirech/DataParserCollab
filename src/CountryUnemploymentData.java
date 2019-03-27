import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class CountryUnemploymentData {
    //Use this class to store the data under the name of a country

    private String countryName;
    private String countryCode;
    private String indicatorName;
    private String indicatorCode;
    public ArrayList<DataPoint> data;
    
    public CountryUnemploymentData(){

        countryName = "";
        countryCode = "";
        indicatorName = "";
        indicatorCode = "";
        data = new ArrayList<>();

    }

    public String getCountryName() {
        return this.countryName;
    }
    public String getCountryCode() {
        return this.countryCode;
    }
    public String getIndicatorName() {
        return this.indicatorName;
    }
    public String getIndicatorCode() {
        return this.indicatorCode;
    }
    public DataPoint getDataPoint(int index) {
        return data.get(index);
    }
    public double getDataByYear(int year) {
        for (DataPoint dataPoint : data) {
            if (dataPoint.getYear() == year) {
                return dataPoint.getData();
            }
        }
        return 0;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }
    public void addData(DataPoint p) {
        data.add(p);
    }

    public double getAverageData() {
        return 0;
    }

    public String toString() {
        String out = countryName + ", " + countryCode + ", " + indicatorName + ", " + indicatorCode;
        for (DataPoint p : data) {
            out += ", " + p.toString();
        }
        return out;
    }

}
