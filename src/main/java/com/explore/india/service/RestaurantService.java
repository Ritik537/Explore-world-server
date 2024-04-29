package com.explore.india.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.explore.india.commonclass;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RestaurantService {
	
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private commonclass cs;

    
    @Cacheable(value = "restaurantscache", key = "#city" )
    public ResponseEntity<String> getrestaurantsListByLatLng(String city) throws URISyntaxException, JSONException {
        
    	
        String response = cs.getWeatherResponse(city);

        
        
        String latitude = null;
        String longitude = null;
        JSONObject weatherJson = null;
        
        try {
            weatherJson = new JSONObject(response);
             latitude = weatherJson.getJSONObject("coord").getString("lat");
             longitude = weatherJson.getJSONObject("coord").getString("lon");
        } catch (JSONException e) {
            // Handle the exception, or log it
            e.printStackTrace();
        }

    	double lat = Double.parseDouble(latitude);
    	double lon = Double.parseDouble(longitude);
    	
    	// Set the URL and request parameters
        String url = "https://travel-advisor.p.rapidapi.com/restaurants/list-by-latlng";
        String lunit = "km";
        String currency = "INR";
        int limit = 30;
        int distance = 10;
        String lang = "en_US";
        
        URI uri = new URI(url +
                "?longitude=" + lon +
                "&latitude=" + lat +
                "&lunit=" + lunit +
                "&limit=" + limit +
                "&distance=" + distance +
                "&currency=" + currency +
                "&lang=" + lang);

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "6065befdc5msh09413bca005f787p1e405cjsn5aac85157c6e");
        headers.set("X-RapidAPI-Host", "travel-advisor.p.rapidapi.com");

        // Create the request entity with headers
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);

        // Send the request
        ResponseEntity<String> restaurantsResponseEntity = restTemplate.exchange(requestEntity, String.class);
        
        String restaurantsInfo = restaurantsResponseEntity.getBody();

        
        JSONObject combinedJson = new JSONObject();
        combinedJson.put("response1", new JSONObject(restaurantsInfo));
        combinedJson.put("weather", weatherJson);


        return ResponseEntity.ok(combinedJson.toString());
        
 
        }
}
