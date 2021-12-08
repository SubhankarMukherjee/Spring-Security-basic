package com.example.springsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class APIController {

    @GetMapping
    public String getApiVersion()
    {
        return "Your app is on :2.9X.16V";
    }

    @GetMapping("/admin")
    public String getApiVersionAdmin()
    {
        return "Your app is on :2.9X.16V---admin  version XXX";
    }
}
