package Main.server.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Data
public class Response <T> {
 private String success;
 private String message;
 private T data;
}

