package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;

import java.util.List;

public interface LikeService {
    LikeDto like(CreateLikeDto likeDto);
    Boolean unlike(String id);
    List<LikeDto> getAllLikeFromAPost(String postId);
    List<LikeDto> getAllLikedPosts(String userId);
}
