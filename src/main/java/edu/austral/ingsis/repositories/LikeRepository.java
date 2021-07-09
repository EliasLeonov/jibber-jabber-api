package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.follow.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByPostIdAndUserId(Long postId, Long userId);
    List<Like> findAllByPostId(Long postId);
    List<Like> findAllByUserId(Long userId);
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}
