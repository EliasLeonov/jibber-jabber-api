package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.follow.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {
    Boolean existsByPostIdAndUserId(String postId, String userId);
    List<Like> findAllByPostId(String postId);
    List<Like> findAllByUserId(String userId);
}
