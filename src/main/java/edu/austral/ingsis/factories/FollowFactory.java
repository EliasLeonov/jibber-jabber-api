package edu.austral.ingsis.factories;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.follow.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowFactory {

    public static Follow create(JJUser follower, JJUser following){
        return Follow
                .builder()
                .followerUser(follower)
                .followingUser(following)
                .build();
    }
}
