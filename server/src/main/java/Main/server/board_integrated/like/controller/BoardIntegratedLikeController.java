package Main.server.board_integrated.like.controller;

import Main.server.board_integrated.like.dto.BoardIntegratedLikeDto;
import Main.server.board_integrated.like.service.BoardIntegratedLikeService;
import Main.server.board_integrated.mapper.BoardIntegratedMapper;
import Main.server.board_integrated.service.BoardIntegratedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/integrated/like")
public class BoardIntegratedLikeController {

    private final BoardIntegratedLikeService likeService;
    private final BoardIntegratedService boardService;
    private final BoardIntegratedMapper mapper;

    public BoardIntegratedLikeController(BoardIntegratedLikeService likeService, BoardIntegratedService boardService, BoardIntegratedMapper mapper) {
        this.likeService = likeService;
        this.boardService = boardService;
        this.mapper = mapper;
    }

    @PostMapping("/{post-id}")
    public ResponseEntity insert(@PathVariable("post-id") long postId,
                       @RequestBody BoardIntegratedLikeDto likeDto) throws Exception {
        if(postId == likeDto.getPostId()) {
            likeService.insert(likeDto);
            boardService.addLike(postId);

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
