package com.example.memorydb.config;

import com.example.memorydb.user.db.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {


    // Exception처럼 수정할 수 없는 클래스를 Bean으로 등록하고 싶을 때 사용
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

}
