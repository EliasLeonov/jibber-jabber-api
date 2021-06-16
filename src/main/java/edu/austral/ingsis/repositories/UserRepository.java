package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.JJUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JJUser, Long> {
    JJUser findByUsername(String username);
}
