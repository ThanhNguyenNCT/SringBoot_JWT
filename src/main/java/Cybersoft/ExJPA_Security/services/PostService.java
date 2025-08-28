package Cybersoft.ExJPA_Security.services;

import Cybersoft.ExJPA_Security.entity.Post;

public interface PostService {
    Post getPostByUsername(String username);
    Post createPost(String username, String title, String content);

}
