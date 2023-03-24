package Main.server.like;

import Main.server.board_infj.BoardInfj;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardIntegratedLikeRepository extends JpaRepository<BoardIntegratedLike, Long> {
    // 통합 게시판에서 게시글 찾기
    Optional<BoardIntegratedLike> findByUsersAndPost(Users users, BoardIntegrated post);
}
