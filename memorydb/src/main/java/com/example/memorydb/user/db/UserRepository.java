package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    public List<UserEntity> findAllScoreGreaterThan(int score) {
        return findAll().stream().filter(u -> u.getScore() >= score).toList();
    }

}
