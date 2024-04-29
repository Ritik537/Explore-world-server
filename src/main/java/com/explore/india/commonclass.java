package com.explore.india;

import java.net.URISyntaxException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class commonclass {

	
	
    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "weathercache", key = "#city")
    public String getWeatherResponse(String city) throws URISyntaxException {
        // Set the URL for OpenWeatherMap API to retrieve latitude and longitude
    	
    	
        String openWeatherMapUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=3a2c6519e175d78247fb5fd5921a0ca1";

        // Make a request to OpenWeatherMap API to retrieve latitude and longitude
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(openWeatherMapUrl, String.class);
        
        
        
        String response = responseEntity.getBody();


            // Use latitude and longitude in the API request to get attractions list
            return response;
    }
}
