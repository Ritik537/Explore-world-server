package com.explore.india.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.explore.india.service.AttractionsService;

import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://explore-world-omega.vercel.app/"})
public class AttractionsControllers {
	
    @Autowired
    private  AttractionsService apiService;






	@GetMapping("/attractions/{city}")
    public ResponseEntity<String> getAttractionsList(@PathVariable String city) {
        try {
            ResponseEntity<String> responseEntity = apiService.getAttractionsListByLatLng(city);
            return ResponseEntity.ok(responseEntity.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
    }
}
