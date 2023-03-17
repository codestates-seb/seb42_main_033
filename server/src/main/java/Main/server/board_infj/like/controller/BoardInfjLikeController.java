package Main.server.board_infj.like.controller;

import Main.server.board_infj.like.dto.BoardInfjLikeDto;
import Main.server.board_infj.like.service.BoardInfjLikeService;
import Main.server.board_infj.mapper.BoardInfjMapper;
import Main.server.board_infj.service.BoardInfjService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
