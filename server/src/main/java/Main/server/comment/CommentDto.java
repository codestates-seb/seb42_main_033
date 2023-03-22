package Main.server.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CommentDto {
    @Getter
    @Setter
    public static class Post {
        @Positive
        private Long userId;

        private Long postId;

        @NotBlank
        String content;

        @NotBlank
        String username;
    }

    @Getter
    @Setter
    public static class Patch {
        @Positive
        private Long userId;

        @Positive
        private Long id;

        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private Long userId;
        private Long boardId;
        private String content;
        private String username;
        private String category;
    }
}
