package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.dto.PostDTO;
import Cybersoft.ExJPA_Security.payload.request.PostRequest;
import Cybersoft.ExJPA_Security.payload.respone.BaseResponse;
import Cybersoft.ExJPA_Security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    // Lấy bài viết của người dùng theo username
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getPostByUsername(@PathVariable String username) {
        List<PostDTO>  listPost = postService.getPostByUsername(username);
        BaseResponse baseResponse = new BaseResponse(200, "Success", listPost);
        return ResponseEntity.ok(baseResponse);
    }

    // Tạo bài viết mới
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createPost(@RequestBody PostRequest postRequest) {
        PostDTO postDTO = postService.createPost(postRequest);
        BaseResponse baseResponse = new BaseResponse(200, "Post created successfully", postDTO);
        return ResponseEntity.ok(baseResponse);
    }

    // Xóa bài viết theo title
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<BaseResponse> deletePost(@PathVariable String title) {
        postService.deletePost(title);
        BaseResponse baseResponse = new BaseResponse(200, "Post deleted successfully", null);
        return ResponseEntity.ok(baseResponse);
    }

    // Sửa bài viết theo title
    @PutMapping("/edit/{title}")
    public ResponseEntity<BaseResponse> editPost(@RequestBody PostRequest postRequest, @PathVariable String title) {
        PostDTO postDTO = postService.editPost(postRequest, title);
        BaseResponse baseResponse = new BaseResponse(200, "Post updated successfully", postDTO);
        return ResponseEntity.ok(baseResponse);
    }

    // Tìm bài viết theo title
    @GetMapping("/{title}")
    public ResponseEntity<BaseResponse> findByTitle(@PathVariable String title) {
        PostDTO postDTO = postService.findByTitle(title);
        BaseResponse baseResponse = new BaseResponse(200, "Post found", postDTO);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> allPost() {
        List<PostDTO> listPost = postService.allPost();
        BaseResponse baseResponse = new BaseResponse(200, "All posts", listPost);
        return ResponseEntity.ok(baseResponse);
    }
}
