package Main.server.board_esfp;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.comment.Comment;
import Main.server.comment.CommentRepository;
import Main.server.like.Like;
import Main.server.like.LikeDto;
import Main.server.like.LikeRepository;
import Main.server.user.UserRepository;
import Main.server.user.Users;
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
public class BoardEsfpService {
    private final BoardEsfpRepository boardEsfpRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public BoardEsfpService(BoardEsfpRepository boardEsfpRepository,
                            UserRepository userRepository,
                            LikeRepository likeRepository,
                            CommentRepository commentRepository) {
        this.boardEsfpRepository = boardEsfpRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public BoardEsfp createPost(BoardEsfp post, long userId) {
        Users findUser = getUserFromId(userId);

        if(findUser.getMbti().equalsIgnoreCase("esfp")) {
            post.setUsers(findUser);
            post.setViewCount(0L);
            post.setLikeCount(0L);
            post.setCommentCount(0L);
            post.setCategory("esfp");

            return boardEsfpRepository.save(post);
        }
        else {
            return null;
        }
    }

    @Transactional
    public BoardEsfp updatePost(BoardEsfp post) {
        BoardEsfp findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardEsfp readPost(long id) {
        BoardEsfp post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    @Transactional
    public Like insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardEsfp post = boardEsfpRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndBoardEsfp(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        if(users.getMbti().equalsIgnoreCase("esfp")) {
            Like boardEsfpLike = Like.builder()
                    .boardEsfp(post)
                    .users(users)
                    .category("esfp")
                    .build();

            return likeRepository.save(boardEsfpLike);
        }
        else {
            return null;
        }
    }

    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardEsfp post = boardEsfpRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        Like boardEsfpLike = likeRepository.findByUsersAndBoardEsfp(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(boardEsfpLike);
    }

    public BoardEsfp addLike(long id) {
        BoardEsfp post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardEsfpRepository.save(post);
    }

    public BoardEsfp deleteLike(long id) {
        BoardEsfp post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardEsfpRepository.save(post);
    }

    public void deletePost(long id) {
        BoardEsfp post = findPost(id);

        List<Like> boardEsfpLikes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < boardEsfpLikes.size(); i++) {
                Like boardEsfpLike = boardEsfpLikes.get(i);
                if(boardEsfpLike.getCategory().equals("esfp") && boardEsfpLike.getBoardEsfp().getId() == id) {
                    likeRepository.delete(boardEsfpLike);
                    deleteLike(id);
                }
            }
        }

        List<Comment> comments = commentRepository.findAll();

        while (post.getCommentCount() != 0) {
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if(comment.getCategory().equals("esfp") && comment.getBoardEsfp().getId() == id) {
                    commentRepository.delete(comment);
                    post.setCommentCount(post.getCommentCount() - 1);
                }
            }
        }

        boardEsfpRepository.deleteById(id);
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Comment comment, long userId, long postId) {
        Users findUser = getUserFromId(userId);
        BoardEsfp findPost = findPost(postId);

        if(findUser.getMbti().equalsIgnoreCase("esfp")) {
            findPost.setCommentCount(findPost.getCommentCount()+1);
            comment.setUser(findUser);
            comment.setBoardEsfp(findPost);
            comment.setCategory("esfp");
            return commentRepository.save(comment);
        }
        else {
            return null;
        }
    }

    // 댓글 수정
    @Transactional
    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getId());
        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));

        findComment.setModifiedAt(LocalDateTime.now());
        return commentRepository.save(findComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        BoardEsfp post = findComment.getBoardEsfp();
        commentRepository.deleteById(commentId);
        post.setCommentCount(post.getCommentCount()-1);
    }

    // 댓글 조회
    public Comment findVerifiedComment(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    // 전체 댓글 조회
    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public BoardEsfp findPost(long id) {
        return boardEsfpRepository.findById(id).orElse(null);
    }

    public Page<BoardEsfp> findAllPost(int page, int size) {
        return boardEsfpRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserFromId(long userId) {
        return userRepository.findById(userId).get();
    }
}
