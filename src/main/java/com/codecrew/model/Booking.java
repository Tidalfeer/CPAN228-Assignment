package com.codecrew.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Service location is required")
    @Column(nullable = false)
    private String location;

    @NotBlank(message = "Please select a service type")
    @Column(nullable = false)
    private String serviceType;

    @NotNull(message = "Service date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Appointment must be in the future")
    @Column(nullable = false)
    private LocalDate serviceDate;

    @NotNull(message = "Start time is required")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime startTime;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}