package example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
