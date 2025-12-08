package com.example.memorydb.user.service;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  public List<UserEntity> findByScoreGreaterThan(Integer score) {
    return userRepository.findByScoreGreaterThan(score);
  }

  public void deleteById(Long id) {
    userRepository.delete(id);
  }

  public Optional<UserEntity> findById(Long id) {
    return userRepository.findById(id);
  }

}
