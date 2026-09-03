package example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

