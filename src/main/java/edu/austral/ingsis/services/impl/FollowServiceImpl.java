package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.domain.follow.Follow;
import edu.austral.ingsis.exceptions.BadRequestException;
import edu.austral.ingsis.factories.FollowFactory;
import edu.austral.ingsis.repositories.FollowRepository;
import edu.austral.ingsis.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository repository;

    @Autowired
    public FollowServiceImpl(FollowRepository repository) {
        this.repository = repository;
    }

    @Override
    public FollowDto follow(CreateFollowDto createFollowDto) {
        if (repository.existsFollowByFollowerUserIdAndFollowingUserId(createFollowDto.getFollowerUserId(), createFollowDto.getFollowUserId()))
            throw  new BadRequestException("Follow already exist");
        return repository.save(FollowFactory.create(createFollowDto)).toDto();
    }

    @Override
    public Boolean unfollow(String id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    @Override
    public List<String> getFollowers(String userId) {
        if (!repository.existsFollowByFollowerUserId(userId)) throw new BadRequestException("Follower does not exist");
        return repository
                .findAllByFollowingUserId(userId)
                .stream()
                .map(Follow::getFollowerUserId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getFollowing(String userId) {
        if (!repository.existsFollowByFollowingUserId(userId)) throw new BadRequestException("Following does not exist");
        return repository
                .findAllByFollowingUserId(userId)
                .stream()
                .map(Follow::getFollowingUserId)
                .collect(Collectors.toList());
    }

}
