package Main.server.board_infj.like.service;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.board_infj.BoardInfj;
import Main.server.board_infj.like.dto.BoardInfjLikeDto;
import Main.server.board_infj.like.entity.BoardInfjLike;
import Main.server.board_infj.like.repository.BoardInfjLikeRepository;
import Main.server.board_infj.BoardInfjRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardInfjLikeService {

    private final BoardInfjLikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardInfjRepository boardInfjRepository;

    public BoardInfjLikeService(BoardInfjLikeRepository likeRepository, UserRepository userRepository, BoardInfjRepository boardInfjRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardInfjRepository = boardInfjRepository;
    }

    @Transactional
    public void insert(BoardInfjLikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardInfj post = boardInfjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndPost(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        BoardInfjLike like = BoardInfjLike.builder()
                .post(post)
                .users(users)
                .build();

        likeRepository.save(like);
    }


}
