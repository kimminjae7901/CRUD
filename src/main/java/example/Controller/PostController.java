package example.Controller;

import example.Service.PostService;
import example.DTO.PostResponse;
import example.DTO.PostRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/posts")
    public PostResponse PostData(@RequestBody PostRequest postRequest)
    {
        return postService.PostData(postRequest);
    }
}
