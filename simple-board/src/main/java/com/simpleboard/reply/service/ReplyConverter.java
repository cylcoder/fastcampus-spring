package com.simpleboard.reply.service;

import com.simpleboard.crud.Converter;
import com.simpleboard.post.db.Post;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.model.ReplyRequest;
import com.simpleboard.reply.model.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReplyConverter implements Converter<Reply, ReplyRequest, ReplyResponse> {

  private final PostRepository postRepository;

  @Override
  public Reply toEntity(ReplyRequest replyRequest) {
    Post post = postRepository.findById(replyRequest.getPostId())
        .orElseThrow();
    return ReplyRequest.toEntity(replyRequest, post);
  }

  @Override
  public ReplyResponse toDto(Reply reply) {
    return ReplyResponse.toDto(reply);
  }

}
