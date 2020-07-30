package ua.pride.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean loginResult;
    private String username;
    private String role;

    public static LoginResponse authorized(String name, String role) {
        return new LoginResponse(true, name, role);
    }

    public static LoginResponse unauthorized() {
        return new LoginResponse();
    }
}
