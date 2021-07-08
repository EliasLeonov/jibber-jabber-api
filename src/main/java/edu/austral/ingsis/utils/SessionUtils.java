package edu.austral.ingsis.utils;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.repositories.UserRepository;
import edu.austral.ingsis.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {

    private final UserRepository userRepository;

    public SessionUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JJUser getUserLogged(){
        Authentication jwt = SecurityContextHolder.getContext().getAuthentication();
        if (jwt == null) throw new NotFoundException("Error while getting session token");

        UserDetailsImpl user = (UserDetailsImpl) jwt.getPrincipal();
        return this.userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new NotFoundException("User does not found"));
    }
}
