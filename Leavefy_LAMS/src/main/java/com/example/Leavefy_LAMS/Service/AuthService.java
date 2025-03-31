package com.example.Leavefy_LAMS.Service;



import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Dto.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);
    ResponseEntity<?> logout();
}
