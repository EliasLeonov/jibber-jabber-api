package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.follow.CreateFollowDto;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.services.FollowService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }

    @PostMapping("/follow")
    public FollowDto follow(@RequestBody @Valid CreateFollowDto followDto){
        return service.follow(followDto);
    }

    @DeleteMapping("/unfollow/{id}")
    public Boolean unfollow(@PathVariable(value = "id") @Valid String id){
        return service.unfollow(id);
    }

    @GetMapping("/get-followers/{userId}")
    public List<String> getFollowers(@PathVariable(value = "userId") @Valid String userId){
        return service.getFollowers(userId);
    }

    @GetMapping("/get-following/{userId}")
    public List<String> getFollowing(@PathVariable(value = "userId") @Valid String userId){
        return service.getFollowing(userId);
    }
}
