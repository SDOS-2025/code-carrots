package com.example.Leavefy_LAMS.Service.Impl;


import com.example.Leavefy_LAMS.Exception.UserIdNotFoundException;
import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Repository.AdminRepository;
import com.example.Leavefy_LAMS.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Override
//    public UserDetails loadUserByUserId(Long userId) throws UserIdNotFoundException {
//        User user = adminRepository.findByUserId(userId)
//                .orElseThrow(() -> new UserIdNotFoundException("User not found with id: " + userId));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(String.valueOf(userId))
//                .password(user.getPassword())
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName())))
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();
//    }
//}
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = adminRepository.findByUserId(Long.valueOf(userId))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(String.valueOf(user.getUserId()))
//                .password(user.getPassword())
//                .authorities(Collections.emptyList())
//                .build();
//    }
//}


// Should use Spring Security's interface instead of custom one
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = adminRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(user.getUserId()))
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}