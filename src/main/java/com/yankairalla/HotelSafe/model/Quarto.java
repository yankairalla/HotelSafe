package com.yankairalla.HotelSafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class Quarto {

    @Id
    private long id;
    private String number;
    private Integer MaxCapacity;
    private BigDecimal price;
    private String descritpion;
//    private StatusQuarto statusQuarto;
    @ManyToOne
    private Hotel hotel;
}
