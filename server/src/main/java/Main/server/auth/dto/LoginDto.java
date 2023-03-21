package Main.server.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String password1;
}