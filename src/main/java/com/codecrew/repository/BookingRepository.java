package com.codecrew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codecrew.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByServiceType(String serviceType);

}