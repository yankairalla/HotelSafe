package com.yankairalla.HotelSafe.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class  Reserva {
    @Id
    private long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer NumGuests;
    private BigDecimal totalValue;
//    private StatusReserva status;
    private String observation;
    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
//    @JoinColumn(name = "quarto_id")
    private Quarto quarto;
}
