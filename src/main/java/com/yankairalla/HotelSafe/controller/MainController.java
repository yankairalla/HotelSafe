package com.yankairalla.HotelSafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {



    @GetMapping("/")
    public HashMap<String, String> index() {
        HashMap test = new HashMap();
        test.put("test", "test");
        return test;
    }
}
