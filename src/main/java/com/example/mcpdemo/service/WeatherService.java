package com.example.mcpdemo.service;

import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    private final RestClient restClient;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WeatherService.class);

    public WeatherService(){
        restClient = RestClient.builder()
                .baseUrl("https://api.weather.gov")
                .defaultHeader("Accept", "application/geo+json")
                .defaultHeader("User-Agent","WeatherApiClient/1.0 Test")
                .build();
    }

    @Tool(description = "Get weather forecast for a specific latitude and longitude")
    public String getWeatherForecastByLocation(double latitude, double longitude) {
        LOGGER.info("Fetching weather forecast for latitude: " + latitude + ", longitude: " + longitude);
        return restClient.get()
                .uri("/points/" + latitude + "," + longitude)
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get weather forecast for a specific city")
    public String getWeatherForecastByCity(String city) {
        LOGGER.info("Fetching weather forecast for city: " + city);
        return restClient.get()
                .uri("/points/" + city)
                .retrieve()
                .body(String.class);    
    }

    @Tool(description = "Get detailed weather forecast for a specific grid location")
    public String getDetailedForecast(String office, int gridX, int gridY) {
        LOGGER.info("Fetching detailed forecast for office: " + office + ", gridX: " + gridX + ", gridY: " + gridY);
        return restClient.get()
                .uri("/gridpoints/" + office + "/" + gridX + "," + gridY + "/forecast")
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get active weather alerts for a specific zone")
    public String getActiveAlerts(String zoneId) {
        LOGGER.info("Fetching active alerts for zone: " + zoneId);
        return restClient.get()
                .uri("/alerts/active/zone/" + zoneId)
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get latest weather observations for a specific station")
    public String getLatestObservations(String stationId) {
        LOGGER.info("Fetching latest observations for station: " + stationId);
        return restClient.get()
                .uri("/stations/" + stationId + "/observations/latest")
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get metadata for a specific zone")
    public String getZoneInfo(String type, String zoneId) {
        LOGGER.info("Fetching zone info for type: " + type + ", zoneId: " + zoneId);
        return restClient.get()
                .uri("/zones/" + type + "/" + zoneId)
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "Get all active weather alerts nationwide")
    public String getNationwideAlerts() {
        LOGGER.info("Fetching nationwide active alerts");
        return restClient.get()
                .uri("/alerts/active")
                .retrieve()
                .body(String.class);
    }
}
