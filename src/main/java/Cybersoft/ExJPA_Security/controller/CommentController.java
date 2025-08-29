package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.dto.CommentDTO;
import Cybersoft.ExJPA_Security.entity.Comment;
import Cybersoft.ExJPA_Security.payload.request.CommentRequest;
import Cybersoft.ExJPA_Security.payload.respone.BaseResponse;
import Cybersoft.ExJPA_Security.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        CommentDTO commentDTO = commentService.addComment(commentRequest);
        BaseResponse baseResponse = new BaseResponse(200, "Add comment successfully", commentDTO);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") int id, @RequestBody CommentRequest commentRequest) {
        CommentDTO commentDTO = commentService.updateComment(id, commentRequest.getContent());
        BaseResponse baseResponse = new BaseResponse(200, "Update comment successfully", commentDTO);
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
        BaseResponse baseResponse = new BaseResponse(200, "Delete comment successfully", null);
        return ResponseEntity.ok(baseResponse);
    }
}
