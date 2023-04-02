package Main.server.board_estj;

import Main.server.comment.CommentDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardEstjDto {
    @Getter
    @Setter
    public static class Post {
        long userId;
        String title;
        String content;
        String tag;
    }

    @Getter
    @Setter
    public static class Patch {
        String title;
        String content;
        String tag;
    }

    @Getter
    @Setter
    public static class Response {
        long id;
        long userId;
        String nickName;
        String title;
        String content;
        String tag;
        String category;
        long commentCount;
        long viewCount;
        long likeCount;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime createdAt;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime modifiedAt;

        private List<CommentDto.Response> comments;
    }
}
