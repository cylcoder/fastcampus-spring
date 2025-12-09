package com.example.memorydb.user.db;

import com.example.memorydb.user.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByScoreGreaterThan(Integer score);

  List<User> findAllByScoreBetweenV1(Integer min, Integer max);

  @Query("SELECT u FROM User u WHERE u.score BETWEEN :min AND :max")
  List<User> findAllByScoreBetweenV2(Integer min, Integer max);

  @Query("SELECT * FROM users WHERE score BETWEEN ?1 AND ?2")
  List<User> findAllByScoreBetweenV3(Integer min, Integer max);

}
