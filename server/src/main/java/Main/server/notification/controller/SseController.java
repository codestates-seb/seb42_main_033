package Main.server.notification.controller;

import Main.server.notification.SseEmitters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RestController
public class SseController {
    private final SseEmitters sseEmitters;

    public SseController(SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(){
        SseEmitter emitter = new SseEmitter(60 * 1000L);
        sseEmitters.add(emitter);

        try{
            emitter.send(SseEmitter.event()
                    .name("connect") // 해당 이벤트 이름 지정
                    .data("연결완료!")); // 503 에러 방지
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(emitter);
    }
}