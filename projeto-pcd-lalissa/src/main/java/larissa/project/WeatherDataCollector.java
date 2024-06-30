package larissa.project;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDataCollector {

    private static final String[] CITIES = {
            "Brasilia", "Rio de Janeiro", "Sao Paulo", "Belo Horizonte", "Salvador",
            "Curitiba", "Fortaleza", "Manaus", "Belem", "Porto Alegre", "Recife", "Goiania",
            "Boa Vista", "Macapa", "São Luis", "Vitoria","Maceio", "Porto Velho", "Palmas",
            "Campo Grande", "Teresina", "Florianopolis", "Joao Pessoa", "Cuiaba", "Natal",
            "Aracaju", "Rio Branco"
    };

    private static final int DAYS = 7;
    private final WeatherApiService weatherApiService;
    private final Map<String, CityWeatherData> cityWeatherDataMap;

    public WeatherDataCollector(HttpClient httpClient) {
        this.weatherApiService = new WeatherApiService(httpClient);
        this.cityWeatherDataMap = new HashMap<>();
    }

    public void collectAndProcessDataWithoutThreads() {
        for (String city : CITIES) {
            try {
                double[] coordinates = CityCoordinates.getCoordinates(city);
                if (coordinates != null) {
                    double latitude = coordinates[0];
                    double longitude = coordinates[1];
                    String response = weatherApiService.makeApiRequest(latitude, longitude);
                    List<WeatherData> weatherDataList = parseWeatherData(response);
                    processWeatherData(city, weatherDataList);
                } else {
                    System.err.println("❌ Failed to get coordinates for city: " + city);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void collectAndProcessDataWithThreads(int numberOfThreads, int citiesPerThread) throws InterruptedException {
        if (numberOfThreads == 0) {
            // Processa todos os dados sem threads
            for (int i = 0; i < CITIES.length; i++) {
                String city = CITIES[i];
                try {
                    double[] coordinates = CityCoordinates.getCoordinates(city);
                    if (coordinates != null) {
                        double latitude = coordinates[0];
                        double longitude = coordinates[1];
                        String response = weatherApiService.makeApiRequest(latitude, longitude);
                        List<WeatherData> weatherDataList = parseWeatherData(response);
                        processWeatherData(city, weatherDataList);
                    } else {
                        System.err.println("❌ Failed to get coordinates for city: " + city);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < numberOfThreads; i++) {
                int start = i * citiesPerThread;
                int end = Math.min(start + citiesPerThread, CITIES.length);

                Thread thread = new Thread(() -> {
                    for (int j = start; j < end; j++) {
                        String city = CITIES[j];
                        try {
                            double[] coordinates = CityCoordinates.getCoordinates(city);
                            if (coordinates != null) {
                                double latitude = coordinates[0];
                                double longitude = coordinates[1];
                                String response = weatherApiService.makeApiRequest(latitude, longitude);
                                List<WeatherData> weatherDataList = parseWeatherData(response);
                                processWeatherData(city, weatherDataList);
                            } else {
                                System.err.println("❌ Failed to get coordinates for city: " + city);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        }
    }

    private void processWeatherData(String city, List<WeatherData> weatherDataList) {
        CityWeatherData cityWeather = new CityWeatherData(city);

        for (int i = 0; i < DAYS; i++) {
            WeatherData weatherData = weatherDataList.get(i);
            cityWeather.setMinTemperature(i + 1, weatherData.getMinTemperature());
            cityWeather.setMaxTemperature(i + 1, weatherData.getMaxTemperature());
            cityWeather.setAvgTemperature(i + 1, weatherData.getAvgTemperature());
        }
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║                                Weather Data of %-18s                                      ", city);
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int day = 1; day <= DAYS; day++) {
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("╠ Day " + day);
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║                             Registrated temperatures                                     ╣");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("╠ Min temperature: " + cityWeather.getMinTemperature(day) + "°C");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("╠️ Max temperature: " + cityWeather.getMaxTemperature(day) + "°C");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("╠️ Avg temperature: " + cityWeather.getAvgTemperature(day) + "°C");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println();

        }
        System.out.println();

        cityWeatherDataMap.put(city, cityWeather);
    }

    private List<WeatherData> parseWeatherData(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject daily = jsonObject.getAsJsonObject("daily");

        JsonArray temperatureMaxArray = daily.getAsJsonArray("temperature_2m_max");
        JsonArray temperatureMinArray = daily.getAsJsonArray("temperature_2m_min");

        List<WeatherData> weatherDataList = new ArrayList<>();

        for (int i = 0; i < temperatureMaxArray.size(); i++) {
            double maxTemperature = temperatureMaxArray.get(i).getAsDouble();
            double minTemperature = temperatureMinArray.get(i).getAsDouble();
            double avgTemperature = (maxTemperature + minTemperature) / 2;
            weatherDataList.add(new WeatherData(minTemperature, maxTemperature, avgTemperature));
        }

        return weatherDataList;
    }
}
