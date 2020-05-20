package com.ayoub.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitBreakerController {

    @GetMapping("defaultCountries")
    public Map<String, String> defaultCountries(){
        Map<String, String> data = new HashMap<>();
        data.put("message", "default countries");
        data.put("countries", "Morocco, France, Italy");
        return data;
    }
}
