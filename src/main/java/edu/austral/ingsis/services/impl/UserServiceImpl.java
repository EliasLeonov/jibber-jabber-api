package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.domain.dto.user.UserPublicDataDto;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.repositories.UserRepository;
import edu.austral.ingsis.services.FollowService;
import edu.austral.ingsis.services.PostService;
import edu.austral.ingsis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;
    private final PostService postService;
    private final FollowService followService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PostService postService, FollowService followService) {
        this.repository = repository;
        this.postService = postService;
        this.followService = followService;
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final JJUser JJUser = repository.findByUsername(username);

        if(JJUser == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JJUser;
    }

    @Override
    public JJUser getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User does not found"));
    }

    public UserPublicDataDto getPublicData(String username){
        JJUser user = repository.findByUsername(username);
        Set<PostDto> posts = postService.getAllByUser(username);
        Set<UserFollowData> followers = followService.getFollowers(user.getId());
        Set<UserFollowData> following = followService.getFollowing(user.getId());
        return UserPublicDataDto.builder()
                .username(user.getUsername())
                .firstname(user.getName())
                .posts(posts)
                .followers(followers)
                .following(following)
                .build();
    }


}
