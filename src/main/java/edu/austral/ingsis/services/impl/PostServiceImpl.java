package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;
import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.factories.PostFactory;
import edu.austral.ingsis.repositories.PostRepository;
import edu.austral.ingsis.services.FollowService;
import edu.austral.ingsis.services.LikeService;
import edu.austral.ingsis.services.PostService;
import edu.austral.ingsis.services.UserService;
import edu.austral.ingsis.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final FollowService followService;
    private final SessionUtils sessionUtils;
    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    public PostServiceImpl(PostRepository repository, FollowService followService, SessionUtils sessionUtils, UserService userService, LikeService likeService) {
        this.repository = repository;
        this.followService = followService;
        this.sessionUtils = sessionUtils;
        this.userService = userService;
        this.likeService = likeService;
    }

    @Override
    public PostDto save(CreatePostDto createPostDto) {
        return repository.save(PostFactory.create(createPostDto, sessionUtils.getUserLogged())).toDto();
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public PostDto findById(Long id) {
        PostDto postDto =  repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Post does not found"))
                .toDto();
        postDto.setLikes((long) likeService.getAllLikeFromAPost(id).size());
        return postDto;
    }

    @Override
    public Set<PostDto> getAll() {
        return  repository
                .findAll()
                .stream()
                .map(Post::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PostDto> getAllByUser(String username){
        return repository
                .findAllByUsername(username)
                .stream()
                .map(Post::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PostDto> getFeed() {
        JJUser user = sessionUtils.getUserLogged();
        Set<Long> userIds = followService.getFollowingIds(user.getId());
        List<String> usernames = userIds
                .stream()
                .map(x -> userService.getById(x).getUsername())
                .collect(Collectors.toList());
        return usernames
                .stream()
                .map(repository::findAllByUsername)
                .flatMap(Set::stream)
                .map(Post::toDto)
                .peek(x -> x.setLikes((long) likeService.getAllLikeFromAPost(x.getId()).size()))
                .collect(Collectors.toSet());
    }


}
