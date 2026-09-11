package example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import example.Entity.Post;
import example.Entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
