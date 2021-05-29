package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
    Boolean existsFollowByFollowerUserIdAndFollowingUserId(String followerUserId, String followUserId);
    Boolean existsFollowByFollowerUserId(String followerUserId);
    Boolean existsFollowByFollowingUserId(String followingUserId);
    List<Follow> findAllByFollowingUserId(String followingId);
}
