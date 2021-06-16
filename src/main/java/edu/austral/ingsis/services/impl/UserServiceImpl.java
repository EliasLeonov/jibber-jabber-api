package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.repositories.UserRepository;
import edu.austral.ingsis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final JJUser JJUser = repository.findByUsername(username);

        if(JJUser == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JJUser;
    }
}
