package edu.austral.ingsis.controllers;

import edu.austral.ingsis.domain.dto.user.JJUserDto;
import edu.austral.ingsis.domain.dto.user.UserPrivateDataDto;
import edu.austral.ingsis.domain.dto.user.UserPublicDataDto;
import edu.austral.ingsis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-info")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/public-profile/{username}")
    public UserPublicDataDto getUserPublicData(@PathVariable(value = "username") @Valid String username){
        return service.getPublicData(username);
    }

    @GetMapping("/private-profile")
    public UserPrivateDataDto getUserPrivateData(){
        return service.getPrivateData();
    }

    @GetMapping("/user/{username}")
    public JJUserDto getUser(@PathVariable(name = "username") @Valid String username){
        return service.getByUsername(username);
    }

}
