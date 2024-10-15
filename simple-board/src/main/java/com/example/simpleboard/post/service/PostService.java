package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.service.BoardService;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;

    public PostEntity create(PostRequest postRequest) {
        BoardEntity board = boardRepository.findById(postRequest.getBoardId()).orElseThrow(NoSuchElementException::new);

        PostEntity entity = PostEntity.builder()
                .board(board)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    public PostEntity view(PostViewRequest postViewRequest) {
        Long postId = postViewRequest.getPostId();
        PostEntity postEntity = postRepository.findFirstByIdAndStatusOrderByIdDesc(postId, "REGISTERED")
                .orElseThrow(() -> new NoSuchElementException("No such post(post id: " + postId + ")"));

        if (postEntity.getPassword().equals(postViewRequest.getPassword())) {
            List<ReplyEntity> replyList = replyService.findAllByPostId(postId);
            postEntity.setReplyList(replyList);
            return postEntity;
        } else {
            throw new RuntimeException(String.format("Wrong password(Expected: %s, Actual: %s)",
                    postEntity.getPassword(), postViewRequest.getPassword()));
        }
    }

    public List<PostEntity> all() {
        return postRepository.findAllByStatusOrderByIdDesc("REGISTERED");
    }

    @PostMapping("/delete")
    public void delete(PostViewRequest postViewRequest) {
        Long postId = postViewRequest.getPostId();
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("No such post(post id: " + postId + ")"));

        if (postEntity.getPassword().equals(postViewRequest.getPassword())) {
            postEntity.setStatus("UNREGISTERED");
        } else {
            throw new RuntimeException(String.format("Wrong password(Expected: %s, Actual: %s)",
                    postEntity.getPassword(), postViewRequest.getPassword()));
        }
    }

}
