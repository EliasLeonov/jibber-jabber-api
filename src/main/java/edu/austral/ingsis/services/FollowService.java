package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;

import java.util.List;

public interface FollowService {
    FollowDto follow(CreateFollowDto createFollowDto);
    Boolean unfollow(String id);
    List<String> getFollowers(String userId);
    List<String> getFollowing(String userId);

}
