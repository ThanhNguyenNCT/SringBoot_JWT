package Cybersoft.ExJPA_Security.payload.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private String postTitle;
}
