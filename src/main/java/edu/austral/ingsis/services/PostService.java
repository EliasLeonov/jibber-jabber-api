package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.dto.post.PostDto;

import java.util.List;

public interface PostService {
    PostDto save(CreatePostDto createPostDto);
    void delete(String id);
    PostDto findById(String id);
    List<PostDto> getAll();
}
