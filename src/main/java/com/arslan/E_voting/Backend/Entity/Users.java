package com.arslan.E_voting.Backend.Entity;

import com.arslan.E_voting.Backend.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // You can keep this if you want bi-directional votes link
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Vote> votes;

    // -------- UserDetails methods --------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Prefix role with ROLE_ as Spring Security expects
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;  // using email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // implement if you want
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // implement if you want
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // implement if you want
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // Getters and setters for all fields below

    // constructors, equals, hashcode, toString can be added as needed
}
