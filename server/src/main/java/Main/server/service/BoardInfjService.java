package Main.server.board_infj.service;

import Main.server.board_infj.entity.BoardInfj;
import Main.server.board_infj.repository.BoardInfjRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardInfjService {
    private final BoardInfjRepository boardInfjRepository;
    private final UserRepository userRepository;

    public BoardInfjService(BoardInfjRepository boardInfjRepository, UserRepository userRepository) {
        this.boardInfjRepository = boardInfjRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BoardInfj createPost(BoardInfj post, long userId) {
        Users findUser = getUserId(userId);
        post.setUsers(findUser);
        post.setViewCount(0L);
        post.setLikeCount(0L);
        post.setCommentCount(0L);

        return boardInfjRepository.save(post);
    }

    @Transactional
    public BoardInfj updatePost(BoardInfj post) {
        BoardInfj findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardInfj readPost(long id) {
        BoardInfj post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
//        return boardInfjRepository.save(post);
    }

    public BoardInfj addLike(long id) {
        BoardInfj post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardInfjRepository.save(post);
    }

    public void deletePost(long id) {
        boardInfjRepository.deleteById(id);
    }

    public BoardInfj findPost(long id) {
        return boardInfjRepository.findById(id).orElse(null);
    }

    public Page<BoardInfj> findAllPost(int page, int size) {
        return boardInfjRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserId(long userId) {
        return userRepository.findById(userId).get();
    }
}
