package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void login(HttpSession httpSession, LoginRequest loginRequest) {
    String username = loginRequest.username();
    String password = loginRequest.password();

    UserDto userDto = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (!userDto.password().equals(password)) {
      throw new IllegalArgumentException("Invalid username or password");
    }

    httpSession.setAttribute("USER", userDto);
  }

}
