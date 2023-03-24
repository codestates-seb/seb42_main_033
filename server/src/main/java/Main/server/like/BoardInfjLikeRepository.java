package Main.server.like;

import Main.server.board_infj.BoardInfj;
import Main.server.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardInfjLikeRepository  extends JpaRepository<BoardInfjLike, Long> {
    // INFJ 게시판에서 게시글 찾기
    Optional<BoardInfjLike> findByUsersAndPost(Users users, BoardInfj post);
}
