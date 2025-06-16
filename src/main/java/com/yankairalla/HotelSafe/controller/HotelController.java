package com.yankairalla.HotelSafe.controller;

import com.yankairalla.HotelSafe.dto.HotelDTO;
import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        List<Hotel> hotels =  hotelService.getAllHotels();
        model.addAttribute("hoteis", hotels);
        return "hotel/index";
    }

    @GetMapping("/{id}")
    public String getHotelById(@PathVariable long id, Model model) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);

        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel/hotel";
        }
        return "redirect:/hotel";
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
    public String criar(@ModelAttribute("hotel") HotelDTO hotel, Model model) {
        model.addAttribute("hotel", hotel);
        return "hotel/criar";
    }

    @PostMapping("/criar")
    public String criarHotel(@Valid @ModelAttribute("hotel") HotelDTO hotel, BindingResult bindingResult, Model model) {

        if (hotel.getRooms() != null) {
            hotel.setRooms(hotel.getRooms().stream()
                    .filter(room -> room.getName() != null ||
                            room.getPrice() != null ||
                            room.getDescription() != null
                            )
                    .toList());
        }


        if(hotelService.emailExists(hotel.getEmail())) {
            bindingResult.rejectValue("email","email.cadastrado", "Email já cadastrado");
        }

        if(hotelService.cnpjExists(hotel.getCnpj())) {
            bindingResult.rejectValue("cnpj","cnpj.cadastrado", "CNPJ já cadastrado");
        }


        if (bindingResult.hasErrors()) {
            return "hotel/criar";
        }
        hotelService.saveHotel(hotel);
        return "redirect:/hotel";
    }
}