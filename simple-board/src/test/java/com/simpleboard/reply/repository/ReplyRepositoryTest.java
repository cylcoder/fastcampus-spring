package com.simpleboard.reply.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.simpleboard.reply.db.Reply;
import com.simpleboard.reply.db.ReplyRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReplyRepositoryTest {

  @Autowired
  private ReplyRepository replyRepository;

  @Test
  void findAllByPostId() {
    List<Reply> replies = replyRepository.findAllByPostIdOrderById(1L);
    assertThat(replies).hasSize(2);
  }

}