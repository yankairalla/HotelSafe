package com.yankairalla.HotelSafe.dto;

import com.yankairalla.HotelSafe.model.Hotel;
import com.yankairalla.HotelSafe.model.Quarto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelDTO {
    private int id;
    @NotBlank(message = "Nome é obrigatório.")
    private String name;
    @Email(message = "Email deve ser valido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Endereço é obrigatório.")
    private String address;
    @NotBlank(message = "Cidade é obrigatório.")
    private String city;
    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14}",
            message = "CNPJ deve estar no formato 00.000.000/0000-00")
    private String cnpj;
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}",
            message = "Telefone deve estar no formato (00) 00000-0000")
    private String phone;
    @NotNull(message = "Check-in é obrigatório")
    private LocalTime checkIn;
    @NotNull(message = "Check-out é obrigatório")
    private LocalTime checkOut;
    private MultipartFile[] photos;
    @NotBlank(message = "Descrição é obrigatório")
    private String description;
    private List<QuartoDTO> rooms = new ArrayList<>();

    public Hotel toEntity(List<String> photos) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setCity(city);
        hotel.setCnpj(cnpj);
        hotel.setPhone(phone);
        hotel.setEmail(email);
        hotel.setCheckIn(checkIn);
        hotel.setCheckOut(checkOut);
        hotel.setDescription(description);
        hotel.setPhotos(photos);


        if (this.rooms != null) {
            List<Quarto> quartos = this.rooms.stream()
                    .map(quartoDTO -> {
                        Quarto quarto = quartoDTO.toEntity();
                        quarto.setHotel(hotel);  // Set the hotel reference
                        return quarto;
                    })
                    .collect(Collectors.toList());
            hotel.setRooms(quartos);
        }


        return hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuartoDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<QuartoDTO> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rooms=" + rooms +
                // other fields...
                '}';
    }

}
