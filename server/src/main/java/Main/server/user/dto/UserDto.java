package Main.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        String nickName;
        @Email
        @NotBlank
        String email;
        @NotBlank
        String password1;
        @NotBlank
        String password2;
        @NotBlank
        String mbti;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        long userId;
        String nickName;
        String email;
        String password1;
        String password2;
        String mbti;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        long userId;
        String nickName;
        String email;
        String password1;
        String password2;
        String mbti;
        String url;
    }
}
