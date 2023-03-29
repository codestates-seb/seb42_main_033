package Main.server.board_integrated;

import Main.server.comment.Comment;
import Main.server.comment.CommentDto;
import Main.server.comment.CommentMapper;
import Main.server.like.LikeDto;
import Main.server.like.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/board/integrated")
public class BoardIntegratedController {
    private final BoardIntegratedService service;
    private final CustomBoardIntegratedMapper mapper;
    private final LikeRepository likeRepository;
    private final CommentMapper commentMapper;

    public BoardIntegratedController(BoardIntegratedService service,
                                     CustomBoardIntegratedMapper mapper,
                                     LikeRepository likeRepository,
                                     CommentMapper commentMapper) {
        this.service = service;
        this.mapper = mapper;
        this.likeRepository = likeRepository;
        this.commentMapper = commentMapper;
    }

    //게시글 등록
    @PostMapping
    public ResponseEntity postPost(@RequestBody BoardIntegratedDto.Post postDto) {

        BoardIntegrated createdPost = service.createPost(mapper.postDtoToBoardIntegrated(postDto), postDto.getUserId());
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(createdPost);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //전체 게시글 조회
    @GetMapping
    public ResponseEntity getPosts(@Positive @RequestParam(defaultValue = "1") int page,
                                   @Positive @RequestParam(defaultValue = "100") int size) {

        Page<BoardIntegrated> pagePosts = service.findAllPost(page - 1, size);
        List<BoardIntegrated> posts = pagePosts.getContent();
        List<BoardIntegratedDto.Response> result = mapper.boardIntegratedToResponseDtos(posts);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //게시글 조회
    @GetMapping("/{post-id}")
    public ResponseEntity getPost(@PathVariable("post-id") long postId) {
        BoardIntegrated post = service.readPost(postId);
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(post);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //게시글 수정
    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long postId,
                                    @RequestBody BoardIntegratedDto.Patch patchDto) {
        BoardIntegrated post = mapper.patchDtoToBoardIntegrated(patchDto);
        post.setId(postId);

        BoardIntegrated modifiedPost = service.updatePost(post);
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(modifiedPost);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {

        service.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //추천 등록
    @PostMapping("/{post-id}/like")
    public ResponseEntity insert(@PathVariable("post-id") long postId,
                                 @RequestBody LikeDto likeDto) throws Exception {

        if(postId == likeDto.getPostId()) {
            service.insert(likeDto);
            service.addLike(postId);

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //추천 취소
    @DeleteMapping("/{post-id}/like")
    public ResponseEntity delete(@PathVariable("post-id") long postId,
                                 @RequestBody @Valid LikeDto likeDto) {

        if(postId == likeDto.getPostId()) {

            service.delete(likeDto);
            service.deleteLike(postId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //댓글 등록
    @PostMapping("/{post-id}")
    public ResponseEntity postComment(@PathVariable("post-id") Long postId,
                                      @RequestBody CommentDto.Post postDto) {

        if(postId == postDto.getPostId()) {
            Comment createComment = service.createComment(commentMapper.commentPostDtoToComment(postDto), postDto.getUserId(), postId);
            CommentDto.Response result = commentMapper.commentToCommentResponseDto(createComment);
            result.setUserId(createComment.getUser().getUserId());
            result.setUsername(createComment.getUser().getNickName());
            result.setBoardId(createComment.getBoardIntegrated().getId());

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //전체 댓글 조회
    @GetMapping("/{post-id}/comment")
    public ResponseEntity getComments() {
        List<Comment> comments = service.findComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    //댓글 조회
    @GetMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity getComment(@PathVariable("post-id") long postId,
                                     @PathVariable("comment-id") long commentId) {

        Comment comment = service.findVerifiedComment(commentId);
        CommentDto.Response result = commentMapper.commentToCommentResponseDto(comment);
        BoardIntegrated post = service.readPost(postId);

        if(post.getId() == postId && comment.getId() == commentId) {
            result.setUserId(comment.getUser().getUserId());
            result.setUsername(comment.getUser().getNickName());
            result.setBoardId(comment.getBoardIntegrated().getId());

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //댓글 수정
    @PatchMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("post-id") Long postId,
                                       @PathVariable("comment-id") Long commentId,
                                       @RequestBody CommentDto.Patch patchDto) {
        Comment comment = commentMapper.commentPatchDtoToComment(patchDto);
        comment.setId(commentId);

        Comment patchComment = service.updateComment(comment);
        CommentDto.Response result = commentMapper.commentToCommentResponseDto(patchComment);
        result.setUserId(patchComment.getUser().getUserId());
        result.setUsername(patchComment.getUser().getNickName());
        result.setBoardId(postId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("post-id") long postId,
                                        @PathVariable("comment-id") long commentId) {

        service.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //test
    @GetMapping("/")
    public String forTest() {
        return "This is Test Page....";
    }
}
