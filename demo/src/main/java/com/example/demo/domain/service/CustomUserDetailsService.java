package com.example.demo.domain.service;

import com.example.demo.domain.object.User;
import com.example.demo.domain.repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final RdsRepository rds;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = rds.read(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found: " + username);
        }

        return user.get();
    }
}
