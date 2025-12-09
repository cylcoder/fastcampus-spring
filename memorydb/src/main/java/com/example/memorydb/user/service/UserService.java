package com.example.memorydb.user.service;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public List<User> findAllByScoreGreaterThan(Integer score) {
    return userRepository.findAllByScoreGreaterThan(score);
  }

  public List<User> findAllByScoreBetween(Integer min, Integer max) {
    return userRepository.findAllByScoreBetweenV2(min, max);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

}
