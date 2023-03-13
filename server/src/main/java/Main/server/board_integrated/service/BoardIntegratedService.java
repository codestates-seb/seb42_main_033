package Main.server.board_integrated.service;

import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.like.entity.BoardIntegratedLike;
import Main.server.board_integrated.like.repository.BoardIntegratedLikeRepository;
import Main.server.board_integrated.repository.BoardIntegratedRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardIntegratedService {
    private final BoardIntegratedRepository boardIntegratedRepository;
    private final UserRepository userRepository;

    public BoardIntegratedService(BoardIntegratedRepository boardIntegratedRepository, UserRepository userRepository) {
        this.boardIntegratedRepository = boardIntegratedRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public BoardIntegrated createPost(BoardIntegrated post, long userId) {
        Users findUser = getUserId(userId);
        post.setUsers(findUser);
        post.setViewCount(0L);
        post.setLikeCount(0L);
        post.setCommentCount(0L);

        return boardIntegratedRepository.save(post);
    }

    @Transactional
    public BoardIntegrated updatePost(BoardIntegrated post) {
        BoardIntegrated findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());
//        return boardIntegratedRepository.save(findPost);

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardIntegrated readPost(long id) {
        BoardIntegrated post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return boardIntegratedRepository.save(post);
    }

    public BoardIntegrated addLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardIntegratedRepository.save(post);
    }

    public BoardIntegrated removeLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardIntegratedRepository.save(post);
    }

    public void deletePost(long id) {
        boardIntegratedRepository.deleteById(id);
    }

    public BoardIntegrated findPost(long id) {
        return boardIntegratedRepository.findById(id).orElse(null);
    }

    public Page<BoardIntegrated> findAllPost(int page, int size) {
        return boardIntegratedRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserId(long userId) {
        return userRepository.findById(userId).get();
    }
}
