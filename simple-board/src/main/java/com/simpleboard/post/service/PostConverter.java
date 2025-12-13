package com.simpleboard.post.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.crud.Converter;
import com.simpleboard.post.db.Post;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConverter implements Converter<Post, PostRequest, PostResponse> {

  private final BoardRepository boardRepository;

  @Override
  public Post toEntity(PostRequest postRequest) {
    Board board = boardRepository.findById(postRequest.getBoardId())
        .orElseThrow();
    return PostRequest.toEntity(postRequest, board);
  }

  @Override
  public PostResponse toDto(Post post) {
    return PostResponse.toDto(post);
  }

}
