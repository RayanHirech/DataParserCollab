import java.util.ArrayList;

public class StateUnemploymentData {

    private int FIPS;
    private String state;
    private String areaName;
    private int ruralUrbanContinuumCode;
    private int urbanInfluenceCode;
    private int metro;
    private ArrayList<DataPoint> employmentData;
    private ArrayList<DataPoint> unemploymentData;

    public StateUnemploymentData() {

        employmentData = new ArrayList<>();
        unemploymentData = new ArrayList<>();

    }

    public int getFIPS() {
        return FIPS;
    }
    public void setFIPS(int FIPS) {
        this.FIPS = FIPS;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getRuralUrbanContinuumCode() {
        return ruralUrbanContinuumCode;
    }
    public void setRuralUrbanContinuumCode(int ruralUrbanContinuumCode) {
        this.ruralUrbanContinuumCode = ruralUrbanContinuumCode;
    }

    public int getUrbanInfluenceCode() {
        return urbanInfluenceCode;
    }
    public void setUrbanInfluenceCode(int urbanInfluenceCode) {
        this.urbanInfluenceCode = urbanInfluenceCode;
    }

    public int getMetro() {
        return metro;
    }
    public void setMetro(int metro) {
        this.metro = metro;
    }

    public double getEmploymentDataByYear(int year) {
        for (DataPoint dataPoint : employmentData) {
            if (dataPoint.getYear() == year) {
                return dataPoint.getData();
            }
        }
        return 0;
    }
    public void addEmploymentData(DataPoint p) {
        employmentData.add(p);
    }

    public double getUnemploymentDataByYear(int year) {
        for (DataPoint dataPoint : unemploymentData) {
            if (dataPoint.getYear() == year) {
                return dataPoint.getData();
            }
        }
        return 0;
    }
    public void addUnemploymentData(DataPoint p) {
        unemploymentData.add(p);
    }

}
