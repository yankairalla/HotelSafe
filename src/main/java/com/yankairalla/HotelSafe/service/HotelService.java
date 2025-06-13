package com.yankairalla.HotelSafe.service;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(long id) {
        return hotelRepository.findById(id);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(long id) {
        hotelRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
       return hotelRepository.existsByEmail(email);
    }

    public boolean cnpjExists(String cnpj) {
        return hotelRepository.existsByCnpj(cnpj);
    }
}
