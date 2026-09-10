package example.Service;

import org.springframework.stereotype.Service;

import example.Repository.PostRepository;
import example.Repository.UserRepository;
import example.DTO.PostResponse;
import example.DTO.PostRequest;
import example.Entity.Post;
import example.exception.UserNotFoundException;
import example.Entity.User;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }

    public PostResponse PostData(PostRequest postRequest)
    {
        Post post=new Post();

        User finduser= userRepository.findById(postRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUser(finduser);
        Post repositoryPost=postRepository.save(post);

        return new PostResponse(
            repositoryPost.getTitle(),
            repositoryPost.getContent(),
            repositoryPost.getUser().getId()
        );
    }
}
