package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;
import edu.austral.ingsis.domain.follow.Follow;
import edu.austral.ingsis.exceptions.BadRequestException;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.factories.FollowFactory;
import edu.austral.ingsis.repositories.FollowRepository;
import edu.austral.ingsis.repositories.UserRepository;
import edu.austral.ingsis.services.FollowService;
import edu.austral.ingsis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public FollowDto follow(CreateFollowDto createFollowDto) {
        if (repository.existsFollowByFollowerUserIdAndFollowingUserId(createFollowDto.getFollowerUserId(), createFollowDto.getFollowUserId()))
            throw  new BadRequestException("Follow already exist");
        return repository.save(FollowFactory.create(createFollowDto)).toDto();
    }

    @Override
    public Boolean unfollow(Long id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    @Override
    public Set<UserFollowData> getFollowers(Long userId) {
        if (!repository.existsFollowByFollowerUserId(userId)) throw new BadRequestException("Follower does not exist");
        return repository
                .findAllByFollowingUserId(userId)
                .stream()
                .map(Follow::getFollowerUserId)
                .map(x -> {
                    var user = userRepository.findById(x).orElseThrow(() -> new NotFoundException("User does not found"));
                    return UserFollowData.builder()
                            .firstname(user.getName())
                            .username(user.getUsername())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserFollowData> getFollowing(Long userId) {
        if (!repository.existsFollowByFollowingUserId(userId)) throw new BadRequestException("Following does not exist");
        return repository
                .findAllByFollowingUserId(userId)
                .stream()
                .map(Follow::getFollowingUserId)
                .map(x -> {
                    var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User does not found"));
                    return UserFollowData.builder()
                            .firstname(user.getName())
                            .username(user.getUsername())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getFollowingIds(Long userId) {
        if (!repository.existsFollowByFollowingUserId(userId)) throw new BadRequestException("Following does not exist");
        return repository
                .findAllByFollowingUserId(userId)
                .stream()
                .map(Follow::getFollowingUserId)
                .collect(Collectors.toSet());
    }

}
