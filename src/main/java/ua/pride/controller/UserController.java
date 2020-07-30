package ua.pride.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pride.dto.UserDto;
import ua.pride.security.LoginResponse;
import ua.pride.service.UserService;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public LoginResponse login() {
        return userService.login();
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }
}
