package com.yankairalla.HotelSafe.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private Integer MaxCapacity;
    private BigDecimal price;
    private String description;
    @ManyToOne
    private Hotel hotel;
}
