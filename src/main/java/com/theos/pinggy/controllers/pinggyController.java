package com.theos.pinggy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theos.pinggy.config.pinggyFilter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("")
public class pinggyController {
    
    @GetMapping
    public ResponseEntity<String> getPinggy() {
        String value = pinggyFilter.getAuthHeaderValue();
        return ResponseEntity.ok(value);
    }
    
}
