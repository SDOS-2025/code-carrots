package com.example.Leavefy_LAMS.Service;

import com.example.Leavefy_LAMS.Exception.UserIdNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String userId) throws UserIdNotFoundException;
}