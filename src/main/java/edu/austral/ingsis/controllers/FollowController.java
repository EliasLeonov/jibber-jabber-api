package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;
import edu.austral.ingsis.services.FollowService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }

    @PostMapping("/follow/{userId}")
    public FollowDto follow(@PathVariable(value = "userId") @Valid Long userId){
        return service.follow(userId);
    }

    @DeleteMapping("/unfollow/{following-id}")
    public Boolean unfollow(@PathVariable(value = "following-id") @Valid Long followingId){
        return service.unfollow(followingId);
    }

    @GetMapping("/get-followers/{userId}")
    public Set<UserFollowData> getFollowers(@PathVariable(value = "userId") @Valid Long userId){
        return service.getFollowers(userId);
    }

    @GetMapping("/get-following/{userId}")
    public Set<UserFollowData> getFollowing(@PathVariable(value = "userId") @Valid Long userId){
        return service.getFollowing(userId);
    }

    @GetMapping("/is-following/{following-id}")
    public Boolean isFollowing(@PathVariable(value = "following-id") @Valid Long followingId){
        return service.isFollowing(followingId);
    }
}
