package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.User;
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
  public User create(@RequestBody User user) {
    return userService.save(user);
  }

  @GetMapping(params = "score")
  public List<User> findAllByScoreGreaterThan(Integer score) {
    return userService.findAllByScoreGreaterThan(score);
  }

  @GetMapping(params = {"min", "max"})
  public List<User> findAllByScoreBetween(Integer min, Integer max) {
    return userService.findAllByScoreBetween(min, max);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    return userService.findById(id)
        .orElse(null);
  }

}
