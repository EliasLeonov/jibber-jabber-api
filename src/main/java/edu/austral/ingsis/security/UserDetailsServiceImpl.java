package edu.austral.ingsis.security;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final JJUser JJUser = repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User does not found"));

        if(JJUser == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JJUser;
    }
}
