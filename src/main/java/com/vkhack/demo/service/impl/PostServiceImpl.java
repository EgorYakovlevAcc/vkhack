package com.vkhack.demo.service.impl;

import com.vkhack.demo.repo.DonateRepository;
import com.vkhack.demo.repo.PostRepository;
import com.vkhack.demo.model.Post;
import com.vkhack.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DonateRepository donateRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
