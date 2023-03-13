package Main.server.board_infj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/infj")
public class BoardMBTIController {

    @PostMapping
    public ResponseEntity postPost() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
