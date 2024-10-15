package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    public PostDto create(PostRequest postRequest) {
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

        return postConverter.toDto(postRepository.save(entity));
    }

    public PostDto view(PostViewRequest postViewRequest) {
        Long postId = postViewRequest.getPostId();
        PostDto postDto = postConverter
                .toDto(postRepository.findFirstByIdAndStatusOrderByIdDesc(postId, "REGISTERED")
                .orElseThrow(() -> new NoSuchElementException("No such post(post id: " + postId + ")")));

        if (postDto.getPassword().equals(postViewRequest.getPassword())) {
            return postDto;
        } else {
            throw new RuntimeException(String.format("Wrong password(Expected: %s, Actual: %s)",
                    postDto.getPassword(), postViewRequest.getPassword()));
        }
    }

    public Api<List<PostEntity>> all(Pageable pageable) {
        Page<PostEntity> page = postRepository.findAll(pageable);

        Pagination pagination = Pagination.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .currentElements(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();

        return Api.<List<PostEntity>>builder()
                .body(page.stream().toList())
                .pagination(pagination)
                .build();
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
