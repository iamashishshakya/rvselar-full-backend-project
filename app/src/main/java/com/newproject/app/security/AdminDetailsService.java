package com.newproject.app.security;

import com.newproject.app.models.Admin;
import com.newproject.app.reporesatry.AdminEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminEntity adminEntity;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = adminEntity.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));

        return User.builder()
                .username(admin.getEmail())  // email stored as username in spring security context
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }

}
