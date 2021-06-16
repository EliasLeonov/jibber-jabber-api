package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Boolean existsFollowByFollowerUserIdAndFollowingUserId(Long followerUserId, Long followUserId);
    Boolean existsFollowByFollowerUserId(Long followerUserId);
    Boolean existsFollowByFollowingUserId(Long followingUserId);
    List<Follow> findAllByFollowingUserId(Long followingId);
}
