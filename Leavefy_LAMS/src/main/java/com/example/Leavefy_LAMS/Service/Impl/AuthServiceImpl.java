package com.example.Leavefy_LAMS.Service.Impl;
import lombok.extern.slf4j.Slf4j;

import com.example.Leavefy_LAMS.Exception.UserIdNotFoundException;
import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Repository.AdminRepository;
import com.example.Leavefy_LAMS.Service.AuthService;
import com.example.Leavefy_LAMS.Dto.LoginRequest;
import com.example.Leavefy_LAMS.Dto.JwtResponse;
import com.example.Leavefy_LAMS.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           AdminRepository adminRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.adminRepository = adminRepository;
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            // Convert userId to String for authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            String.valueOf(loginRequest.getUserId()), // Convert to String
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);

            User user = adminRepository.findByUserId(loginRequest.getUserId())
                    .orElseThrow(() -> new UserIdNotFoundException("User not found"));

            user.setPassword(null); // Remove sensitive information
            return ResponseEntity.ok(new JwtResponse(jwt, user));

        } catch (UserIdNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        } catch (Exception e) {
            log.error("Authentication error", e);  // Add this line
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Authentication error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> logout() {
        try {
            // Clear the security context
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok()
                    .body("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error during logout: " + e.getMessage());
        }
    }
}