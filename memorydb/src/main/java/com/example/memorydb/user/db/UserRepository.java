package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

  public List<UserEntity> findByScoreGreaterThan(Integer score) {
    if (score != null) {
      return findAll().stream()
          .filter(u -> u.getScore() > score)
          .toList();
    }

    return findAll();
  }

}
