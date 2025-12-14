package com.example.cookie.db;

import com.example.cookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

  private List<UserDto> users = new ArrayList<>();

  @PostConstruct
  private void postConstruct() {
    UserDto john = new UserDto("john", "john");
    UserDto jane = new UserDto("jane", "jane");
    UserDto richard = new UserDto("richard", "richard");
    users.add(john);
    users.add(jane);
    users.add(richard);
  }

  public Optional<UserDto> findByUsername(String username) {
    return users.stream()
        .filter(u -> u.username().equals(username))
        .findAny();
  }

}
