package Cybersoft.ExJPA_Security.repo;

import Cybersoft.ExJPA_Security.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByAuthorId(String id);
}
