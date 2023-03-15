package Main.server.board_integrated.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardIntegratedLikeDto {

    private Long userId;

    private Long postId;
}
