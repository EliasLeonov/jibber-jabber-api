package edu.austral.ingsis.services;

import edu.austral.ingsis.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.dto.post.PostDto;

import java.util.List;

public interface PostService {
    PostDto save(CreatePostDto createPostDto);
    PostDto update(PostDto postDto);
    void delete(String id);
    PostDto findById(String id);
    List<PostDto> getAll();
}
