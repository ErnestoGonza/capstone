package com.ernestogonzalez.tanititourism.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.User;
import com.ernestogonzalez.tanititourism.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(User.Role.ADMIN)
                .build();
        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
//                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = repository.findByUsername(authenticationRequest.getUsername()).get();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .token(jwtToken)
                .build();
    }
}
