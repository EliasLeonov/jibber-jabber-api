package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Set<Post> findAllByOwner(JJUser owner);
}
