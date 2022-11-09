package com.novare.traderabackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//This class is for testing purpose

@RestController
public class DemoAuth {

    //everybody have access
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome -  This Page is available without Login</h1>");
    }

    //only logged in user have access
    @GetMapping("/trader")
    public String user() {
        return ("<h1>Welcome trader -  This page is available only after Login</h1>");
    }
}