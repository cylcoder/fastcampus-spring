package com.simpleboard.post.controller;

import com.simpleboard.common.Api;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostResponse;
import com.simpleboard.post.model.PostViewRequest;
import com.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

  private final PostService postService;

  @PostMapping
  public PostResponse create(@Valid @RequestBody PostRequest postRequest) {
    return postService.create(postRequest);
  }

  @PostMapping("/view")
  public PostResponse view(@Valid @RequestBody PostViewRequest postViewRequest) {
    return postService.view(postViewRequest);
  }

  @GetMapping
  public Api<List<PostResponse>> findAll(@PageableDefault Pageable pageable) {
    return postService.findAll(pageable);
  }

  @PostMapping("/delete")
  public void delete(@Valid @RequestBody PostViewRequest postViewRequest) {
    postService.delete(postViewRequest);
  }

}
