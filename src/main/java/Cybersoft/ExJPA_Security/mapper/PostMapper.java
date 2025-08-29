package Cybersoft.ExJPA_Security.mapper;

import Cybersoft.ExJPA_Security.dto.PostDTO;
import Cybersoft.ExJPA_Security.entity.Post;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setAuthorName(post.getAuthor().getUsername());
        dto.setComments(post.getComments().stream().map(CommentMapper::toDTO).toList());
        dto.setContent(post.getContent());
        dto.setTitle(post.getTitle());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}
