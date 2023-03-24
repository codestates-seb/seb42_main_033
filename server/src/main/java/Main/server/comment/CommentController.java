package Main.server.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/integrated")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/{post-id}")
    public ResponseEntity postComment(@PathVariable("post-id") Long boardId,
                                      @RequestBody CommentDto.Post postDto) {

        Comment createComment = commentService.createComment(commentMapper.commentPostDtoToComment(postDto), postDto.getUserId(), boardId);
        CommentDto.Response result = commentMapper.commentToCommentResponseDto(createComment);
        result.setUserId(createComment.getUser().getUserId());
        result.setUsername(createComment.getUser().getNickName());
        result.setBoardId(createComment.getBoard().getId());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity getComment(@PathVariable("post-id") long boardId,
                                     @PathVariable("comment-id") long commentId) {

        Comment comment = commentService.findVerifiedComment(commentId);
        CommentDto.Response result = commentMapper.commentToCommentResponseDto(comment);
        result.setUserId(comment.getUser().getUserId());
        result.setUsername(comment.getUser().getNickName());
        result.setBoardId(comment.getBoard().getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{post-id}/comment")
    public ResponseEntity getComments() {
        List<Comment> comments = commentService.findComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PatchMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("post-id") Long boardId,
                                       @PathVariable("comment-id") Long commentId,
                                       @RequestBody CommentDto.Patch patchDto) {
        Comment comment = commentMapper.commentPatchDtoToComment(patchDto);
        comment.setId(commentId);

        Comment patchComment = commentService.updateComment(comment);
        CommentDto.Response result = commentMapper.commentToCommentResponseDto(patchComment);
        result.setUserId(patchComment.getUser().getUserId());
        result.setUsername(patchComment.getUser().getNickName());
        result.setBoardId(boardId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("post-id") long boardId,
                                        @PathVariable("comment-id") long commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
