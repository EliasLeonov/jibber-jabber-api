package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.like.CreateLikeDto;
import edu.austral.ingsis.domain.dto.like.LikeDto;
import edu.austral.ingsis.services.LikeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Boolean unlike(@PathVariable(value = "id") @Valid String id){
        return service.unlike(id);
    }

    @GetMapping("/get-all-likes")
    public List<LikeDto> getAllLikesFromAPost(String postId){
        return service.getAllLikeFromAPost(postId);
    }

    @GetMapping("/get-all-liked-posts")
    public List<LikeDto> getAllLikedPosts(String userId){
        return service.getAllLikedPosts(userId);
    }
}
