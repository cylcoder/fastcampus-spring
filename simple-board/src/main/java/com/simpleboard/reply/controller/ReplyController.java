package com.simpleboard.reply.controller;

import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.model.ReplyRequest;
import com.simpleboard.reply.model.ReplyResponse;
import com.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

  private final ReplyService replyService;

  @PostMapping
  public ReplyResponse create(@RequestBody @Valid ReplyRequest replyRequest) {
    return replyService.save(replyRequest);
  }

}
