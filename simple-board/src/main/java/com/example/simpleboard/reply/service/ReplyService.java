package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyDto;
import com.example.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final ReplyConverter replyConverter;

    public ReplyDto create(ReplyRequest replyRequest) {
        PostEntity post = postRepository.findById(replyRequest.getPostId()).orElseThrow(NoSuchElementException::new);

        ReplyEntity entity = ReplyEntity.builder()
                .post(post)
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("REGISTERED")
                .repliedAt(LocalDateTime.now())
                .build();

        return replyConverter.toDto(replyRepository.save(entity));
    }

    public List<ReplyDto> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED")
                .stream().map(replyConverter::toDto).toList();
    }

}
