package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.post.CreatePostDto;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable @Valid String id){
        return service.findById(id);
    }

    @PostMapping("/save")
    public PostDto save(@RequestBody @Valid CreatePostDto createPostDto){
        return service.save(createPostDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @Valid String id){
        service.delete(id);
    }

    @GetMapping("/get-all")
    public List<PostDto> getAll(){
        return service.getAll();
    }
}
