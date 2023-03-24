package Main.server.board_infj;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.comment.Comment;
import Main.server.comment.CommentRepository;
import Main.server.like.BoardInfjLike;
import Main.server.like.BoardInfjLikeRepository;
import Main.server.like.LikeDto;
import Main.server.like.BoardIntegratedLikeRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardInfjService {
    private final BoardInfjRepository boardInfjRepository;
    private final UserRepository userRepository;
    private final BoardInfjLikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public BoardInfjService(BoardInfjRepository boardInfjRepository,
                            UserRepository userRepository,
                            BoardInfjLikeRepository likeRepository,
                            CommentRepository commentRepository) {
        this.boardInfjRepository = boardInfjRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public BoardInfj createPost(BoardInfj post, long userId) {
        Users findUser = getUserId(userId);
        post.setUsers(findUser);
        post.setViewCount(0L);
        post.setLikeCount(0L);
        post.setCommentCount(0L);
        post.setCategory("infj");

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
    }

    @Transactional
    public BoardInfjLike insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardInfj post = boardInfjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndPost(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        BoardInfjLike boardInfjLike = BoardInfjLike.builder()
                .post(post)
                .users(users)
                .category("infj")
                .build();

        return likeRepository.save(boardInfjLike);
    }

    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardInfj post = boardInfjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        BoardInfjLike boardInfjLike = likeRepository.findByUsersAndPost(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(boardInfjLike);
    }

    public BoardInfj addLike(long id) {
        BoardInfj post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardInfjRepository.save(post);
    }

    public BoardInfj deleteLike(long id) {
        BoardInfj post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardInfjRepository.save(post);
    }

    public void deletePost(long id) {
        BoardInfj post = findPost(id);

        List<BoardInfjLike> boardInfjLikes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < boardInfjLikes.size(); i++) {
                BoardInfjLike boardInfjLike = boardInfjLikes.get(i);
                if(boardInfjLike.getCategory().equals("infj") && boardInfjLike.getPost().getId() == id) {
                    likeRepository.delete(boardInfjLike);
                    deleteLike(id);
                }
            }
        }

        List<Comment> comments = commentRepository.findAll();

        while (post.getCommentCount() != 0) {
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if(comment.getCategory().equals("infj") && comment.getPost().getId() == id) {
                    commentRepository.delete(comment);
                    post.setCommentCount(post.getCommentCount() - 1);
                }
            }
        }

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
