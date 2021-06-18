package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Boolean existsFollowByFollowerUserAndFollowingUser(JJUser followerUser, JJUser followingUser);
    Set<Follow> findAllByFollowingUser(JJUser followingUserId);
    Set<Follow> findAllByFollowerUser(JJUser followerUserId);
}
