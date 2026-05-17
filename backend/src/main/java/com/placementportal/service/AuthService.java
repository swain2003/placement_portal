package com.placementportal.service;

import com.placementportal.dto.AuthResponse;
import com.placementportal.dto.LoginRequest;
import com.placementportal.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse registerStudent(RegisterRequest request);

    AuthResponse registerRecruiter(RegisterRequest request);

    AuthResponse me(String email);
}
