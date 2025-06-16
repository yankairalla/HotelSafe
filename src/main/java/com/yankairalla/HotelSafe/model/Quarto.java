package com.yankairalla.HotelSafe.model;

import com.yankairalla.HotelSafe.dto.HotelDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private int MaxCapacity;
    private BigDecimal price;
    private String description;
    @ManyToOne
    private Hotel hotel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getMaxCapacity() {
        return MaxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        MaxCapacity = maxCapacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
