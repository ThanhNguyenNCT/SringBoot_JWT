package Cybersoft.ExJPA_Security.services.Imp;

import Cybersoft.ExJPA_Security.dto.PostDTO;
import Cybersoft.ExJPA_Security.entity.Post;
import Cybersoft.ExJPA_Security.entity.User;
import Cybersoft.ExJPA_Security.exception.DataNotFoundException;
import Cybersoft.ExJPA_Security.exception.InvalidException;
import Cybersoft.ExJPA_Security.mapper.PostMapper;
import Cybersoft.ExJPA_Security.payload.request.PostRequest;
import Cybersoft.ExJPA_Security.repo.PostRepository;
import Cybersoft.ExJPA_Security.repo.UserRepository;
import Cybersoft.ExJPA_Security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<PostDTO> getPostByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
        try {
            return postRepository.findByAuthor_id(user.getId()).stream().map(PostMapper::toDTO).toList();
        } catch (Exception e) {
            throw new DataNotFoundException("No posts found for user with username: " + username);
        }

    }

    @Override
    public PostDTO createPost(PostRequest postRequest) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(user);
        Post savedPost = postRepository.save(post);
        return PostMapper.toDTO(savedPost);
    }

    @Override
    public void deletePost(String title) {
        Post post = postRepository.findByTitle(title)
                .orElseThrow(() -> new DataNotFoundException("Post not found with title: " + title));
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("username: " + username);
        if(!post.getAuthor().getUsername().equals(username)) {
            throw new InvalidException("You are not authorized to delete this post");
        }
        postRepository.delete(post);
    }

    @Override
    public PostDTO editPost(PostRequest postRequest, String title) {
        Post post = postRepository.findByTitle(title)
                .orElseThrow(() -> new DataNotFoundException("Post not found with title: " + title));
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!post.getAuthor().getUsername().equals(username)) {
            throw new InvalidException("You are not authorized to edit this post");
        }
        post.setTitle(postRequest.getTitle().isEmpty() ? post.getTitle() : postRequest.getTitle());
        post.setContent(postRequest.getContent().isEmpty() ? post.getContent() : postRequest.getContent());
        Post updatedPost = postRepository.save(post);
        return PostMapper.toDTO(updatedPost);
    }

    @Override
    public PostDTO findByTitle(String title) {
        return postRepository.findByTitle(title).stream().map(PostMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Post not found with title: " + title));
    }

    @Override
    public List<PostDTO> allPost() {
        return postRepository.findAll().stream().map(PostMapper::toDTO).toList();
    }
}
