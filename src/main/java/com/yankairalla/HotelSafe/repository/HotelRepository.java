package com.yankairalla.HotelSafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yankairalla.HotelSafe.model.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {



}
