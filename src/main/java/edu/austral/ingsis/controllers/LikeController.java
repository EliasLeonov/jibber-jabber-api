package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.services.LikeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService service;

    public LikeController(LikeService service) {
        this.service = service;
    }

    @PostMapping
    public LikeDto like(@RequestBody @Valid CreateLikeDto likeDto){
        return service.like(likeDto);
    }

    @DeleteMapping("/{id}")
    public Boolean unlike(@PathVariable(value = "id") @Valid Long id){
        return service.unlike(id);
    }

    @GetMapping("/get-all-likes")
    public Set<LikeDto> getAllLikesFromAPost(Long postId){
        return service.getAllLikeFromAPost(postId);
    }

    @GetMapping("/get-all-liked-posts")
    public Set<PostDto> getAllLikedPosts(Long userId){
        return service.getAllLikedPosts(userId);
    }
}
