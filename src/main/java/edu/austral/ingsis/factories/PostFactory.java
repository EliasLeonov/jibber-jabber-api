package edu.austral.ingsis.factories;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {

    public static Post create(CreatePostDto createPostDto, JJUser owner){
        return Post
                .builder()
                .text(createPostDto.getText())
                .owner(owner)
                .build();
    }
}
