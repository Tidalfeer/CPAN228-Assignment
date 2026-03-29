package com.codecrew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codecrew.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByServiceType(String serviceType);

}
import org.springframework.stereotype.Repository;
import com.codecrew.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByServiceType(Long userId, Pageable pageable);
    Page<Booking> findByServiceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Booking> findByServiceTypeAndServiceDateBetween(String serviceType, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
