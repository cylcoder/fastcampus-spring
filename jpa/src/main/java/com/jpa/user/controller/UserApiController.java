package com.jpa.user.controller;

import com.jpa.user.db.UserEntity;
import com.jpa.user.db.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

  private final UserRepository userRepository;

  @GetMapping
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @PostMapping
  public void save(@RequestBody UserEntity userEntity) {
    userRepository.save(userEntity);
  }

}
