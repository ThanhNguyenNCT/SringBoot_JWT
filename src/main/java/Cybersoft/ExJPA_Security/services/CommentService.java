package Cybersoft.ExJPA_Security.services;

import Cybersoft.ExJPA_Security.dto.CommentDTO;
import Cybersoft.ExJPA_Security.payload.request.CommentRequest;

public interface CommentService {
    CommentDTO addComment(CommentRequest commentRequest);
    void deleteComment(int id);
    CommentDTO updateComment(int id, String content);
}
