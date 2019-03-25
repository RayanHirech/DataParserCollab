public class DataPoint {

    private int year;
    private double data;

    public DataPoint(int year, double data) {
        this.year = year;
        this.data = data;
    }

    public int getYear() {
        return this.year;
    }
    public double getData() {
        return this.data;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public void setData(double data) {
        this.data = data;
    }

    public String toString() {
        String out = "";
        out = "\"" + year + ", " + data + "\"";
        return out;
    }

}
