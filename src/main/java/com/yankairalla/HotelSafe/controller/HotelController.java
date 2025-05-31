package com.yankairalla.HotelSafe.controller;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> index() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public void addHotel(@RequestBody Hotel hotel) {
        hotelService.saveHotel(hotel);
    }
}
