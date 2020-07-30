package ua.pride.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pride.dto.UserDto;
import ua.pride.entity.User;
import ua.pride.security.LoginResponse;

public interface UserService {

    LoginResponse login();

    ResponseEntity<HttpStatus> register(UserDto userDto);

    User getAuthorizedUser();
}
