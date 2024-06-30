package larissa.project;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeatherApiService {

    private static final String API_URL = "https://api.open-meteo.com/v1/forecast";

    private final HttpClient httpClient;

    public WeatherApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String makeApiRequest(double latitude, double longitude) throws IOException {
        String apiUrl = API_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&daily=temperature_2m_min,temperature_2m_max,temperature_2m_mean";
        HttpGet request = new HttpGet(apiUrl);

        HttpResponse response = httpClient.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new IOException("HTTP request failed: " + statusCode);
        }

        return EntityUtils.toString(response.getEntity());
    }
}
