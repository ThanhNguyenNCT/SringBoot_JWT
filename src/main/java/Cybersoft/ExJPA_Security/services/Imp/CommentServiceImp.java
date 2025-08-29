package Cybersoft.ExJPA_Security.services.Imp;

import Cybersoft.ExJPA_Security.dto.CommentDTO;
import Cybersoft.ExJPA_Security.entity.Comment;
import Cybersoft.ExJPA_Security.entity.Post;
import Cybersoft.ExJPA_Security.entity.User;
import Cybersoft.ExJPA_Security.exception.DataNotFoundException;
import Cybersoft.ExJPA_Security.mapper.CommentMapper;
import Cybersoft.ExJPA_Security.payload.request.CommentRequest;
import Cybersoft.ExJPA_Security.repo.CommentRepository;
import Cybersoft.ExJPA_Security.repo.PostRepository;
import Cybersoft.ExJPA_Security.repo.UserRepository;
import Cybersoft.ExJPA_Security.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentDTO addComment(CommentRequest commentRequest) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new DataNotFoundException("User not found with username: " + userName));
        Post post = postRepository.findByTitle(commentRequest.getPostTitle())
                .orElseThrow(() -> new DataNotFoundException("Post not found with title: " + commentRequest.getPostTitle()));
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setAuthor(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return CommentMapper.toDTO(savedComment);
    }

    @Override
    public void deleteComment(int id) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found with id: " + id));
        if(!comment.getAuthor().getUsername().equals(userName)) {
            throw new DataNotFoundException("You are not authorized to delete this comment");
        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentDTO updateComment(int id, String content) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found with id: " + id));
        if(!comment.getAuthor().getUsername().equals(userName)) {
            throw new DataNotFoundException("You are not authorized to update this comment");
        }
        comment.setContent(content);
        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.toDTO(updatedComment);
    }
}
