package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.dto.post.PostDto;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.factories.PostFactory;
import edu.austral.ingsis.repositories.PostRepository;
import edu.austral.ingsis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostDto save(CreatePostDto createPostDto) {
        return repository.save(PostFactory.create(createPostDto)).toDto();
    }

    @Override
    public PostDto update(PostDto postDto) {
        Post oldPost = repository.findById(postDto.getId()).orElseThrow(() -> new NotFoundException("Post does not found"));
        return repository.save(PostFactory.update(postDto, oldPost)).toDto();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public PostDto findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Post does not found")).toDto();
    }

    @Override
    public List<PostDto> getAll() {
        return  StreamSupport
                .stream(repository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList())
                .stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

}
