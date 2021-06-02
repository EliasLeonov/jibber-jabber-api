package edu.austral.ingsis.factories;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.follow.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowFactory {

    public static Follow create(CreateFollowDto followDto){
        return Follow
                .builder()
                .followingUserId(followDto.getFollowUserId())
                .followerUserId(followDto.getFollowerUserId())
                .build();
    }
}
