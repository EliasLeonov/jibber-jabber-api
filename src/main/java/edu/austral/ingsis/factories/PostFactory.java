package edu.austral.ingsis.factories;

import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {

    public static Post create(CreatePostDto createPostDto){
        return Post
                .builder()
                .text(createPostDto.getText())
                .username(createPostDto.getUsername())
                .build();
    }
}
