package com.yankairalla.HotelSafe.service;

import com.yankairalla.HotelSafe.dto.HotelDTO;
import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private static final String UPLOAD_DIR = "uploads/hoteis/";
    @Autowired
    private HotelRepository hotelRepository;


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(long id) {
        return hotelRepository.findById(id);
    }

    public Hotel saveHotel(HotelDTO hotelDTO) {
        List<String> paths = savePhotos(hotelDTO.getPhotos());
        Hotel hotel = hotelDTO.toEntity(paths);
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

    public List<String> savePhotos(MultipartFile[] archives) {
        List<String> paths = new ArrayList<>();
        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) folder.mkdirs();

        for (MultipartFile file : archives) {
            if (!file.isEmpty()) {
                try {
                    String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path path = Paths.get(UPLOAD_DIR + filename);
                    Files.write(path, file.getBytes());
                    paths.add("/uploads/hoteis/" + filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return paths;
    }

}
