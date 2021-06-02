package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;
import edu.austral.ingsis.domain.follow.Like;
import edu.austral.ingsis.exceptions.BadRequestException;
import edu.austral.ingsis.factories.LikeFactory;
import edu.austral.ingsis.repositories.LikeRepository;
import edu.austral.ingsis.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;

    @Autowired
    public LikeServiceImpl(LikeRepository repository) {
        this.repository = repository;
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
    public List<LikeDto> getAllLikeFromAPost(String postId) {
        return repository
                .findAllByPostId(postId)
                .stream()
                .map(Like::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LikeDto> getAllLikedPosts(String userId) {
        return repository
                .findAllByUserId(userId)
                .stream()
                .map(Like::toDto)
                .collect(Collectors.toList());
    }

}
