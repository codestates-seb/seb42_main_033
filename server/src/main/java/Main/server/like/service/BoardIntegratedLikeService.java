package Main.server.board_integrated.like.service;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.like.dto.BoardIntegratedLikeDto;
import Main.server.board_integrated.like.entity.BoardIntegratedLike;
import Main.server.board_integrated.like.repository.BoardIntegratedLikeRepository;
import Main.server.board_integrated.repository.BoardIntegratedRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardIntegratedLikeService {

    private final BoardIntegratedLikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardIntegratedRepository boardIntegratedRepository;

    public BoardIntegratedLikeService(BoardIntegratedLikeRepository likeRepository, UserRepository userRepository, BoardIntegratedRepository boardIntegratedRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardIntegratedRepository = boardIntegratedRepository;
    }

    @Transactional
    public void insert(BoardIntegratedLikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndPost(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        BoardIntegratedLike like = BoardIntegratedLike.builder()
                .post(post)
                .users(users)
                .build();

        likeRepository.save(like);
    }

    @Transactional
    public void delete(BoardIntegratedLikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        BoardIntegratedLike like = likeRepository.findByUsersAndPost(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(like);
    }
}
