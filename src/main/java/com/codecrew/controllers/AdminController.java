package com.codecrew.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codecrew.Service.AdminService;
import com.codecrew.model.AuditLog;
import com.codecrew.model.Booking;
import com.codecrew.model.User;
import com.codecrew.repository.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AuditLogRepository auditLogRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String role,
            @RequestParam String adminPassword
    ) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!adminService.verifyAdminPassword(adminEmail, adminPassword)) {
            return ResponseEntity.status(403).build(); // forbidden
        }

        User updatedUser = adminService.updateUser(id, name, role, adminEmail);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestParam String adminPassword
    ) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!adminService.verifyAdminPassword(adminEmail, adminPassword)) {
            return ResponseEntity.status(403).build(); // forbidden
        }

        adminService.deleteUser(id, adminEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings(
            @RequestParam(required = false) String serviceType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate end = endDate != null ? LocalDate.parse(endDate) : null;
        return adminService.getBookings(serviceType, start, end);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestParam String serviceType,
            @RequestParam String serviceDate,
            @RequestParam String adminPassword
    ) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!adminService.verifyAdminPassword(adminEmail, adminPassword)) {
            return ResponseEntity.status(403).build();
        }

        Booking updatedBooking = adminService.updateBooking(id, adminEmail, serviceType, LocalDate.parse(serviceDate));
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Long id,
            @RequestParam String adminPassword
    ) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!adminService.verifyAdminPassword(adminEmail, adminPassword)) {
            return ResponseEntity.status(403).build();
        }

        adminService.deleteBooking(id, adminEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/audit-logs")
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }
}