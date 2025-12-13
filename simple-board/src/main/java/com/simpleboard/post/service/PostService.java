package com.simpleboard.post.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.common.Api;
import com.simpleboard.common.Pagination;
import com.simpleboard.post.db.Post;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostResponse;
import com.simpleboard.post.model.PostViewRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public PostResponse create(PostRequest postRequest) {
    Board board = boardRepository.findById(postRequest.getBoardId())
        .orElseThrow();
    Post post = PostRequest.toEntity(postRequest, board);
    Post savedPost = postRepository.save(post);
    return PostResponse.toDto(savedPost);
  }

  public PostResponse view(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(postViewRequest.getPassword());
    return PostResponse.toDto(post);
  }

  public Api<List<PostResponse>> findAll(Pageable pageable) {
    Page<Post> page = postRepository.findAll(pageable);
    Pagination pagination = Pagination.toDto(page);
    List<PostResponse> postResponses = page
        .stream()
        .map(PostResponse::toDto)
        .toList();
    return Api.ok(postResponses, pagination);
  }

  @Transactional
  public void delete(@Valid PostViewRequest postViewRequest) {
    Post post = postRepository.findById(postViewRequest.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    post.validatePassword(post.getPassword());
    postRepository.delete(post);
  }

}
