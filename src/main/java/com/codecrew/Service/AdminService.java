package com.codecrew.Service;

import com.codecrew.model.Booking;
import com.codecrew.model.User;
import com.codecrew.repository.BookingRepository;
import com.codecrew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final AuditService auditService;
    private final PasswordEncoder passwordEncoder;


    public boolean verifyAdminPassword(String adminEmail, String rawPassword) {
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return  admin.isAdmin() && passwordEncoder.matches(rawPassword, admin.getPassword());

        
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(Long userId, String name, String newRole, String adminEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String oldValue = "Name=" + user.getName() + ", Role=" + user.getRole();

        user.setName(name);
        user.setRoleSafe(newRole);
        userRepository.save(user);

        String newValue = "Name=" + user.getName() + ", Role=" + user.getRole();

        auditService.log(adminEmail, "USER", userId, "UPDATE", oldValue, newValue);
        return user;
    }


    public void deleteUser(Long userId, String adminEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String oldValue = "Email=" + user.getEmail() + ", Role=" + user.getRole();
        userRepository.delete(user);

        auditService.log(adminEmail, "USER", userId, "DELETE", oldValue, "DELETED");
    }


    public List<Booking> getBookings(String serviceType, LocalDate start, LocalDate end) {
        if (serviceType != null && !serviceType.isEmpty()) {
            return bookingRepository.findByServiceType(serviceType);
        }
        if (start != null && end != null) {
            return bookingRepository.findByServiceDateBetween(start, end, null).getContent();
        }
        return bookingRepository.findAll();
    }


    public Booking updateBooking(Long bookingId, String adminEmail, String newServiceType, LocalDate newDate) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        String oldValue = "Service=" + booking.getServiceType() + ", Date=" + booking.getServiceDate();

        booking.setServiceType(newServiceType);
        booking.setServiceDate(newDate);
        bookingRepository.save(booking);

        String newValue = "Service=" + booking.getServiceType() + ", Date=" + booking.getServiceDate();
        auditService.log(adminEmail, "BOOKING", bookingId, "UPDATE", oldValue, newValue);

        return booking;
    }
}