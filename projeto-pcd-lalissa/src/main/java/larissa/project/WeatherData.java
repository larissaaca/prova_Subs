package larissa.project;

public class WeatherData {
    private final double minTemperature;
    private final double maxTemperature;
    private final double avgTemperature;

    public WeatherData(double minTemperature, double maxTemperature, double avgTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.avgTemperature = avgTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }
}