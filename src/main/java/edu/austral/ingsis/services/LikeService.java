package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;
import edu.austral.ingsis.domain.dto.post.PostDto;

import java.util.List;
import java.util.Set;

public interface LikeService {
    LikeDto like(CreateLikeDto likeDto);
    Boolean unlike(Long postID);
    Set<LikeDto> getAllLikeFromAPost(Long postId);
    Set<PostDto> getAllLikedPosts(Long userId);
    Boolean existLikeOfPost(Long postId, Long userId);
}
