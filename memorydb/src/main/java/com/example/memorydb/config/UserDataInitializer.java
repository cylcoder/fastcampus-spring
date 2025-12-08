package com.example.memorydb.config;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.UserEntity;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    for (int i = 0; i < 5; i++) {
      String name = UUID.randomUUID().toString().substring(0, 5);
      int score = random.nextInt(100);
      UserEntity user = new UserEntity(name, score);
      userRepository.save(user);
    }
  }

}
