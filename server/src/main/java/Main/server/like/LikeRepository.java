package Main.server.like;

import Main.server.board_infj.BoardInfj;
import Main.server.board_infp.BoardInfp;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.board_isfj.BoardIsfj;
import Main.server.board_istj.BoardIstj;
import Main.server.board_istp.BoardIstp;
import Main.server.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 통합 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIntegrated(Users users, BoardIntegrated post);

    // INFJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardInfj(Users users, BoardInfj post);

    // INFP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardInfp(Users users, BoardInfp post);

    // ISTJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIstj(Users users, BoardIstj post);

    // ISTP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIstp(Users users, BoardIstp post);

    // ISFJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIsfj(Users users, BoardIsfj post);

}

