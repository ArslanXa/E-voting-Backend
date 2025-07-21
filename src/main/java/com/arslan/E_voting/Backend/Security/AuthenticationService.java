package com.arslan.E_voting.Backend.Security;

import com.arslan.E_voting.Backend.DTO.AuthenticationRequest;
import com.arslan.E_voting.Backend.DTO.AuthenticationResponse;
import com.arslan.E_voting.Backend.DTO.RegisterRequest;
import com.arslan.E_voting.Backend.Enums.Role;
import com.arslan.E_voting.Backend.Entity.Users;
import com.arslan.E_voting.Backend.Repository.UserRepository;
import com.arslan.E_voting.Backend.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Users user = Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase())) // "ADMIN" or "VOTER"
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
