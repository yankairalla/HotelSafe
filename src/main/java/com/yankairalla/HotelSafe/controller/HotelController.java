package com.yankairalla.HotelSafe.controller;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public String index(Model model) {

        List<Hotel> hotel =  hotelService.getAllHotels();
        model.addAttribute("hotel", hotel);
        return "hoteis";
    }

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {

        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);

        if (hotel.isPresent()) {
            return ResponseEntity.ok(hotel.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping ("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);

        if (hotel.isPresent()) {
            hotelService.deleteHotel(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        return "hotel/criar";
    }

    @PostMapping("/criar")
    public void criarHotel(Model model, @ModelAttribute Hotel hotel) {
        System.out.println(hotel.toString());
    }
}
