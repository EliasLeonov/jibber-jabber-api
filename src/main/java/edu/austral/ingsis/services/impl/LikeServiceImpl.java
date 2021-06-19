package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.domain.follow.Like;
import edu.austral.ingsis.exceptions.BadRequestException;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.factories.LikeFactory;
import edu.austral.ingsis.repositories.LikeRepository;
import edu.austral.ingsis.repositories.PostRepository;
import edu.austral.ingsis.services.LikeService;
import edu.austral.ingsis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final PostRepository postRepository;


    @Autowired
    public LikeServiceImpl(LikeRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Override
    public LikeDto like(CreateLikeDto likeDto) {
        if (!repository.existsByPostIdAndUserId(likeDto.getPostId(),likeDto.getUserId()))
            throw new BadRequestException("Like already exist");
        return repository.save(LikeFactory.createLike(likeDto)).toDto();
    }

    @Override
    public Boolean unlike(String id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    @Override
    public Set<LikeDto> getAllLikeFromAPost(Long postId) {
        return repository
                .findAllByPostId(postId)
                .stream()
                .map(Like::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PostDto> getAllLikedPosts(Long userId) {
        return repository
                .findAllByUserId(userId)
                .stream()
                .map(x -> postRepository
                        .findById(x.getPostId())
                        .orElseThrow(() -> new NotFoundException("Post does not found"))
                        .toDto())
                .collect(Collectors.toSet());
    }

    @Override
    public Boolean existLikeOfPost(Long postId, Long userId) {
        return repository.existsByPostIdAndUserId(postId, userId);
    }

}
