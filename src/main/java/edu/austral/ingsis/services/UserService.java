package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.JJUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);
    public JJUser getById(Long id);
}
