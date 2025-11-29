package com.example.restapi;

import com.example.restapi.model.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
class RestApiApplicationTests {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void contextLoads() {
    UserRequest userRequest = new UserRequest("john", 100, "john@example.com", true);
    String json = objectMapper.writeValueAsString(userRequest);
    System.out.println("json = " + json);

    UserRequest dto = objectMapper.readValue(json, UserRequest.class);
    System.out.println("dto = " + dto);
  }

}
