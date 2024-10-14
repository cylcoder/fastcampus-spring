package com.example.memorydb.user.db;

import com.example.memorydb.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByScoreGreaterThanEqual(int score);

    // query method
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);

    // JPQL
    @Query("select u from user u where u.score >= ?1 and u.score <= ?2")
    List<UserEntity> score1(int min, int max);

    // native query
    @Query(value = "select * from user u where u.score >= ?1 and u.score <= ?2", nativeQuery = true)
    List<UserEntity> score2(int min, int max);

    // named parameter
    @Query(value = "select * from user u where u.score >= :min and u.score <= :max", nativeQuery = true)
    List<UserEntity> score3(@Param("min") int min, @Param("max") int max);

}