package Main.server.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MultiResponse <T, P> {
    T data;
    P pageInfo;
}
