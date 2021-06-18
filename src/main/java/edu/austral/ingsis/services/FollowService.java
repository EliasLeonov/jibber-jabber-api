package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;

import java.util.List;
import java.util.Set;

public interface FollowService {
    FollowDto follow(CreateFollowDto createFollowDto);
    Boolean unfollow(Long id);
    Set<UserFollowData> getFollowers(Long userId);
    Set<UserFollowData> getFollowing(Long userId);
    Set<Long> getFollowingIds(JJUser userId);

}
