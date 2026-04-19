package com.codecrew.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/admin/users/**", "/admin/bookings/**")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/services", "/technicians", "/register", "/login", "/css/**", "/images/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/admin", "/admin/users", "/admin/bookings", "/admin/audit-logs").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/booking/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }
}