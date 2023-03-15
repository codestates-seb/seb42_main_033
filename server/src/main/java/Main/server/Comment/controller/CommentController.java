package Main.server.Comment.controller;

import Main.server.Comment.dto.CommentPatchDto;
import Main.server.Comment.dto.CommentResponseDto;
import Main.server.Comment.entity.Comment;
import Main.server.Comment.dto.CommentPostDto;
import Main.server.Comment.mapper.CommentMapper;
import Main.server.Comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.Console;

@CrossOrigin
@RestController
@RequestMapping("/board/integrated")
public class CommentController {
    private final Main.server.Comment.service.CommentService commentService;
    private final Main.server.Comment.mapper.CommentMapper commentMapper;

    public CommentController(Main.server.Comment.service.CommentService commentService, Main.server.Comment.mapper.CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/{post-id}")
    public ResponseEntity postComment(@PathVariable("post-id") Long boardId,
                                      @RequestBody Main.server.Comment.dto.CommentPostDto postDto) {

        Comment createComment = commentService.createComment(commentMapper.commentPostDtoToComment(postDto), postDto.getUserId(), boardId);
        CommentResponseDto result = commentMapper.commentToCommentResponseDto(createComment);
        result.setUserId(createComment.getUser().getUserId());
        result.setUsername(createComment.getUser().getNickName());
        result.setBoardId(createComment.getBoard().getId());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity getComment(@PathVariable("post-id") long boardId,
                                     @PathVariable("comment-id") long commentId) {

        Comment comment = commentService.findVerifiedComment(commentId);
        CommentResponseDto result = commentMapper.commentToCommentResponseDto(comment);
        result.setUserId(comment.getUser().getUserId());
        result.setUsername(comment.getUser().getNickName());
        result.setBoardId(comment.getBoard().getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 전체 질문 가져오기
    @GetMapping("/{post-id}/comment")
    public ResponseEntity getComments() {
        List<Comment> comments = commentService.findComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PatchMapping("/{post-id}/comment/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("post-id") Long boardId,
                                       @PathVariable("comment-id") Long commentId,
                                       @RequestBody CommentPatchDto patchDto) {
        Comment comment = commentMapper.commentPatchDtoToComment(patchDto);
        comment.setCommentId(commentId);

        Comment patchComment = commentService.updateComment(comment);
        CommentResponseDto result = commentMapper.commentToCommentResponseDto(patchComment);
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
