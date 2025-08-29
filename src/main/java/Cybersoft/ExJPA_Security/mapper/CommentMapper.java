package Cybersoft.ExJPA_Security.mapper;

import Cybersoft.ExJPA_Security.dto.CommentDTO;
import Cybersoft.ExJPA_Security.entity.Comment;

public class CommentMapper {
    public static CommentDTO toDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setContent(comment.getContent());
        dto.setCommentAuthorName(comment.getAuthor().getUsername());
        dto.setCreateDate(comment.getCreatedAt());
        dto.setUpdateDate(comment.getUpdatedAt());
        return dto;
    }
}
