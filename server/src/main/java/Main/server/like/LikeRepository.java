package Main.server.like;

import Main.server.board_enfj.BoardEnfj;
import Main.server.board_enfp.BoardEnfp;
import Main.server.board_entj.BoardEntj;
import Main.server.board_entp.BoardEntp;
import Main.server.board_esfj.BoardEsfj;
import Main.server.board_esfp.BoardEsfp;
import Main.server.board_estj.BoardEstj;
import Main.server.board_estp.BoardEstp;
import Main.server.board_infj.BoardInfj;
import Main.server.board_infp.BoardInfp;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.board_intj.BoardIntj;
import Main.server.board_intp.BoardIntp;
import Main.server.board_isfj.BoardIsfj;
import Main.server.board_isfp.BoardIsfp;
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

    // ISFP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIsfp(Users users, BoardIsfp post);

    // INTJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIntj(Users users, BoardIntj post);

    // INTP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardIntp(Users users, BoardIntp post);

    // ESTJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEstj(Users users, BoardEstj post);

    // ESTP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEstp(Users users, BoardEstp post);

    // ESFJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEsfj(Users users, BoardEsfj post);

    // ESFP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEsfp(Users users, BoardEsfp post);

    // ENTJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEntj(Users users, BoardEntj post);

    // ENTP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEntp(Users users, BoardEntp post);

    // ENFJ 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEnfj(Users users, BoardEnfj post);

    // ENFP 게시판에서 게시글 찾기
    Optional<Like> findByUsersAndBoardEnfp(Users users, BoardEnfp post);

}

