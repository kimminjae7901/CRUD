package example.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import example.Repository.PostRepository;
import example.Repository.UserRepository;
import example.DTO.PostResponse;
import example.DTO.PostRequest;
import example.Entity.Post;
import example.exception.UserNotFoundException;
import example.exception.PostNotFoundException;
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

    public PostResponse ReadPostData(Long id)
    {
        Post post=postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        return new PostResponse(
            post.getTitle(),
            post.getContent(),
            post.getUser().getId()
        );
    }

    public PostResponse UpdatePostData(Long id, PostRequest postRequest)
    {
        Post post= postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        User user=userRepository.findById(postRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUser(user);
        
        postRepository.save(post);

        return new PostResponse(
            post.getTitle(),
            post.getContent(),
            post.getUser().getId()
        );

    }

    public String DeletePostData(Long id)
    {
        Post post= postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);

        return "삭제가 완료되었습니다";

    }

    public ArrayList<PostResponse> ReadUserAllPost(Long id)
    {
        User user=userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Post> posts=postRepository.findByUser(user);
        ArrayList<PostResponse> list= new ArrayList<>();
        for(int i=0; i<posts.size(); i++)
        {
            list.add(new PostResponse(posts.get(i).getTitle(),posts.get(i).getContent(),posts.get(i).getUser().getId()));
        }

        return list;
    }
}
