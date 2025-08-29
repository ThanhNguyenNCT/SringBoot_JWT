package Cybersoft.ExJPA_Security.dto;

import Cybersoft.ExJPA_Security.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class PostDTO {
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDTO> comments;

}
