package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

  private final UserService userService;

  @PutMapping
  public UserEntity create(@RequestBody UserEntity userEntity) {
    return userService.save(userEntity);
  }

  @GetMapping
  public List<UserEntity> findAll(@RequestParam(required = false) Integer score) {
    return userService.findByScoreGreaterThan(score);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }

  @GetMapping("/{id}")
  public UserEntity findById(@PathVariable Long id) {
    return userService.findById(id)
        .orElse(null);
  }

}
