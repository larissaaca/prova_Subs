package larissa.project;

import java.util.HashMap;
import java.util.Map;

public class CityWeatherData {
    private final String city;
    private final Map<Integer, Double> minTemperatures;
    private final Map<Integer, Double> maxTemperatures;
    private final Map<Integer, Double> avgTemperatures;

    public CityWeatherData(String city) {
        this.city = city;
        this.minTemperatures = new HashMap<>();
        this.maxTemperatures = new HashMap<>();
        this.avgTemperatures = new HashMap<>();
    }

    public String getCity() {
        return city;
    }

    public void setMinTemperature(int day, double minTemperature) {
        minTemperatures.put(day, minTemperature);
    }

    public double getMinTemperature(int day) {
        return minTemperatures.getOrDefault(day, 0.0);
    }

    public void setMaxTemperature(int day, double maxTemperature) {
        maxTemperatures.put(day, maxTemperature);
    }

    public double getMaxTemperature(int day) {
        return maxTemperatures.getOrDefault(day, 0.0);
    }

    public void setAvgTemperature(int day, double avgTemperature) {
        avgTemperatures.put(day, avgTemperature);
    }

    public double getAvgTemperature(int day) {
        return avgTemperatures.getOrDefault(day, 0.0);
    }
}