package com.yankairalla.HotelSafe.dto;

import com.yankairalla.HotelSafe.model.Quarto;

import java.math.BigDecimal;

public class QuartoDTO {
    private Long id;
    private String name;
    private String number;
    private BigDecimal price;
    private String description;
    private int quantity;
    private int maxCapacity;
    // private HotelDTO hotel;
    



    public Quarto toEntity() {
        Quarto quarto = new Quarto();
        quarto.setName(this.name);
        quarto.setQuantity(this.quantity);
        quarto.setMaxCapacity(this.maxCapacity);
        quarto.setPrice(this.price);
        quarto.setDescription(this.description);
        return quarto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuartoDTO{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", maxCapacity=" + maxCapacity +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}