package Main.server.notification.service;

import Main.server.notification.SseEmitters;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class SseService {
    private final SseEmitters sseEmitters;
    private static final Long DEFAULT_TIMEOUT = 60 * 1000L;

    public SseService(SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
    }

    public SseEmitter connectSse(){
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        sseEmitters.add(emitter);

        try{
            emitter.send(SseEmitter.event()
                    .name("Connect")
                    .data("Connect Success!")); // 503 에러 방지
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return emitter;
    }
}
