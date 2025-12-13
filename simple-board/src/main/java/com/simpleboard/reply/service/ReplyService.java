package com.simpleboard.reply.service;

import com.simpleboard.post.db.Post;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.db.ReplyRepository;
import com.simpleboard.reply.model.ReplyRequest;
import com.simpleboard.reply.model.ReplyResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

  private final ReplyRepository replyRepository;
  private final PostRepository postRepository;

  public ReplyResponse save(ReplyRequest replyRequest) {
    Post post = postRepository.findById(replyRequest.getPostId())
        .orElseThrow();
    Reply reply = ReplyRequest.toEntity(post, replyRequest);
    Reply savedReply = replyRepository.save(reply);
    return ReplyResponse.toDto(savedReply);
  }

  public List<ReplyResponse> findAllByPostId(Long postId) {
    return replyRepository.findAllByPostIdOrderById(postId)
        .stream()
        .map(ReplyResponse::toDto)
        .toList();
  }

}
