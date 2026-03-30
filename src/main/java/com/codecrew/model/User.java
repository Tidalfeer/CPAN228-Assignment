package com.codecrew.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_STAFF = "STAFF";
    public static final String ROLE_USER = "USER";

    public static final List<String> VALID_ROLES =
            Arrays.asList(ROLE_ADMIN, ROLE_STAFF, ROLE_USER);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean isAdmin() {
        return ROLE_ADMIN.equalsIgnoreCase(this.role);
    }

    public boolean isStaff() {
        return ROLE_STAFF.equalsIgnoreCase(this.role);
    }

    public boolean isUser() {
        return ROLE_USER.equalsIgnoreCase(this.role);
    }

    
    public void setRoleSafe(String newRole) {
        if (newRole == null) return;

        String upperRole = newRole.toUpperCase();

        if (VALID_ROLES.contains(upperRole)) {
            this.role = upperRole;
        } else {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }
    }
    
    @PrePersist
    @PreUpdate
    public void normalizeRole() {
        if (this.role != null) {
            this.role = this.role.toUpperCase();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}