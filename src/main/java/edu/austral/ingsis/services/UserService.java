package edu.austral.ingsis.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);
}
