package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
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

    public PostEntity create(PostRequest postRequest) {
        PostEntity entity = PostEntity.builder()
                .boardId(1L)
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
