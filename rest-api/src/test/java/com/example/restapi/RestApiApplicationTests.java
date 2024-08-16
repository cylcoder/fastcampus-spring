package com.example.restapi;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        UserRequest user = new UserRequest("John", "010-8080-2000", "john@mail.com", true);
        String value = objectMapper.writeValueAsString(user);
        System.out.println("value = " + value);
        UserRequest convertedValue = objectMapper.readValue(value, UserRequest.class);
        System.out.println("convertedValue = " + convertedValue);
    }

}
