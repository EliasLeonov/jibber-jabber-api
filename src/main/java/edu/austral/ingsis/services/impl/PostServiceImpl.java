package edu.austral.ingsis.services.impl;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.post.Post;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.exceptions.NotFoundException;
import edu.austral.ingsis.factories.PostFactory;
import edu.austral.ingsis.repositories.PostRepository;
import edu.austral.ingsis.repositories.UserRepository;
import edu.austral.ingsis.services.FollowService;
import edu.austral.ingsis.services.LikeService;
import edu.austral.ingsis.services.PostService;
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
    private final UserRepository userRepository;
    private final LikeService likeService;

    @Autowired
    public PostServiceImpl(PostRepository repository, FollowService followService, SessionUtils sessionUtils, UserRepository userRepository, LikeService likeService) {
        this.repository = repository;
        this.followService = followService;
        this.sessionUtils = sessionUtils;
        this.userRepository = userRepository;
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
        JJUser user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return repository
                .findAllByOwner(user)
                .stream()
                .map(Post::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PostDto> getFeed() {
        JJUser user = sessionUtils.getUserLogged();
        Set<Long> userIds = followService.getFollowingIds(user);
        List<JJUser> users = userIds
                .stream()
                .map(x -> userRepository
                        .findById(x)
                        .orElseThrow(() -> new NotFoundException("User does not found")))
                .collect(Collectors.toList());
        return users
                .stream()
                .map(repository::findAllByOwner)
                .flatMap(Set::stream)
                .map(Post::toDto)
                .peek(x -> x.setLikes((long) likeService.getAllLikeFromAPost(x.getId()).size()))
                .collect(Collectors.toSet());
    }


}
