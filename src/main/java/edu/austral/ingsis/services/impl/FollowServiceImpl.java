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
import edu.austral.ingsis.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository repository;
    private final UserRepository userRepository;
    private final SessionUtils sessionUtils;

    @Autowired
    public FollowServiceImpl(FollowRepository repository, UserRepository userRepository, SessionUtils sessionUtils) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.sessionUtils = sessionUtils;
    }

    @Override
    public FollowDto follow(Long userId) {
        Long followerId = sessionUtils.getUserLogged().getId();
        JJUser follower = userRepository.findById(followerId).orElseThrow(() -> new NotFoundException("User not found"));
        JJUser following = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        if (repository.existsFollowByFollowerUserAndFollowingUser(follower, following))
            throw  new BadRequestException("Follow already exist");
        return repository.save(FollowFactory.create(follower, following)).toDto();
    }

    @Override
    public Boolean unfollow(Long followingId) {
        Long followerId = sessionUtils.getUserLogged().getId();
        Follow follow = repository.findByFollowerUser_IdAndFollowingUser_Id(followerId, followingId).orElseThrow(() -> new NotFoundException("Follow not exist"));
        repository.deleteById(follow.getId());
        return repository.existsById(follow.getId());
    }

    @Override
    public Set<UserFollowData> getFollowers(Long userId) {
        JJUser jjUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        return repository
                .findAllByFollowingUser(jjUser)
                .stream()
                .map(Follow::getFollowerUser)
                .map(user -> UserFollowData
                            .builder()
                            .firstname(user.getFirstname())
                            .username(user.getUsername())
                            .build())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserFollowData> getFollowing(Long userId) {
        JJUser jjUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return repository
                .findAllByFollowerUser(jjUser)
                .stream()
                .map(Follow::getFollowingUser)
                .map(user -> UserFollowData
                            .builder()
                            .firstname(user.getFirstname())
                            .username(user.getUsername())
                            .build())
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

    @Override
    public Boolean isFollowing(Long followingId){
        JJUser follower = sessionUtils.getUserLogged();
        JJUser following = userRepository.findById(followingId).orElseThrow(() -> new NotFoundException("User not found"));
        return repository.existsFollowByFollowerUserAndFollowingUser(follower, following);
    }

    public Set<JJUser> getFollowingUsers(JJUser user){
        return repository
                .findAllByFollowerUser(user)
                .stream()
                .map(Follow::getFollowingUser)
                .collect(Collectors.toSet());
    }
}
