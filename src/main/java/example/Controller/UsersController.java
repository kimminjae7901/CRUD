package example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.DTO.UserRequest;
import example.DTO.UserResponse;
import example.Service.UsersService;

@RestController
public class UsersController {

    private final UsersService helloService;

    public UsersController(UsersService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/users/{id}")
    public UserResponse hello(@PathVariable long id) {
        return helloService.getUser(id);
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserRequest userRequest)
    {
        return helloService.createUser(userRequest);
    }
    
}
