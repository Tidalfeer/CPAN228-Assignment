package com.codecrew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codecrew.model.Booking;

@Controller
public class BookingController {

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

}