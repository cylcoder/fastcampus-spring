package com.simpleboard.reply.controller;

import com.simpleboard.crud.CRUDApiController;
import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.model.ReplyRequest;
import com.simpleboard.reply.model.ReplyResponse;
import com.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replies")
public class ReplyApiController extends CRUDApiController<Reply, ReplyRequest, ReplyResponse> {

  private final ReplyService replyService;

  public ReplyApiController(ReplyService replyService) {
    super(replyService);
    this.replyService = replyService;
  }

 /* @PostMapping
  public ReplyResponse create(@RequestBody @Valid ReplyRequest replyRequest) {
    return replyService.save(replyRequest);
  }*/

}
