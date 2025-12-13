package com.simpleboard.reply.service;

import com.simpleboard.crud.CRUDService;
import com.simpleboard.crud.Converter;
import com.simpleboard.post.db.PostRepository;
import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.db.ReplyRepository;
import com.simpleboard.reply.model.ReplyRequest;
import com.simpleboard.reply.model.ReplyResponse;
import org.springframework.stereotype.Service;

@Service
public class ReplyService extends CRUDService<Reply, ReplyRequest, ReplyResponse> {

  private final ReplyRepository replyRepository;
  private final PostRepository postRepository;
  private final Converter<Reply, ReplyRequest, ReplyResponse> converter;

  public ReplyService(
      ReplyRepository replyRepository,
      PostRepository postRepository,
      Converter<Reply, ReplyRequest, ReplyResponse> converter) {
    super(replyRepository, converter);
    this.replyRepository = replyRepository;
    this.postRepository = postRepository;
    this.converter = converter;
  }

}
