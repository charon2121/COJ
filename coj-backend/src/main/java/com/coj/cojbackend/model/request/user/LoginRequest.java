package com.coj.cojbackend.model.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 6, max = 20, message = "username length must be between 6 and 20")
    private String username;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, max = 20, message = "password length must be between 6 and 20")
    private String password;
}
