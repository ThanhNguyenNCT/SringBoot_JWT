package Cybersoft.ExJPA_Security.services.Imp;

import Cybersoft.ExJPA_Security.entity.Post;
import Cybersoft.ExJPA_Security.entity.User;
import Cybersoft.ExJPA_Security.exception.DataNotFoundException;
import Cybersoft.ExJPA_Security.repo.PostRepository;
import Cybersoft.ExJPA_Security.repo.UserRepository;
import Cybersoft.ExJPA_Security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post getPostByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
        return postRepository.findByAuthorId(user.getId())
                .orElseThrow(() -> new DataNotFoundException("Post not found for user with username: " + username));
    }

    @Override
    public Post createPost(String username, String title, String content) {
        return null;
    }
}
