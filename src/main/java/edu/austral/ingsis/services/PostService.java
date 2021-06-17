package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.dto.post.PostDto;

import java.util.List;
import java.util.Set;

public interface PostService {
    PostDto save(CreatePostDto createPostDto);
    boolean delete(Long id);
    PostDto findById(Long id);
    Set<PostDto> getAll();
    Set<PostDto> getFeed();
    Set<PostDto> getAllByUser(String username);

    }
