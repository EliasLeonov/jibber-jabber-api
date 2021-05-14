package edu.austral.ingsis.factories;

import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.dto.post.CreatePostDto;
import edu.austral.ingsis.dto.post.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {
    public static Post create(CreatePostDto createPostDto){
        return Post
                .builder()
                .text(createPostDto.getText())
                .build();
    }
    public static Post update(PostDto postDto, Post oldPost){
        return Post
                .builder()
                .id(oldPost.getId())
                .text(postDto.getText())
                .build();
    }
}
