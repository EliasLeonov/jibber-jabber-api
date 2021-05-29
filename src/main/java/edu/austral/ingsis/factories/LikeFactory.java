package edu.austral.ingsis.factories;


import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.follow.Like;

public class LikeFactory {

    public static Like createLike(CreateLikeDto likeDto){
        return Like
                .builder()
                .postId(likeDto.getPostId())
                .userId(likeDto.getUserId())
                .build();
    }

}
