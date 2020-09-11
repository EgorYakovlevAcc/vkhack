package com.vkhack.demo.service;

import com.vkhack.demo.model.Post;

public interface PostService {
    Post save(Post post);
    void delete(Post post);

    void deletePostById(Long postId);
}
