package com.codecrew.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codecrew.model.User;
import com.codecrew.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            return "redirect:/register?error";
        }                        

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        userRepository.save(user);

        return "redirect:/login";
    }
}