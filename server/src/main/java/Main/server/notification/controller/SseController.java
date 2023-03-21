package Main.server.notification.controller;

import Main.server.notification.SseEmitters;
import Main.server.notification.service.SseService;
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
    private final SseService sseService;

    public SseController(SseEmitters sseEmitters, SseService sseService) {
        this.sseEmitters = sseEmitters;
        this.sseService = sseService;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(){
        SseEmitter emitter = sseService.connectSse();
        return ResponseEntity.ok(emitter);
    }
}