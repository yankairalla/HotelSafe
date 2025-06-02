package com.yankairalla.HotelSafe.controller;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);

        if (hotel.isPresent()) {
            return ResponseEntity.ok(hotel.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {

        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);

        if (hotel.isPresent()) {
            hotelService.deleteHotel(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
