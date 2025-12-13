package com.simpleboard.post.service;

import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.crud.CRUDService;
import com.simpleboard.crud.Converter;
import com.simpleboard.post.db.Post;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostResponse;
import com.simpleboard.post.model.PostViewRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class PostService extends CRUDService<Post, PostRequest, PostResponse> {

  private final PostRepository postRepository;
  private final BoardRepository boardRepository;
  private final Converter<Post, PostRequest, PostResponse> converter;

  public PostService(
      BoardRepository boardRepository,
      PostRepository postRepository,
      Converter<Post, PostRequest, PostResponse> converter
  ) {
    super(postRepository, converter);
    this.boardRepository = boardRepository;
    this.postRepository = postRepository;
    this.converter = converter;
  }

  public PostResponse view(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(postViewRequest.getPassword());
    return PostResponse.toDto(post);
  }

  @Transactional
  public void delete(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(post.getPassword());
    postRepository.delete(post);
  }

}
