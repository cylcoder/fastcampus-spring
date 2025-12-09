package com.example.memorydb.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.memorydb.user.model.User;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  void findAllByScoreGreaterThan() {
    Random random = new Random();
    int targetScore = 70;
    int count = 0;

    for (int i = 0; i < 10; i++) {
      int sign = random.nextBoolean() ? 1 : -1;
      int gap = random.nextInt(30);
      int score = targetScore + sign * gap;
      if (score >= targetScore) {
        count++;
      }
      User user = new User("user" + i, score);
      userService.save(user);
    }

    List<User> result = userService.findAllByScoreGreaterThan(targetScore);
    assertThat(result).hasSize(count);
  }

}