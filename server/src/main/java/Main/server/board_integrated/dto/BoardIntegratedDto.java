package Main.server.board_integrated.dto;

import Main.server.comment.dto.CommentResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardIntegratedDto {
    @Getter
    @Setter
    public static class Post {
        long userId;
        String nickName;
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
        long commentCount;
        long viewCount;
        long likeCount;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime createdAt;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime modifiedAt;

        private List<CommentResponseDto> comments;
    }
}

