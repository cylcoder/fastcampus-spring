package com.simpleboard.post.controller;

import com.simpleboard.crud.CRUDApiController;
import com.simpleboard.post.db.Post;
import com.simpleboard.post.model.PostRequest;
import com.simpleboard.post.model.PostResponse;
import com.simpleboard.post.model.PostViewRequest;
import com.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostApiController extends CRUDApiController<Post, PostRequest, PostResponse> {

  private final PostService postService;

  public PostApiController(PostService postService) {
    super(postService);
    this.postService = postService;
  }

  @PostMapping("/view")
  public PostResponse view(@Valid @RequestBody PostViewRequest postViewRequest) {
    return postService.view(postViewRequest);
  }

  @PostMapping("/delete")
  public void delete(@Valid @RequestBody PostViewRequest postViewRequest) {
    postService.delete(postViewRequest);
  }

}
