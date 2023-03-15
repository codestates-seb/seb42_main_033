package Main.server.Comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostDto {
    @Positive
    private Long userId;

    @NotBlank
    String content;

    @NotBlank
    String username;
}
