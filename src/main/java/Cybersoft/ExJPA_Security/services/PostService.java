package Cybersoft.ExJPA_Security.services;

import Cybersoft.ExJPA_Security.dto.PostDTO;
import Cybersoft.ExJPA_Security.payload.request.PostRequest;

import java.util.List;

public interface PostService {
    List<PostDTO> getPostByUsername(String username);
    PostDTO createPost(PostRequest postRequest);
    void deletePost(String title);
    PostDTO editPost(PostRequest postRequest, String title);
    PostDTO findByTitle(String title);
    List<PostDTO> allPost();

}
