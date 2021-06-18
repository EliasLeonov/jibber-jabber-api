package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.JJUser;
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

import java.util.HashSet;
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
        JJUser follower = userRepository.findById(createFollowDto.getFollowerUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        JJUser following = userRepository.findById(createFollowDto.getFollowingUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        if (repository.existsFollowByFollowerUserAndFollowingUser(follower, following))
            throw  new BadRequestException("Follow already exist");
        return repository.save(FollowFactory.create(createFollowDto, follower, following)).toDto();
    }

    @Override
    public Boolean unfollow(Long id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    @Override
    public Set<UserFollowData> getFollowers(Long userId) {
        JJUser jjUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        return repository
                .findAllByFollowingUser(jjUser)
                .stream()
                .map(Follow::getFollowerUser)
                .map(x -> {
                    var user = userRepository.findById(x.getId()).orElseThrow(() -> new NotFoundException("User does not found"));
                    return UserFollowData.builder()
                            .firstname(user.getName())
                            .username(user.getUsername())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserFollowData> getFollowing(Long userId) {
        JJUser jjUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return repository
                .findAllByFollowingUser(jjUser)
                .stream()
                .map(Follow::getFollowingUser)
                .map(x -> {
                    var user = userRepository.findById(jjUser.getId()).orElseThrow(() -> new NotFoundException("User does not found"));
                    return UserFollowData.builder()
                            .firstname(user.getName())
                            .username(user.getUsername())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getFollowingIds(JJUser user) {
        return repository
                .findAllByFollowingUser(user)
                .stream()
                .map(Follow::getFollowingUser)
                .map(JJUser::getId)
                .collect(Collectors.toSet());
    }

}
