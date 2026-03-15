package com.codecrew.controllers;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codecrew.model.Booking;
import com.codecrew.repository.BookingRepository;

import jakarta.validation.Valid;

@Controller
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/technicians")
    public String technicians() {
        return "technicians";
    }

    @GetMapping("/bookings/new")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "bookingform";
    }
@PostMapping("/bookings/save")
public String saveBooking(@Valid @ModelAttribute("booking") Booking booking,
                          BindingResult result) {
    if (result.hasErrors()) {
        return "bookingform";
    }

    Booking savedBooking = bookingRepository.save(booking);
    return "redirect:/bookings/confirmation/" + savedBooking.getId();
}

@GetMapping("/bookings/confirmation/{id}")
public String showConfirmation(@PathVariable Long id, Model model) {
    Booking booking = bookingRepository.findById(id).orElseThrow();
    model.addAttribute("booking", booking);
    return "confirmation";
}

@GetMapping("/bookings")
public String listBookings(Model model,
                           @RequestParam(required = false) String serviceType,
                           @RequestParam(required = false) String startDate,
                           @RequestParam(required = false) String endDate,
                           @RequestParam(defaultValue = "serviceDate") String sort,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    Page<Booking> bookingsPage;

    if (serviceType != null && !serviceType.isEmpty()) {
        bookingsPage = bookingRepository.findByServiceType(serviceType, pageable);
    } 
    else if (startDate != null && !startDate.isEmpty() &&
             endDate != null && !endDate.isEmpty()) {
        bookingsPage = bookingRepository.findByServiceDateBetween(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                pageable
        );
    } 
    else {
        bookingsPage = bookingRepository.findAll(pageable);
    }

    model.addAttribute("bookings", bookingsPage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", bookingsPage.getTotalPages());

    model.addAttribute("serviceType", serviceType);
    model.addAttribute("startDate", startDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("sort", sort);

    return "bookinglist";
}
}
