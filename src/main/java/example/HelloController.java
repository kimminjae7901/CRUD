package example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/users/{id}")
    public String hello(@PathVariable int id) {
        return helloService.getUser(id);
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserRequest userRequest)
    {
        return helloService.createUser(userRequest);
    }
    
}
