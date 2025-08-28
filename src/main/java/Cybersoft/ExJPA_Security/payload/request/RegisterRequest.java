package Cybersoft.ExJPA_Security.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;
}
