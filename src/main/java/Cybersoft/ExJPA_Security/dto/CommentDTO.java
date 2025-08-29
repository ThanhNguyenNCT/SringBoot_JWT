package Cybersoft.ExJPA_Security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private String commentAuthorName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
