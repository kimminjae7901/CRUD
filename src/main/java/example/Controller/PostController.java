package example.Controller;

import java.util.ArrayList;

import example.Service.PostService;
import example.DTO.PostResponse;
import example.DTO.PostRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/posts")
    public PostResponse PostData(@Valid @RequestBody PostRequest postRequest)
    {
        return postService.PostData(postRequest);
    }

    @GetMapping("/posts/{id}")
    public PostResponse ReadPostData(@PathVariable Long id)
    {
        return postService.ReadPostData(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponse UpdatePostData(@PathVariable Long id,@RequestBody PostRequest postRequest)
    {
        return postService.UpdatePostData(id,postRequest);
    }

    @DeleteMapping("/posts/{id}")
    public String DeletePostData(@PathVariable Long id)
    {
        return postService.DeletePostData(id);
    }
    
    @GetMapping("/users/{id}/posts")
    public ArrayList<PostResponse> ReadUserAllPost(@PathVariable Long id)
    {
        return postService.ReadUserAllPost(id);
    }
}
