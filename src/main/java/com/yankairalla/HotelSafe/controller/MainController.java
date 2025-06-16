package com.yankairalla.HotelSafe.controller;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/")
    public String index(Model model) {
        List<Hotel> hotels =  hotelService.getAllHotels();
        model.addAttribute("hoteis", hotels);
        return "home";
    }
}
