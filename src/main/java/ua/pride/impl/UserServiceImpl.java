package ua.pride.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pride.dto.UserDto;
import ua.pride.entity.User;
import ua.pride.repository.UserRepository;
import ua.pride.security.LoginResponse;
import ua.pride.service.UserService;

import static ua.pride.security.LoginResponse.authorized;
import static ua.pride.security.LoginResponse.unauthorized;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login() {
        try {
            User authorizedUser = getAuthorizedUser();
            if (authorizedUser == null) return unauthorized();
            return authorized(authorizedUser.getUsername(), authorizedUser.getRole().getName());
        } catch (Exception e) {
            log.error("Exception during request to '/login'.", e);
            return unauthorized();
        }
    }

    @Override
    public ResponseEntity<HttpStatus> register(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword())
        );
        userRepository.save(user);
        log.info("New user with username '{}' has been added", userDto.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            log.warn("Authentication is anonymous.");
            return null;
        } else {
            return (User) authentication.getPrincipal();
        }
    }
}
