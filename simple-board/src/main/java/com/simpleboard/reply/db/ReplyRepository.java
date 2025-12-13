package com.simpleboard.reply.db;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  List<Reply> findAllByPostIdOrderById(Long postId);

}
