package com.chatapp.chatapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    
    @RequestMapping("/health")
    public String helth() {
        return "Running ........";
    }
}
