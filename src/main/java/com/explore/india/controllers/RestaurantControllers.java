package com.explore.india.controllers;

import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.explore.india.service.RestaurantService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://explore-world-omega.vercel.app/"})
public class RestaurantControllers {

    @Autowired
    private  RestaurantService apiService;


    @GetMapping("/restaurants/{city}")
    public ResponseEntity<String> getrestaurantsList(@PathVariable String city) {
        try {
            ResponseEntity<String> responseEntity = apiService.getrestaurantsListByLatLng(city);
            return ResponseEntity.ok(responseEntity.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
    }
}

