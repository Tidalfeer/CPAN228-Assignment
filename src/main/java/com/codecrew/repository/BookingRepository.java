package com.codecrew.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codecrew.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByServiceType(String serviceType);
    List<Booking> findByCustomerEmailContainingIgnoreCase(String email);
    Page<Booking> findByServiceType(String serviceType, Pageable pageable);
    Page<Booking> findByServiceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}