package com.placementportal.service.impl;

import com.placementportal.dto.AuthResponse;
import com.placementportal.dto.LoginRequest;
import com.placementportal.dto.RegisterRequest;
import com.placementportal.entity.RecruiterProfile;
import com.placementportal.entity.Role;
import com.placementportal.entity.StudentProfile;
import com.placementportal.entity.User;
import com.placementportal.exception.BadRequestException;
import com.placementportal.exception.ResourceNotFoundException;
import com.placementportal.repository.RecruiterProfileRepository;
import com.placementportal.repository.StudentProfileRepository;
import com.placementportal.repository.UserRepository;
import com.placementportal.security.JwtService;
import com.placementportal.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           StudentProfileRepository studentProfileRepository,
                           RecruiterProfileRepository recruiterProfileRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return buildAuthResponse(user);
    }

    @Override
    public AuthResponse registerStudent(RegisterRequest request) {
        validateUniqueEmail(request.getEmail());
        User user = userRepository.save(User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build());
        studentProfileRepository.save(StudentProfile.builder().user(user).build());
        return buildAuthResponse(user);
    }

    @Override
    public AuthResponse registerRecruiter(RegisterRequest request) {
        validateUniqueEmail(request.getEmail());
        if (request.getCompanyName() == null || request.getCompanyName().isBlank()) {
            throw new BadRequestException("Company name is required for recruiter signup");
        }
        User user = userRepository.save(User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.RECRUITER)
                .build());
        recruiterProfileRepository.save(RecruiterProfile.builder().user(user).companyName(request.getCompanyName()).build());
        return buildAuthResponse(user);
    }

    @Override
    public AuthResponse me(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return buildAuthResponse(user);
    }

    private void validateUniqueEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Email already in use");
        }
    }

    private AuthResponse buildAuthResponse(User user) {
        String token = jwtService.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
