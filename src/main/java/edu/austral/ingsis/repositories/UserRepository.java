package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.JJUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JJUser, Long> {
    Optional<JJUser> findByUsername(String username);
    Optional<JJUser> findById(Long id);
    List<JJUser> findAll();
}
