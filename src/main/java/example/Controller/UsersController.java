package example.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable long id,@RequestBody UserRequest userRequest){
        return helloService.updateUser(id,userRequest);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id){
        return helloService.deleteUser(id);
    }

    
}
