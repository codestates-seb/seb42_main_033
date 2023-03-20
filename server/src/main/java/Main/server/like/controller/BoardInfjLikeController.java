package Main.server.board_infj.like.controller;

import Main.server.board_infj.like.dto.BoardInfjLikeDto;
import Main.server.board_infj.like.service.BoardInfjLikeService;
import Main.server.mapper.BoardInfjMapper;
import Main.server.service.BoardInfjService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/infj/like")
public class BoardInfjLikeController {

    private final BoardInfjLikeService likeService;
    private final BoardInfjService boardService;
    private final BoardInfjMapper mapper;

    public BoardInfjLikeController(BoardInfjLikeService likeService, BoardInfjService boardService, BoardInfjMapper mapper) {
        this.likeService = likeService;
        this.boardService = boardService;
        this.mapper = mapper;
    }

    @PostMapping("/{post-id}")
    public ResponseEntity insert(@PathVariable("post-id") long postId,
                                 @RequestBody BoardInfjLikeDto likeDto) throws Exception {
        if(postId == likeDto.getUserId()) {
            likeService.insert(likeDto);
            boardService.addLike(postId);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
