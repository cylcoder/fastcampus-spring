package com.simpleboard.post.service;

import com.simpleboard.post.db.Post;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostViewRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public Post create(PostRequest postRequest) {
    return postRepository.save(PostRequest.toEntity(postRequest));
  }

  public Post view(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(postViewRequest.getPassword());
    return post;
  }

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Transactional
  public void delete(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(post.getPassword());
    postRepository.delete(post);
    // TODO:
    // 1. Soft delete
    // 2. Transactional
  }

}
