package Main.server.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class SseEmitters {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter){
        this.emitters.add(emitter);
        log.info("새로운 에미터 등록 : {}", emitter);
        log.info("에미터 사이즈 : {}", emitters.size());
        log.info("만료시간 (초): {}", emitter.getTimeout() / 1000L);
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter); //만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

}