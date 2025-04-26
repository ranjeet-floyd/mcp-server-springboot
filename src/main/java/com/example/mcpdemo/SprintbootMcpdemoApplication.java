package com.example.mcpdemo;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mcpdemo.service.WeatherService;

// This is a sample Spring Boot application that uses the Spring AI library to create a weather forecast service.
// The application is configured to use the WeatherService class, which contains methods to get weather forecasts based on different parameters

@SpringBootApplication
public class SprintbootMcpdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintbootMcpdemoApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTools(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}

}
