package com.explore.india.controllers;

import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.CrossOrigin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.explore.india.service.HotelsService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://explore-world-git-main-ritik537s-projects.vercel.app/"})
public class HotelsControllers {

    @Autowired
    private  HotelsService apiService;


    @GetMapping("/hotels/{city}")
    public ResponseEntity<String> gethotelsList(@PathVariable String city) {
        try {
            ResponseEntity<String> responseEntity = apiService.gethotelsListByLatLng(city);
            return ResponseEntity.ok(responseEntity.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
    }
}
