package example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.DTO.UserRequest;
import example.DTO.UserResponse;
import example.Entity.User;
import example.Service.UsersService;
import jakarta.validation.Valid;

@RestController
public class UsersController {

    private final UsersService helloService;

    public UsersController(UsersService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/users")
    public ArrayList<UserResponse> ReadAllUser()
    {
        return helloService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> hello(@PathVariable long id) {
        UserResponse userResponse= helloService.getUser(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest)
    {
        UserResponse userResponse=helloService.createUser(userRequest);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userResponse);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable long id,@RequestBody UserRequest userRequest){
        return helloService.updateUser(id,userRequest);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id){

        return helloService.deleteUser(id); 
    }

    @GetMapping("/users/with-posts")
    public List<User> findAllWithPosts()
    {
        return helloService.findAllWithPosts();
    }
    
}
