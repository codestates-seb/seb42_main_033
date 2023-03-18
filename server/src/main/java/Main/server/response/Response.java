package Main.server.response;

import Main.server.message.dto.MessageDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Data
public class Response <T> {
 private String success;
 private String message;
 private T data;
}

